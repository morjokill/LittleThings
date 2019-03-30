package mqtt.example;

import mqtt.example.app.Application;
import mqtt.example.mqtt_client.SimpleMqttClient;

public class Main {
    public static String RESOURCES_PATH;
    private static String MQTT_ADDRESS;
    private static String TOPIC;
    private static int PORT;

    public static void main(String[] args) throws Exception {
        initProperties(args);

        new SimpleMqttClient(MQTT_ADDRESS, TOPIC);

        Application.start(PORT);
    }

    private static void initProperties(String[] args) {
        if (args.length != 0) {
            RESOURCES_PATH = args[0];
            MQTT_ADDRESS = args[1];
            TOPIC = args[2];
            PORT = Integer.valueOf(args[3]);
        } else {
            System.out.println("You should declare properties by args[] massive!");
            System.exit(0);
        }
    }
}
