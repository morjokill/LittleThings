package little.things.streams;

import java.util.List;
import java.util.stream.Collectors;

public class Distinct {
    public static void main(String[] args) {
        List<String> list = CollectionKeeper.getCollection();
        list.add("a");
        System.out.println(list);
        System.out.println(list.stream().distinct().collect(Collectors.toList()));
    }
}
