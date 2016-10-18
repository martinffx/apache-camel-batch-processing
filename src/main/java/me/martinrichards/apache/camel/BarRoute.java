package me.martinrichards.apache.camel;

import org.apache.camel.builder.RouteBuilder;

/**
 * Created by martinrichards on 2016/10/18.
 */
public class BarRoute extends org.apache.camel.builder.RouteBuilder {
    public static final String ROUTE_NAME = "Default";

    @Override
    public void configure() throws Exception {
        from("direct:foo").routeId(ROUTE_NAME).to("direct:bar");

//        from("direct:input").unmarshal(inCSV).split(body()).streaming().parallelProcessing()
//                .bean(validator, "validateNumber")
//                .filter(header(ValidateProcess.ISVALID).isEqualTo(true))
//                .throttle(transactionsPerSecond).bean(validator, "validate")
//                .aggregate(constant(true)).aggregationStrategy(new CSVBundlingStrategy())
//                .completionPredicate(new MethodCallExpression(validator, "isComplete"))
//                .marshal().csvLazyLoad()
//                .to(out).log("done.").end();

    }
}
