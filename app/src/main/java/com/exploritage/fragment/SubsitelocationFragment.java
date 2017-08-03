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
import com.exploritage.activity.SubsiteMapActivity;
import com.exploritage.model.responses.CityDetail;
import com.exploritage.util.AppBaseFragment;
import com.exploritage.util.PicassoUtil;
import com.squareup.picasso.NetworkPolicy;
import com.squareup.picasso.Picasso;

import java.util.List;

import simplifii.framework.utility.AppConstants;

/**
 * Created by ajay on 18/2/17.
 */
public class SubsitelocationFragment extends AppBaseFragment {

    private Double lat;
    private Double lon;
    private ImageView ivSubiteMap;
    private LinearLayout viewContainer;
    private String mapUrl;
    private List<CityDetail> cityDetailList;
    private boolean isImageAvailable = false;

    public static Fragment getInstance(String mapUrl) {
        SubsitelocationFragment locationFragment = new SubsitelocationFragment();
        locationFragment.mapUrl = mapUrl;
        return locationFragment;
    }

    @Override
    public void initViews() {
        TextView tvNoSubsiteMapAvailable = (TextView) findView(R.id.tv_no_sitemap_available);
        ivSubiteMap = (ImageView) findView(R.id.iv_google_map);
        viewContainer = (LinearLayout) findView(R.id.lay_view_container);
        String title = getResources().getString(R.string.how_to_reach);

        final String sitemap = mapUrl;
        if (!TextUtils.isEmpty(sitemap)) {
            ivSubiteMap.setVisibility(View.VISIBLE);
            tvNoSubsiteMapAvailable.setVisibility(View.GONE);
            isImageAvailable = true;
            PicassoUtil.loadImage(getActivity(), sitemap, ivSubiteMap, R.mipmap.placeholder);
        } else {
            isImageAvailable = false;
            tvNoSubsiteMapAvailable.setText("No Subsite Map Available");
            tvNoSubsiteMapAvailable.setVisibility(View.VISIBLE);
        }
        setOnClickListener(R.id.iv_google_map);
    }

    @Override
    public void onClick(View v) {
        super.onClick(v);
        switch (v.getId()) {
            case R.id.iv_google_map:
                Bundle b = new Bundle();
                b.putString(AppConstants.BUNDLE_KEYS.KEY_URL, mapUrl);
                b.putString(AppConstants.BUNDLE_KEYS.KEY_TITLE, "Site Map");
                startNextActivity(b, SiteMapActivity.class);
                break;
        }
    }

    @Override
    public int getViewID() {
        return R.layout.fragment_location;
    }

}
