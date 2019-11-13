package little.things.acmp.utils;

import java.io.FileWriter;
import java.io.IOException;
import java.io.PrintWriter;
import java.util.List;

public class OutputUtil {
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

    public static <T> void writeAllOutputNumbersGeneric(String filePath, List<T[]> allOutputNumbers) {
        StringBuilder output = new StringBuilder();
        for (int i = 0; i < allOutputNumbers.size(); i++) {
            T[] outputNumbers = allOutputNumbers.get(i);
            StringBuilder outputLine = new StringBuilder();
            for (T outputNumber : outputNumbers) {
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
