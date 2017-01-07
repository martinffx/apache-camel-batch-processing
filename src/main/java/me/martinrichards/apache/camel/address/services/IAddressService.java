package me.martinrichards.apache.camel.address.services;

import org.apache.camel.Headers;
import org.apache.camel.Service;

import java.util.List;
import java.util.Map;

/**
 * Created by martinrichards on 2016/10/29.
 */
public interface IAddressService extends Service {
    int getTPS();
    String getIsValidHeader();
    Map<String, String> filterZone(List<String> row, @Headers Map<String, Object> headers);
    Map<String, String> getAddress(List<String> row, @Headers Map<String, Object> headers);
    boolean isComplete(List<String> row, @Headers Map<String, Object> headers);
}
