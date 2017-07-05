package me.martinrichards.apache.camel.address;

import com.google.inject.Guice;
import com.google.inject.Injector;

import org.apache.camel.guice.CamelModuleWithRouteTypes;
import org.junit.Ignore;
import org.junit.Test;

import me.martinrichards.apache.camel.address.routes.AddressRoute;

import static junit.framework.TestCase.assertTrue;

/**
 * @author martinrichards
 */
public class ApplicationIntegrationTest {
    @Test
    @Ignore
    public void testApplicationStartupUrl() throws Exception {
       Application.main("https://s3.amazonaws.com/nyc-tlc/trip+data/yellow_tripdata_2016-01.csv");
    }

    @Test
    public void testApplicationStartStop() throws Exception {
        Injector injector = Guice.createInjector(new CamelModuleWithRouteTypes(AddressRoute.class),
                new Module());

        final Application app = injector.getInstance(Application.class);
        app.start();
        app.process("https://s3.amazonaws.com/nyc-tlc/trip+data/yellow_tripdata_2016-01.csv");
        assertTrue(app.getStatus().isStarted());
        assertTrue(app.isRunning());

        app.stop();
        assertTrue(app.getStatus().isStopped());
    }
}
