package streams;

public class FindAny {
    public static void main(String[] args) {
        for (int i = 0; i < 100; i++) {
            System.out.println(CollectionKeeper.getCollection().stream().findAny());
        }
    }
}
