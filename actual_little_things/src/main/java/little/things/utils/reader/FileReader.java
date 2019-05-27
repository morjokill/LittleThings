package little.things.utils.reader;

import com.google.common.base.Charsets;

import java.io.IOException;
import java.nio.file.Files;
import java.nio.file.Paths;
import java.util.stream.Stream;

public class FileReader {
    public static String readFile(String filePath) {
        StringBuilder sb = new StringBuilder();
        try (Stream<String> stream = Files.lines(Paths.get(filePath), Charsets.UTF_8)) {
            stream.forEach(sb::append);
        } catch (IOException ioe) {
            System.out.println("Could not read file: " + filePath +
                    " due to exception: " + ioe);
        }
        return sb.toString();
    }

    public static void main(String[] args) {
        System.out.println(readFile("actual_little_things\\src\\main\\resources\\test_read.txt"));
    }
}
