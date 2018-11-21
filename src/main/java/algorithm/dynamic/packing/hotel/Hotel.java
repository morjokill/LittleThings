package algorithm.dynamic.packing.hotel;

import java.util.*;

public class Hotel {
    public static void main(String[] args) {
        int[] input = new int[]{
                1, 3, 230,
                1, 2, 210,
                1, 3, 50,
                3, 5, 150,
                2, 4, 20,
                1, 2, 200,
                4, 6, 20,
                2, 5, 100,
        };
        System.out.println(Arrays.toString(input));

        int firstDay = input[0];
        int lastDay = input[1];
        Map<Integer, List<Rent>> lastDayRentMap = new HashMap<>();
        if (input.length % 3 == 0) {
            for (int i = 0; i < input.length; i += 3) {
                Rent rent = new Rent(input[i], input[i + 1], input[i + 2]);
                int day1 = input[i];
                int day2 = input[i + 1];

                if (day1 < firstDay) firstDay = day1;
                if (day2 > lastDay) lastDay = day2;

                System.out.println(rent);
                List<Rent> rents;
                if (Objects.isNull(lastDayRentMap.get(day2))) {
                    lastDayRentMap.put(day2, new LinkedList<>());
                }
                rents = lastDayRentMap.get(day2);
                rents.add(rent);
            }
        } else {
            System.out.println("Input corrupted");
        }
        System.out.println();
        for (Integer endDay : lastDayRentMap.keySet()) {
            System.out.print("endDay " + endDay + ":  [");
            for (Rent rent : lastDayRentMap.get(endDay)) {
                System.out.print(rent + ", ");
            }
            System.out.println("]");
        }

        System.out.println();
        System.out.println("firstDay: " + firstDay);
        System.out.println("lastDay: " + lastDay);

        int[] arrayF = new int[lastDay + 1];
        for (int i = firstDay; i < arrayF.length; i++) {
            List<Rent> rentsForThisDay = lastDayRentMap.get(i);
            int maxPayment = 0;
            if (Objects.nonNull(rentsForThisDay)) {
                for (Rent rent : rentsForThisDay) {
                    int current = Math.max(arrayF[i - 1], arrayF[rent.getDay1()] + rent.getPayment());
                    if (current > maxPayment) maxPayment = current;
                }
            }
            arrayF[i] = maxPayment;
        }
        System.out.println(Arrays.toString(arrayF));
    }
}
