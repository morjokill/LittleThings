package streams;

import java.util.List;
import java.util.stream.Collectors;

public class Sorted {
    public static void main(String[] args) {
        List<String> list = CollectionKeeper.getReversedCollection().stream().sorted(String::compareTo).collect(Collectors.toList());
        System.out.println(list);
    }
}
