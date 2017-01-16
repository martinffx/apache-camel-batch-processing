package me.martinrichards.apache.camel.address.processor;

import org.apache.commons.csv.CSVFormat;
import org.apache.commons.csv.CSVParser;
import org.junit.Test;

import java.io.IOException;
import java.io.Reader;
import java.net.URISyntaxException;

import me.martinrichards.apache.camel.address.BaseTestCase;
import me.martinrichards.apache.camel.address.processor.taxi.data.TaxiDataCSV;
import me.martinrichards.apache.camel.address.processor.taxi.data.TaxiDataCSVRecord;

import static org.junit.Assert.assertNotNull;

/**
 * @author martinrichards
 */
public class TaxiDataCSVTest extends BaseTestCase {

    @Test
    public void testTaxiDataCSVParser() throws IOException, URISyntaxException {
        Reader reader = readFile("fixtures/taxi_data.csv");
        CSVParser parser = CSVFormat.DEFAULT.withHeader().parse(reader);
        TaxiDataCSV taxiDataCSVParser = new TaxiDataCSV(parser);
        assert taxiDataCSVParser.iterator().hasNext();
        TaxiDataCSVRecord record = taxiDataCSVParser.iterator().next();
        assertNotNull(record);
    }
}
