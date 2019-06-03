package little.things.streams;

import java.util.List;
import java.util.stream.Collectors;

import static java.util.stream.Collectors.toList;

public class Limit {
    public static void main(String[] args) {
        List<String> list = CollectionKeeper.getCollection();
        System.out.println("All elements: " + list);

        int limit = 3;
        List<String> limitedList = list.stream()
                .limit(limit)
                .collect(toList());
        System.out.println("Limited list: " + limitedList);
    }
}
