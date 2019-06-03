package little.things.streams;

import java.util.List;
import java.util.stream.Collectors;

import static java.util.stream.Collectors.toList;

public class Distinct {
    public static void main(String[] args) {
        List<String> list = CollectionKeeper.getCollection();
        System.out.println("Before distinct: " + list);

        List<String> nonRepeatable = list.stream()
                .distinct()
                .collect(toList());
        System.out.println("After distinct: " + nonRepeatable);
    }
}
