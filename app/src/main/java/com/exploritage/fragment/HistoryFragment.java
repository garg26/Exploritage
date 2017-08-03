package com.exploritage.fragment;

import android.os.AsyncTask;
import android.support.v4.app.Fragment;
import android.text.TextUtils;
import android.widget.LinearLayout;

import com.exploritage.R;
import com.exploritage.model.responses.CityDetail;
import com.exploritage.util.AppBaseFragment;
import com.exploritage.util.ReadFromUrlUtil;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.net.MalformedURLException;
import java.net.URL;
import java.util.List;

/**
 * Created by ajay on 18/2/17.
 */
public class HistoryFragment extends AppBaseFragment implements ReadFromUrlUtil.ProcessFinished {

    private static HistoryFragment historyFragment;
    private LinearLayout viewContainer;
    private String placeDescriptionUrl;
    String title_about, title_thingsToDo, title_history, description;
    private List<CityDetail> cityDetailList;
    private ReadFromUrlUtil readFromUrlUtil;

    public static Fragment getInstance(String placeDescriptionUrl) {
        historyFragment = new HistoryFragment();
        historyFragment.placeDescriptionUrl=placeDescriptionUrl;
        return historyFragment;
    }

    @Override
    public void initViews() {
        title_about = getResources().getString(R.string.about);
        title_thingsToDo = getResources().getString(R.string.thingstodo);
        title_history = getResources().getString(R.string.history);
        readFromUrlUtil = new ReadFromUrlUtil(historyFragment);
//       description = getResources().getString(R.string.short_description);
        if(!TextUtils.isEmpty(placeDescriptionUrl))
            readFromUrlUtil.execute(placeDescriptionUrl);



    }



    @Override
    public void onProcessFinished(String s) {
        viewContainer = (LinearLayout) findView(R.id.lay_view_container);
        if (viewContainer != null && null != getActivity()) {
            if (!TextUtils.isEmpty(s)) {
                viewContainer.addView(getDetailView(title_about, s, viewContainer, true));
            } else
                viewContainer.addView(getDetailView(title_about, "Not available", viewContainer, true));
        }

    }


    @Override
    public int getViewID() {
        return R.layout.fragment_history;
    }

}