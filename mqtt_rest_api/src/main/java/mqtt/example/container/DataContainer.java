package mqtt.example.container;

import mqtt.example.utils.RandomNumbersProducer;

public class DataContainer {
    private volatile static Data data;

    public static Data getData() {
        DataContainer.setData(RandomNumbersProducer.getRandomPixels());
        return data;
    }

    public synchronized static void setData(Data data) {
        DataContainer.data = data;
    }
}
