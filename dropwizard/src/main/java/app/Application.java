package app;

import controller.SimpleController;
import io.dropwizard.Configuration;
import io.dropwizard.setup.Environment;

public class Application extends io.dropwizard.Application<Configuration> {
    public static void main(String[] args) throws Exception {
        //run with args: {server}
        new Application().run(args);
    }

    @Override
    public void run(Configuration configuration, Environment environment) {
        environment.jersey().register(new SimpleController());
    }
}
