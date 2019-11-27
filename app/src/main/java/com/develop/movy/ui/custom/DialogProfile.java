package com.develop.movy.ui.custom;

import android.app.Dialog;
import android.content.Context;
import android.graphics.Color;
import android.graphics.drawable.ColorDrawable;
import android.os.Bundle;
import android.view.View;
import android.view.WindowManager;
import android.widget.Button;
import android.widget.ImageView;

import androidx.annotation.NonNull;

import com.bumptech.glide.Glide;
import com.bumptech.glide.request.RequestOptions;
import com.develop.movy.R;
import com.develop.movy.model.Profiles;
import com.develop.movy.utils.Image;

public class DialogProfile extends Dialog implements View.OnClickListener {

    String profileUrl;
    private ImageView actorProfile;
    private Button btCancle;
    private Button btSave;
    Profiles profiles;
    private Image image;
    private String imageUrl;


    public DialogProfile(@NonNull Context context, Profiles profiles) {
        super(context);
        this.profiles = profiles;
    }

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        this.getWindow().setBackgroundDrawable(new ColorDrawable(Color.TRANSPARENT));
        this.setCancelable(true);
        this.setContentView(R.layout.dialog_profile);
        getWindow().setLayout(WindowManager.LayoutParams.MATCH_PARENT, WindowManager.LayoutParams.WRAP_CONTENT);

        actorProfile = findViewById(R.id.Card_profile);
        btCancle = findViewById(R.id.bt_cancle);
        btSave = findViewById(R.id.bt_save);

        image = new Image(profiles.filePath);
        imageUrl = image.getOrignalImagePath();
        Glide.with(getContext()).load(imageUrl).apply(new RequestOptions().error(R.drawable.ic_launcher_background))
                .into(actorProfile);

        btSave.setOnClickListener(this);
        btCancle.setOnClickListener(this);
    }

    @Override
    public void onClick(View view) {
        switch (view.getId()) {
            case R.id.bt_cancle:
                dismiss();
                break;
            case R.id.bt_save:
                Image.downloadImage(actorProfile);
                break;


        }
    }


}
