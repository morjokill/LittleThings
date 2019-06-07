package little.things.optional.object_methods;

import java.util.Optional;

public class IsPresent {
    public static void main(String[] args) {
        //noinspection ConstantConditions
        System.out.println("Is value present: " + Optional.of("Such string").isPresent());
        //noinspection ConstantConditions
        System.out.println("Is value present: " + Optional.empty().isPresent());
    }
}
