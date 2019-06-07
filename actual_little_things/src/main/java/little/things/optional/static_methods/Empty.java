package little.things.optional.static_methods;

import java.util.Optional;

public class Empty {
    public static void main(String[] args) {
        System.out.println("Putting empty string value to optional");
        Optional<String> empty = Optional.empty();

        System.out.println("Getting value. Should throw exception.");
        //noinspection ConstantConditions
        System.out.println(empty.get());
    }
}
