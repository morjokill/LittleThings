package little.things.streams;

import java.util.List;
import java.util.stream.Collectors;

import static java.util.stream.Collectors.toList;

public class Sorted {
    public static void main(String[] args) {
        List<String> reversedList = CollectionKeeper.getReversedCollection();
        System.out.println("All elements reversed: " + reversedList);

        List<String> sorted = reversedList.stream()
                .sorted(String::compareTo)
                .collect(toList());
        System.out.println("Sorted: " + sorted);
    }
}
