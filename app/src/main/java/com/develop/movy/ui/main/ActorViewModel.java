package com.develop.movy.ui.main;

import androidx.lifecycle.LiveData;
import androidx.lifecycle.ViewModel;
import androidx.paging.LivePagedListBuilder;
import androidx.paging.PageKeyedDataSource;
import androidx.paging.PagedList;

import com.develop.movy.DataSource.ActorDataSource;
import com.develop.movy.DataSource.ActorDataSourceFactory;
import com.develop.movy.model.Actors;

public class ActorViewModel extends ViewModel {

    LiveData<PagedList<Actors>> actorsPagedList;
    private LiveData<PageKeyedDataSource<Long,Actors>> liveDataSource;

    public ActorViewModel() {
        init();
    }

    private void init() {
        ActorDataSourceFactory itemDataSourceFactory = new ActorDataSourceFactory();
        liveDataSource = itemDataSourceFactory.actorLiveDataSource;
        PagedList.Config config = new PagedList.Config.Builder()
                .setEnablePlaceholders(false)
                .setPageSize(ActorDataSource.PAGE_SIZE)
                .build();
        actorsPagedList = new LivePagedListBuilder<>(itemDataSourceFactory, config).build();

    }
}
