package little.things.streams;

import java.util.List;
import java.util.stream.Collectors;

public class Skip {
    public static void main(String[] args) {
        List<String> stringList = CollectionKeeper.getCollection();
        int skipNumber = 2;
        List<String> skippedList = stringList.stream().skip(skipNumber).collect(Collectors.toList());
        System.out.println(skippedList);
    }
}
