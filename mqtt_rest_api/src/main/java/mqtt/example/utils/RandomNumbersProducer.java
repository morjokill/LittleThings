package mqtt.example.utils;

import mqtt.example.model.Pixels;

import java.util.Random;

public class RandomNumbersProducer {
    public static Pixels getRandomPixels() {
        return new Pixels(new int[]{getRandomInt(1500),
                getRandomInt(1500),
                getRandomInt(1500),
                getRandomInt(1500)});
    }

    private static int getRandomInt(int to) {
        return getRandomInt(0, to);
    }

    private static int getRandomInt(int from, int to) {
        Random random = new Random();
        return random.nextInt(to) + from;
    }
}
