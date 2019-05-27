package jersey.client.example;

import javax.ws.rs.client.Client;
import javax.ws.rs.client.ClientBuilder;
import javax.ws.rs.client.Entity;
import javax.ws.rs.client.Invocation;
import javax.ws.rs.core.Response;

import static javax.ws.rs.core.Response.Status.GATEWAY_TIMEOUT;

public class ClientExample {
    private static Client client;
    private final static String CONNECTION_TIMEOUT_STRING = "jersey.config.client.connectTimeout";
    private final static String READ_TIMEOUT_STRING = "jersey.config.client.readTimeout";

    public static Response request(String body, String link,
                                   String contentType, String accept) {
        System.out.println("Sending request to: '" + link + "' with body: " + body);
        try {
            Client client = ClientExample.client;
            Invocation.Builder builder = client
                    .target(link)
                    .request()
                    .header("Content-Type", contentType);

            if (accept != null) {
                builder.header("Accept", accept);
            }

            return builder.post(Entity.entity(body, contentType));
        } catch (Exception e) {
            System.out.println("Client exception: " + e);
            return Response.status(GATEWAY_TIMEOUT).build();
        }
    }

    public static void initClient(int connectionTimeoutMs, int readTimeoutMs) {
        System.out.println("Initializing xml client");
        client = ClientBuilder
                .newClient()
                .property(CONNECTION_TIMEOUT_STRING, connectionTimeoutMs)
                .property(READ_TIMEOUT_STRING, readTimeoutMs);
    }

    public static void close() {
        System.out.println("Closing XmlClient");
        if (null != client) {
            client.close();
        }
        System.out.println("Client closed");
    }

    public static void main(String[] args) {
        ClientExample.initClient(1000, 5000);
        Response wow = ClientExample.request("wow", "https://google.com", "plain/text", null);
        System.out.println(wow);
        String x = wow.readEntity(String.class);
        System.out.println(x);
        ClientExample.close();
    }
}
