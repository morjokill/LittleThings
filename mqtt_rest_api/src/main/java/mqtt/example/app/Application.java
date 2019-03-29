package mqtt.example.app;

import io.dropwizard.Configuration;
import io.dropwizard.setup.Environment;
import mqtt.example.controller.SimpleController;
import mqtt.example.controller.TrainController;
import org.eclipse.jetty.servlets.CrossOriginFilter;

import javax.servlet.DispatcherType;
import javax.servlet.FilterRegistration;
import java.util.EnumSet;

public class Application extends io.dropwizard.Application<Configuration> {
    public static void start() throws Exception {
        new Application().run("server");
    }

    @Override
    public void run(Configuration configuration, Environment environment) {
        addCorsFilter(environment);

        environment.jersey().register(new SimpleController());
        environment.jersey().register(new TrainController());
    }

    private void addCorsFilter(Environment environment) {
        FilterRegistration.Dynamic corsFilter = environment.servlets().addFilter("CORS", CrossOriginFilter.class);

        corsFilter.setInitParameter("allowedOrigins", "*");
        corsFilter.setInitParameter("allowedHeaders", "X-Requested-With,Content-Type,Accept,Origin");
        corsFilter.setInitParameter("allowedMethods", "OPTIONS,GET,PUT,POST,DELETE,HEAD");

        corsFilter.addMappingForUrlPatterns(EnumSet.allOf(DispatcherType.class), true, "/*");
    }
}
