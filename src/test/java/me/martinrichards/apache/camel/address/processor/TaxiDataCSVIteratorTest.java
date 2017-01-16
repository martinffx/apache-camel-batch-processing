package me.martinrichards.apache.camel.address.processor;

import org.apache.commons.csv.CSVFormat;
import org.apache.commons.csv.CSVParser;
import org.junit.Before;
import org.junit.Test;

import java.io.IOException;
import java.io.Reader;
import java.net.URISyntaxException;

import me.martinrichards.apache.camel.address.BaseTestCase;
import me.martinrichards.apache.camel.address.processor.taxi.data.TaxiDataCSVIterator;

import static junit.framework.TestCase.assertNotNull;
import static junit.framework.TestCase.assertTrue;

/**
 * @author martinrichards
 */
public class TaxiDataCSVIteratorTest extends BaseTestCase {
    private CSVParser parser;

    @Before
    public void setup() throws IOException, URISyntaxException {
        Reader reader = readFile("fixtures/taxi_data.csv");
        parser = CSVFormat.DEFAULT.parse(reader);
    }

    @Test
    public void testCSVIterator() {
        TaxiDataCSVIterator iterator = new TaxiDataCSVIterator(parser);
        assertTrue(iterator.hasNext());
        assertNotNull(iterator.next());
    }
}
