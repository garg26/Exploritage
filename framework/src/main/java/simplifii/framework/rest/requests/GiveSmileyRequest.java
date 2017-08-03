package simplifii.framework.rest.requests;

import com.google.gson.annotations.Expose;
import com.google.gson.annotations.SerializedName;

public class GiveSmileyRequest {

    @SerializedName("receiver_id")
    @Expose
    private Integer receiverId;
    @SerializedName("comment")
    @Expose
    private String comment;

    /**
     * @return The receiverId
     */
    public Integer getReceiverId() {
        return receiverId;
    }

    /**
     * @param receiverId The receiver_id
     */
    public void setReceiverId(Integer receiverId) {
        this.receiverId = receiverId;
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