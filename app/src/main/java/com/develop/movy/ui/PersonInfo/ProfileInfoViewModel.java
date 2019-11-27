package com.develop.movy.ui.PersonInfo;

import android.util.Log;

import androidx.lifecycle.MutableLiveData;
import androidx.lifecycle.ViewModel;

import com.develop.movy.data.ApiService;
import com.develop.movy.data.ApiServiceBuilder;
import com.develop.movy.model.ActorResource;
import com.develop.movy.model.Profiles;

import java.util.List;

import retrofit2.Call;
import retrofit2.Callback;
import retrofit2.Response;

public class ProfileInfoViewModel extends ViewModel {

    MutableLiveData<List<Profiles>> mutableLiveData = new MutableLiveData<>();
    public static final String API_KEY = "2b52898e5f0c1cfc27c940eecb94ea80";

    public void getProfiles(Integer id) {
        ApiServiceBuilder.buildService(ApiService.class).getActorImages(id, API_KEY).enqueue(new Callback<ActorResource>() {
            @Override
            public void onResponse(Call<ActorResource> call, Response<ActorResource> response) {
                if (response.body() != null) {
                    Log.e("response", response.body().toString());
                    mutableLiveData.setValue(response.body().profiles);
                }
            }

            @Override
            public void onFailure(Call<ActorResource> call, Throwable t) {

            }
        });
    }
}
