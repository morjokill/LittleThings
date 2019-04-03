package little.things.algorithm.dynamic.packing.bags;

import java.util.Arrays;

public class Bags {
    public static void main(String[] args) {
        int[] input = new int[]{
                15,
                3, 20,
                5, 32,
                6, 2,
                2, 12,
                1, 5,
                10, 8,
        };

        if (input.length % 2 != 0) {
            int weightCup = input[0];
            int[] weights = new int[(input.length - 1) / 2 + 1];
            int[] costs = new int[(input.length - 1) / 2 + 1];
            int k = 1;
            for (int i = 1; i < input.length - 1; i += 2) {
                weights[k] = input[i];
                costs[k] = input[i + 1];
                k++;
            }
            System.out.println(Arrays.toString(weights));
            System.out.println(Arrays.toString(costs));

            System.out.println(calcBackPack(weights, costs, weightCup));
        } else {
            System.out.println("Corrupted input");
        }
    }

    private static int calcBackPack(int weights[], int costs[], int weightCup) {
        int weightsLength = weights.length;
        int arrayF[][] = new int[weightCup + 1][weightsLength + 1];
        for (int j = 1; j <= weightsLength; j++) {
            for (int w = 1; w <= weightCup; w++) {
                if (weights[j - 1] <= w) {
                    arrayF[w][j] = Math.max(arrayF[w][j - 1], arrayF[w - weights[j - 1]][j - 1] + costs[j - 1]);
                } else {
                    arrayF[w][j] = arrayF[w][j - 1];
                }
            }
        }
        return arrayF[weightCup][weightsLength];
    }
}
