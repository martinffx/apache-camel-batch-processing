package me.martinrichards.apache.camel.address;

import com.google.inject.Guice;
import com.google.inject.Inject;
import com.google.inject.Injector;

import org.apache.camel.CamelContext;
import org.apache.camel.Route;
import org.apache.camel.ServiceStatus;
import org.apache.camel.guice.CamelModuleWithRouteTypes;
import org.apache.commons.io.FileUtils;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import me.martinrichards.apache.camel.address.routes.AddressRoute;
import me.martinrichards.apache.camel.address.services.IAddressService;

/**
 * Created by martinrichards on 2016/10/16.
 */
public class Application {
    private static final Logger log = LoggerFactory.getLogger(Application.class);
    private final CamelContext camel;

    @Inject
    public Application(CamelContext camelContext, IAddressService addressService) throws Exception {
        camel = camelContext;
        camel.addService(addressService);
    }

    public static void main(String... args) throws Exception {
        Injector injector = Guice.createInjector(new CamelModuleWithRouteTypes(AddressRoute.class),
                new Module());



        final Application app = injector.getInstance(Application.class);
        app.start();
        log.info("Application has started!");
        Runtime.getRuntime().addShutdownHook(new Thread() {
            @Override
            public void run()  {
                try {
                    app.stop();
                } catch (Exception ex) {
                    log.error("Error stopping", ex);
                }
            }
        });
        
        while (app.isRunning()) {
            Thread.sleep(100);
        }
    }

    public void start() throws Exception {
        camel.start();
    }

    public void stop() throws Exception {
        camel.stop();
    }

    public boolean isRunning() {
        ServiceStatus status = camel.getRouteStatus(AddressRoute.ROUTE_NAME);
        return status.isStarted();
    }
    public ServiceStatus getStatus() {
        return camel.getStatus();
    }
}
