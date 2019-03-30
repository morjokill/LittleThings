package mqtt.example.container;

public class RawDataContainer {
    private volatile static byte[] rawData;

    public static byte[] getData() {
        return rawData;
    }

    public synchronized static void setData(byte[] data) {
        rawData = data;
    }
}
