package mqtt.example.mqtt_client;

import mqtt.example.container.Data;
import mqtt.example.container.RawDataContainer;
import mqtt.example.model.MockData;
import org.eclipse.paho.client.mqttv3.*;

import java.util.Arrays;
import java.util.UUID;

public class SimpleMqttClient {
    private static final String PUBLISHER_ID = UUID.randomUUID().toString();
    private String address;
    private String topic;
    private IMqttClient client;

    public SimpleMqttClient(String address, String topic) throws MqttException {
        this.address = address;
        this.topic = topic;
        initClient();
    }

    private void initClient() throws MqttException {
        this.client = new MqttClient(address, PUBLISHER_ID);
        connectToMqtt();
        executeReceiveTask();
    }

    private void connectToMqtt() throws MqttException {
        MqttConnectOptions options = new MqttConnectOptions();
        options.setAutomaticReconnect(true);
        options.setCleanSession(true);
        options.setConnectionTimeout(10);
        client.connect(options);
    }

    private void executeReceiveTask() throws MqttException {
        client.subscribe(topic, (topic, msg) -> {
            byte[] payload = msg.getPayload();
//            DataContainer.setData(getDataFromBytePayload(payload));
            RawDataContainer.setData(payload);
            System.out.println("Message came: " + Arrays.toString(payload));
        });
    }

    private static Data getDataFromBytePayload(byte[] payload) {
        return new MockData(new String(payload));
    }

    public void publishMsg(byte[] payloadToPublish) throws Exception {
        if (!client.isConnected()) {
            throw new Exception("Client is not connected");
        }
        MqttMessage msg = new MqttMessage(payloadToPublish);
        msg.setQos(0);
        msg.setRetained(true);
        client.publish(topic, msg);
    }

    public static void main(String[] args) throws Exception {
        SimpleMqttClient publisher = new SimpleMqttClient("tcp://iot.eclipse.org:1883", "TOPIC");
        publisher.publishMsg(new byte[]{35, 23, 89, 23});
    }
}
