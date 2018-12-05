package streams;

import java.util.List;
import java.util.stream.Collectors;

public class Map {
    public static void main(String[] args) {
        List<String> list = CollectionKeeper.getCollection();
        System.out.println(list);
        System.out.println(list.stream().map((line)->line + "_mapped").collect(Collectors.toList()));
    }
}
