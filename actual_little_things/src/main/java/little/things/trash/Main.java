package little.things.trash;

import java.io.FileNotFoundException;

public class Main {
    public static void main(String[] args) throws FileNotFoundException {
        String line = new String(new byte[]{50, 53, 48, 50, 55, 48, 55, 48, 48, 50, 49, 55, 52, 51, (byte) 0xF0});
        System.out.println(line);
    }
}
