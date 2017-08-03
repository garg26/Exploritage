package com.exploritage.activity;

import android.Manifest;
import android.content.Intent;
import android.os.Bundle;
import android.support.v4.app.Fragment;
import android.support.v4.app.FragmentManager;
import android.support.v4.app.FragmentTransaction;
import android.support.v4.widget.DrawerLayout;
import android.text.TextUtils;
import android.view.Gravity;
import android.view.View;
import android.widget.FrameLayout;
import android.widget.TextView;

import com.exploritage.R;
import com.exploritage.fragment.AboutAppFragment;
import com.exploritage.fragment.AvailableCityGuidesFragment;
import com.exploritage.fragment.HowToUseFragment;
import com.exploritage.model.responses.sharetext.ShareTextClass;
import com.exploritage.model.responses.sharetext.ShareTextResponse;
import com.exploritage.util.ApiGenerator;
import com.exploritage.util.HomeFragmentListener;
import com.gun0912.tedpermission.PermissionListener;
import com.gun0912.tedpermission.TedPermission;

import java.util.ArrayList;
import java.util.List;

import simplifii.framework.activity.BaseActivity;
import simplifii.framework.asyncmanager.HttpParamObject;
import simplifii.framework.exceptionhandler.RestException;
import simplifii.framework.utility.AppConstants;

public class MainActivity extends BaseActivity implements HomeFragmentListener {

    private DrawerLayout drawerLayout;
    private FrameLayout fragmentContainer;
    private TextView tvHome, tvFeedback, tvShareApp, tvAbout, tvHowToUse;
    private FragmentManager supportFragmentManager;

    public boolean isBackPressed() {
        return isBackPressed;
    }

    public void setBackPressed(boolean backPressed) {
        isBackPressed = backPressed;
    }

    private boolean isBackPressed = false;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        findViews();
        Fragment fragment = AvailableCityGuidesFragment.getInstance(this);
        initToolBar("");
        setTitle(getString(R.string.welcome_to_exploritage));
        addFragment(fragment, true);
        setOnClickListener(R.id.tv_feedback, R.id.tv_share_app, R.id.lay_navigation, R.id.tv_about, R.id.tv_how_to_use);
    }

    @Override
    public void onClick(View v) {
        super.onClick(v);
        switch (v.getId()) {
            /*case R.id.tv_home:
                drawerLayout.closeDrawer(Gravity.LEFT);
                break;*/
            case R.id.tv_about:
//                AboutAppFragment appFragment = AboutAppFragment.getInstance(this);
//                addFragment(appFragment, false);
                drawerLayout.closeDrawer(Gravity.LEFT);
                startNextActivity(AboutActivity.class);
                break;
            case R.id.tv_how_to_use:
//                HowToUseFragment howToUseFragment = HowToUseFragment.getInstance(this);
//                addFragment(howToUseFragment, false);
                drawerLayout.closeDrawer(Gravity.LEFT);
                startNextActivity(HowToUseActivity.class);
                break;
            case R.id.tv_feedback:
                drawerLayout.closeDrawer(Gravity.LEFT);
                startNextActivity(FeedbackActivity.class);
                break;
            case R.id.tv_share_app:
                drawerLayout.closeDrawer(Gravity.LEFT);
                onShareApp();
                break;
            case R.id.lay_navigation:
//                drawerLayout.closeDrawer(Gravity.LEFT);
                break;
        }
    }

    private void onShareApp() {
        callGetShareTextApi();
    }

    private void callGetShareTextApi(){
        HttpParamObject httpParamObject= ApiGenerator.getHttpObjectForShareText();
        executeTask(AppConstants.TASK_CODES.SHARE_TEXT, httpParamObject);
    }

    @Override
    public void onBackgroundError(RestException re, Exception e, int taskCode, Object... params) {
        super.onBackgroundError(re, e, taskCode, params);
    }

    @Override
    public void onPostExecute(Object response, int taskCode, Object... params) {
        super.onPostExecute(response, taskCode, params);
        if (null == response) {
            showToast(getResources().getString(R.string.no_response));
            return;
        }
        switch (taskCode) {
            case AppConstants.TASK_CODES.SHARE_TEXT:
                String message="";
                ShareTextResponse shareTextResponse = (ShareTextResponse) response;
                if (shareTextResponse != null) {
                    List<ShareTextClass> data = shareTextResponse.getData();
                    if (data != null) {
                        ShareTextClass shareTextObject = data.get(0);
                        if (shareTextObject != null) {
                            message = shareTextObject.getTextToShare();
                        }

                    }

                }
                if(message == null)
                    message = "Text I want to share.";
                Intent share = new Intent(Intent.ACTION_SEND);
                share.setType("text/plain");
                share.putExtra(Intent.EXTRA_TEXT, message);
                startActivity(Intent.createChooser(share, "Share on"));

                break;
        }
    }

    private void findViews() {
        drawerLayout = (DrawerLayout) findViewById(R.id.drawer_layout);
        fragmentContainer = (FrameLayout) findViewById(R.id.fragment_container);
        tvFeedback = (TextView) findViewById(R.id.tv_feedback);
        tvShareApp = (TextView) findViewById(R.id.tv_share_app);
    }

    @Override
    public void onBackPressed() {
        if (!isBackPressed) {
            if (supportFragmentManager.getBackStackEntryCount() > 1) {
                supportFragmentManager.popBackStack("others", 1);
            } else {
                showToast("Press back again to exit");
                setBackPressed(true);
            }
        } else {
            finish();
        }
    }

    @Override
    protected void onHomePressed() {
//        super.onHomePressed();
        drawerLayout.openDrawer(Gravity.LEFT);
    }

    @Override
    protected int getHomeIcon() {
        return R.mipmap.ic_menu;
    }

    @Override
    public void addFragment(Fragment fragment, boolean isAddToBackStack) {
        supportFragmentManager = getSupportFragmentManager();
        FragmentTransaction fragmentTransaction = supportFragmentManager.beginTransaction();
        if (isAddToBackStack) {
            fragmentTransaction.addToBackStack("mainfragment");
        } else {
            fragmentTransaction.addToBackStack("others");
        }
        fragmentTransaction.replace(R.id.fragment_container, fragment).commit();
    }

    @Override
    public void setTitle(String title) {
        TextView tv = (TextView) findViewById(R.id.tv_toolbar_title);
        tv.setText(title);
    }

    @Override
    public void setSubTitle(String subtitle) {
        if (!TextUtils.isEmpty(subtitle)) {
            TextView tv = (TextView) findViewById(R.id.tv_toolbar_subtitle);
            tv.setText(subtitle);
        }
    }
}
