package little.things.generics;

public class CovariantArrays {
    public static void main(String[] args) {
        Object[] objects = new Integer[5];
        objects[0] = 1;

        try {
            //It's compiling but exception in runtime
            objects[1] = "String?";
        } catch (ArrayStoreException ase) {
            System.out.println("Wow what a surprise! " + ase);
        }
    }
}
