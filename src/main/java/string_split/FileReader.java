package string_split;

import java.io.BufferedReader;
import java.io.IOException;
import java.util.LinkedList;
import java.util.List;
import java.util.Objects;

public class FileReader {
    public static List<String> readFile(String file) throws IOException {
        List<String> lines = new LinkedList<>();
        try (BufferedReader bufferedReader = new BufferedReader(new java.io.FileReader(file))) {
            String line;
            while (Objects.nonNull(line = bufferedReader.readLine())) {
                lines.add(line);
            }
            return lines;
        } catch (IOException ioe) {
            System.out.println("Error reading file: " + file + " ." + ioe);
            throw ioe;
        }
    }
}
