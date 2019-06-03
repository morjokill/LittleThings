package little.things.streams;

import java.util.List;
import java.util.Optional;

public class Reduce {
    public static void main(String[] args) {
        List<String> stringCollection = CollectionKeeper.getCollection();
        System.out.println("String elements: " + stringCollection);
        Optional<String> concat = stringCollection.stream()
                .reduce(String::concat);
        //noinspection ConstantConditions
        System.out.println("Concat result: " + concat.get());

        List<Integer> intCollection = CollectionKeeper.getIntCollection();
        System.out.println("Integer elements: " + intCollection);
        Optional<Integer> sum = intCollection.stream()
                .reduce(Integer::sum);
        //noinspection ConstantConditions
        System.out.println("Sum result: " + sum.get());
    }
}
