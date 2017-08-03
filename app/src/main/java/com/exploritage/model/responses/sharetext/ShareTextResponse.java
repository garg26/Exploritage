package com.exploritage.model.responses.sharetext;

/**
 * Created by admin on 4/4/2017.
 */

import java.util.List;
import com.google.gson.annotations.Expose;
import com.google.gson.annotations.SerializedName;


public class ShareTextResponse {

        @SerializedName("offset")
        @Expose
        private Integer offset;
        @SerializedName("data")
        @Expose
        private List<ShareTextClass> data = null;
        @SerializedName("nextPage")
        @Expose
        private Object nextPage;
        @SerializedName("totalObjects")
        @Expose
        private Integer totalObjects;

        public Integer getOffset() {
            return offset;
        }

        public void setOffset(Integer offset) {
            this.offset = offset;
        }

        public List<ShareTextClass> getData() {
            return data;
        }

        public void setData(List<ShareTextClass> data) {
            this.data = data;
        }

        public Object getNextPage() {
            return nextPage;
        }

        public void setNextPage(Object nextPage) {
            this.nextPage = nextPage;
        }

        public Integer getTotalObjects() {
            return totalObjects;
        }

        public void setTotalObjects(Integer totalObjects) {
            this.totalObjects = totalObjects;
        }




}
