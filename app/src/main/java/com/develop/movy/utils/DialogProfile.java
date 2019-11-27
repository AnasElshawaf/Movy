package com.develop.movy.utils;

import android.app.Dialog;
import android.content.Context;
import android.content.Intent;
import android.graphics.Bitmap;
import android.graphics.Color;
import android.graphics.drawable.BitmapDrawable;
import android.graphics.drawable.ColorDrawable;
import android.net.Uri;
import android.os.Bundle;
import android.os.Environment;
import android.view.View;
import android.view.WindowManager;
import android.widget.Button;
import android.widget.ImageView;
import android.widget.Toast;

import androidx.annotation.NonNull;

import com.bumptech.glide.Glide;
import com.bumptech.glide.request.RequestOptions;
import com.develop.movy.R;
import com.develop.movy.model.Profiles;

import java.io.File;
import java.io.FileOutputStream;
import java.io.IOException;

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
        setContentView(R.layout.dialog_profile);
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
                downloadImage();
                break;


        }
    }

    private void downloadImage() {
        BitmapDrawable draw = (BitmapDrawable) actorProfile.getDrawable();
        Bitmap bitmap = draw.getBitmap();

        FileOutputStream outStream = null;
        File sdCard = Environment.getExternalStorageDirectory();
        File dir = new File(sdCard.getAbsolutePath() + "Actors Profiles");
        dir.mkdirs();
        String fileName = String.format("%d.jpg", System.currentTimeMillis());
        File outFile = new File(dir, fileName);

        Toast.makeText(getContext(), "Image Saved", Toast.LENGTH_SHORT).show();
        try {
            outStream = new FileOutputStream(outFile);
            bitmap.compress(Bitmap.CompressFormat.JPEG, 100, outStream);
            outStream.flush();
            outStream.close();

        } catch (IOException e) {
            e.printStackTrace();
        }

        Intent intent = new Intent(Intent.ACTION_MEDIA_SCANNER_SCAN_FILE);
        intent.setData(Uri.fromFile(outFile));
        getContext().sendBroadcast(intent);
    }

}
