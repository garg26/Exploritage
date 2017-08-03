package com.exploritage.activity;

import android.Manifest;
import android.graphics.Typeface;
import android.os.Bundle;
import android.support.design.widget.TabLayout;
import android.support.v4.app.Fragment;
import android.support.v4.view.ViewPager;
import android.text.TextUtils;
import android.util.TypedValue;
import android.view.Menu;
import android.view.MenuItem;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;
import android.widget.TextView;

import com.exploritage.R;
import com.exploritage.fragment.AboutFragment;
import com.exploritage.fragment.SitelocationFragment;
import com.exploritage.fragment.PlaceFragment;
import com.exploritage.model.responses.CityDetail;
import com.exploritage.model.responses.Datum;
import com.exploritage.model.responses.place.PlaceData;
import com.exploritage.util.DownloadFileService;
import com.gun0912.tedpermission.PermissionListener;
import com.gun0912.tedpermission.TedPermission;

import java.util.ArrayList;
import java.util.List;

import simplifii.framework.ListAdapters.CustomPagerAdapter;
import simplifii.framework.activity.BaseActivity;
import simplifii.framework.utility.AppConstants;
import simplifii.framework.utility.Preferences;

/**
 * Created by ajay on 21/2/17.
 */

public class SiteDescriptionActivity extends BaseActivity implements CustomPagerAdapter.PagerAdapterInterface {
    public List<String> tabsList;
    private List<CityDetail> cityDetailList;
    private ArrayList<PlaceData> placesList;
    private Datum city;
    private ImageView ivBackArrow;
    public TabLayout tabLayout;
    public List<Integer> tabIcons;
    private PlaceFragment placeFragment;
    private DownloadFileService downloadFileService;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_sitedeccription);
//        initToolBar("");
        cityDetailList = new ArrayList<>();
        placesList = new ArrayList<>();
        initTabs();
        initTabIcons();
        initViewPager();

        Bundle extras = getIntent().getExtras();
        if (extras != null) {
            city = (Datum) extras.get(AppConstants.BUNDLE_KEYS.CITY_DATA);
            if (city != null) {
                String cityname = city.getCityname();
                String welcomeTitle = city.getWelcomeTitle();

//                TextView tvToolbarTitle = (TextView) findViewById(R.id.tv_toolbar_title);
                if (!TextUtils.isEmpty(welcomeTitle)) {
                    initToolBar(welcomeTitle);
//                    getSupportActionBar().setTitle(welcomeTitle);
                } else {
                    initToolBar(cityname);
//                    getSupportActionBar().setTitle(cityname);
                }
//                TextView tvSubToolbarTitle = (TextView) findViewById(R.id.tv_toolbar_subtitle);
//                tvSubToolbarTitle.setText(getString(R.string.below_attractions));
                placeFragment = PlaceFragment.getInstance(city);
            }
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
    public boolean onCreateOptionsMenu(Menu menu) {
        getMenuInflater().inflate(R.menu.menu_sites_fragment, menu);
        return super.onCreateOptionsMenu(menu);
    }
    //8077017166 jio


    @Override
    public boolean onOptionsItemSelected(MenuItem item) {
        switch (item.getItemId()) {
            case R.id.menu_download:
                if (!Preferences.getData(city.getObjectId(), false))
                    new TedPermission(this)
                            .setPermissions(Manifest.permission.WRITE_EXTERNAL_STORAGE, Manifest.permission.READ_EXTERNAL_STORAGE)
                            .setPermissionListener(new PermissionListener() {
                                @Override
                                public void onPermissionGranted() {
                                    if (placeFragment.getAllSubsitesData() != null) {
                                        placesList.clear();
                                        placesList.addAll(placeFragment.getAllSubsitesData());
                                        downloadFileService = new DownloadFileService(placesList, SiteDescriptionActivity.this, city);
                                        downloadFileService.execute(placesList);
                                    } else {
                                        showToast("No subsites to download");
                                    }
                                }

                                @Override
                                public void onPermissionDenied(ArrayList<String> deniedPermissions) {
                                    showToast("Permission to write to storage denied");
                                }
                            }).check();
                else
                    showToast("All audio guides are already downloaded");

                break;
        }
        return super.onOptionsItemSelected(item);
    }

    public void initTabIcons() {
        tabIcons = new ArrayList<>();
        tabIcons.add(R.drawable.ic_audio_guide);
        tabIcons.add(R.drawable.ic_information);
        tabIcons.add(R.drawable.ic_map);
    }

    private void initViewPager() {
        tabLayout = (TabLayout) findViewById(R.id.tab_layout_city);
        ViewPager viewPager = (ViewPager) findViewById(R.id.viewpager_city);
        CustomPagerAdapter pagerAdapter = new CustomPagerAdapter(getSupportFragmentManager(), tabsList, this);
        viewPager.setOffscreenPageLimit(2);
        viewPager.setAdapter(pagerAdapter);
        viewPager.setCurrentItem(0);
        tabLayout.setupWithViewPager(viewPager);
        changeTabsFont();
        setupTabIcons();
    }

    public void setupTabIcons() {
        tabLayout.getTabAt(0).setIcon(tabIcons.get(0));
        tabLayout.getTabAt(1).setIcon(tabIcons.get(1));
        tabLayout.getTabAt(2).setIcon(tabIcons.get(2));
    }


    @Override
    public Fragment getFragmentItem(int position, Object listItem) {

        switch (position) {
            case 0:
                return placeFragment;
            case 1:
                return AboutFragment.getInstance(city);
            case 2:
                return SitelocationFragment.getInstance(city);
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
        tabsList.add(getResources().getString(R.string.audio_guide));
        tabsList.add(getResources().getString(R.string.about));
        tabsList.add(getString(R.string.site_map));
    }

}
