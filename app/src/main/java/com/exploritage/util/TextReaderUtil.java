package com.exploritage.util;

import android.os.AsyncTask;
import android.text.TextUtils;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.net.MalformedURLException;
import java.net.URL;

/**
 * Created by ajay on 1/3/17.
 */

public class TextReaderUtil {

    private String text;

    public TextReaderUtil(String textUrl) {
        new ReadFromUrl().execute(textUrl);
    }

    public String getText() {
        return this.text;
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
                text = result;
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

}
