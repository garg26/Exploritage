package simplifii.framework.rest.v2.requests;

import com.google.gson.annotations.Expose;
import com.google.gson.annotations.SerializedName;

/**
 * Created by robin on 12/27/16.
 */

public class SendSmileyRequest {

    @SerializedName("receiver_id")
    @Expose
    private Integer receiverId;

    public Integer getReceiverId() {
        return receiverId;
    }

    public void setReceiverId(Integer receiverId) {
        this.receiverId = receiverId;
    }
}
