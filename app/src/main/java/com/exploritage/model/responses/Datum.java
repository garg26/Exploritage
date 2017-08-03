package com.exploritage.model.responses;

import com.google.gson.annotations.Expose;
import com.google.gson.annotations.SerializedName;

import java.io.Serializable;

/**
 * Created by ajay on 20/2/17.
 */

public class Datum implements Serializable {

    private String welcomeTitle;

    public String getWelcomeTitle() {
        return welcomeTitle;
    }

    public void setWelcomeTitle(String welcomeTitle) {
        this.welcomeTitle = welcomeTitle;
    }

    @SerializedName("hasData")
    @Expose
    private boolean hasData;

    @SerializedName("image_url")
    @Expose
    private String image_url;
    @SerializedName("created")
    @Expose
    private long created;
    @SerializedName("cityname")
    @Expose
    private String cityname;
    @SerializedName("description")
    @Expose
    private String description;
    @SerializedName("lon")
    @Expose
    private Object lon;
    @SerializedName("ownerId")
    @Expose
    private Object ownerId;
    @SerializedName("__meta")
    @Expose
    private String __meta;
    @SerializedName("___class")
    @Expose
    private String ___class;
    @SerializedName("updated")
    @Expose
    private long updated;
    @SerializedName("lat")
    @Expose
    private Object lat;
    @SerializedName("sitemap")
    @Expose
    private String sitemap;
    @SerializedName("objectId")
    @Expose
    private String objectId;
    @SerializedName("order")
    @Expose
    private long order;

    public String getImage_url() {
        return image_url;
    }

    public void setImage_url(String image_url) {
        this.image_url = image_url;
    }

    public long getCreated() {
        return created;
    }

    public void setCreated(long created) {
        this.created = created;
    }

    public String getCityname() {
        return cityname;
    }

    public void setCityname(String cityname) {
        this.cityname = cityname;
    }

    public String getDescription() {
        return description;
    }

    public void setDescription(String description) {
        this.description = description;
    }

    public Object getLon() {
        return lon;
    }

    public void setLon(Object lon) {
        this.lon = lon;
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

    public Object getLat() {
        return lat;
    }

    public void setLat(Object lat) {
        this.lat = lat;
    }

    public String getSitemap() {
        return sitemap;
    }

    public void setSitemap(String sitemap) {
        this.sitemap = sitemap;
    }

    public String getObjectId() {
        return objectId;
    }

    public void setObjectId(String objectId) {
        this.objectId = objectId;
    }

    public long getOrder() {
        return order;
    }

    public void setOrder(long order) {
        this.order = order;
    }

    public boolean isHasData() {
        return hasData;
    }

    public void setHasData(boolean hasData) {
        this.hasData = hasData;
    }


}
