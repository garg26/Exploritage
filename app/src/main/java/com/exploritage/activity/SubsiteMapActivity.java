package com.exploritage.activity;

import android.graphics.Bitmap;
import android.graphics.BitmapFactory;
import android.graphics.PorterDuff;
import android.os.AsyncTask;
import android.os.Bundle;
import android.text.TextUtils;
import android.view.View;
import android.widget.ProgressBar;

import com.exploritage.R;
import com.exploritage.model.responses.CityDetail;
import com.exploritage.util.ZoomableImageView;

import java.io.IOException;
import java.io.InputStream;
import java.net.HttpURLConnection;
import java.net.URL;

import simplifii.framework.activity.BaseActivity;
import simplifii.framework.utility.AppConstants;

/**
 * Created by ajay on 2/3/17.
 */

public class SubsiteMapActivity extends BaseActivity {

    private ZoomableImageView ivSubSiteMap;
    private ProgressBar progressBar;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_subsite_map);

        ivSubSiteMap = (ZoomableImageView) findViewById(R.id.iv_sub_site_map);
        progressBar = (ProgressBar) findViewById(R.id.progressbar);
        progressBar.getIndeterminateDrawable().setColorFilter(getResources().getColor(R.color.color_light_green), PorterDuff.Mode.MULTIPLY);
        Bundle extras = getIntent().getExtras();
        if (extras != null) {
            CityDetail cityDetail = (CityDetail) extras.getSerializable(AppConstants.BUNDLE_KEYS.CITY_DETAIL_OBJ);
            if (cityDetail != null) {
                String name = cityDetail.getName();
                if (!TextUtils.isEmpty(name)) {
                    initToolBar(name + " " + "Map");
                }

                String sitemap = cityDetail.getSitemap();
                if (!TextUtils.isEmpty(sitemap)) {
                    new BitmapFromUrl().execute(sitemap);
                }
            }
        }

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
            ivSubSiteMap.setImageBitmap(bitmap);
        } else {
            Bitmap bp = BitmapFactory.decodeResource(getResources(), R.mipmap.bg_nav);
            ivSubSiteMap.setImageBitmap(bp);
            ivSubSiteMap.setImageBitmap(bp);
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
