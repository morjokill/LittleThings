package little.things.optional.object_methods;

import java.util.Optional;

public class OrElseGet {
    public static void main(String[] args) {
        System.out.println("String value: " + Optional.of("Such string").orElseGet(OrElseGet::getDefaultString));
        System.out.println("Default string value: " + Optional.empty().orElseGet(OrElseGet::getDefaultString));
    }

    private static String getDefaultString() {
        return "Default string";
    }
}
