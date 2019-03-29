package mqtt.example.mqtt_client;

import mqtt.example.container.Data;
import mqtt.example.model.MockData;
import org.eclipse.paho.client.mqttv3.*;

import java.util.Arrays;
import java.util.UUID;

public class SimpleMqttClient {
    private static final String PUBLISHER_ID = UUID.randomUUID().toString();
    private static final String TOPIC = "TOPIC";
    private IMqttClient client;

    public SimpleMqttClient(String address) throws MqttException {
        initClient(address);
    }

    private void initClient(String address) throws MqttException {
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
        client.subscribe(TOPIC, (topic, msg) -> {
            byte[] payload = msg.getPayload();
//            DataContainer.setData(getDataFromBytePayload(payload));
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
        client.publish(TOPIC, msg);
    }
}
