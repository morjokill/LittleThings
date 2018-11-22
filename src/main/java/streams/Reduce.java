package streams;

public class Reduce {
    public static void main(String[] args) {
        System.out.println(CollectionKeeper.getCollection().stream().reduce(String::concat).toString());
    }
}
