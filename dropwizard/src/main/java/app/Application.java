package app;

import app.health_check.TestHealthCheck;
import controller.SimpleController;
import io.dropwizard.Configuration;
import io.dropwizard.setup.Environment;

public class Application extends io.dropwizard.Application<Configuration> {
    public static void main(String[] args) throws Exception {
        new Application().run("server");
    }

    @Override
    public void run(Configuration configuration, Environment environment) {
        environment.jersey().register(new SimpleController());
        environment.healthChecks().register("test_heath_check", new TestHealthCheck());
    }
}
