package little.things.optional.object_methods;

import java.util.Optional;

public class Get {
    public static void main(String[] args) {
        //noinspection ConstantConditions
        System.out.println("String value: " + Optional.of("Such string").get());
    }
}
