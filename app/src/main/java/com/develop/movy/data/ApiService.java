package com.develop.movy.data;

import com.develop.movy.model.ActorResource;
import com.develop.movy.model.PopularPeople;

import retrofit2.Call;
import retrofit2.http.GET;
import retrofit2.http.Path;
import retrofit2.http.Query;

public interface ApiService {

    @GET("person/popular")
    Call<PopularPeople> getPopularPeople(@Query("api_key") String api_key,
                                         @Query("language") String language,
                                         @Query("page") long page
    );

    @GET("person/{person_id}/images")
    public Call<ActorResource> getActorImages(@Path("person_id") Integer personId,
                                             @Query("api_key") String api_key
    );
}
