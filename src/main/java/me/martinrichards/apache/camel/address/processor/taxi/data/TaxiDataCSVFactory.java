package me.martinrichards.apache.camel.address.processor.taxi.data;

import org.apache.commons.csv.CSVFormat;
import org.apache.commons.csv.CSVParser;

import java.io.IOException;
import java.net.URL;
import java.nio.charset.Charset;

/**
 * @author martinrichards
 */
public class TaxiDataCSVFactory {
    public TaxiDataCSV build(URL url) throws IOException {
        return new TaxiDataCSV(CSVParser.parse(url, Charset.defaultCharset(),
                CSVFormat.RFC4180.withHeader()));
    }
}
