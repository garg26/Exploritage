package com.exploritage.fragment;

import android.os.AsyncTask;
import android.text.TextUtils;
import android.widget.TextView;

import com.exploritage.R;
import com.exploritage.util.HomeFragmentListener;
import com.exploritage.util.ReadFromUrlUtil;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.net.MalformedURLException;
import java.net.URL;

import simplifii.framework.fragments.BaseFragment;
import simplifii.framework.utility.AppConstants;

/**
 * Created by ajay on 2/3/17.
 */

public class HowToUseFragment extends BaseFragment implements ReadFromUrlUtil.ProcessFinished {

    private HomeFragmentListener homeFragmentListener;
    private TextView tvHowToUse;
    private ReadFromUrlUtil readFromUrlUtil;
    private static HowToUseFragment howToUseFragment;

    public static HowToUseFragment getInstance(HomeFragmentListener homeFragmentListener) {
        howToUseFragment = new HowToUseFragment();
        howToUseFragment.homeFragmentListener = homeFragmentListener;
        return howToUseFragment;
    }

    @Override
    public void initViews() {
//        homeFragmentListener.setTitle("How To Use");
        readFromUrlUtil = new ReadFromUrlUtil(howToUseFragment);
        tvHowToUse = (TextView) findView(R.id.tv_how_to_use);
        readFromUrlUtil.execute(AppConstants.PAGE_URL.HOWTOUSE_TEXT_URL);
    }

    @Override
    public void onProcessFinished(String s) {
        if (!TextUtils.isEmpty(s))
            tvHowToUse.setText(s);
    }


    private class ReadFromUrl extends AsyncTask<String, Void, String> {

        @Override
        protected String doInBackground(String... params) {

            String textUrl = params[0];
            String text = getTextFromgUrl(textUrl);

            return text;
        }

        @Override
        protected void onPostExecute(String result) {
            super.onPostExecute(result);
            if (!TextUtils.isEmpty(result)) {
                tvHowToUse.setText(result);
            }
        }
    }


    public String getTextFromgUrl(String textFileUrl) {
        StringBuilder total = null;
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


    @Override
    public int getViewID() {
        return R.layout.fragment_how_to_use;
    }


}
