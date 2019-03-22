import api.LocalhostApi;
import converter.Converter;
import factory.ServiceFactory;
import retrofit2.Call;
import service.LocalhostService;

import java.io.IOException;
import java.net.URL;

public class Main {
    public static void main(String[] args) throws IOException {
        String urlLine = "http://localhost:8080/";
        checkUrl(urlLine);
        LocalhostApi apiInstance = ServiceFactory.buildService(urlLine);
        LocalhostService service = new LocalhostService(apiInstance);
        Call<String> serviceCall = service.getHi();
        String message = Converter.convertCall(serviceCall);
        System.out.println(message);
    }

    private static void checkUrl(String urlLine) throws IOException {
        URL url;
        try {
            url = new URL(urlLine);
            url.openConnection().connect();
        } catch (IOException ioe) {
            throw new IOException("URL is not accessible. " + ioe);
        }
    }
}
