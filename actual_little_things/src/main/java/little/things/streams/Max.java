package little.things.streams;

import java.util.List;
import java.util.Optional;
import java.util.stream.Collectors;

public class Max {
    public static void main(String[] args) {
        List<String> list = CollectionKeeper.getCollection();
        System.out.println("All elements: " + list);

        Optional<String> max = list.stream()
                .max(String::compareTo);
        //noinspection ConstantConditions
        System.out.println("Max element: " + max.get());
    }
}
