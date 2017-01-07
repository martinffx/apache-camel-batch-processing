package me.martinrichards.apache.camel.address;

import com.google.inject.Guice;
import com.google.inject.Injector;

import org.apache.camel.guice.CamelModuleWithRouteTypes;
import org.junit.Test;

import me.martinrichards.apache.camel.address.routes.AddressRoute;

import static junit.framework.TestCase.assertFalse;
import static junit.framework.TestCase.assertTrue;

/**
 * Created by martinrichards on 2016/10/16.
 */
public class ApplicationIntegrationTest {

    @Test
    public void testApplicationStartup() throws Exception {
        Injector injector = Guice.createInjector(new CamelModuleWithRouteTypes(AddressRoute.class),
                new Module());

        final Application app = injector.getInstance(Application.class);
        app.start();
        assertTrue(app.getStatus().isStarted());
        assertTrue(app.isRunning());

        app.stop();
        assertTrue(app.getStatus().isStopped());
    }
}
