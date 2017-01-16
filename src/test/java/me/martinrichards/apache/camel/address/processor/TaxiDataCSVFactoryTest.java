package me.martinrichards.apache.camel.address.processor;

import org.junit.Test;

import java.io.IOException;
import java.net.URISyntaxException;

import me.martinrichards.apache.camel.address.BaseTestCase;
import me.martinrichards.apache.camel.address.processor.taxi.data.TaxiDataCSV;
import me.martinrichards.apache.camel.address.processor.taxi.data.TaxiDataCSVIterator;
import me.martinrichards.apache.camel.address.processor.taxi.data.TaxiDataCSVFactory;

import static org.junit.Assert.assertNotNull;

/**
 * @author martinrichards
 */
public class TaxiDataCSVFactoryTest extends BaseTestCase {

    @Test
    public void testFactory() throws URISyntaxException, IOException {
        TaxiDataCSVFactory factory = new TaxiDataCSVFactory();
        TaxiDataCSV parser =
                factory.build(getURI("fixtures/taxi_data.csv").toURL());
        assertNotNull(parser);
    }
}
