package little.things.utils.writer;

import java.io.IOException;
import java.io.PrintWriter;

public class FileWriter {
    public static void writeToFile(String line, String file) {
        try (PrintWriter printWriter = new PrintWriter(file, "UTF-8")) {
            printWriter.append(line);
        } catch (IOException ioe) {
            System.out.println("Could not write line to file: " + ioe);
        }
    }

    public static void main(String[] args) {
        writeToFile("русские вперед!", "actual_little_things\\src\\main\\resources\\test_write.txt");
    }
}
