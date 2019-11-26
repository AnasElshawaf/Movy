package com.develop.movy.DataSource;

import android.util.Log;

import androidx.annotation.NonNull;
import androidx.paging.PageKeyedDataSource;

import com.develop.movy.data.ApiService;
import com.develop.movy.data.ApiServiceBuilder;
import com.develop.movy.pojo.Actors;
import com.develop.movy.pojo.PopularPeople;

import java.util.List;

import retrofit2.Call;
import retrofit2.Callback;
import retrofit2.Response;

public class ActorDataSource extends PageKeyedDataSource<Long, Actors> {
    public static int PAGE_SIZE = 6;
    public static long FIRST_PAGE = 1;
    public static final String API_KEY = "2b52898e5f0c1cfc27c940eecb94ea80";


    @Override
    public void loadInitial(@NonNull LoadInitialParams<Long> params, @NonNull final LoadInitialCallback<Long, Actors> callback) {
        ApiService service = ApiServiceBuilder.buildService(ApiService.class);
        Call<PopularPeople> call = service.getPopularPeople(API_KEY, "en-US", FIRST_PAGE);

        call.enqueue(new Callback<PopularPeople>() {
            @Override
            public void onResponse(Call<PopularPeople> call, Response<PopularPeople> response) {
                if (response.body().getActorsList() != null)
                    callback.onResult(response.body().getActorsList(), null, FIRST_PAGE + 1);

            }

            @Override
            public void onFailure(Call<PopularPeople> call, Throwable t) {
                Log.e("error",t.getMessage());

            }
        });
    }

    @Override
    public void loadBefore(@NonNull final LoadParams<Long> params, @NonNull final LoadCallback<Long, Actors> callback) {
        ApiService apiService = ApiServiceBuilder.buildService(ApiService.class);
        Call<PopularPeople> call = apiService.getPopularPeople(API_KEY, "en-US", params.key);
        call.enqueue(new Callback<PopularPeople>() {
            @Override
            public void onResponse(Call<PopularPeople> call, Response<PopularPeople> response) {
                if (response.body() != null) {
                    List<Actors> actorsList = response.body().getActorsList();
                    long key;
                    if (params.key > 1) {
                        key = params.key - 1;
                    } else {
                        key = 0;
                    }
                    callback.onResult(actorsList, key);

                }
            }

            @Override
            public void onFailure(Call<PopularPeople> call, Throwable t) {

            }
        });
    }

    @Override
    public void loadAfter(@NonNull final LoadParams<Long> params, @NonNull final LoadCallback<Long, Actors> callback) {

        ApiService apiService = ApiServiceBuilder.buildService(ApiService.class);
        Call<PopularPeople> call = apiService.getPopularPeople(API_KEY, "en-US", params.key);

        call.enqueue(new Callback<PopularPeople>() {
            @Override
            public void onResponse(Call<PopularPeople> call, Response<PopularPeople> response) {
                if (response.body() != null) {
                    List<Actors> actorsList = response.body().getActorsList();
                    callback.onResult(actorsList, params.key + 1);
                }
            }

            @Override
            public void onFailure(Call<PopularPeople> call, Throwable t) {

            }
        });
    }
}
