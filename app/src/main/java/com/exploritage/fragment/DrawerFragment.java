package com.exploritage.fragment;

import android.content.Intent;
import android.support.v4.app.Fragment;
import android.support.v4.widget.DrawerLayout;
import android.view.Gravity;
import android.view.View;

import com.exploritage.R;

import com.exploritage.activity.AboutActivity;
import com.exploritage.activity.FaqActivity;
import com.exploritage.activity.FeedbackActivity;
import com.exploritage.activity.HowToUseActivity;
import com.exploritage.activity.MyAudioCityActivity;
import com.exploritage.model.responses.sharetext.ShareTextClass;
import com.exploritage.model.responses.sharetext.ShareTextResponse;
import com.exploritage.util.ApiGenerator;

import java.util.List;

import simplifii.framework.asyncmanager.HttpParamObject;
import simplifii.framework.exceptionhandler.RestException;
import simplifii.framework.fragments.BaseFragment;
import simplifii.framework.utility.AppConstants;

/**
 * Created by kartikeya on 26/7/17.
 */

public class DrawerFragment extends BaseFragment{
    private DrawerLayout drawerLayout;

    public static DrawerFragment getInstance(DrawerLayout drawerLayout) {
        DrawerFragment drawerFragment = new DrawerFragment();
        drawerFragment.drawerLayout = drawerLayout;
        return drawerFragment;
    }

    @Override
    public void initViews() {
        setOnClickListener(R.id.tv_my_destination,R.id.tv_feedback, R.id.tv_share_app, R.id.lay_navigation, R.id.tv_about, R.id.what_s_new,R.id.faq_s,R.id.tv_my_destination);

    }

    @Override
    public int getViewID() {
        return R.layout.fragment_drawer;
    }



    @Override
    public void onClick(View v) {
        super.onClick(v);
        switch (v.getId()) {
            /*case R.id.tv_home:
                drawerLayout.closeDrawer(Gravity.LEFT);
                break;*/
            case R.id.tv_about:
                //AboutAppFragment appFragment = AboutAppFragment.getInstance(this);
                //addFragment(appFragment, false);
                drawerLayout.closeDrawer(Gravity.LEFT);
                //FragmentContainer.startActivityForResult(getActivity(), AppConstants.ABOUTUS.ABOUTUS_ID, null, AppConstants.REQUEST_CODES.FRAGMENT);

//                initToolBar("AboutAppFragment");
//                AboutAppFragment appFragment = AboutAppFragment.getInstance();
//                addFragment(appFragment);
                startNextActivity(AboutActivity.class);
                break;
            case R.id.tv_my_destination:
                drawerLayout.closeDrawer(Gravity.LEFT);
                startNextActivity(MyAudioCityActivity.class);
                break;
            case R.id.what_s_new:
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
            case R.id.faq_s:
                drawerLayout.closeDrawer(Gravity.LEFT);
                startNextActivity(FaqActivity.class);
                break;


        }
    }

    private void addFragment(Fragment fragment){
        getActivity().getSupportFragmentManager().beginTransaction().add(R.id.fragment_container,fragment).commit();
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


}
