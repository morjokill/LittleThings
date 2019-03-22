package api;

import retrofit2.Call;
import retrofit2.http.GET;

public interface LocalhostApi {
    @GET("hi")
    Call<String> getHi();
}
