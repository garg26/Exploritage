package com.exploritage.model;

import com.google.gson.annotations.Expose;
import com.google.gson.annotations.SerializedName;

/**
 * Created by ajay on 20/2/17.
 */

public class BaseApiResponse {

    @SerializedName("offset")
    @Expose
    private Integer offset;
    @SerializedName("totalObjects")
    @Expose
    private Integer totalObjects;
    @SerializedName("nextPage")
    @Expose
    private Object nextPage;

    public Integer getOffset() {
        return offset;
    }

    public void setOffset(Integer offset) {
        this.offset = offset;
    }

    public Integer getTotalObjects() {
        return totalObjects;
    }

    public void setTotalObjects(Integer totalObjects) {
        this.totalObjects = totalObjects;
    }

    public Object getNextPage() {
        return nextPage;
    }

    public void setNextPage(Object nextPage) {
        this.nextPage = nextPage;
    }
}
