package little.things.streams;

import java.util.List;
import java.util.Map;
import java.util.function.Function;
import java.util.stream.Collectors;

import static java.util.function.Function.identity;
import static java.util.stream.Collectors.groupingBy;

public class GroupBy {
    public static void main(String[] args) {
        List<String> list = CollectionKeeper.getCollection();
        System.out.println("All elements: " + list);

        Map<String, List<String>> groupedBy = list.stream()
                .collect(groupingBy(identity()));
        System.out.println("Elements grouping by themselves: " + groupedBy);
    }
}
