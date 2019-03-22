import com.fasterxml.jackson.databind.ObjectMapper;
import model.TimeModel;
import okhttp3.*;

import java.io.IOException;

public class Main {
    private static final OkHttpClient client = new OkHttpClient();

    public static void main(String[] args) throws IOException {
        String url = "http://worldtimeapi.org/api/timezone/Europe/Moscow";
        Response moscowTime = sendGetRequest(url);
        ResponseBody body = moscowTime.body();
        if (null != body) {
            String bodyValue = body.string();
            System.out.println(bodyValue);
            System.out.println(mapJsonWithModel(bodyValue));
        } else {
            System.out.println("ResponseBody is NULL");
        }
    }

    private static Response sendGetRequest(String url) throws IOException {
        Request request = new Request.Builder()
                .url(url)
                .build();

        return client.newCall(request).execute();
    }

    private static TimeModel mapJsonWithModel(String bodyValue) throws IOException {
        ObjectMapper objectMapper = new ObjectMapper();
        return objectMapper.readValue(bodyValue, TimeModel.class);
    }

    private static Response sendPostRequest(String url, String json) throws IOException {
        RequestBody body = RequestBody.create(MediaType.get("application/json; charset=utf-8"), json);
        Request request = new Request.Builder()
                .url(url)
                .post(body)
                .build();

        return client.newCall(request).execute();
    }
}
