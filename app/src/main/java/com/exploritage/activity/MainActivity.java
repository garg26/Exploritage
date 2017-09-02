package com.exploritage.activity;

import android.os.Build;
import android.os.Bundle;
import android.support.annotation.NonNull;
import android.support.design.widget.NavigationView;
import android.support.v4.app.Fragment;
import android.support.v4.app.FragmentManager;
import android.support.v4.app.FragmentTransaction;
import android.support.v4.widget.DrawerLayout;
import android.support.v7.app.ActionBarDrawerToggle;
import android.support.v7.widget.Toolbar;
import android.view.Gravity;
import android.view.Menu;
import android.view.MenuInflater;
import android.view.MenuItem;
import android.view.View;
import android.widget.TextView;
import com.exploritage.R;
import com.exploritage.fragment.DrawerFragment;
import com.exploritage.fragment.ExploritageDestinationtabFragment;
import com.exploritage.util.HomeFragmentListener;

import simplifii.framework.activity.BaseActivity;
import simplifii.framework.exceptionhandler.RestException;

public class MainActivity extends BaseActivity implements HomeFragmentListener, DrawerLayout.DrawerListener  {


    private FragmentManager supportFragmentManager;
    private boolean isDrawerOpen;
    private DrawerLayout drawerLayout;


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
        Toolbar toolbar = (Toolbar) findViewById(R.id.toolbar);
        initToolBar("");
        drawerLayout = (DrawerLayout) findViewById(R.id.drawerlayout);
        if(Build.VERSION.SDK_INT>=Build.VERSION_CODES.LOLLIPOP){
            toolbar.setNavigationIcon(getDrawable(R.mipmap.wireframes_final_44));
            toolbar.setNavigationOnClickListener(new View.OnClickListener() {
                @Override
                public void onClick(View v) {
                    drawerOperation(drawerLayout);
                }
            });
        }
        drawerLayout.addDrawerListener(this);
        FragmentManager fragmentManager = getSupportFragmentManager();
        addDrawerFragment(toolbar,drawerLayout, fragmentManager);
        setAddFragment();
        initNavigationDrawer();


        showingBannerAds(R.id.adView);
        //setOnClickListener(R.id.lay_navigation);
    }

    @Override
    protected int getHomeIcon() {
        return R.mipmap.wireframes_final_44;
    }

    private void setAddFragment() {
        Fragment fragment = ExploritageDestinationtabFragment.getInstance(this);
        setTitle(getString(R.string.exploritage_destinations));
        addFragment(fragment, true);
    }

    private void addDrawerFragment(Toolbar toolbar, DrawerLayout drawerLayout, FragmentManager fragmentManager) {
        ActionBarDrawerToggle toggle = new ActionBarDrawerToggle(this, drawerLayout, toolbar, R.string.navigation_drawer_open,R.string.navigation_drawer_close);
        drawerLayout.addDrawerListener(toggle);
        toggle.syncState();
        DrawerFragment drawerFragment = DrawerFragment.getInstance(drawerLayout);
        fragmentManager.beginTransaction().replace(R.id.lay_drawer, drawerFragment).commit();
    }

    private void initNavigationDrawer() {
        NavigationView navigationView = (NavigationView) findViewById(R.id.lay_drawer);
        navigationView.setNavigationItemSelectedListener(new NavigationView.OnNavigationItemSelectedListener() {
            @Override
            public boolean onNavigationItemSelected(@NonNull MenuItem item) {
                return false;
            }
        });

    }





//    @Override
//    public void onClick(View v) {
//        super.onClick(v);
//        switch (v.getId()) {
//
//
//            case R.id.tv_feedback:
//                drawerLayout.closeDrawer(Gravity.LEFT);
//                startNextActivity(FeedbackActivity.class);
//                break;
//            case R.id.tv_share_app:
//                drawerLayout.closeDrawer(Gravity.LEFT);
//                onShareApp();
//                break;
//            case R.id.lay_navigation:
////                drawerLayout.closeDrawer(Gravity.LEFT);
//                break;
//            case R.id.faq_s:
//                drawerLayout.closeDrawer(Gravity.LEFT);
//                startNextActivity(FaqActivity.class);
//                break;
//
//        }
//    }

    private void onShareApp() {
 //       callGetShareTextApi();
    }

//    private void callGetShareTextApi(){
//        HttpParamObject httpParamObject= ApiGenerator.getHttpObjectForShareText();
//        executeTask(AppConstants.TASK_CODES.SHARE_TEXT, httpParamObject);
//    }

    @Override
    public void onBackgroundError(RestException re, Exception e, int taskCode, Object... params) {
        super.onBackgroundError(re, e, taskCode, params);
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
    public boolean onCreateOptionsMenu(Menu menu) {
        MenuInflater inflater = getMenuInflater();
        inflater.inflate(R.menu.menu_destination, menu);
        return true;
    }

    @Override
    public boolean onOptionsItemSelected(MenuItem item) {
        return super.onOptionsItemSelected(item);

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
//        if (!TextUtils.isEmpty(subtitle)) {
//            TextView tv = (TextView) findViewById(R.id.tv_toolbar_subtitle);
//            tv.setText(subtitle);
//        }
    }


    @Override
    public void onDrawerSlide(View drawerView, float slideOffset) {

    }

    @Override
    public void onDrawerOpened(View drawerView) {
        isDrawerOpen = true;
    }

    @Override
    public void onDrawerClosed(View drawerView) {
        isDrawerOpen = false;
    }

    @Override
    public void onDrawerStateChanged(int newState) {

    }
    private void drawerOperation(DrawerLayout drawerLayout) {
        if (isDrawerOpen) {
            drawerLayout.closeDrawer(Gravity.LEFT);
        } else {
            drawerLayout.openDrawer(Gravity.LEFT);
        }
    }
}
