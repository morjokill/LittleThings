package algorithm.dynamic.packing.bags;

import java.util.LinkedList;
import java.util.List;

public class Bags {
    public static void main(String[] args) {
        int[] input = new int[]{
                21,
                3, 20,
                5, 32,
                6, 2,
                2, 12,
                1, 5,
                10, 8,
        };

        int m = input[0];
        List<Item> items = new LinkedList<>();
        int[] arrayF = new int[m + 1];
        if (input.length % 2 != 0) {
            for (int i = 1; i < input.length; i += 2) {
                items.add(new Item(input[i], input[i + 1]));
            }
            System.out.println(items);
        } else {
            System.out.println("Corrupted input");
        }
    }
}
