package com.exploritage.fragment;

import android.preference.Preference;
import android.support.v4.app.Fragment;
import android.text.TextUtils;
import android.view.View;
import android.widget.LinearLayout;

import com.exploritage.R;
import com.exploritage.model.responses.Datum;
import com.exploritage.util.AppBaseFragment;
import com.exploritage.util.ReadFromUrlUtil;

import simplifii.framework.utility.Preferences;

/**
 * Created by ajay on 21/2/17.
 */
public class AboutFragment extends AppBaseFragment implements ReadFromUrlUtil.ProcessFinished {

    private Datum city;
    private LinearLayout viewContainer;
    private static AboutFragment aboutFragment;
    private ReadFromUrlUtil readFromUrlUtil;
    private String title;

    public static AboutFragment getInstance(Datum city) {
        aboutFragment = new AboutFragment();
        aboutFragment.city = city;
        return aboutFragment;
    }

    @Override
    public void initViews() {
        if (city != null)
            if (TextUtils.isEmpty(Preferences.getData(city.getDescription(), ""))) {
                readFromUrlUtil = new ReadFromUrlUtil(aboutFragment);
                if (!TextUtils.isEmpty(city.getDescription())) {
                    String description = city.getDescription();
                    readFromUrlUtil.execute(description);
                }
            } else {
                setAboutText(Preferences.getData(city.getDescription(), ""));
            }
        viewContainer = (LinearLayout) findView(R.id.lay_view_container);
    }

    @Override
    public int getViewID() {
        return R.layout.fragment_about;
    }

    @Override
    public void onProcessFinished(String s) {
        Preferences.saveData(city.getDescription(), s);
        setAboutText(s);

    }

    private void setAboutText(String s) {
        title = getActivity().getResources().getString(R.string.about);
        viewContainer = (LinearLayout) findView(R.id.lay_view_container);
        viewContainer.addView(getDetailView(title, s, viewContainer, true));
    }
}
