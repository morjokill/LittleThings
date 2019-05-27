package little.things.utils.url;

import java.io.IOException;
import java.net.InetSocketAddress;
import java.net.Socket;
import java.net.URL;

public class UrlChecker {
    public static boolean isUrlReachable(String url, int timeoutMs, int defaultPort) {
        try (Socket socket = new Socket()) {
            URL urlInstance = new URL(url);
            String host = urlInstance.getHost();
            int port = urlInstance.getPort() == -1 ? defaultPort : urlInstance.getPort();

            socket.connect(new InetSocketAddress(host, port), timeoutMs);
            return true;
        } catch (IOException e) {
            return false;
        }
    }

    public static void main(String[] args) {
        System.out.println(isUrlReachable("https://google.com", 200, 80));
    }
}
