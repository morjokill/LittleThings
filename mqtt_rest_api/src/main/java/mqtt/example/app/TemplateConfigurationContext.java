package mqtt.example.app;

import freemarker.template.Configuration;
import freemarker.template.TemplateExceptionHandler;

import java.io.File;

import static mqtt.example.Main.RESOURCES_PATH;

public class TemplateConfigurationContext {
    private static Configuration configuration = new Configuration(Configuration.VERSION_2_3_27);

    public static Configuration getConfiguration() {
        try {
            configuration.setDirectoryForTemplateLoading(new File(RESOURCES_PATH));
            configuration.setDefaultEncoding("UTF-8");
            configuration.setTemplateExceptionHandler(TemplateExceptionHandler.RETHROW_HANDLER);
            configuration.setLogTemplateExceptions(false);
            configuration.setWrapUncheckedExceptions(true);
        } catch (Exception e) {
            System.out.println(e.getMessage());
        }
        return configuration;
    }
}
