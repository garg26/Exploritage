package com.exploritage;

import android.support.multidex.MultiDexApplication;
import com.crashlytics.android.Crashlytics;
import com.exploritage.util.PicassoUtil;
import com.google.firebase.FirebaseApp;
import io.fabric.sdk.android.Fabric;
import simplifii.framework.utility.Preferences;

public class AppController extends MultiDexApplication {

    @Override
    public void onCreate() {
        super.onCreate();
        FirebaseApp.initializeApp(this);
        Fabric.with(this, new Crashlytics());
        Preferences.initSharedPreferences(this);
        PicassoUtil.initPicasso(this);
    }
}