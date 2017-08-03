package simplifii.framework.rest.requests;

import com.google.gson.annotations.Expose;
import com.google.gson.annotations.SerializedName;

import java.util.ArrayList;
import java.util.List;

public class GiveStarRequest {

    @SerializedName("receiver_ids")
    @Expose
    private List<Integer> receiverIds = new ArrayList<Integer>();
    @SerializedName("comment")
    @Expose
    private String comment;

    /**
     * @return The receiverIds
     */
    public List<Integer> getReceiverIds() {
        return receiverIds;
    }

    /**
     * @param receiverIds The receiver_ids
     */
    public void setReceiverIds(List<Integer> receiverIds) {
        this.receiverIds = receiverIds;
    }

    /**
     * @return The comment
     */
    public String getComment() {
        return comment;
    }

    /**
     * @param comment The comment
     */
    public void setComment(String comment) {
        this.comment = comment;
    }

}