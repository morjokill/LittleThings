package mqtt.example.app;

import io.dropwizard.Configuration;
import io.dropwizard.jetty.ConnectorFactory;
import io.dropwizard.jetty.HttpConnectorFactory;
import io.dropwizard.server.DefaultServerFactory;
import io.dropwizard.setup.Environment;
import mqtt.example.controller.SimpleController;
import mqtt.example.controller.TrainController;
import org.eclipse.jetty.servlets.CrossOriginFilter;

import javax.servlet.DispatcherType;
import javax.servlet.FilterRegistration;
import java.util.EnumSet;

public class Application extends io.dropwizard.Application<Configuration> {
    private static int port;

    public static void start(int port) throws Exception {
        Application.port = port;
        new Application().run("server");
    }

    @Override
    public void run(Configuration configuration, Environment environment) {
        addCorsFilter(environment);
        changePort(configuration);

        environment.jersey().register(new SimpleController());
        environment.jersey().register(new TrainController());
    }

    private void changePort(Configuration configuration) {
        DefaultServerFactory serverFactory = (DefaultServerFactory) configuration.getServerFactory();
        for (ConnectorFactory connector : serverFactory.getApplicationConnectors()) {
            if (connector.getClass().isAssignableFrom(HttpConnectorFactory.class)) {
                ((HttpConnectorFactory) connector).setPort(port);
            }
        }
        for (ConnectorFactory connector : serverFactory.getAdminConnectors()) {
            if (connector.getClass().isAssignableFrom(HttpConnectorFactory.class)) {
                ((HttpConnectorFactory) connector).setPort(port + 1);
            }
        }
    }

    private void addCorsFilter(Environment environment) {
        FilterRegistration.Dynamic corsFilter = environment.servlets().addFilter("CORS", CrossOriginFilter.class);

        corsFilter.setInitParameter("allowedOrigins", "*");
        corsFilter.setInitParameter("allowedHeaders", "X-Requested-With,Content-Type,Accept,Origin");
        corsFilter.setInitParameter("allowedMethods", "OPTIONS,GET,PUT,POST,DELETE,HEAD");

        corsFilter.addMappingForUrlPatterns(EnumSet.allOf(DispatcherType.class), true, "/*");
    }
}
