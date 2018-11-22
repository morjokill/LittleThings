package ParseByteString;

import java.nio.ByteBuffer;
import java.util.Arrays;

public class Main {
    public static void main(String[] args) {
        String line = "ABiyAAAADm8=";
        System.out.println("Line CHARS: " + line);
        byte[] byteLine;
        byteLine = line.getBytes();
        System.out.println("Bytes: " + Arrays.toString(byteLine));

        int number = 123;
        System.out.println("Number: " + number);
        String lineOfNumber = Integer.toHexString(number);
        System.out.println("Line: " + lineOfNumber);
        int lineValue = Integer.valueOf(lineOfNumber, 16);
        System.out.println("Back: " + lineValue);

        byte[] byteArray = new byte[]{65, 64, 23, 12};
        System.out.println(ByteBuffer.wrap(byteArray).getInt());
    }
}
