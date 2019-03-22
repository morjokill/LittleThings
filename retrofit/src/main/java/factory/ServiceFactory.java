package factory;

import api.LocalhostApi;
import retrofit2.Retrofit;
import retrofit2.converter.scalars.ScalarsConverterFactory;

public class ServiceFactory {
    public static LocalhostApi buildService(String url) {
        Retrofit retrofit = buildRetrofit(url);
        return createApiInstance(retrofit);
    }

    private static Retrofit buildRetrofit(String url) {
        return new Retrofit.Builder()
                .baseUrl(url)
                .addConverterFactory(ScalarsConverterFactory.create())
                .build();
    }

    private static LocalhostApi createApiInstance(Retrofit retrofit) {
        return retrofit.create(LocalhostApi.class);
    }
}
