package little.things.acmp.task15;

import java.io.FileWriter;
import java.io.IOException;
import java.io.PrintWriter;
import java.nio.file.Files;
import java.nio.file.Paths;
import java.util.LinkedList;
import java.util.List;
import java.util.stream.Stream;

import static java.nio.charset.StandardCharsets.UTF_8;

public class CitiesGraph {
    private static final String INPUT_PATH = "INPUT.TXT";
    private static final String OUTPUT_PATH = "OUTPUT.TXT";

    public static void main(String[] args) {
        List<int[]> allInputNumbers = getAllInputNumbers(INPUT_PATH);
        List<long[]> outputNumbers = new LinkedList<>();

        int[][] citiesGraph = new int[allInputNumbers.size()][allInputNumbers.size()];
        for (int i = 1; i < allInputNumbers.size(); i++) {
            citiesGraph[i - 1] = allInputNumbers.get(i);
        }

        long roadsCount = getRoadsCount(citiesGraph);
        outputNumbers.add(new long[]{roadsCount});
        writeAllOutputNumbers(OUTPUT_PATH, outputNumbers);
    }

    private static long getRoadsCount(int[][] citiesGraph) {
        int roadsCount = 0;
        for (int i = 0; i < citiesGraph.length; i++) {
            for (int j = i; j < citiesGraph[i].length; j++) {
                if (citiesGraph[i][j] == 1) {
                    roadsCount++;
                }
            }
        }

        return roadsCount;
    }

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

    public static void writeToFile(String file, String line, boolean append) {
        try (PrintWriter printWriter = new PrintWriter(new FileWriter(file, append))) {
            printWriter.append(line);
        } catch (IOException ioe) {
            System.out.println("Could not write line to file: " + ioe);
        }
    }

    public static void clearFile(String file) {
        writeToFile(file, "", false);
    }

    public static void writeAllOutputNumbers(String filePath, List<long[]> allOutputNumbers) {
        StringBuilder output = new StringBuilder();
        for (int i = 0; i < allOutputNumbers.size(); i++) {
            long[] outputNumbers = allOutputNumbers.get(i);
            StringBuilder outputLine = new StringBuilder();
            for (long outputNumber : outputNumbers) {
                outputLine.append(outputNumber).append(" ");
            }
            String trimmedLine;
            if (i != allOutputNumbers.size() - 1) {
                trimmedLine = outputLine.toString().trim() + "\r\n";
            } else {
                trimmedLine = outputLine.toString().trim();
            }
            output.append(trimmedLine);
        }
        clearFile(filePath);
        writeToFile(filePath, output.toString(), true);
    }
}
