package little.things.streams;

import com.google.common.collect.Sets;

import java.util.HashSet;
import java.util.List;
import java.util.Set;
import java.util.stream.Collectors;

import static java.util.stream.Collectors.toList;

public class Filter {
    public static void main(String[] args) {
        List<String> list = CollectionKeeper.getCollection();
        System.out.println("Before filter: " + list);

        Set<String> allowedSymbols = Sets.newHashSet("a", "b");
        List<String> filtered = list.stream()
                .filter(allowedSymbols::contains)
                .collect(toList());
        System.out.println("Filtered: " + filtered);
    }
}
