package com.exploritage;

import android.app.Application;

import com.crashlytics.android.Crashlytics;
import com.exploritage.util.PicassoUtil;

import io.fabric.sdk.android.Fabric;
import simplifii.framework.utility.Preferences;

public class AppController extends Application {

    @Override
    public void onCreate() {
        super.onCreate();
        Fabric.with(this, new Crashlytics());
        Preferences.initSharedPreferences(this);
        PicassoUtil.initPicasso(this);
    }
}