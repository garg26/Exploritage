package com.exploritage.util;

import android.app.Activity;
import android.content.Context;
import android.os.AsyncTask;
import android.text.TextUtils;
import android.view.View;
import android.widget.TextView;
import android.widget.Toast;

import com.exploritage.R;
import com.exploritage.activity.SiteDescriptionActivity;
import com.exploritage.model.responses.CityDetail;
import com.exploritage.model.responses.Datum;
import com.exploritage.model.responses.place.PlaceData;

import java.lang.ref.WeakReference;
import java.util.ArrayList;
import java.util.List;

import simplifii.framework.utility.AppConstants;
import simplifii.framework.utility.Preferences;

/**
 * Created by Dhillon on 3/28/2017.
 */

public class DownloadFileService extends AsyncTask<List<PlaceData>, Void, Void> {
    private List<PlaceData> subcityDetailList;
    private Context ctx;
    private String cityId;
    private Datum city;
    private WeakReference<TextView> downloadingTextView;

    public DownloadFileService(List<PlaceData> subcityDetailList, Context ctx, Datum city) {
        this.subcityDetailList = subcityDetailList;
        this.ctx = ctx;
        this.city = city;
        TextView tv = (TextView) ((Activity) ctx).findViewById(R.id.tv_downloading);
        tv.setVisibility(View.VISIBLE);
        downloadingTextView = new WeakReference<TextView>(tv);
        cityId = city.getObjectId();
    }

    @Override
    protected Void doInBackground(List<PlaceData>... params) {
        for (int i = 0; i < params[0].size(); i++) {
            PlaceData placeDetail = (PlaceData) params[0].get(i);
            String fileName = Preferences.getData(AppConstants.PREF_KEYS.KEY_AUDIO_FILE + placeDetail.getAudiourl(), "");

            if (!TextUtils.isEmpty(placeDetail.getAudiourl()) && TextUtils.isEmpty(Preferences.getData(AppConstants.PREF_KEYS.KEY_AUDIO_FILE + placeDetail.getAudiourl(), ""))) {
                Preferences.saveData(AppConstants.PREF_KEYS.KEY_AUDIO_FILE + placeDetail.getAudiourl(),
                        DownloadFileUtil.downloadAudioFile(placeDetail.getAudiourl(), placeDetail.getName()));
            }
        }
        return null;
    }

    @Override
    protected void onPreExecute() {
        super.onPreExecute();
        Toast.makeText(ctx, "Downloading all audio guides for " + city.getCityname(), Toast.LENGTH_SHORT).show();
        downloadingTextView.get().setText("Downloading all audio guides for " + city.getCityname());
    }

    @Override
    protected void onPostExecute(Void aVoid) {
        super.onPostExecute(aVoid);
        Toast.makeText(ctx, "Downloaded all audio guides for " + city.getCityname(), Toast.LENGTH_SHORT).show();
        Preferences.saveData(cityId, true);
        if (downloadingTextView.get() != null) {
            downloadingTextView.get().setText("Downloaded all audio guides for " + city.getCityname());
        }
    }

}
