package converter;

import retrofit2.Call;
import retrofit2.Response;

import java.io.IOException;

public class Converter {
    public static String convertCall(Call<String> call) throws IOException {
        return getMessageFromCall(call);
    }

    private static String getMessageFromCall(Call<String> call) throws IOException {
        Response<String> execute = call.execute();
        return execute.body();
    }
}
