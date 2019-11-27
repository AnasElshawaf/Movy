package com.develop.movy.ui.main;

import android.Manifest;
import android.content.DialogInterface;
import android.content.Intent;
import android.net.Uri;
import android.os.Bundle;
import android.provider.Settings;
import android.widget.ImageView;

import androidx.appcompat.app.AlertDialog;
import androidx.appcompat.app.AppCompatActivity;
import androidx.lifecycle.Observer;
import androidx.lifecycle.ViewModelProviders;
import androidx.paging.PagedList;
import androidx.recyclerview.widget.GridLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import com.develop.movy.R;
import com.develop.movy.model.Actors;
import com.develop.movy.ui.PersonInfo.ProfileInfoView;
import com.develop.movy.utils.SimplePermissionListener;
import com.karumi.dexter.Dexter;
import com.karumi.dexter.PermissionToken;

public class ActorsView extends AppCompatActivity {

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
        Dexter.withActivity(ActorsView.this).withPermission(Manifest.permission.WRITE_EXTERNAL_STORAGE).withListener(simplePermissionListener).check();
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
                startActivity(new Intent(ActorsView.this, ProfileInfoView.class).putExtra("actorInfo", item));
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
