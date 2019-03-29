package mqtt.example.utils;

import java.io.BufferedReader;
import java.io.FileInputStream;
import java.io.IOException;
import java.io.InputStreamReader;

public class FileReader {
    public static String readFile(String fileName) {
        StringBuilder builder = new StringBuilder();
        try (BufferedReader bufferedReader = new BufferedReader(new InputStreamReader(new FileInputStream(fileName)))) {
            String line;
            while ((line = bufferedReader.readLine()) != null) builder.append(line);
        } catch (IOException ioe) {
            System.out.println("Exception while reading file: " + fileName + ". " + ioe.getMessage());
        }
        return builder.toString();
    }
}
