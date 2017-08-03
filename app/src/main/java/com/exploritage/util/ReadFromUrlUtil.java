package com.exploritage.util;

import android.os.AsyncTask;
import android.text.TextUtils;

import com.exploritage.fragment.AboutAppFragment;
import com.exploritage.fragment.AboutFragment;
import com.exploritage.fragment.HistoryFragment;
import com.exploritage.fragment.HowToUseFragment;

import org.w3c.dom.Text;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.lang.ref.WeakReference;
import java.net.MalformedURLException;
import java.net.URL;

import simplifii.framework.utility.AppConstants;
import simplifii.framework.utility.Preferences;

/**
 * Created by saurabh on 1/4/17.
 */

public class ReadFromUrlUtil extends AsyncTask<String, Void, String> {
    private WeakReference<ProcessFinished> processFinished;

    public ReadFromUrlUtil(HistoryFragment historyFragment) {
        processFinished = new WeakReference<ProcessFinished>(historyFragment);
    }

    public ReadFromUrlUtil() {
    }

    public ReadFromUrlUtil(AboutFragment historyFragment) {
        processFinished = new WeakReference<ProcessFinished>(historyFragment);
    }

    public ReadFromUrlUtil(AboutAppFragment aboutAppFragment) {
        processFinished = new WeakReference<ProcessFinished>(aboutAppFragment);
    }

    public ReadFromUrlUtil(HowToUseFragment howToUseFragment) {
        processFinished = new WeakReference<ProcessFinished>(howToUseFragment);
    }

    @Override
    protected String doInBackground(String... params) {
        String textUrl = params[0];
        String savedText = Preferences.getData(AppConstants.PREF_KEYS.TEXT_FILE_URL + textUrl, null);
        if (!TextUtils.isEmpty(savedText)) {
            return savedText;
        }
        String text = getTextFromgUrl(textUrl);
        if (!TextUtils.isEmpty(text)) {
            Preferences.saveData(AppConstants.PREF_KEYS.TEXT_FILE_URL + textUrl, text);
        }
        return text;
    }

    @Override
    protected void onPostExecute(String s) {
        super.onPostExecute(s);
        if (!TextUtils.isEmpty(s) && processFinished != null && processFinished.get() != null)
            processFinished.get().onProcessFinished(s);
    }

    public String getTextFromgUrl(String textFileUrl) {
        StringBuilder total = new StringBuilder();
        try {
            // Create a URL for the desired page
            URL url = new URL(textFileUrl);
            // Read all the text returned by the server
            BufferedReader in = new BufferedReader(new InputStreamReader(url.openStream()));
            total = new StringBuilder();
            String line;
            while ((line = in.readLine()) != null) {
                // line is one line of text; readLine() strips the newline character(s)
                total.append(line);
            }
            in.close();

        } catch (MalformedURLException e) {

        } catch (IOException e) {

        }
        return total.toString();
    }

    public interface ProcessFinished {
        public void onProcessFinished(String s);
    }
}
