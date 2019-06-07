package little.things.streams;

import java.util.List;

public class AnyMatch {
    public static void main(String[] args) {
        List<String> list = CollectionKeeper.getCollection();
        System.out.println("All elements: " + list);

        System.out.println("Is any string empty? " + list.stream().anyMatch(String::isEmpty));
        System.out.println("Does any string contain 'a' character? " + list.stream().anyMatch(s -> s.contains("a")));
    }
}
