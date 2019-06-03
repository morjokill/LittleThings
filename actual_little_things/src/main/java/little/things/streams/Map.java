package little.things.streams;

import java.util.List;

import static java.util.stream.Collectors.toList;

public class Map {
    public static void main(String[] args) {
        List<String> list = CollectionKeeper.getCollection();
        System.out.println("All elements: " + list);

        List<String> mapped = list.stream()
                .map((line) -> line + "_mapped")
                .collect(toList());
        System.out.println("Mapped elements: " + mapped);
    }
}
