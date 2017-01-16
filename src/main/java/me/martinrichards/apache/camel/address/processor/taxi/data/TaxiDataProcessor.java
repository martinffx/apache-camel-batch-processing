package me.martinrichards.apache.camel.address.processor.taxi.data;

import com.google.inject.Inject;

import org.apache.camel.Exchange;
import org.apache.camel.Processor;

import java.net.URL;

/**
 * @author martinrichards
 */
public class TaxiDataProcessor implements Processor {
    private final TaxiDataCSVFactory csvParserFactory;

    @Inject
    public TaxiDataProcessor(TaxiDataCSVFactory csvParserFactory) {
        this.csvParserFactory = csvParserFactory;
    }

    @Override
    public void process(Exchange exchange) throws Exception {
        String file = (String) exchange.getIn().getBody();
        URL url = new URL(file);
        TaxiDataCSV taxiDataCSV = csvParserFactory.build(url);
        for (TaxiDataCSVRecord record : taxiDataCSV) {
            exchange.getIn().setBody(record);
        }
    }
}
