package little.things.streams;

import java.util.List;

public class AllMatch {
    public static void main(String[] args) {
        List<String> list = CollectionKeeper.getCollection();
        System.out.println("All elements: " + list);

        System.out.println("Are all strings empty? " + list.stream().allMatch(String::isEmpty));
        System.out.println("Do all strings contain 'a' character? " + list.stream().allMatch(s -> s.contains("a")));
    }
}
