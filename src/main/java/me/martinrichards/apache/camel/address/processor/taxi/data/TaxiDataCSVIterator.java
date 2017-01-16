package me.martinrichards.apache.camel.address.processor.taxi.data;

import org.apache.commons.csv.CSVParser;
import org.apache.commons.csv.CSVRecord;

import java.util.Iterator;


/**
 * @author martinrichards
 */
public class TaxiDataCSVIterator implements Iterator<TaxiDataCSVRecord> {
    private final Iterable<CSVRecord> parser;

    public TaxiDataCSVIterator(Iterable<CSVRecord> parser) {
        this.parser = parser;
    }

    @Override
    public boolean hasNext() {
        return parser.iterator().hasNext();
    }

    @Override
    public TaxiDataCSVRecord next() {
        return new TaxiDataCSVRecord(parser.iterator().next());
    }
}
