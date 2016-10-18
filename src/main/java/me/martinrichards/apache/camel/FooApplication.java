package me.martinrichards.apache.camel;

import com.google.inject.Guice;
import com.google.inject.Inject;
import com.google.inject.Injector;

import org.apache.camel.CamelContext;
import org.apache.camel.Route;
import org.apache.camel.guice.CamelModuleWithRouteTypes;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

/**
 * Created by martinrichards on 2016/10/16.
 */
public class FooApplication {
    private static final Logger log = LoggerFactory.getLogger(FooApplication.class);
    private final CamelContext camel;

    @Inject
    public FooApplication(CamelContext camelContext){
        camel = camelContext;
    }

    public static void main(String... args) throws Exception {
        Injector injector = Guice.createInjector(new CamelModuleWithRouteTypes(BarRoute.class));
        final FooApplication app = injector.getInstance(FooApplication.class);
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
        Route routeName = camel.getRoute(BarRoute.ROUTE_NAME);
        return true;
    }
}
