package little.things.annotations;

public class AnnotationKeeper {
    @AnnotationWithValueExample(100)
    private static void printSomething() {
        System.out.println("Something");
    }

    @AnnotationExample
    private static void printMeow() {
        System.out.println("Meow");
    }
}
