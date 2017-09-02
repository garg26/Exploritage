package com.exploritage.util;

import android.content.Context;
import android.text.TextUtils;
import android.widget.ImageView;

import com.squareup.picasso.LruCache;
import com.squareup.picasso.NetworkPolicy;
import com.squareup.picasso.OkHttpDownloader;
import com.squareup.picasso.Picasso;

import java.io.File;

import simplifii.framework.utility.Preferences;

/**
 * Created by admin on 4/5/2017.
 */

public class PicassoUtil {

    private static Picasso instance;
    private static final long PICASSO_DISK_CACHE_SIZE = 1024 * 1024 * 100;

    public static void initPicasso(Context context) {
//        Picasso.Builder builder = new Picasso.Builder(context).memoryCache(new LruCache(Integer.MAX_VALUE));
////        builder.downloader(new OkHttpDownloader(context, Integer.MAX_VALUE));
//        Picasso built = builder.build();
//        built.setIndicatorsEnabled(true);
//        built.setLoggingEnabled(true);
//        Picasso.setSingletonInstance(built);
//        instance = built;
    }

    public static Picasso getPicasso(Context context) {
        if (instance != null) {
            return instance;
        }
        Picasso pic = Picasso.with(context);
        return pic;
    }

    public static void  loadImage(Context context, String imageUrl, ImageView imageView, int placeholder) {
        String imageFileName = Preferences.getData(imageUrl, null);
        if (!TextUtils.isEmpty(imageFileName)) {
            File imageFile = new File(imageFileName);
            if (imageFile.exists()) {
                if (placeholder != 0) {
                    getPicasso(context).load(imageFile).placeholder(placeholder).into(imageView);
                } else {
                    getPicasso(context).load(imageFile).into(imageView);
                }
                return;
            }

        }

        getPicasso(context).load(imageUrl).placeholder(placeholder).into(imageView);
    }
}
