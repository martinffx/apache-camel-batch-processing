package me.martinrichards.apache.camel.address.processor.taxi.data;

import org.apache.commons.csv.CSVParser;
import org.apache.commons.csv.CSVRecord;

import java.util.Iterator;

/**
 * @author martinrichards
 */
public class TaxiDataCSV implements Iterable<TaxiDataCSVRecord> {
    private final TaxiDataCSVIterator iterator;

    public TaxiDataCSV(Iterable<CSVRecord> parser){
        this.iterator = new TaxiDataCSVIterator(parser);
    }

    @Override
    public Iterator<TaxiDataCSVRecord> iterator() {
        return iterator;
    }
}


