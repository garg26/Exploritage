package com.exploritage.activity;

import android.content.Intent;
import android.os.Bundle;
import android.util.Log;
import android.view.View;

import com.exploritage.R;
import com.exploritage.model.UserProfile;
import com.exploritage.util.FireBaseFBLoginUtil;
import com.exploritage.util.FireBaseGoogleLoginUtil;
import com.facebook.FacebookSdk;
import com.google.android.gms.auth.api.signin.GoogleSignInAccount;

import simplifii.framework.activity.BaseActivity;
import simplifii.framework.utility.CollectionsUtils;
import simplifii.framework.utility.Preferences;

public class LoginActivity extends BaseActivity {
    private FireBaseFBLoginUtil fireBaseFbLoginUtil;
    private FireBaseGoogleLoginUtil fireBaseGoogleLoginUtil;
    private UserProfile profile;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);

        FacebookSdk.sdkInitialize(this);
        setContentView(R.layout.activity_login);
        initGoogleFaceBook();
        setOnClickListener(R.id.btn_signIn,R.id.tv_create_account,R.id.iv_fb,R.id.iv_gmail);

//         fireBaseFBLoginUtil = new FireBaseFBLoginUtil(this, new FireBaseFBLoginUtil.FBLoginCallback() {
//            @Override
//            public void onSuccess(Bundle bundle) {
//                setProfile(bundle);
//                Preferences.saveData(Preferences.LOGIN_KEY,true);
//                startNextActivity(MainActivity.class);
//                finish();
//            }
//
//            @Override
//            public void onFailure() {
//                showToast("Failed");
//            }
//        });


    }

    private void initGoogleFaceBook() {
        fireBaseFbLoginUtil = new FireBaseFBLoginUtil(this, new FireBaseFBLoginUtil.FBLoginCallback() {
            @Override
            public void onSuccess(Bundle bundle) {
                setProfile(bundle);
                Preferences.saveData(Preferences.LOGIN_KEY,true);
                startNextActivity(MainActivity.class);
                finish();
            }

            @Override
            public void onFailure() {
                showToast("Failed to Facebook login..!");
            }
        });

        fireBaseGoogleLoginUtil = FireBaseGoogleLoginUtil.getInstance(this, new FireBaseGoogleLoginUtil.GoogleSignInListener() {
            @Override
            public void onSuccess(GoogleSignInAccount account) {
                handleSignInResult(account);
                Preferences.saveData(Preferences.LOGIN_KEY,true);
                startNextActivity(MainActivity.class);
                finish();
            }

            @Override
            public void onFailed() {
                showToast("Failed");
            }
        });
    }

    private void handleSignInResult(GoogleSignInAccount account) {
        profile = new UserProfile();
        profile.setEmail(account.getEmail());
        profile.setName(account.getDisplayName());
        if (account.getPhotoUrl() != null)
            profile.setImageUrl(account.getPhotoUrl().toString());
        profile.setAccountId(account.getId());
        Log.i("msg", profile.getEmail() + " " + profile.getName() + " " + profile.getAccountId());
    }

    private void setProfile(Bundle bundle) {
        profile = new UserProfile();
        profile.setName(bundle.getString("name"));
        profile.setEmail(bundle.getString("email"));
        profile.setImageUrl(bundle.getString(""));



    }




    @Override
    public void onClick(View v) {
        switch (v.getId()){
            case R.id.btn_signIn:
                onLogin();
                break;
            case R.id.tv_create_account:
                onRegisterClick();
                break;
            case R.id.iv_fb:
                fireBaseFbLoginUtil.initiateFbLogin();
                break;
            case R.id.iv_gmail:
                if (!isNetworkAvailable()) {
                    showToast("Internet not connected..!");
                    return;
                }
                fireBaseGoogleLoginUtil.login();
                break;
        }
    }




    private void onRegisterClick() {
        startNextActivity(RegisterActivity.class);
    }

    private void onLogin() {
        if(CollectionsUtils.isNotEmpty(getEditText(R.id.et_mobile_number)) && getEditText(R.id.et_mobile_number).length()==10){
            if (CollectionsUtils.isNotEmpty(getEditText(R.id.et_password)) && getEditText(R.id.et_password).length()>4){
                Preferences.saveData(Preferences.LOGIN_KEY,true);
                startNextActivity(MainActivity.class);
                finish();
            }
        }else{
            showToast(getString(R.string.error_empty_phone_number));
        }

    }

    @Override
    protected void onActivityResult(int requestCode, int resultCode, Intent data) {
        super.onActivityResult(requestCode, resultCode, data);
        if (resultCode != RESULT_OK) return;
        fireBaseFbLoginUtil.onActivityResult(requestCode, resultCode, data);
        fireBaseGoogleLoginUtil.onActivityResult(requestCode, resultCode, data);

    }
}
