package me.martinrichards.apache.camel.address;

import com.google.inject.AbstractModule;

import me.martinrichards.apache.camel.address.services.AddressService;
import me.martinrichards.apache.camel.address.services.IAddressService;

/**
 * Created by martinrichards on 2016/10/29.
 */
public class Module extends AbstractModule {
    @Override
    protected void configure() {
        bind(IAddressService.class).to(AddressService.class);
    }
}
