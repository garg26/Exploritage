package com.exploritage.activity;

import android.os.Bundle;
import android.os.Handler;

import com.exploritage.R;

import simplifii.framework.activity.BaseActivity;
import simplifii.framework.utility.Preferences;

/**
 * Created by ajay on 27/2/17.
 */

public class SplashActivity extends BaseActivity {
    // Splash screen timer
    private static int SPLASH_TIME_OUT = 2000;


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_splash);
        new Handler().postDelayed(new Runnable() {

            @Override
            public void run() {
                Boolean data = Preferences.getData(Preferences.LOGIN_KEY, false);
                if (data!=null) {
                    if (data) {
                        startNextActivity(MainActivity.class);
                        finish();
                    }
                    else{
                        startNextActivity(LoginActivity.class);
                        finish();
                    }
                }else{
                    startNextActivity(LoginActivity.class);
                    finish();
                }
            }
        }, SPLASH_TIME_OUT);
        // setExpandableList();

    }


}