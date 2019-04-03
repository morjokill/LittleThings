package little.things.algorithm.binary_search;

import java.util.Arrays;

public class Football {
    public static void main(String[] args) {
        int[] efficiency = new int[]{1, 5, 2, 9, 15, 22, 11, 8};
        int[] sums = new int[efficiency.length];
        Arrays.sort(efficiency);

        if (efficiency.length > 0) {
            sums[0] = efficiency[0];
            for (int i = 1; i < sums.length; i++) {
                sums[i] = sums[i - 1] + efficiency[i];
            }

            int firstPlayerEff = efficiency[efficiency.length - 1];
            if (efficiency.length > 1) {
                int maxEff;
                int maxLeft;
                int maxRight;

                int twoPlayersEff = efficiency[efficiency.length - 1] + efficiency[efficiency.length - 2];

                if (firstPlayerEff > twoPlayersEff) {
                    maxEff = firstPlayerEff;
                    maxLeft = efficiency.length - 1;
                    maxRight = efficiency.length - 1;
                } else {
                    maxEff = twoPlayersEff;
                    maxLeft = efficiency.length - 2;
                    maxRight = efficiency.length - 1;
                }

                int n = efficiency.length - 1;
                for (int i = 0; i < n; i++) {
                    int leftInTeam = i;
                    int rightInTeam = findElement(efficiency[leftInTeam] + efficiency[leftInTeam + 1], efficiency, leftInTeam, n);
                    if (rightInTeam == -1) {
                        rightInTeam = leftInTeam + 1;
                    }
                    int rightSum = sums[rightInTeam];
                    int leftSum = leftInTeam > 0 ? sums[leftInTeam -1] : 0;
                    if (rightSum - leftSum > maxEff) {
                        maxEff = rightSum - leftSum;
                        maxLeft = leftInTeam;
                        maxRight = rightInTeam;
                    }
                }
                System.out.println("maxEff: " + maxEff + ", left: " + maxLeft + ", right: " + maxRight);
            } else {
                System.out.println("maxEff: " + firstPlayerEff + "first player");
            }
        } else {
            System.out.println("No players");
        }
    }

    private static int findElement(int sum, int[] array, int i, int j) {
        int lastLower = -1;

        do {
            int pos = (j - i) / 2 + i;
            int value = array[pos];
            if (value == sum) return pos;
            else {
                if (value > sum) {
                    j = j - (j - i) / 2 - 1;
                }
                if (value < sum) {
                    i = pos + 1;
                    lastLower = pos;
                }
            }
        } while (i != j);
        return lastLower;
    }
}
