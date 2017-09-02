package com.exploritage.fragment;

import android.os.AsyncTask;
import android.text.TextUtils;
import android.widget.TextView;

import com.exploritage.R;
import com.exploritage.util.HomeFragmentListener;
import com.exploritage.util.ReadFromUrlUtil;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.net.MalformedURLException;
import java.net.URL;

import simplifii.framework.fragments.BaseFragment;
import simplifii.framework.utility.AppConstants;

/**
 * Created by ajay on 2/3/17.
 */

public class AboutAppFragment extends BaseFragment implements ReadFromUrlUtil.ProcessFinished {
    private TextView tvAboutApp;
    private HomeFragmentListener homeFragmentListener;
    private ReadFromUrlUtil readFromUrlUtil;
    private static AboutAppFragment aboutAppFragment;

    public static AboutAppFragment getInstance() {
        aboutAppFragment = new AboutAppFragment();
                return aboutAppFragment;
    }

    @Override
    public void initViews() {
//        homeFragmentListener.setTitle("About");

        readFromUrlUtil = new ReadFromUrlUtil(aboutAppFragment);
        tvAboutApp = (TextView) findView(R.id.tv_how_to_use);
        readFromUrlUtil.execute(AppConstants.PAGE_URL.ABOUT_TEXT_URL);
    }

    @Override
    public int getViewID() {
        return R.layout.fragment_about_app;
    }

    @Override
    public void onProcessFinished(String s) {
        if (!TextUtils.isEmpty(s))
            tvAboutApp.setText(s);
    }





}
