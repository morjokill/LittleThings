package little.things.streams;

import java.util.List;
import java.util.stream.Collectors;

import static java.util.stream.Collectors.toList;

public class Skip {
    public static void main(String[] args) {
        List<String> list = CollectionKeeper.getCollection();
        System.out.println("All elements: " + list);

        int skipNumber = 2;
        List<String> leftElements = list.stream()
                .skip(skipNumber)
                .collect(toList());
        System.out.println("List after skip: " + leftElements);
    }
}
