package com.exploritage.model;

import java.util.List;

/**
 * Created by kartikeya on 27/7/17.
 */

public class DownloadCityInformation {

    private String cityID;
    private String cityName;
    private String cityImageUrl;
    private  List<String> audioFilePath;

    public List<String> getAudioFilePath() {
        return audioFilePath;
    }

    public void setAudioFilePath(List<String> audioFilePath) {
        this.audioFilePath = audioFilePath;
    }

    public DownloadCityInformation(String cityID, String cityname, String image_url) {
        this.cityID=cityID;
        this.cityName=cityname;
        this.cityImageUrl=image_url;


    }

    public String getCityID() {
        return cityID;
    }

    public void setCityID(String cityID) {
        this.cityID = cityID;
    }

    public String getCityName() {
        return cityName;
    }

    public void setCityName(String cityName) {
        this.cityName = cityName;
    }

    public String getCityImageUrl() {
        return cityImageUrl;
    }

    public void setCityImageUrl(String cityImageUrl) {
        this.cityImageUrl = cityImageUrl;
    }



}
