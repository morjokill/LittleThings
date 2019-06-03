package little.things.streams;

import java.util.List;
import java.util.Optional;
import java.util.stream.Collectors;

public class FindFirst {
    public static void main(String[] args) {
        List<String> list = CollectionKeeper.getCollection();
        System.out.println("All elements: " + list);

        Optional<String> first = list.stream()
                .findFirst();
        //noinspection ConstantConditions
        System.out.println("First element: " + first.get());
    }
}
