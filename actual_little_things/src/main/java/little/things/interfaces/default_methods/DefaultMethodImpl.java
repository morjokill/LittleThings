package little.things.interfaces.default_methods;

public class DefaultMethodImpl implements DefaultMethod {
    public static void main(String[] args) {
        DefaultMethod defaultMethod = new DefaultMethodImpl();
        defaultMethod.printValue("Print me!");
    }
}
