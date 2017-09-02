package com.exploritage.activity;

import android.graphics.Typeface;
import android.os.Bundle;
import android.support.design.widget.TabLayout;
import android.support.v4.app.Fragment;
import android.support.v4.view.ViewPager;
import android.support.v4.widget.DrawerLayout;
import android.text.TextUtils;
import android.view.Menu;
import android.view.MenuInflater;
import android.view.MenuItem;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;
import android.widget.TextView;

import com.exploritage.R;
import com.exploritage.fragment.AboutFragment;
import com.exploritage.fragment.MapViewFragment;
import com.exploritage.fragment.PlaceFragment;
import com.exploritage.model.responses.Datum;
import com.exploritage.model.responses.place.PlaceData;
import com.exploritage.model.responses.place.PlaceToVisitResponse;
import com.exploritage.util.ApiGenerator;
import com.exploritage.util.DownloadFileService;
import com.google.android.gms.ads.AdRequest;
import com.google.android.gms.ads.AdView;

import java.util.ArrayList;
import java.util.List;

import simplifii.framework.ListAdapters.CustomPagerAdapter;
import simplifii.framework.activity.BaseActivity;
import simplifii.framework.asyncmanager.HttpParamObject;
import simplifii.framework.utility.AppConstants;
import simplifii.framework.utility.JsonUtil;
import simplifii.framework.utility.Preferences;

/**
 * Created by ajay on 21/2/17.
 */

public class SiteDescriptionActivity extends BaseActivity implements CustomPagerAdapter.PagerAdapterInterface {
    public List<String> tabsList;
    private Datum city;
    private ImageView ivBackArrow;
    public TabLayout tabLayout;
    public List<Integer> tabIcons;
    private PlaceFragment placeFragment;
    private DownloadFileService downloadFileService;
    private DrawerLayout drawerLayout;
    private Bundle extras;
    private MapViewFragment mapViewFragment;
    private PlaceToVisitResponse placeToVisitResponse;
    private ViewPager viewPager;
    private ArrayList<Object> placesList;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_sitedeccription);

        extras = getIntent().getExtras();
        if (extras!=null){
            city = (Datum) extras.get(AppConstants.BUNDLE_KEYS.CITY_DATA);
        }

        placesList = new ArrayList<>();


        String objectId = city.getObjectId();
         placeToVisitResponse = PlaceToVisitResponse.getInstance(objectId);
        setPlacesList(placeToVisitResponse);


        //Getting updated data.
        if (city != null) {
            getPlaceToVisitApi(city);
        }

        initToolBarwithIcon("");
        //initTabIcons();
        initTabs();
        initViewPager();


        TextView tv_title_name = (TextView) findViewById(R.id.tv_title_name);


        if (extras != null) {

            if (city != null) {
                String cityname = city.getCityname();
                String welcomeTitle = city.getWelcomeTitle();

//                TextView tvToolbarTitle = (TextView) findViewById(R.id.tv_toolbar_title);
                if (!TextUtils.isEmpty(welcomeTitle)) {
                    tv_title_name.setText(welcomeTitle);
                    initToolBarwithIcon(welcomeTitle);
//                    getSupportActionBar().setTitle(welcomeTitle);
                } else {
                    tv_title_name.setText(welcomeTitle);
                    initToolBarwithIcon(cityname);

//                    getSupportActionBar().setTitle(cityname);
                }
//                TextView tvSubToolbarTitle = (TextView) findViewById(R.id.tv_toolbar_subtitle);
//                tvSubToolbarTitle.setText(getString(R.string.below_attractions));


            }
        }



        showingBannerAds(R.id.adView);
        setOnClickListener(R.id.iv_back);
    }

    private void getPlaceToVisitApi(Datum city) {
        String objectId = city.getObjectId();
        if (!TextUtils.isEmpty(objectId)) {
            HttpParamObject httpParamObject = ApiGenerator.getPlace(objectId);
            executeTask(AppConstants.TASK_CODES.PLACES_TO_VISIT, httpParamObject);
        }
    }
    @Override
    public void onPostExecute(Object response, int taskCode, Object... params) {
        super.onPostExecute(response, taskCode, params);

        if (null == response) {
            showToast(getResources().getString(R.string.no_response));
            return;
        }

        switch (taskCode) {
            case AppConstants.TASK_CODES.PLACES_TO_VISIT:
                placeToVisitResponse = (PlaceToVisitResponse) response;
                if (placeToVisitResponse != null) {
                    initTabs();
                    initViewPager();
                    Preferences.saveData(AppConstants.PREF_KEYS.KEY_CITY_DESTINATION_DATA,placeToVisitResponse.toString());
                    setPlacesList(placeToVisitResponse);
                    savePlaceData(placeToVisitResponse);

                }

                break;
        }
    }
    private void savePlaceData(PlaceToVisitResponse placeToVisitResponse) {
        if (placeToVisitResponse != null) {
            String objectId = city.getObjectId();
            Preferences.saveData(AppConstants.PREF_KEYS.KEY_PLACE_TO_VISIT_JSON + objectId, JsonUtil.toJson(placeToVisitResponse));
        }
    }

    private void changeTabsFont() {
        ViewGroup vg = (ViewGroup) tabLayout.getChildAt(0);
        int tabsCount = vg.getChildCount();
        for (int j = 0; j < tabsCount; j++) {
            ViewGroup vgTab = (ViewGroup) vg.getChildAt(j);
            int tabChildsCount = vgTab.getChildCount();
            for (int i = 0; i < tabChildsCount; i++) {
                View tabViewChild = vgTab.getChildAt(i);
                if (tabViewChild instanceof TextView) {
                    Typeface typeface = Typeface.createFromAsset(getAssets(), AppConstants.DEF_REGULAR_FONT);
                    ((TextView) tabViewChild).setTypeface(typeface, Typeface.NORMAL);
                }
            }
        }
    }

    @Override
    public void onClick(View v) {
        switch (v.getId()) {
            case R.id.iv_back:
                onBackPressed();
                break;
        }
    }




    //    @Override
