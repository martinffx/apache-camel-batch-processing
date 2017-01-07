package me.martinrichards.apache.camel.address.routes;

import com.google.inject.Inject;

import org.apache.camel.model.dataformat.CsvDataFormat;
import org.apache.camel.model.language.MethodCallExpression;

import me.martinrichards.apache.camel.address.services.IAddressService;

/**
 * Created by martinrichards on 2016/10/18.
 */
public class AddressRoute extends org.apache.camel.builder.RouteBuilder {
    public static final String ROUTE_NAME = "Default";

    private final IAddressService addressService;

    @Inject
    public AddressRoute(IAddressService addressService) {
        this.addressService = addressService;
    }

    @Override
    public void configure() throws Exception {
        from("direct:input").routeId(ROUTE_NAME).unmarshal(new CsvDataFormat())
                .split(body()).streaming().parallelProcessing()
                .bean(addressService, "filterZone")
                .filter(header(addressService.getIsValidHeader()).isEqualTo(true))
                .throttle(addressService.getTPS()).bean(addressService, "getAddress")
                .aggregate(constant(true)).aggregationStrategy(new BundlingStrategy())
                .completionPredicate(new MethodCallExpression(addressService, "isComplete"))
                .marshal().csvLazyLoad()
                .to("direct:out").log("done.").end();
    }
}
