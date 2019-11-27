package com.develop.movy.utils;

import android.util.Log;

import com.develop.movy.data.ApiServiceBuilder;

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

    public String getLowQualityImagePath(){
        return baseUrl +SMALL_IAMGE_SIZE+imageKey;

    }
    public String getMediumQualityImagePath(){
        return baseUrl +MEDIUM_IAMGE_SIZE+imageKey;

    }
    public String getHighQualityImagePath(){
        Log.e("image",baseUrl +BIG_IAMGE_SIZE+imageKey);

        return baseUrl +BIG_IAMGE_SIZE+imageKey;
    }

    public String getOrignalImagePath(){
        Log.e("image",baseUrl +ORGINAL_IAMGE_SIZE+imageKey);
        return baseUrl +ORGINAL_IAMGE_SIZE+imageKey;
    }
}
