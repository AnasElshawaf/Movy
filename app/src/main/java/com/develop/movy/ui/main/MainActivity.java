package com.develop.movy.ui.main;

import android.Manifest;
import android.content.DialogInterface;
import android.content.Intent;
import android.net.Uri;
import android.os.Bundle;
import android.provider.Settings;
import android.view.Menu;
import android.widget.ImageView;
import android.widget.Toast;

import androidx.appcompat.app.AlertDialog;
import androidx.appcompat.app.AppCompatActivity;
import androidx.appcompat.widget.SearchView;
import androidx.lifecycle.Observer;
import androidx.lifecycle.ViewModelProviders;
import androidx.paging.PagedList;
import androidx.recyclerview.widget.GridLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import com.develop.movy.R;
import com.develop.movy.model.Actors;
import com.develop.movy.ui.PersonInfo.PersonInfoView;
import com.develop.movy.utils.SimplePermissionListener;
import com.karumi.dexter.Dexter;
import com.karumi.dexter.PermissionToken;

public class MainActivity extends AppCompatActivity {

    ActorViewModel actorViewModel;
    private RecyclerView recyclerView;
    ActorsAdapter actorsAdapter;
    private SimplePermissionListener simplePermissionListener;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        simplePermissionListener = new SimplePermissionListener(this);
        requestStoragePermission();

        setUpActorsRecyclerView();
        getAllActors();

    }

    private void requestStoragePermission() {
        Dexter.withActivity(MainActivity.this).withPermission(Manifest.permission.WRITE_EXTERNAL_STORAGE).withListener(simplePermissionListener).check();
    }

    @Override
    public boolean onCreateOptionsMenu(Menu menu) {
        // Inflate the menu; this adds items to the action bar if it is present.
        getMenuInflater().inflate(R.menu.menu, menu);
        SearchView search = (SearchView) menu.findItem(R.id.search).getActionView();
        search.setOnQueryTextListener(new SearchView.OnQueryTextListener() {
            @Override
            public boolean onQueryTextSubmit(String query) {
                if (query != null && !query.isEmpty()) {
                    Toast.makeText(MainActivity.this, query, Toast.LENGTH_SHORT).show();
                }
                return false;
            }

            @Override
            public boolean onQueryTextChange(String newText) {
                return false;
            }
        });

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
        actorsAdapter = new ActorsAdapter(new ActorsAdapter.onItemClick() {
            @Override
            public void onItemClick(Actors item, ImageView profile) {
                startActivity(new Intent(MainActivity.this, PersonInfoView.class).putExtra("actorInfo", item));
            }
        });
    }

    public void showPermissionGranted(String permissionName) {

        switch (permissionName) {

            case Manifest.permission.WRITE_EXTERNAL_STORAGE:
                break;
        }
    }

    public void showPermissionDenied(String permissionName) {

        switch (permissionName) {

            case Manifest.permission.WRITE_EXTERNAL_STORAGE:
                break;
        }
    }

    public void showPermissionRational(final PermissionToken token) {


        new AlertDialog.Builder(this).setTitle("We need this permission").
                setMessage("Please allow this permission for do download images")
                .setPositiveButton("Allow", new DialogInterface.OnClickListener() {
                    @Override
                    public void onClick(DialogInterface dialog, int which) {
                        token.continuePermissionRequest();
                        dialog.dismiss();
                    }
                })
                .setNegativeButton("Cancel", new DialogInterface.OnClickListener() {
                    @Override
                    public void onClick(DialogInterface dialog, int which) {
                        token.cancelPermissionRequest();
                        dialog.dismiss();
                    }
                })
                .setOnDismissListener(new DialogInterface.OnDismissListener() {
                    @Override
                    public void onDismiss(DialogInterface dialog) {
                        token.cancelPermissionRequest();
                    }
                }).show();

    }

    public void handlePermenentDeniedPermission(String permissionName) {

        switch (permissionName) {

            case Manifest.permission.WRITE_EXTERNAL_STORAGE:
                break;
        }
        new AlertDialog.Builder(this).setTitle("We need this permission").
                setMessage("Please allow this permission from app settings")
                .setPositiveButton("Allow", new DialogInterface.OnClickListener() {
                    @Override
                    public void onClick(DialogInterface dialog, int which) {
                        openSettings();
                        dialog.dismiss();
                    }
                })
                .setNegativeButton("Cancel", new DialogInterface.OnClickListener() {
                    @Override
                    public void onClick(DialogInterface dialog, int which) {
                        dialog.dismiss();
                    }
                }).show();


    }

    public void openSettings() {
        Intent intent = new Intent(Settings.ACTION_APPLICATION_DETAILS_SETTINGS);
        Uri uri = Uri.fromParts("package", getPackageName(), null);
        intent.setData(uri);
        startActivity(intent);
    }
}
