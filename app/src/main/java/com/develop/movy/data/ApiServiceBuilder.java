package com.develop.movy.data;


import retrofit2.Retrofit;
import retrofit2.converter.gson.GsonConverterFactory;

public class ApiServiceBuilder {
    // Base URL
    private static final String Base_URL = "https://api.themoviedb.org/3/";

    private ApiService apiService;

    private static ApiServiceBuilder INSTANCE;

    private static Retrofit retrofit = new Retrofit.Builder()
            .baseUrl(Base_URL)
            .addConverterFactory(GsonConverterFactory.create())
            .build();


    public static ApiServiceBuilder getINSTANCE() {
        if (null == INSTANCE) {
            INSTANCE = new ApiServiceBuilder();
        }
        return INSTANCE;
    }

    public static <T> T buildService(Class<T> type)
    {
        return retrofit.create(type);
    }
}
