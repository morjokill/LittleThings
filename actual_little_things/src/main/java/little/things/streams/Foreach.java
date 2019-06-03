package little.things.streams;

import java.util.List;
import java.util.stream.Collectors;

public class Foreach {
    public static void main(String[] args) {
        List<String> list = CollectionKeeper.getCollection();
        System.out.println("All elements: " + list);

        String suchString = "such string";
        System.out.println("Elements contained in '" + suchString + "': ");
        list.stream()
                .filter(suchString::contains)
                .forEach(System.out::println);
    }
}
