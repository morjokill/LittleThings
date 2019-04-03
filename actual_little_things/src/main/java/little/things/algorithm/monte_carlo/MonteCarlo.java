package little.things.algorithm.monte_carlo;

import java.util.Random;

public class MonteCarlo {
    public static void main(String[] args) {
        int n = 5;
        int accuracy = 1000000;
        int count = 0;
        for (int i = 0; i < accuracy; i++) {
            double x = getRandomDouble();
            double up = x + 1;
            double sum = 0;
            for (int j = 0; j < n - 1; j++) {
                sum += getRandomDouble();
            }
            double down = sum + n  + x;
            if (up / down > getRandomDouble()) {
                count++;
            }
        }
        double P = (double) count / accuracy;
        System.out.println("Accuracy: " + P);
    }

    private static double getRandomDouble() {
        Random rd = new Random();
        return rd.nextDouble();
    }
}
