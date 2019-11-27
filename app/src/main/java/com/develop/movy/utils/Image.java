package com.develop.movy.utils;

import android.content.Intent;
import android.graphics.Bitmap;
import android.graphics.drawable.BitmapDrawable;
import android.net.Uri;
import android.os.Environment;
import android.util.Log;
import android.widget.ImageView;
import android.widget.Toast;

import com.develop.movy.data.ApiServiceBuilder;

import java.io.File;
import java.io.FileOutputStream;
import java.io.IOException;

public class Image {

    public static final String SMALL_IAMGE_SIZE = "w300/";
    public static final String MEDIUM_IAMGE_SIZE = "w700/";
    public static final String BIG_IAMGE_SIZE = "w1280/";
    public static final String ORGINAL_IAMGE_SIZE = "original";

    private String baseUrl = ApiServiceBuilder.BASE_IMAGE_URL;
    private String imageKey;


    public Image(String imageKey) {
        this.imageKey = imageKey;
    }

    public String getLowQualityImagePath() {
        return baseUrl + SMALL_IAMGE_SIZE + imageKey;

    }

    public String getMediumQualityImagePath() {
        return baseUrl + MEDIUM_IAMGE_SIZE + imageKey;

    }

    public String getHighQualityImagePath() {
        Log.e("image", baseUrl + BIG_IAMGE_SIZE + imageKey);

        return baseUrl + BIG_IAMGE_SIZE + imageKey;
    }

    public String getOrignalImagePath() {
        Log.e("image", baseUrl + ORGINAL_IAMGE_SIZE + imageKey);
        return baseUrl + ORGINAL_IAMGE_SIZE + imageKey;
    }

    public static void downloadImage(ImageView view) {
        BitmapDrawable draw = (BitmapDrawable) view.getDrawable();
        Bitmap bitmap = draw.getBitmap();

        FileOutputStream outStream = null;
        File sdCard = Environment.getExternalStorageDirectory();
        File dir = new File(sdCard.getAbsolutePath() + "/Actors Profiles");
        dir.mkdirs();
        String fileName = String.format("%d.jpg", System.currentTimeMillis());
        File outFile = new File(dir, fileName);

        Toast.makeText(view.getContext(), "Image Saved", Toast.LENGTH_SHORT).show();
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
        view.getContext().sendBroadcast(intent);
    }

}
