package me.martinrichards.apache.camel.address;

import com.google.inject.Guice;
import com.google.inject.Inject;
import com.google.inject.Injector;

import org.apache.camel.CamelContext;
import org.apache.camel.ProducerTemplate;
import org.apache.camel.Route;
import org.apache.camel.ServiceStatus;
import org.apache.camel.guice.CamelModuleWithRouteTypes;
import org.apache.commons.collections4.CollectionUtils;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import java.util.Arrays;
import java.util.Collections;
import java.util.concurrent.TimeUnit;

import me.martinrichards.apache.camel.address.routes.AddressRoute;
import me.martinrichards.apache.camel.address.services.IAddressService;

/**
 * @author martinrichards
 */
public class Application {
    private static final Logger log = LoggerFactory.getLogger(Application.class);
    private final CamelContext camel;

    @Inject
    public Application(final CamelContext camelContext, final IAddressService addressService)
            throws Exception {
        camel = camelContext;
        camel.addService(addressService);
    }

    public static void main(final String... args) throws Exception {
        final Injector injector = Guice.createInjector(
                new CamelModuleWithRouteTypes(AddressRoute.class),
                new Module());

        final Application app = injector.getInstance(Application.class);
        app.start();
        app.process(args);
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

    public void process(final String... args) {
        if (args == null || args.length == 0) {
            throw new IllegalArgumentException("args");
        }

        final ProducerTemplate template = camel.createProducerTemplate();
        for (String arg : args) {
            template.asyncSendBody(AddressRoute.FROM_CSV, arg);
        }
    }

    public void stop() throws Exception {
        camel.stop();
    }

    public boolean isRunning() {
        for(final Route route : camel.getRoutes()){
            final ServiceStatus status = camel.getRouteStatus(route.getId());
            if(!status.isStarted()) {
                return false;
            }
        }
        return true;
    }

    public ServiceStatus getStatus() {
        return camel.getStatus();
    }
}
