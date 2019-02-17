package api_json;

import com.google.gson.JsonElement;
import com.google.gson.JsonObject;
import com.google.gson.JsonParser;

import java.io.FileWriter;
import java.io.IOException;
import java.io.InputStream;
import java.io.InputStreamReader;
import java.net.URL;
import java.net.URLConnection;
import java.util.Arrays;

public class OpenKazan {
    public static void main(String[] args) {
        double[] dost = new double[]{55.787227, 49.160156};
        double[] centre = new double[]{55.787489, 49.122289};

        try {
            initFile("2754", dost);//30 //55.787227, 49.160156 DOSTOEVSKOGO
            initFile("2435", centre);//30 //55.787489, 49.122289 BAUMANA
            initFile("11934", centre);//90 //55.787489, 49.122289 BAUMANA
            initFile("16638", centre);//35A //55.787489, 49.122289 BAUMANA
        } catch (Exception e) {
            System.out.println("Went wrong: " + e);
        }

        while (true) {
            try {
                getCordsForBus("2754", dost);
                getCordsForBus("2435", centre);
                getCordsForBus("11934", centre);
                getCordsForBus("16638", centre);
                Thread.sleep(60000);
            } catch (Exception e) {
                System.out.println("Went wrong: " + e);
            }
        }
    }

    private static void initFile(String busNum, double[] rootCord) throws IOException {
        String text = "Bus number: " + busNum + ", root cords: " + Arrays.toString(rootCord);
        writeToFile("src/main/java/api_json/" + busNum + ".txt", text);
    }

    private static void getCordsForBus(String busNum, double[] rootCord) throws IOException {
        JsonElement root = getJsonFromURL("http://data.kzn.ru:8082/api/v0/dynamic_datasets/bus/" + busNum + ".json");
        double[] cords = getCoordinates(root);
        double dist = getDistAB(rootCord[0], rootCord[1], cords[0], cords[1]);
        String text = Arrays.toString(cords) + " " + dist;
        writeToFile("src/main/java/api_json/" + busNum + ".txt", text);
    }

    private static JsonElement getJsonFromURL(String sUrl) throws IOException {
        URL url = new URL(sUrl);
        URLConnection connection = url.openConnection();
        connection.connect();

        JsonParser parser = new JsonParser();
        JsonElement json = parser.parse(new InputStreamReader((InputStream) connection.getContent()));
        return json;
    }

    private static double[] getCoordinates(JsonElement jsonElement) {
        JsonObject root = jsonElement.getAsJsonObject();
        JsonObject data = root.get("data").getAsJsonObject();
        JsonElement latJ = data.get("Latitude");
        JsonElement lonJ = data.get("Longitude");
        double lon = lonJ.getAsDouble();
        double lat = latJ.getAsDouble();
        return new double[]{lat, lon};
    }

    private static void writeToFile(String file, String text) throws IOException {
        try (FileWriter fileWriter = new FileWriter(file, true)) {
            fileWriter.append(text).append("\n");
        }
    }

    private static double getDistAB(double Ax, double Ay, double Bx, double By) {
        return Math.sqrt((Ax - Bx) * (Ax - Bx) + (Ay - By) * (Ay - By));
    }
}
