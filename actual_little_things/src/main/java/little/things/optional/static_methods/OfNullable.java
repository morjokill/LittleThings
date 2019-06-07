package little.things.optional.static_methods;

import java.util.Optional;

public class OfNullable {
    public static void main(String[] args) {
        System.out.println("Putting string value into optional");
        Optional<String> string = Optional.ofNullable("Such string");
        System.out.println("Getting value: " + string.get());

        System.out.println("Putting null value");
        string = Optional.ofNullable(null);
        System.out.println("Getting value. Should throw exception");
        //noinspection ConstantConditions
        System.out.println(string.get());
    }
}
