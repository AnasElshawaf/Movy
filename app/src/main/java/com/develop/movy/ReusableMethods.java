package com.develop.movy;

import android.content.Context;
import android.widget.ImageView;

import com.bumptech.glide.Glide;
import com.bumptech.glide.request.RequestOptions;

public class ReusableMethods {

    public static void loadImage(Context context, String url, ImageView imageView) {
        if (url != null)
            Glide.with(context).load(url)
                    .apply(RequestOptions.centerCropTransform()).apply(new RequestOptions().error(R.drawable.ic_launcher_background))
                    .into(imageView);

    }
}
