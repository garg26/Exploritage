package com.exploritage.model.responses;

import com.google.gson.annotations.Expose;
import com.google.gson.annotations.SerializedName;

/**
 * Created by ajay on 20/2/17.
 */

public class Placestogo {
    @SerializedName("created")
    @Expose
    private long created;
    @SerializedName("name")
    @Expose
    private String name;
    @SerializedName("___class")
    @Expose
    private String ___class;
    @SerializedName("ownerId")
    @Expose
    private Object ownerId;
    @SerializedName("updated")
    @Expose
    private Object updated;
    @SerializedName("objectId")
    @Expose
    private String objectId;
    @SerializedName("__meta")
    @Expose
    private String __meta;

    public long getCreated() {
        return created;
    }

    public void setCreated(long created) {
        this.created = created;
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

    public Object getOwnerId() {
        return ownerId;
    }

    public void setOwnerId(Object ownerId) {
        this.ownerId = ownerId;
    }

    public Object getUpdated() {
        return updated;
    }

    public void setUpdated(Object updated) {
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
