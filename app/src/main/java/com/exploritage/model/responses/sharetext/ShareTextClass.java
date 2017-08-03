package com.exploritage.model.responses.sharetext;

/**
 * Created by admin on 4/4/2017.
 */

import com.google.gson.annotations.Expose;
import com.google.gson.annotations.SerializedName;

public class ShareTextClass {

        @SerializedName("created")
        @Expose
        private long created;
        @SerializedName("___class")
        @Expose
        private String _class;
        @SerializedName("textToShare")
        @Expose
        private String textToShare;
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
        private String meta;

        public long getCreated() {
            return created;
        }

        public void setCreated(Integer created) {
            this.created = created;
        }

        public String getClass_() {
            return _class;
        }

        public void setClass_(String _class) {
            this._class = _class;
        }

        public String getTextToShare() {
            return textToShare;
        }

        public void setTextToShare(String textToShare) {
            this.textToShare = textToShare;
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

        public String getMeta() {
            return meta;
        }

        public void setMeta(String meta) {
            this.meta = meta;
        }


}
