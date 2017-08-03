package com.exploritage.model.responses;

import com.google.gson.annotations.Expose;
import com.google.gson.annotations.SerializedName;

import java.io.Serializable;

/**
 * Created by ajay on 20/2/17.
 */

public class CityDetail implements Serializable{
    @SerializedName("thingstodo")
    @Expose
    private String thingstodo;
    @SerializedName("shortdescription")
    @Expose
    private String shortdescription;
    @SerializedName("audioimage")
    @Expose
    private String audioimage;
    @SerializedName("created")
    @Expose
    private long created;
    @SerializedName("placeid")
    @Expose
    private String placeid;
    @SerializedName("about")
    @Expose
    private String about;
    @SerializedName("lon")
    @Expose
    private Double lon;
    @SerializedName("history")
    @Expose
    private String history;
    @SerializedName("cityid")
    @Expose
    private String cityid;
    @SerializedName("ownerId")
    @Expose
    private Object ownerId;
    @SerializedName("__meta")
    @Expose
    private String __meta;
    @SerializedName("audiourl")
    @Expose
    private String audiourl;
    @SerializedName("name")
    @Expose
    private String name;
    @SerializedName("___class")
    @Expose
    private String ___class;
    @SerializedName("updated")
    @Expose
    private long updated;
    @SerializedName("lat")
    @Expose
    private Double lat;
    @SerializedName("objectId")
    @Expose
    private String objectId;
    @SerializedName("sitemap")
    @Expose
    private String sitemap;
    private String downloadedFilePath;
    private Boolean downloadedFlag = false;

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

    public String getThingstodo() {
        return thingstodo;
    }

    public void setThingstodo(String thingstodo) {
        this.thingstodo = thingstodo;
    }

    public String getShortdescription() {
        return shortdescription;
    }

    public void setShortdescription(String shortdescription) {
        this.shortdescription = shortdescription;
    }

    public String getAudioimage() {
        return audioimage;
    }

    public void setAudioimage(String audioimage) {
        this.audioimage = audioimage;
    }

    public long getCreated() {
        return created;
    }

    public void setCreated(long created) {
        this.created = created;
    }

    public String getPlaceid() {
        return placeid;
    }

    public void setPlaceid(String placeid) {
        this.placeid = placeid;
    }

    public String getAbout() {
        return about;
    }

    public void setAbout(String about) {
        this.about = about;
    }

    public Double getLon() {
        return lon;
    }

    public void setLon(Double lon) {
        this.lon = lon;
    }

    public String getHistory() {
        return history;
    }

    public void setHistory(String history) {
        this.history = history;
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

    public String get__meta() {
        return __meta;
    }

    public void set__meta(String __meta) {
        this.__meta = __meta;
    }

    public String getAudiourl() {
        return audiourl;
    }

    public void setAudiourl(String audiourl) {
        this.audiourl = audiourl;
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

    public long getUpdated() {
        return updated;
    }

    public void setUpdated(long updated) {
        this.updated = updated;
    }

    public Double getLat() {
        return lat;
    }

    public void setLat(Double lat) {
        this.lat = lat;
    }

    public String getObjectId() {
        return objectId;
    }

    public void setObjectId(String objectId) {
        this.objectId = objectId;
    }

    public String getSitemap() {
        return sitemap;
    }

    public void setSitemap(String sitemap) {
        this.sitemap = sitemap;
    }

}
