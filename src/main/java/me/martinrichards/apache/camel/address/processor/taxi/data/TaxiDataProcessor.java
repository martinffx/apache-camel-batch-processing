package me.martinrichards.apache.camel.address.processor.taxi.data;

import com.google.inject.Inject;

import org.apache.camel.CamelContext;
import org.apache.camel.Exchange;
import org.apache.camel.Processor;
import org.apache.camel.ProducerTemplate;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import java.net.URL;

import me.martinrichards.apache.camel.address.routes.AddressRoute;

/**
 * @author martinrichards
 */
public class TaxiDataProcessor implements Processor {
    private final Logger log = LoggerFactory.getLogger(TaxiDataProcessor.class);
    private final TaxiDataCSVFactory csvParserFactory;
    private final CamelContext camel;

    @Inject
    public TaxiDataProcessor(final TaxiDataCSVFactory csvParserFactory, final CamelContext camel) {
        this.csvParserFactory = csvParserFactory;
        this.camel = camel;
    }

    @Override
    public void process(final Exchange exchange) throws Exception {
        final String file = (String) exchange.getIn().getBody();
        final URL url = new URL(file);
        final ProducerTemplate template = camel.createProducerTemplate();

        log.info("Fetching file: " + file);
        final TaxiDataCSV taxiDataCSV = csvParserFactory.build(url);
        log.info("Processing file: " + file);
        for (TaxiDataCSVRecord record : taxiDataCSV) {
            template.asyncSendBody(AddressRoute.FROM_TRIP_RECORD, record);
        }
        log.info("Finished processing file: " + file);
    }
}
