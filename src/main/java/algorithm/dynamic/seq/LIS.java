package algorithm.dynamic.seq;

import java.util.Arrays;

public class LIS {
    private static char[] seq = "518267".toCharArray();
    private static int[] lcs = new int[seq.length];
    private static int[] lcsIndex = new int[seq.length];

    public static void main(String[] args) {
        System.out.println(Arrays.toString(seq));

        for (int i = 0; i < seq.length; i++) {
            lcs[i] = 1;
            lcsIndex[i] = -1;
            for (int j = 0; j < i; j++) {
                if (seq[j] < seq[i]) {
                    if (lcs[j] + 1 > lcs[i]) {
                        lcs[i] = lcs[j] + 1;
                        lcsIndex[i] = j;
                    }
                }
            }
        }

        System.out.println(Arrays.toString(lcs));
        System.out.println(Arrays.toString(lcsIndex));
        printLIS(findMaxIndex(lcs));
    }

    private static void printLIS(int i) {
        if (lcsIndex[i] != -1) {
            printLIS(lcsIndex[i]);
        }
        System.out.print(seq[i] + " ");
    }

    private static int findMaxIndex(int[] array) {
        int max = 0;
        for (int i = 0; i < array.length; i++) {
            if (array[i] > array[max]) {
                max = i;
            }
        }
        return max;
    }
}
