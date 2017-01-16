package me.martinrichards.apache.camel.address.processor.taxi.data;

import org.apache.commons.csv.CSVRecord;

/**
 * @author martinrichards
 */
public class TaxiDataCSVRecord {
    public static final String VENDOR_ID = "VendorID";
    public static final String TPEP_PICKUP_DATETIME = "tpep_pickup_datetime";
    public static final String TPEP_DROPOFF_DATETIME = "tpep_dropoff_datetime";
    public static final String PASSENGER_COUNT = "passenger_count";
    public static final String TRIP_DISTANCE = "trip_distance";
    public static final String PICKUP_LONGITUDE = "pickup_longitude";
    public static final String PICKUP_LATITUDE = "pickup_latitude";
    public static final String RATECODE_ID = "RatecodeID";
    public static final String STORE_AND_FWD_FLAG = "store_and_fwd_flag";
    public static final String DROPOFF_LONGITUDE = "dropoff_longitude";
    public static final String DROPOFF_LATITUDE = "dropoff_latitude";
    public static final String PAYMENT_TYPE = "payment_type";
    public static final String FARE_AMOUNT = "fare_amount";
    public static final String EXTRA = "extra";
    public static final String MTA_TAX = "mta_tax";
    public static final String TIP_AMOUNT = "tip_amount";
    public static final String TOLLS_AMOUNT = "tolls_amount";
    public static final String IMPROVEMENT_SURCHARGE = "improvement_surcharge";
    public static final String TOTAL_AMOUNT = "total_amount";

    private final CSVRecord record;

    public TaxiDataCSVRecord(CSVRecord record) {
        this.record = record;
    }

}
