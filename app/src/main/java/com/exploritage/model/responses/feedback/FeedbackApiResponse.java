package com.exploritage.model.responses.feedback;

import com.google.gson.annotations.Expose;
import com.google.gson.annotations.SerializedName;

/**
 * Created by ajay on 23/2/17.
 */

public class FeedbackApiResponse {
    @SerializedName("feedback")
    @Expose
    private Object feedback;
    @SerializedName("data")
    @Expose
    private String data;
    @SerializedName("created")
    @Expose
    private long created;
    @SerializedName("___class")
    @Expose
    private String ___class;
    @SerializedName("id")
    @Expose
    private Object id;
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

    public Object getFeedback() {
        return feedback;
    }

    public void setFeedback(Object feedback) {
        this.feedback = feedback;
    }

    public String getData() {
        return data;
    }

    public void setData(String data) {
        this.data = data;
    }

    public long getCreated() {
        return created;
    }

    public void setCreated(long created) {
        this.created = created;
    }

    public String get___class() {
        return ___class;
    }

    public void set___class(String ___class) {
        this.___class = ___class;
    }

    public Object getId() {
        return id;
    }

    public void setId(Object id) {
        this.id = id;
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