//    public boolean onCreateOptionsMenu(Menu menu) {
//        getMenuInflater().inflate(R.menu.menu_sites_fragment, menu);
//        return super.onCreateOptionsMenu(menu);
//    }
//    //8077017166 jio
//
//
//    @Override
//    public boolean onOptionsItemSelected(MenuItem item) {
//        switch (item.getItemId()) {
//            case R.id.menu_download:
//                if (!Preferences.getData(city.getObjectId(), false))
//                    new TedPermission(this)
//                            .setPermissions(Manifest.permission.WRITE_EXTERNAL_STORAGE, Manifest.permission.READ_EXTERNAL_STORAGE)
//                            .setPermissionListener(new PermissionListener() {
//                                @Override
//                                public void onPermissionGranted() {
//                                    if (placeFragment.getAllSubsitesData() != null) {
//                                        placesList.clear();
//                                        placesList.addAll(placeFragment.getAllSubsitesData());
//                                        downloadFileService = new DownloadFileService(placesList, SiteDescriptionActivity.this, city);
//                                        downloadFileService.execute(placesList);
//                                    } else {
//                                        showToast("No subsites to download");
//                                    }
//                                }
//
//                                @Override
//                                public void onPermissionDenied(ArrayList<String> deniedPermissions) {
//                                    showToast("Permission to write to storage denied");
//                                }
//                            }).check();
//                else
//                    showToast("All audio guides are already downloaded");
//
//                break;
//        }
//        return super.onOptionsItemSelected(item);
//    }


    @Override
    protected void onHomePressed() {

    }

    private void setPlacesList(PlaceToVisitResponse placeToVisitResponse) {
        if (placeToVisitResponse != null) {

            List<PlaceData> dataList = placeToVisitResponse.getData();

            placesList.clear();
            placesList.addAll(dataList);


        }
    }


    public void initTabIcons() {
        tabIcons = new ArrayList<>();
        tabIcons.add(R.drawable.ic_audio_guide);
        tabIcons.add(R.drawable.ic_information);
        tabIcons.add(R.drawable.ic_map);
    }

    private void initViewPager() {
        tabLayout = (TabLayout) findViewById(R.id.sliding_tabs);
        viewPager = (ViewPager) findViewById(R.id.view_pager);
        CustomPagerAdapter pagerAdapter = new CustomPagerAdapter(getSupportFragmentManager(), tabsList, this);
        viewPager.setOffscreenPageLimit(2);
        viewPager.setAdapter(pagerAdapter);
        viewPager.setCurrentItem(0);
        tabLayout.setupWithViewPager(viewPager);
        //changeTabsFont();
        //setupTabIcons();
    }

    public void setupTabIcons() {
        tabLayout.getTabAt(0).setIcon(tabIcons.get(0));
        tabLayout.getTabAt(1).setIcon(tabIcons.get(1));
        tabLayout.getTabAt(2).setIcon(tabIcons.get(2));
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
    public Fragment getFragmentItem(int position, Object listItem) {

        switch (position) {
            case 0:
                return PlaceFragment.getInstance(city,placeToVisitResponse);
            case 1:
                return AboutFragment.getInstance(city);
            case 2:
                mapViewFragment = MapViewFragment.getInstance(placeToVisitResponse);
                return mapViewFragment;

            default:
                return AboutFragment.getInstance(city);
        }
    }


    @Override
    public CharSequence getPageTitle(int position, Object listItem) {
        return (CharSequence) listItem;
    }

    private void initTabs() {
        tabsList = new ArrayList<>();
//        tabsList.add("");
//        tabsList.add("");
//        tabsList.add("");
        tabsList.add(getResources().getString(R.string.about_place));
        tabsList.add(getResources().getString(R.string.sites));
        tabsList.add(getString(R.string.site_map));
    }

}
