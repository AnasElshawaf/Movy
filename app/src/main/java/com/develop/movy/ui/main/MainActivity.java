package com.develop.movy.ui.main;

import androidx.appcompat.app.AppCompatActivity;
import androidx.appcompat.widget.Toolbar;
import androidx.lifecycle.Observer;
import androidx.lifecycle.ViewModelProviders;
import androidx.paging.PagedList;
import androidx.recyclerview.widget.GridLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import android.os.Bundle;
import android.util.Log;
import android.view.Menu;
import android.view.View;
import android.view.Window;

import com.develop.movy.R;
import com.develop.movy.pojo.Actors;
import com.develop.movy.pojo.PopularPeople;

import java.util.List;

public class MainActivity extends AppCompatActivity {

    ActorViewModel actorViewModel;
    private RecyclerView recyclerView;
    final ActorsAdapter actorsAdapter = new ActorsAdapter();

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        setUpActorsRecyclerView();
        getAllActors();

    }

    @Override
    public boolean onCreateOptionsMenu(Menu menu) {
        // Inflate the menu; this adds items to the action bar if it is present.
        getMenuInflater().inflate(R.menu.menu, menu);
        return true;
    }

    private void getAllActors() {
        actorViewModel = ViewModelProviders.of(this).get(ActorViewModel.class);
        actorViewModel.actorsPagedList.observe(this, new Observer<PagedList<Actors>>() {
            @Override
            public void onChanged(PagedList<Actors> actors) {

                actorsAdapter.submitList(actors);

            }
        });
        recyclerView.setAdapter(actorsAdapter);
    }

    private void setUpActorsRecyclerView() {
        recyclerView = findViewById(R.id.recyclerview);
        recyclerView.setHasFixedSize(true);
        recyclerView.setLayoutManager(new GridLayoutManager(this, 2));
    }
}
