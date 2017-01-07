package me.martinrichards.apache.camel.address.routes;

import org.apache.camel.Exchange;
import org.apache.camel.processor.aggregate.AbstractListAggregationStrategy;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import java.util.Map;

/**
 * Created by martinrichards on 2016/10/24.
 */
public class BundlingStrategy extends AbstractListAggregationStrategy<Map<String, String>> {
    private static final Logger log = LoggerFactory.getLogger(BundlingStrategy.class);

    @Override
    public Map<String, String> getValue(Exchange exchange) {
        return (Map<String, String>) exchange.getIn().getBody();
    }
}
