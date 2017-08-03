package com.exploritage.fragment;

import android.graphics.Typeface;
import android.os.Bundle;
import android.support.design.widget.TabLayout;
import android.support.v4.app.Fragment;
import android.support.v4.view.ViewPager;
import android.view.Menu;
import android.view.MenuInflater;
import android.view.MenuItem;
import android.view.View;
import android.view.ViewGroup;
import android.widget.TextView;

import com.exploritage.R;
import com.exploritage.activity.SiteDescriptionActivity;
import com.exploritage.model.responses.CityDetail;

import java.util.ArrayList;
import java.util.List;

import simplifii.framework.ListAdapters.CustomPagerAdapter;
import simplifii.framework.fragments.BaseFragment;
import simplifii.framework.utility.AppConstants;

/**
 * Created by ajay on 18/2/17.
 */

public class CityFragment extends BaseFragment implements CustomPagerAdapter.PagerAdapterInterface {

    private List<String> tabsList;
    private CityDetail cityDetailHolder;
    private String audioImageUrl;
    private String audioFileUrl;
    private String placeDescriptionUrl;
    private String mapUrl;

    private TabLayout tabLayout;
    private List<Integer> tabIcons;

    public static Fragment getInstance() {
        CityFragment cityFragment = new CityFragment();
        return cityFragment;
    }

    @Override
    public void initViews() {
        Bundle arguments = getArguments();
        if (arguments != null) {

            cityDetailHolder = (CityDetail) arguments.getSerializable(AppConstants.BUNDLE_KEYS.CITY_DETAIL);
            if (cityDetailHolder != null) {
                audioImageUrl = cityDetailHolder.getAudioimage();
                audioFileUrl = cityDetailHolder.getAudiourl();
                placeDescriptionUrl = cityDetailHolder.getShortdescription();
                mapUrl = cityDetailHolder.getSitemap();
            }
        }
        initTabs();
        tabLayout = (TabLayout) findView(R.id.tab_layout_city);
        ViewPager viewPager = (ViewPager) findView(R.id.viewpager_city);
        CustomPagerAdapter pagerAdapter = new CustomPagerAdapter(getFragmentManager(), tabsList, this);
        viewPager.setAdapter(pagerAdapter);
        viewPager.setOffscreenPageLimit(2);
        tabLayout.setupWithViewPager(viewPager);
        changeTabsFont();
        setupTabIcons();
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
                    Typeface typeface = Typeface.createFromAsset(getActivity().getAssets(), AppConstants.DEF_REGULAR_FONT);
                    ((TextView) tabViewChild).setTypeface(typeface, Typeface.NORMAL);
                }
            }
        }
    }

    private void initTabs() {
        tabsList = new ArrayList<>();
        tabsList.add(getResources().getString(R.string.audio_guide));
        tabsList.add(getResources().getString(R.string.about));
        tabsList.add(getResources().getString(R.string.location));
    }

    private void initTabIcons() {
        tabIcons = new ArrayList<>();
        tabIcons.add(R.drawable.ic_audio_guide);
        tabIcons.add(R.drawable.ic_information);
        tabIcons.add(R.drawable.ic_map);
    }

    private void setupTabIcons() {
        initTabIcons();
        tabLayout.getTabAt(0).setIcon(tabIcons.get(0));
        tabLayout.getTabAt(1).setIcon(tabIcons.get(1));
        tabLayout.getTabAt(2).setIcon(tabIcons.get(2));
    }


    @Override
    public int getViewID() {
        return R.layout.fragment_city;
    }

    @Override
    public Fragment getFragmentItem(int position, Object listItem) {
        switch (position) {
            case 0:
                return GuideFragment.getInstance(audioFileUrl, audioImageUrl);
            case 1:
                return HistoryFragment.getInstance(placeDescriptionUrl);
            case 2:
                return SubsitelocationFragment.getInstance(mapUrl);
            default:
                return GuideFragment.getInstance(audioFileUrl, audioImageUrl);
        }
    }

    @Override
    public CharSequence getPageTitle(int position, Object listItem) {
        return (CharSequence) listItem;
    }
}
