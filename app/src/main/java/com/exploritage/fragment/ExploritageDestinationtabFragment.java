package com.exploritage.fragment;

import android.support.design.widget.TabLayout;
import android.support.v4.app.Fragment;
import android.support.v4.view.ViewPager;

import com.exploritage.R;
import com.exploritage.util.HomeFragmentListener;

import java.util.ArrayList;
import java.util.List;

import simplifii.framework.ListAdapters.CustomPagerAdapter;
import simplifii.framework.fragments.BaseFragment;

/**
 * Created by kartikeya-pc on 7/12/17.
 */

public class ExploritageDestinationtabFragment extends BaseFragment implements CustomPagerAdapter.PagerAdapterInterface {
    private final List<String> mFragmentList = new ArrayList<>();
    private HomeFragmentListener homeFragmentListener;



    public static Fragment getInstance(HomeFragmentListener homeFragmentListener) {
        ExploritageDestinationtabFragment exploritageDestinationtabFragment = new ExploritageDestinationtabFragment();
        exploritageDestinationtabFragment.homeFragmentListener = homeFragmentListener;
        return exploritageDestinationtabFragment;
    }

    @Override
    public void initViews() {
        ViewPager viewPager = (ViewPager) findView(R.id.view_pager);
        TabLayout tabLayout = (TabLayout) findView(R.id.sliding_tabs);
        setupViewPager(viewPager,tabLayout);
    }

    private void setupViewPager(ViewPager viewPager, TabLayout tabLayout) {
        initTabs(tabLayout);
        CustomPagerAdapter pagerAdapter = new CustomPagerAdapter(getChildFragmentManager(), mFragmentList, this);
        viewPager.setAdapter(pagerAdapter);
        tabLayout.setupWithViewPager(viewPager);
    }

    private void initTabs(TabLayout tabLayout) {
        mFragmentList.add("About");
        mFragmentList.add("FAQ's");
        mFragmentList.add("Packages");

    }

    @Override
    public int getViewID() {
        return R.layout.layout_tablayout;
    }

    @Override
    public Fragment getFragmentItem(int position, Object listItem) {
        switch (position) {
            case 0:
                AvailableCityGuidesFragment cityGuidesFragment = new AvailableCityGuidesFragment();
                return cityGuidesFragment;
            case 1:
                HistoryFragment historyFragment1 = new HistoryFragment();
                return historyFragment1;
            case 2:
                PackageFragment packageFragment = new PackageFragment();
                return packageFragment;
            default:
                return null;
        }
    }

    @Override
    public CharSequence getPageTitle(int position, Object listItem) {
        return mFragmentList.get(position);
    }
}
