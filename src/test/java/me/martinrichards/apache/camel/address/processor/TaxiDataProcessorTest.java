package me.martinrichards.apache.camel.address.processor;

import org.apache.camel.Exchange;
import org.apache.camel.Message;
import org.junit.Before;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.Mockito;
import org.mockito.junit.MockitoJUnitRunner;

import java.net.URL;

import me.martinrichards.apache.camel.address.BaseTestCase;
import me.martinrichards.apache.camel.address.processor.taxi.data.TaxiDataCSV;
import me.martinrichards.apache.camel.address.processor.taxi.data.TaxiDataCSVFactory;
import me.martinrichards.apache.camel.address.processor.taxi.data.TaxiDataCSVIterator;
import me.martinrichards.apache.camel.address.processor.taxi.data.TaxiDataCSVRecord;
import me.martinrichards.apache.camel.address.processor.taxi.data.TaxiDataProcessor;

import static org.mockito.ArgumentMatchers.any;
import static org.mockito.Mockito.times;
import static org.mockito.Mockito.when;

/**
 * @author martinrichards
 */
@RunWith(MockitoJUnitRunner.class)
public class TaxiDataProcessorTest extends BaseTestCase {
    @Mock
    Exchange exchange;

    @Mock
    Message message;

    @Mock
    TaxiDataCSV taxiDataCSV;

    @Mock
    TaxiDataCSVIterator iterator;

    @Mock
    TaxiDataCSVRecord record;

    @Mock
    TaxiDataCSVFactory factory;

    @InjectMocks
    TaxiDataProcessor processor;

    @Before
    public void setup() {
        when(message.getBody()).thenReturn("https://s3.amazonaws.com/nyc-tlc/trip+data/yellow_tripdata_2016-01.csv");
        when(exchange.getIn()).thenReturn(message);
    }

    @Test
    public void testProcess() throws Exception {
        when(iterator.hasNext()).thenReturn(true, true, true, false);
        when(iterator.next()).thenReturn(record)
                .thenReturn(record).thenReturn(record);
        when(taxiDataCSV.iterator()).thenReturn(iterator);

        when(factory.build(any(URL.class))).thenReturn(taxiDataCSV);
        processor.process(exchange);
        Mockito.verify(message, times(3))
                .setBody(any(TaxiDataCSVRecord.class));
    }
}
