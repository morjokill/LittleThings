package little.things.streams;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;

public class CollectionKeeper {
    public static List<String> getCollection() {
        return new ArrayList<String>() {{
            add("a");
            add("b");
            add("c");
            add("c");
        }};
    }

    public static List<String> getReversedCollection() {
        return new ArrayList<String>() {{
            add("c");
            add("b");
            add("a");
        }};
    }

    public static List<Integer> getIntCollection() {
        return new ArrayList<Integer>() {{
            add(3);
            add(5);
            add(1329);
        }};
    }
}
