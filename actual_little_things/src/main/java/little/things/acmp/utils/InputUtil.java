package little.things.acmp.utils;

import java.io.IOException;
import java.nio.file.Files;
import java.nio.file.Paths;
import java.util.LinkedList;
import java.util.List;
import java.util.stream.Stream;

import static java.nio.charset.StandardCharsets.UTF_8;

public class InputUtil {
    public static String readFile(String filePath) {
        StringBuilder sb = new StringBuilder();
        try (Stream<String> stream = Files.lines(Paths.get(filePath), UTF_8)) {
            stream.forEach(s -> {
                sb.append(s);
                sb.append("\r\n");
            });
        } catch (IOException ioe) {
            System.out.println("Could not read file: " + filePath +
                    " due to exception: " + ioe);
        }
        return sb.toString();
    }

    public static int[] getInputNumbersFromLine(String line, String divider) {
        String[] lineParts = line.split(divider);
        int[] numbers = new int[lineParts.length];
        for (int i = 0; i < numbers.length; i++) {
            numbers[i] = Integer.parseInt(lineParts[i]);
        }
        return numbers;
    }

    public static List<int[]> getAllInputNumbers(String filePath) {
        List<int[]> inputNumbersList = new LinkedList<>();
        String input = readFile(filePath);
        String[] split = input.split("\r\n");
        for (String line : split) {
            int[] numbers = getInputNumbersFromLine(line, " ");
            inputNumbersList.add(numbers);
        }
        return inputNumbersList;
    }
}
