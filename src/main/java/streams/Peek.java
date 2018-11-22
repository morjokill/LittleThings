package streams;

import java.util.stream.Collectors;

public class Peek {
    public static void main(String[] args) {
        System.out.println(CollectionKeeper.getCollection().stream().peek(System.out::println).collect(Collectors.toList()));
    }
}
