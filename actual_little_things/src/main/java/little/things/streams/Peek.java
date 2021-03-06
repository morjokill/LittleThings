package little.things.streams;

import java.util.List;
import java.util.stream.Collectors;

public class Peek {
    public static void main(String[] args) {
        List<String> list = CollectionKeeper.getCollection();
        System.out.println("All elements: " + list);

        List<String> collect = list.stream()
                .peek(System.out::println)
                .collect(Collectors.toList());
        System.out.println(collect);
    }
}
