package little.things.streams;

public class Reduce {
    public static void main(String[] args) {
        System.out.println(CollectionKeeper.getCollection().stream().reduce(String::concat).toString());
        System.out.println(CollectionKeeper.getIntCollection().stream().reduce(Integer::sum).toString());
    }
}
