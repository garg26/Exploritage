package com.exploritage.service;

import android.app.IntentService;
import android.content.Context;
import android.content.Intent;
import android.graphics.Bitmap;
import android.graphics.BitmapFactory;
import android.os.Bundle;
import android.os.Environment;
import android.text.TextUtils;

import java.io.File;
import java.io.FileOutputStream;
import java.net.URL;
import java.net.URLConnection;
import java.util.ArrayList;

import simplifii.framework.utility.AppConstants;
import simplifii.framework.utility.Preferences;

/**
 * Created by admin on 4/6/2017.
 */

public class SaveImageOnExternalStorageService extends IntentService {
    private String imagesFolderName = AppConstants.EXPLORITAGE_IMAGES_DOWNLOAD_FOLDER_EXTERNAL;
    private File storageRoot;
    public File imageFolder;

    public static void startService(ArrayList<String> images, Context context) {
        Intent i = new Intent(context, SaveImageOnExternalStorageService.class);
        Bundle b = new Bundle();
        b.putSerializable(AppConstants.BUNDLE_KEYS.KEY_IMAGES, images);
        i.putExtras(b);
        context.startService(i);
    }

    @Override
    public void onCreate() {
        super.onCreate();
        storageRoot = Environment.getExternalStorageDirectory();
        imageFolder = new File(storageRoot.getAbsoluteFile(), imagesFolderName);
        if (!imageFolder.exists()) {
            imageFolder.mkdirs();
        }
    }

    public SaveImageOnExternalStorageService() {
        super("hello");
    }


    @Override
    protected void onHandleIntent(Intent intent) {
        Bundle b = intent.getExtras();
        ArrayList<String> imagesList = (ArrayList<String>) b.getSerializable(AppConstants.BUNDLE_KEYS.KEY_IMAGES);
        String imageFileName;
        File imageFile;
        for (String imageUrl : imagesList) {
            imageFileName = Preferences.getData(imageUrl, null);
            if (!TextUtils.isEmpty(imageFileName)) {
                imageFile = new File(imageFileName);
                if (!doesFileExists(imageFile))
                    downloadImageFile(imageUrl);
            } else
                downloadImageFile(imageUrl);

        }

    }

    private boolean doesFileExists(File file) {
        return file.exists();
    }

    private void downloadImageFile(String imageUrl) {
        URL url;
        URLConnection connection;
        Bitmap bitmap;
        try {
            url = new URL(imageUrl);
            String imageName = imageUrl.substring(imageUrl.lastIndexOf('/') + 1, imageUrl.length());
            File imageFile = new File(imageFolder.getAbsoluteFile(), imageName);
            connection = url.openConnection();
            bitmap = BitmapFactory.decodeStream(connection.getInputStream());

            FileOutputStream out = new FileOutputStream(imageFile);
            bitmap.compress(Bitmap.CompressFormat.JPEG, 90, out);
            out.flush();
            out.close();
            Preferences.saveData(imageUrl, imageFolder.getAbsolutePath() + "/" + imageName);
        } catch (Exception e) {
            e.printStackTrace();
        }

    }
}
