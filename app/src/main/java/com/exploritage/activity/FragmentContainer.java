package com.exploritage.activity;

import android.app.Activity;
import android.content.Context;
import android.content.Intent;
import android.os.Bundle;
import android.support.v4.app.Fragment;

import com.exploritage.R;
import com.exploritage.fragment.AboutAppFragment;

import simplifii.framework.activity.BaseActivity;
import simplifii.framework.utility.AppConstants;

/**
 * Created by kartikeya on 27/7/17.
 */

public class FragmentContainer extends BaseActivity{
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_fragment_container);

        getSupportFragmentManager().beginTransaction().replace(R.id.frame, getFragment(getIntent().getExtras().getInt(AppConstants.BUNDLE_KEYS.FRAGMENT_TYPE))).commit();
    }

    private Fragment getFragment(int fragmentType) {
        Fragment fragment = null;
        switch (fragmentType) {
            case AppConstants.ABOUTUS.ABOUTUS_ID:
                initToolBar(AppConstants.ABOUTUS.ABOUTUS);
                fragment = new AboutAppFragment();
                break;


        }
        if (fragment != null) {
            fragment.setArguments(getIntent().getBundleExtra(AppConstants.BUNDLE_KEYS.EXTRA_BUNDLE));
        }
        return fragment;
    }

    public static void startActivity(Context ctx, int fragmentType, Bundle extraBundle) {
        Intent i = new Intent(ctx, FragmentContainer.class);
        if (extraBundle != null) {
            i.putExtra(AppConstants.BUNDLE_KEYS.EXTRA_BUNDLE, extraBundle);
        }
        i.putExtra(AppConstants.BUNDLE_KEYS.FRAGMENT_TYPE, fragmentType);
        ctx.startActivity(i);
    }
    public static void startActivityForResult(Context context, int fragmentType, Bundle extraBundle, int requestCode, Fragment fragment){
        Intent intent = new Intent(context, FragmentContainer.class);
        if(extraBundle != null){
            intent.putExtra(AppConstants.BUNDLE_KEYS.EXTRA_BUNDLE, extraBundle);
        }
        intent.putExtra(AppConstants.BUNDLE_KEYS.FRAGMENT_TYPE, fragmentType);
        fragment.startActivityForResult(intent, requestCode);
    }

    public static void startActivityForResult(Activity context, int fragmentType, Bundle extraBundle, int requestCode){
        Intent intent = new Intent(context, FragmentContainer.class);
        if(extraBundle != null){
            intent.putExtra(AppConstants.BUNDLE_KEYS.EXTRA_BUNDLE, extraBundle);
        }
        intent.putExtra(AppConstants.BUNDLE_KEYS.FRAGMENT_TYPE, fragmentType);
        context.startActivityForResult(intent, requestCode);
    }

}
