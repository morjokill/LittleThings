package little.things.optional.static_methods;

import java.util.Optional;

public class Of {
    public static void main(String[] args) {
        System.out.println("Putting string value into optional");
        //noinspection ResultOfMethodCallIgnored
        Optional.of("Such string");

        System.out.println("Putting null value. Should throw exception");
        //noinspection ResultOfMethodCallIgnored
        Optional.of(null);
    }
}
