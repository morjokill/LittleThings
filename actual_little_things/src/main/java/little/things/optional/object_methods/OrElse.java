package little.things.optional.object_methods;

import java.util.Optional;

public class OrElse {
    public static void main(String[] args) {
        System.out.println("Returning default value: " + Optional.empty().orElse("Default string :p"));
    }
}
