package little.things.interfaces.default_methods;

public interface DefaultMethod {
    default void printValue(String value) {
        System.out.println(value);
    }
}
