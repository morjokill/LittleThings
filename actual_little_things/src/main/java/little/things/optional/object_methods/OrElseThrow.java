package little.things.optional.object_methods;

import com.sun.org.apache.xerces.internal.dom.AbortException;

import java.util.Optional;

public class OrElseThrow {
    public static void main(String[] args) {
        System.out.println("String value: " + Optional.of("Such string").orElseThrow(AbortException::new));

        System.out.println("Empty optional. Should throw exception.");
        //noinspection ConstantConditions
        System.out.println(Optional.empty().orElseThrow(AbortException::new));
    }
}
