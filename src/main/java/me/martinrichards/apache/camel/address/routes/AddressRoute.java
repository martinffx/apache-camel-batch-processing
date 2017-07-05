package me.martinrichards.apache.camel.address.routes;

import com.google.inject.Inject;

import org.apache.camel.model.dataformat.CsvDataFormat;
import org.apache.camel.model.language.MethodCallExpression;

import me.martinrichards.apache.camel.address.processor.taxi.data.TaxiDataProcessor;
import me.martinrichards.apache.camel.address.services.IAddressService;

/**
 * @author martinrichards
 */
public class AddressRoute extends org.apache.camel.builder.RouteBuilder {
    public static final String ROUTE_NAME = "Default";
    public static final String FROM = "direct:input";
    public static final String TO = "direct:out";

    private final IAddressService addressService;
    private final TaxiDataProcessor taxiDataProcessor;

    @Inject
    public AddressRoute(final IAddressService addressService,
                        final TaxiDataProcessor taxiDataProcessor) {
        this.addressService = addressService;
        this.taxiDataProcessor = taxiDataProcessor;
    }

    @Override
    public void configure() throws Exception {
        from(FROM).routeId(ROUTE_NAME).process(taxiDataProcessor).unmarshal(new CsvDataFormat())
                .split(body()).streaming().parallelProcessing()
                .bean(addressService, "filterZone")
                .filter(header(addressService.getIsValidHeader()).isEqualTo(true))
                .throttle(addressService.getTPS()).bean(addressService, "getAddress")
                .aggregate(constant(true)).aggregationStrategy(new BundlingStrategy())
                .completionPredicate(new MethodCallExpression(addressService, "isComplete"))
                .marshal().csvLazyLoad()
                .to(TO).log("done.").end();
    }
}
