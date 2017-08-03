package simplifii.framework.rest.v2.requests;

import com.google.gson.annotations.Expose;
import com.google.gson.annotations.SerializedName;

import java.util.List;

/**
 * Created by robin on 12/27/16.
 */

public class SendStarRequest {

    @SerializedName("receiver_ids")
    @Expose
    private List<Integer> receiverIds;
    @SerializedName("comment")
    @Expose
    private String comment;

    public List<Integer> getReceiverIds() {
        return receiverIds;
    }

    public void setReceiverIds(List<Integer> receiverIds) {
        this.receiverIds = receiverIds;
    }

    public String getComment() {
        return comment;
    }

    public void setComment(String comment) {
        this.comment = comment;
    }
}
