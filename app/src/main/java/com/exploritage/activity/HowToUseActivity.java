package com.exploritage.activity;

import android.os.Bundle;
import android.support.v4.app.Fragment;
import android.support.v4.app.FragmentManager;
import android.support.v4.app.FragmentTransaction;

import com.exploritage.R;
import com.exploritage.fragment.HowToUseFragment;
import com.exploritage.util.HomeFragmentListener;

import simplifii.framework.activity.BaseActivity;

/**
 * Created by admin on 4/3/2017.
 */

public class HowToUseActivity extends BaseActivity implements HomeFragmentListener {

    private FragmentManager supportFragmentManager;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_how_to_use);
        initToolBar("How To Use");

        HowToUseFragment howToUseFragment = HowToUseFragment.getInstance(this);
        addFragment(howToUseFragment, false);

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
