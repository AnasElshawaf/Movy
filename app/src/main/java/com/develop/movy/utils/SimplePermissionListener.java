package com.develop.movy.utils;

import com.develop.movy.ui.main.ActorsView;
import com.karumi.dexter.PermissionToken;
import com.karumi.dexter.listener.PermissionDeniedResponse;
import com.karumi.dexter.listener.PermissionGrantedResponse;
import com.karumi.dexter.listener.PermissionRequest;
import com.karumi.dexter.listener.single.PermissionListener;

public class SimplePermissionListener implements PermissionListener {

    private final ActorsView actorsView;

    public SimplePermissionListener(ActorsView actorsView) {
        this.actorsView = actorsView;
    }

    @Override
    public void onPermissionGranted(PermissionGrantedResponse response) {

        actorsView.showPermissionGranted(response.getPermissionName());
    }

    @Override
    public void onPermissionDenied(PermissionDeniedResponse response) {

        if (response.isPermanentlyDenied()) {
            actorsView.handlePermenentDeniedPermission(response.getPermissionName());
            return;
        }
        actorsView.showPermissionDenied(response.getPermissionName());

    }

    @Override
    public void onPermissionRationaleShouldBeShown(PermissionRequest permission, PermissionToken token) {

        actorsView.showPermissionRational(token);
    }
}