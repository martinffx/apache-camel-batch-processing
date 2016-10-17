package me.martinrichards.apache.camel;

import com.google.inject.Guice;
import com.google.inject.Inject;
import com.google.inject.Injector;

import org.apache.camel.CamelContext;
import org.apache.camel.guice.CamelModuleWithRouteTypes;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

/**
 * Created by martinrichards on 2016/10/16.
 */
public class Main {
    private static Logger log = LoggerFactory.getLogger(Main.class);
    private final CamelContext context;

    @Inject
    public Main(CamelContext camelContext){
        context = camelContext;
    }

    public static void main(String... args) throws Exception {
        Injector injector = Guice.createInjector(new CamelModuleWithRouteTypes());
        final Main app = injector.getInstance(Main.class);
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
        context.start();
    }

    public void stop() throws Exception {
        context.stop();
    }

    public boolean isRunning() {
        return true;
    }
}
