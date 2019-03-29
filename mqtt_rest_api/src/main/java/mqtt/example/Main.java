package mqtt.example;

import mqtt.example.app.Application;
import mqtt.example.mqtt_client.SimpleMqttClient;

public class Main {
    private static final String MQTT_ADDRESS = "tcp://iot.eclipse.org:1883";
    public static String RESOURCES_PATH;

    public static void main(String[] args) throws Exception {
        initResourcesPath(args);

        new SimpleMqttClient(MQTT_ADDRESS);

        Application.start();
    }

    private static void initResourcesPath(String[] args) {
        if (args.length != 0) {
            RESOURCES_PATH = args[0];
        } else {
            System.out.println("You should declare path to your resources directory by args[] massive!");
            System.exit(0);
        }
    }
}
