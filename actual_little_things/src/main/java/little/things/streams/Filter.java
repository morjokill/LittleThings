package little.things.streams;

import java.util.HashSet;
import java.util.List;
import java.util.Set;
import java.util.stream.Collectors;

public class Filter {
    public static void main(String[] args) {
        List<String> stringList = CollectionKeeper.getCollection();
        Set<String> allowed = new HashSet<String>() {{
            add("a");
            add("b");
        }};
        List<String> newList = stringList.stream().filter(allowed::contains).collect(Collectors.toList());
        System.out.println(newList);
    }
}
