package little.things.streams;

import java.util.List;
import java.util.Optional;

public class FindAny {
    public static void main(String[] args) {
        List<String> list = CollectionKeeper.getCollection();
        System.out.println("All elements: " + list);

        Optional<String> any = list.stream()
                .findAny();
        //noinspection ConstantConditions
        System.out.println("Any element: " + any.get());
    }
}
