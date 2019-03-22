package service;

import api.LocalhostApi;
import retrofit2.Call;

public class LocalhostService {
    private LocalhostApi api;

    public LocalhostService(LocalhostApi api) {
        this.api = api;
    }

    public Call<String> getHi() {
        return api.getHi();
    }
}
