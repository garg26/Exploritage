package com.exploritage.model.responses.place;

import com.exploritage.model.responses.CityDetail;
import com.google.gson.annotations.Expose;
import com.google.gson.annotations.SerializedName;

import java.io.Serializable;

/**
 * Created by ajay on 21/2/17.
 */

public class PlaceData implements Serializable {



    private String welcomeTitle;
    @SerializedName("created")
    @Expose
    private long created;
    @SerializedName("imageurl")
    @Expose
    private String imageurl;
    @SerializedName("name")
    @Expose
    private String name;
    @SerializedName("___class")
    @Expose
    private String ___class;
    @SerializedName("cityid")
    @Expose
    private String cityid;
    @SerializedName("ownerId")
    @Expose
    private Object ownerId;
    @SerializedName("updated")
    @Expose
    private long updated;
    @SerializedName("objectId")
    @Expose
    private String objectId;
    @SerializedName("__meta")
    @Expose
    private String __meta;
    @SerializedName("audiourl")
    @Expose
    private String audiourl;

    private CityDetail cityDetailObject;

    public CityDetail getCityDetailObject() {
        return cityDetailObject;
    }

    public void setCityDetailObject(CityDetail cityDetailsObject) {
        this.cityDetailObject = cityDetailsObject;
    }

    private String downloadedFilePath;
    private Boolean downloadedFlag = false;

    public String getWelcomeTitle() {
        return welcomeTitle;
    }

    public void setWelcomeTitle(String welcomeTitle) {
        this.welcomeTitle = welcomeTitle;
    }

    public String getDownloadedFilePath() {
        return downloadedFilePath;
    }

    public void setDownloadedFilePath(String downloadedFilePath) {
        this.downloadedFilePath = downloadedFilePath;
    }

    public Boolean getDownloadedFlag() {
        return downloadedFlag;
    }

    public void setDownloadedFlag(Boolean downloadedFlag) {
        this.downloadedFlag = downloadedFlag;
    }

    public String getAudiourl() {
        return audiourl;
    }

    public void setAudiourl(String audiourl) {
        this.audiourl = audiourl;
    }

    public long getCreated() {
        return created;
    }

    public void setCreated(long created) {
        this.created = created;
    }

    public String getImageurl() {
        return imageurl;
    }

    public void setImageurl(String imageurl) {
        this.imageurl = imageurl;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public String get___class() {
        return ___class;
    }

    public void set___class(String ___class) {
        this.___class = ___class;
    }

    public String getCityid() {
        return cityid;
    }

    public void setCityid(String cityid) {
        this.cityid = cityid;
    }

    public Object getOwnerId() {
        return ownerId;
    }

    public void setOwnerId(Object ownerId) {
        this.ownerId = ownerId;
    }

    public long getUpdated() {
        return updated;
    }

    public void setUpdated(long updated) {
        this.updated = updated;
    }

    public String getObjectId() {
        return objectId;
    }

    public void setObjectId(String objectId) {
        this.objectId = objectId;
    }

    public String get__meta() {
        return __meta;
    }

    public void set__meta(String __meta) {
        this.__meta = __meta;
    }
}
