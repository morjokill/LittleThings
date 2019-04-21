package little.things.objects;

import java.util.Objects;

public class NonNull {
    public static void main(String[] args) {
        System.out.println(Objects.requireNonNull(returnNonNull()));
        System.out.println(Objects.requireNonNull(returnNull()));
    }

    private static String returnNull() {
        return null;
    }

    private static String returnNonNull() {
        return "Non null string";
    }
}
