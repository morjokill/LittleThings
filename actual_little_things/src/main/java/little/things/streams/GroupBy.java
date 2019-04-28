package little.things.streams;

import java.util.List;
import java.util.Map;
import java.util.stream.Collectors;

public class GroupBy {
    public static void main(String[] args) {
        Map<String, List<String>> collect = CollectionKeeper.getCollection().stream().collect(Collectors.groupingBy(o -> o));
        System.out.println(collect);
    }
}
