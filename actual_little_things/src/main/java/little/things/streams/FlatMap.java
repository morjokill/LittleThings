package little.things.streams;

import java.util.Arrays;
import java.util.List;
import java.util.Map;
import java.util.stream.Collectors;

import static java.util.stream.Collectors.groupingBy;
import static java.util.stream.Collectors.toList;

public class FlatMap {
    public static void main(String[] args) {
        List<String> list = CollectionKeeper.getCollection();
        System.out.println("All elements: " + list);

        Map<String, List<String>> stringListMap = mapWordsByFirstSymbol();
        List<String> unfolded = list.stream()
                .flatMap(s -> stringListMap.get(s).stream())
                .collect(toList());
        System.out.println(unfolded);
    }

    private static Map<String, List<String>> mapWordsByFirstSymbol() {
        List<String> list = Arrays.asList("array", "alpha", "air", "book", "bean", "block", "clone", "circle", "consequence");
        return list.stream().collect(groupingBy(o -> String.valueOf(o.charAt(0))));
    }
}
