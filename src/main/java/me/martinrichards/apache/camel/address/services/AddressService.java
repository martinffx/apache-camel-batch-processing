package me.martinrichards.apache.camel.address.services;

import org.apache.camel.Headers;

import java.util.List;
import java.util.Map;

/**
 * Created by martinrichards on 2016/10/24.
 */
public class AddressService implements IAddressService {
    public static final String ISVALID = "1";

    @Override
    public int getTPS() {
        return 50;
    }

    @Override
    public String getIsValidHeader() {
        return ISVALID;
    }

    @Override
    public Map<String, String> filterZone(List<String> row, @Headers Map<String, Object> headers) {
        return null;
    }

    @Override
    public Map<String, String> getAddress(List<String> row, @Headers Map<String, Object> headers) {
        return null;
    }

    @Override
    public boolean isComplete(List<String> row, @Headers Map<String, Object> headers) {
        return false;
    }

    @Override
    public void start() throws Exception {

    }

    @Override
    public void stop() throws Exception {

    }
}
