package com.exploritage.activity;

import android.os.Bundle;
import android.support.v4.app.Fragment;
import android.support.v4.app.FragmentManager;
import android.support.v4.app.FragmentTransaction;
import android.widget.TextView;

import com.exploritage.R;
import com.exploritage.fragment.AboutAppFragment;
import com.exploritage.fragment.HowToUseFragment;
import com.exploritage.util.HomeFragmentListener;

import simplifii.framework.activity.BaseActivity;

/**
 * Created by admin on 4/3/2017.
 */

public class AboutActivity extends BaseActivity implements HomeFragmentListener {
    TextView tvTitle, tvSubTitle;

    private FragmentManager supportFragmentManager;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_about);
        initToolBar(getString(R.string.about_title));
//        tvTitle=(TextView) findViewById(R.id.tv_toolbar_title);
//        tvSubTitle = (TextView) findViewById(R.id.tv_toolbar_subtitle);
//        tvSubTitle.setText("YOUR PERSONAL HERITAGE GUIDE");
        setSubTitle(getString(R.string.subtitle_pesonal_guide));

        AboutAppFragment appFragment = AboutAppFragment.getInstance(this);
        addFragment(appFragment, false);

    }

    @Override
    public void addFragment(Fragment fragment, boolean isAddToBackStack) {
        supportFragmentManager = getSupportFragmentManager();
        FragmentTransaction fragmentTransaction = supportFragmentManager.beginTransaction();
        fragmentTransaction.add(R.id.fragment_container, fragment).commit();

    }
    @Override
    public void setTitle(String title) {

    }

    @Override
    public void setSubTitle(String subtitle) {

    }
}
