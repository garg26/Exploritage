package com.exploritage.util;

import android.text.TextUtils;

import java.util.ArrayList;

import simplifii.framework.utility.AppConstants;
import simplifii.framework.utility.Preferences;

/**
 * Created by nbansal2211 on 11/04/17.
 */

public class MultipleTextFilesLoader extends ReadFromUrlUtil {
    private ArrayList<String> files;

    public MultipleTextFilesLoader() {

    }

    public MultipleTextFilesLoader(ArrayList<String> files) {
        this.files = files;
    }

    @Override
    protected String doInBackground(String... params) {
        if (files != null && files.size() > 0) {
            for (String textUrl : files) {
                String savedText = Preferences.getData(AppConstants.PREF_KEYS.TEXT_FILE_URL + textUrl, null);
                if (!TextUtils.isEmpty(savedText)) {
                    continue;
                }
                String text = getTextFromgUrl(textUrl);
                if (!TextUtils.isEmpty(text)) {
                    Preferences.saveData(AppConstants.PREF_KEYS.TEXT_FILE_URL + textUrl, text);
                }
            }
        }
        return "";
    }

    @Override
    protected void onPostExecute(String s) {
    }
}
