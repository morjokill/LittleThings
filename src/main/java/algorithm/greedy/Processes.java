package algorithm.greedy;

import java.util.Arrays;

public class Processes {
    public static void main(String[] args) {
        int[] start = new int[]{1, 2, 3, 4, 5, 4};
        int[] end = new int[]{2, 2, 2, 5, 2, 2};
        int[] punishment = new int[]{70, 20, 50, 100, 30, 20};

        int firstDay = findFirstDay(start);
        int lastDay = findLastDay(end);

        for (int i = 0; i < punishment.length; i++) {
            int maxIndex = i;
            for (int j = i; j < punishment.length; j++) {
                if (punishment[j] > punishment[maxIndex]) {
                    maxIndex = j;
                }
            }
            punishment = swap(punishment, i, maxIndex);
            start = swap(start, i, maxIndex);
            end = swap(end, i, maxIndex);
        }

        System.out.println(Arrays.toString(punishment));

        int[] optimalEnds = new int[lastDay + 1];

        for (int i = 0; i < optimalEnds.length; i++) {
            optimalEnds[i] = -1;
        }

        for (int i = 0; i < punishment.length; i++) {
            int endDay = end[i];
            if (optimalEnds[endDay] == -1) {
                optimalEnds[endDay] = i;
            } else {
                int j = endDay;
                boolean found = false;
                while (j >= firstDay && !found) {
                    if (optimalEnds[j] == -1) {
                        optimalEnds[j] = i;
                        found = true;
                    }
                    j--;
                }
                j = optimalEnds.length - 1;
                if (!found) {
                    while (j > endDay && !found) {
                        if (optimalEnds[j] == -1) {
                            optimalEnds[j] = i;
                            found = true;
                        }
                        j--;
                    }
                }
            }
        }

        System.out.println(Arrays.toString(optimalEnds));
        for (int i = 1; i < optimalEnds.length; i++) {
            if (optimalEnds[i] != -1) {
                System.out.println("Day " + i + " punishment " + punishment[optimalEnds[i]]);
            }
        }
    }

    private static int findLastDay(int[] end) {
        int lastDay = end[0];
        for (int i = 1; i < end.length; i++) {
            if (end[i] > lastDay) {
                lastDay = end[i];
            }
        }
        return lastDay;
    }

    private static int findFirstDay(int[] start) {
        int firstDay = start[0];
        for (int i = 1; i < start.length; i++) {
            if (start[i] < firstDay) {
                firstDay = start[i];
            }
        }
        return firstDay;
    }

    private static int[] swap(int[] array, int i, int maxIndex) {
        int cup = array[i];
        array[i] = array[maxIndex];
        array[maxIndex] = cup;
        return array;
    }
}
