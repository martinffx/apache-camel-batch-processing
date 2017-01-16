package me.martinrichards.apache.camel.address;

import com.google.inject.Guice;
import com.google.inject.Injector;

import org.apache.camel.guice.CamelModuleWithRouteTypes;
import org.junit.Ignore;
import org.junit.Test;

import me.martinrichards.apache.camel.address.routes.AddressRoute;

import static junit.framework.TestCase.assertFalse;
import static junit.framework.TestCase.assertTrue;

/**
 * Created by martinrichards on 2016/10/16.
 */
public class ApplicationIntegrationTest {
    @Test
    @Ignore
    public void testApplicationStartupUrl() throws Exception {
        Injector injector = Guice.createInjector(new CamelModuleWithRouteTypes(AddressRoute.class),
                new Module());

        final Application app = injector.getInstance(Application.class);
        app.start("https://s3.amazonaws.com/nyc-tlc/trip+data/yellow_tripdata_2016-01.csv");
        assertTrue(app.getStatus().isStarted());
        assertTrue(app.isRunning());

        app.stop();
        assertTrue(app.getStatus().isStopped());
    }
}
