package com.develop.movy.data;

import com.develop.movy.pojo.PopularPeople;

import retrofit2.Call;
import retrofit2.http.GET;
import retrofit2.http.Query;

public interface ApiService {

    @GET("person/popular")
    Call<PopularPeople> getPopularPeople(@Query("api_key") String api_key,
                                         @Query("language") String language,
                                         @Query("page") long page
    );
}
