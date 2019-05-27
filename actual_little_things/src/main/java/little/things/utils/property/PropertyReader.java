package little.things.utils.property;

import org.apache.commons.configuration.Configuration;
import org.apache.commons.configuration.ConfigurationException;
import org.apache.commons.configuration.PropertiesConfiguration;

public class PropertyReader {
    private static Configuration propertyConfig = null;

    public static void initProp(String resourcesPath, String propertyFile) throws ConfigurationException {
        System.out.println("Initializing properties configuration with resources directory: " +
                resourcesPath + " and property file: " + propertyFile);
        PropertiesConfiguration propertiesConfiguration = new PropertiesConfiguration();
        propertiesConfiguration.setEncoding("UTF-8");
        propertiesConfiguration.setLogger(null);
        propertiesConfiguration.setDelimiterParsingDisabled(true);
        propertiesConfiguration.setBasePath(resourcesPath);
        propertiesConfiguration.load(propertyFile);
        propertyConfig = propertiesConfiguration;
        System.out.println("Properties configuration successfully initialized");
    }

    public static String getValue(String name) {
        return propertyConfig.getString(name);
    }

    public static boolean getBoolean(String name) {
        String value = propertyConfig.getString(name);
        return Boolean.valueOf(value);
    }

    public static int getInt(String name) {
        String value = propertyConfig.getString(name);
        return Integer.parseInt(value);
    }

    public static void main(String[] args) throws ConfigurationException {
        PropertyReader.initProp("src\\main\\resources", "config.properties");
        System.out.println(PropertyReader.getValue("test.value"));
    }
}
