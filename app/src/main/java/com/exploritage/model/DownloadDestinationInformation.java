package com.exploritage.model;

import java.io.Serializable;

/**
 * Created by kartikeya on 27/7/17.
 */

public class DownloadDestinationInformation implements Serializable {


    private String destinationImageUrl;
    private String cityID;
    private String audioFilePath;

    public String getCityID() {
        return cityID;
    }

    public void setCityID(String cityID) {
        this.cityID = cityID;
    }

    public String getObjectID() {
        return objectID;
    }

    public void setObjectID(String objectID) {
        this.objectID = objectID;
    }

    private String objectID;

    private String destinationName;

    public String getAudioFilePath() {
        return audioFilePath;
    }

    public void setAudioFilePath(String audioFilePath) {
        this.audioFilePath = audioFilePath;
    }

    public DownloadDestinationInformation(String cityID, String objectID, String destinationName, String destination_imageUrl, String audioFileUrl) {
        this.cityID = cityID;
        this.objectID = objectID;
        this.destinationName = destinationName;
        this.destinationImageUrl = destination_imageUrl;
        this.audioFilePath =audioFileUrl;
    }

    public String getDestinationImageUrl() {
        return destinationImageUrl;
    }

    public void setDestinationImageUrl(String destinationImageUrl) {
        this.destinationImageUrl = destinationImageUrl;
    }



    public String getDestinationName() {
        return destinationName;
    }

    public void setDestinationName(String destinationName) {
        this.destinationName = destinationName;
    }

//    public static List<DownloadDestinationInformation> parseJson() {
//        List<DownloadDestinationInformation> audioCityInformations = new ArrayList<>();
//        Gson gson = new Gson();
//        String json = Preferences.getData(AppConstants.PREF_KEYS.DOWNLOAD_AUDIO_DETAIL, "");
//        if (!TextUtils.isEmpty(json)){
//            try {
//                JSONArray jsonArray = new JSONArray(json);
//                for (int i=0;i<jsonArray.length();i++){
//                    String string = jsonArray.getString(i);
//                    audioCityInformations.add(gson.fromJson(string,DownloadDestinationInformation.class));
//                }
//            } catch (JSONException e) {
//                e.printStackTrace();
//            }
//
//        }
//        return audioCityInformations;
//    }

//    public void saveData(DownloadDestinationInformation audioCityInformation) {
//        String json = Preferences.getData(AppConstants.PREF_KEYS.DOWNLOAD_AUDIO_DETAIL, "");
//        try {
//            if (!TextUtils.isEmpty(json)) {
//
//                JSONArray jsonArray = new JSONArray(json);
//                jsonArray.put(new Gson().toJson(audioCityInformation));
//                Preferences.saveData(AppConstants.PREF_KEYS.DOWNLOAD_AUDIO_DETAIL, jsonArray.toString());
//
//            } else {
//                JSONArray jsonArray = new JSONArray();
//                jsonArray.put(new Gson().toJson(audioCityInformation));
//                Preferences.saveData(AppConstants.PREF_KEYS.DOWNLOAD_AUDIO_DETAIL, jsonArray.toString());
//            }
//        } catch (JSONException e) {
//            e.printStackTrace();
//        }
//    }

}
