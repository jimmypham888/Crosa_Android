package com.example.apple.croasa.network;

public class ApiUltis {

    private ApiUltis() {

    }

    private static final String BASE_URL = "http://45.124.94.45:9090/";

    public static APIService getApiService () {
        return RetrofitClient.getClient(BASE_URL).create(APIService.class);
    }
}
