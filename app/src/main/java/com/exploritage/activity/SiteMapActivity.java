package com.exploritage.activity;

import android.content.Context;
import android.graphics.Bitmap;
import android.graphics.BitmapFactory;
import android.graphics.PorterDuff;
import android.os.AsyncTask;
import android.os.Bundle;
import android.text.TextUtils;
import android.view.View;
import android.widget.ImageView;
import android.widget.ProgressBar;

import com.exploritage.R;
import com.exploritage.model.responses.Datum;
import com.exploritage.util.PicassoUtil;
import com.exploritage.util.ZoomableImageView;

import java.io.File;
import java.io.IOException;
import java.io.InputStream;
import java.net.HttpURLConnection;
import java.net.URL;

import simplifii.framework.activity.BaseActivity;
import simplifii.framework.utility.AppConstants;
import simplifii.framework.utility.Preferences;

/**
 * Created by ajay on 2/3/17.
 */

public class SiteMapActivity extends BaseActivity {
    ZoomableImageView ivSiteMap;
    ProgressBar progressBar;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_sitemap);


        ivSiteMap = (ZoomableImageView) findViewById(R.id.iv_site_map);
        progressBar = (ProgressBar) findViewById(R.id.progressbar);
        progressBar.getIndeterminateDrawable().setColorFilter(getResources().getColor(R.color.color_light_green), PorterDuff.Mode.MULTIPLY);

        Bundle extras = getIntent().getExtras();
        if (extras != null) {
            String title = extras.getString(AppConstants.BUNDLE_KEYS.KEY_TITLE, null);
            initToolBar(title);
            String sitemap = extras.getString(AppConstants.BUNDLE_KEYS.KEY_URL, null);
            if (!TextUtils.isEmpty(sitemap)) {
                loadImage(sitemap, ivSiteMap);
            }
        }
    }

    public void loadImage(String imageUrl, ImageView imageView) {
        try {
            String imageFileName = Preferences.getData(imageUrl, null);
            if (!TextUtils.isEmpty(imageFileName)) {
                File imageFile = new File(imageFileName);
                if (imageFile.exists()) {
                    Bitmap bmap = BitmapFactory.decodeFile(imageFile.getPath());
                    imageView.setImageBitmap(bmap);
                    return;
                }

            }
        } catch (OutOfMemoryError e) {
            e.printStackTrace();
        }
        new BitmapFromUrl().execute(imageUrl);
    }

    @Override
    public void showProgressDialog() {
        progressBar.setVisibility(View.VISIBLE);
    }

    @Override
    public void hideProgressBar() {
        progressBar.setVisibility(View.GONE);
    }

    class BitmapFromUrl extends AsyncTask<String, Void, Bitmap> {

        @Override
        protected void onPreExecute() {
            super.onPreExecute();
            showProgressDialog();
        }

        @Override
        protected Bitmap doInBackground(String... params) {

            String url = params[0];
            Bitmap bitmap = getBitmapFromURL(url);
            return bitmap;
        }

        @Override
        protected void onPostExecute(Bitmap bitmap) {
            super.onPostExecute(bitmap);
            hideProgressBar();
            if (bitmap != null) {
                setBitMap(bitmap);
            }
        }
    }

    private void setBitMap(Bitmap bitmap) {

        if (bitmap != null) {
            ivSiteMap.setImageBitmap(bitmap);
        } else {
            Bitmap bp = BitmapFactory.decodeResource(getResources(), R.mipmap.bg_nav);
            ivSiteMap.setImageBitmap(bp);
            ivSiteMap.setImageBitmap(bp);
        }

    }

    public static Bitmap getBitmapFromURL(String src) {
        try {
            URL url = new URL(src);
            HttpURLConnection connection = (HttpURLConnection) url.openConnection();
            connection.setDoInput(true);
            connection.connect();
            InputStream input = connection.getInputStream();
            Bitmap myBitmap = BitmapFactory.decodeStream(input);
            return myBitmap;
        } catch (IOException e) {
            // Log exception
            return null;
        }

    }

}
