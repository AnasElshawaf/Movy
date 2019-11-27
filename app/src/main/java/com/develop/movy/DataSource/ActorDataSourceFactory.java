package com.develop.movy.DataSource;

import androidx.lifecycle.MutableLiveData;
import androidx.paging.DataSource;
import androidx.paging.PageKeyedDataSource;

import com.develop.movy.model.Actors;

public class ActorDataSourceFactory extends DataSource.Factory {
    public MutableLiveData<PageKeyedDataSource<Long,Actors>> actorLiveDataSource = new MutableLiveData<>();

    @Override
    public DataSource<Long, Actors> create() {
        ActorDataSource actorDataSource = new ActorDataSource();
        actorLiveDataSource.postValue(actorDataSource);
        return actorDataSource;
    }

    //getter for itemlivedatasource
    public MutableLiveData<PageKeyedDataSource<Long, Actors>> getItemLiveDataSource() {
        return actorLiveDataSource;
    }
}
