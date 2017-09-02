package com.exploritage.activity;

import android.os.Bundle;
import android.support.v4.app.Fragment;
import android.support.v4.app.FragmentManager;
import android.support.v4.app.FragmentTransaction;
import android.view.Gravity;
import android.widget.TextView;

import com.exploritage.R;
import com.exploritage.fragment.AboutAppFragment;
import com.exploritage.fragment.HowToUseFragment;
import com.exploritage.util.HomeFragmentListener;

import simplifii.framework.activity.BaseActivity;
import simplifii.framework.fragments.BaseFragment;

/**
 * Created by admin on 4/3/2017.
 */

public class AboutActivity extends BaseActivity implements HomeFragmentListener {


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_about);
        initToolBarwithIcon("");
        TextView tv = (TextView) findViewById(R.id.tv_title_name);
        tv.setText(getString(R.string.about_title));
        setSubTitle(getString(R.string.subtitle_pesonal_guide));

        AboutAppFragment appFragment = AboutAppFragment.getInstance();
        addFragment(appFragment, false);

        showingBannerAds(R.id.adView);
    }

    @Override
    protected int getHomeIcon() {
        return R.mipmap.wireframes_final_40;
    }



    @Override
    public void addFragment(Fragment fragment, boolean isAddToBackStack) {
        FragmentManager supportFragmentManager = getSupportFragmentManager();
        FragmentTransaction fragmentTransaction = supportFragmentManager.beginTransaction();
        fragmentTransaction.add(R.id.
                fragment_container, fragment).commit();

    }

    @Override
    public void setTitle(String title) {

    }

    @Override
    public void setSubTitle(String subtitle) {

    }



}
