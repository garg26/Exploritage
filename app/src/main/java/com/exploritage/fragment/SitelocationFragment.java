package com.exploritage.fragment;

import android.os.Bundle;
import android.support.v4.app.Fragment;
import android.text.TextUtils;
import android.view.View;
import android.widget.ImageView;
import android.widget.LinearLayout;
import android.widget.TextView;

import com.exploritage.R;
import com.exploritage.activity.SiteMapActivity;
import com.exploritage.model.responses.Datum;
import com.exploritage.util.AppBaseFragment;
import com.exploritage.util.PicassoUtil;
import com.squareup.picasso.NetworkPolicy;
import com.squareup.picasso.Picasso;

import simplifii.framework.utility.AppConstants;


/**
 * Created by ajay on 21/2/17.
 */
public class SitelocationFragment extends AppBaseFragment {
    private LinearLayout viewContainer;
    private ImageView ivSiteMap;
    private Datum city;
    private Double lat;
    private Double lon;
    private boolean isImageAvailable = false;

    public static Fragment getInstance(Datum city) {
        SitelocationFragment fragment = new SitelocationFragment();
        fragment.city = city;
        return fragment;
    }

    @Override
    public void initViews() {
        TextView tvSiteMapInitial = (TextView) findView(R.id.tv_no_sitemap_available);
        ivSiteMap = (ImageView) findView(R.id.iv_google_map);
        ivSiteMap.setOnClickListener(this);
//        ivSiteMap = (TouchImageView) findView(R.id.iv_google_map);
        viewContainer = (LinearLayout) findView(R.id.lay_view_container);


//        String title = getResources().getString(R.string.how_to_reach);

        if (city != null) {
            final String sitemap = city.getSitemap();
            if (!TextUtils.isEmpty(sitemap)) {
                ivSiteMap.setVisibility(View.VISIBLE);
                tvSiteMapInitial.setVisibility(View.GONE);
                isImageAvailable = true;
                PicassoUtil.loadImage(getActivity(), sitemap, ivSiteMap, R.mipmap.placeholder);
            } else {
                isImageAvailable = false;
                tvSiteMapInitial.setVisibility(View.VISIBLE);
                tvSiteMapInitial.setText(getString(R.string.no_sitemap_available));
                ivSiteMap.setVisibility(View.GONE);
            }
        }

    }

    @Override
    public void onClick(View v) {
        super.onClick(v);

        switch (v.getId()) {
            case R.id.iv_google_map:
                if (isImageAvailable) {
                    Bundle bundle = new Bundle();
                    bundle.putString(AppConstants.BUNDLE_KEYS.KEY_URL, city.getSitemap());
                    bundle.putString(AppConstants.BUNDLE_KEYS.KEY_TITLE, city.getCityname()+ " Site Map");
                    startNextActivity(bundle, SiteMapActivity.class);
                    break;
                }
        }

    }

    /* public static Bitmap getBitmapFromURL(String src) {
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
    }*/


    @Override
    public int getViewID() {
        return R.layout.fragment_location;
    }


   /* class BitmapFromUrl extends AsyncTask<String, Void, Bitmap> {

        @Override
        protected Bitmap doInBackground(String... params) {

            String url = params[0];
            Bitmap bitmap = getBitmapFromURL(url);

            return bitmap;
        }

        @Override
        protected void onPostExecute(Bitmap bitmap) {
            super.onPostExecute(bitmap);

            if (bitmap != null) {
                setBitMap(bitmap);
            }
        }
    }

    private void setBitMap(Bitmap bitmap) {

        if(bitmap != null){
            ivSiteMap.setImageBitmap(bitmap);
        }else{
            Bitmap bp = BitmapFactory.decodeResource(getResources(), R.mipmap.bg_nav);
            ivSiteMap.setImageBitmap(bp);
            ivSiteMap.setImageBitmap(bp);
        }

    }
*/

}

