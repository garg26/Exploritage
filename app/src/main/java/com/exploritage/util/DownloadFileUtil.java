package com.exploritage.util;

import android.app.DownloadManager;
import android.content.Context;
import android.net.Uri;
import android.os.Environment;
import android.text.TextUtils;
import android.util.Log;
import android.widget.Toast;

import java.io.BufferedInputStream;
import java.io.File;
import java.io.FileOutputStream;
import java.io.IOException;
import java.io.InputStream;
import java.net.URL;
import java.net.URLConnection;
import java.util.List;

import simplifii.framework.utility.AppConstants;

import static android.content.Context.DOWNLOAD_SERVICE;
import static simplifii.framework.fragments.BaseFragment.AsyncManager.TAG;

/**
 * Created by ajay on 1/3/17.
 */

public class DownloadFileUtil {

    public static String downloadAudioFile(String audioUrl, String filename) {

        final int TIMEOUT_CONNECTION = 8000;//5sec
        final int TIMEOUT_SOCKET = 30000;//30sec

        String filepath = "";
        //Open a connection to that URL.
        URLConnection ucon = null;
        try {
            filename = audioUrl.substring(audioUrl.lastIndexOf('/') + 1, audioUrl.length());
            if (TextUtils.isEmpty(filename))
                filename = "" + System.currentTimeMillis();

            File folder = new File(Environment.getExternalStorageDirectory(), AppConstants.EXPLORITAGE_AUDIO_DOWNLOAD_FOLDER_EXTERNAL);
            if (!folder.exists()) {
                folder.mkdirs();
            }
            File file = new File(folder.getAbsoluteFile(), filename + ".mp3");
            if (file.exists()) {
                return file.getPath();
            }

            URL url = new URL(audioUrl);
            long startTime = System.currentTimeMillis();
            Log.i(TAG, "audio download beginning: " + url);
            ucon = url.openConnection();
            //this timeout affects how long it takes for the app to realize there's a connection problem
            ucon.setReadTimeout(TIMEOUT_CONNECTION);
            ucon.setConnectTimeout(TIMEOUT_SOCKET);


            //Define InputStreams to read from the URLConnection.
            // uses 3KB download buffer
            InputStream is = ucon.getInputStream();


            FileOutputStream outStream = new FileOutputStream(file);
            BufferedInputStream inStream = new BufferedInputStream(is, 1024 * 5);
            byte[] buff = new byte[5 * 1024];
            int totalSize = ucon.getContentLength();
            int downloadSize = 0;

            //Read bytes (and store them) until there is nothing more to read(-1)
            int bufferlength = 0;
            while ((bufferlength = inStream.read(buff)) != -1) {
                outStream.write(buff, 0, bufferlength);
                downloadSize = downloadSize + bufferlength;
            }

            //clean up
            outStream.flush();
            outStream.close();
            inStream.close();

            if (downloadSize == totalSize) {
                filepath = file.getPath();
            }

        } catch (IOException e) {
            e.printStackTrace();
        }

/*
        Log.i(TAG, "download completed in "
                + ((System.currentTimeMillis() - startTime) / 1000)
                + " sec");*/


        return filepath;

    }


/*

    private String downloadFile(String audiourl) {
        String filepath = null;
        File file;
        try {
            //set the download URL, a url that points to a file on the internet
            //this is the file to be downloaded
            URL url = new URL(audiourl);
            //create the new connection
            HttpURLConnection urlConnection = (HttpURLConnection) url.openConnection();

            //set up some things on the connection
            urlConnection.setRequestMethod("GET");
            urlConnection.setDoOutput(true);
            //and connect!
            urlConnection.connect();
            //set the path where we want to save the file
            //in this case, going to save it on the root directory of the
            //sd card.

            File SDCardRoot = Environment.getExternalStorageDirectory();
            //create a new file, specifying the path, and the filename
            //which we want to save the file as.

            String filename = "audiofile.mp3";   // you can download to any type of file ex:.jpeg (image) ,.txt(text file),.mp3 (audio file)
            Log.i("Local filename:", "" + filename);
            file = new File(SDCardRoot, filename);

            if (file.createNewFile()) {
                file.createNewFile();
            }

            //this will be used to write the downloaded data into the file we created
            FileOutputStream fileOutput = new FileOutputStream(file);

            //this will be used in reading the data from the internet
            InputStream inputStream = urlConnection.getInputStream();

            //this is the total size of the file
            int totalSize = urlConnection.getContentLength();
            //variable to store total downloaded bytes
            int downloadedSize = 0;

            //create a buffer...
            byte[] buffer = new byte[1024];
            int bufferLength = 0; //used to store a temporary size of the buffer

            //now, read through the input buffer and write the contents to the file
            while ((bufferLength = inputStream.read(buffer)) > 0) {
                //add the data in the buffer to the file in the file output stream (the file on the sd card
                fileOutput.write(buffer, 0, bufferLength);
                //add up the size so we know how much is downloaded
                downloadedSize += bufferLength;
                //this is where you would do something to report the prgress, like this maybe
                Log.i("Progress:", "downloadedSize:" + downloadedSize + "totalSize:" + totalSize);

            }
            //close the output stream when done
            fileOutput.close();
            if (downloadedSize == totalSize) filepath = file.getPath();

            //catch some possible errors...
        } catch (MalformedURLException e) {
            e.printStackTrace();
        } catch (IOException e) {
            filepath = null;
            e.printStackTrace();
        }
        Log.i("filepath:", " " + filepath);

        return filepath;
    }
*/


}
