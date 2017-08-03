package simplifii.framework.rest.requests;

import com.google.gson.annotations.Expose;
import com.google.gson.annotations.SerializedName;

public class UpvoteStarRequest {

    @SerializedName("star_id")
    @Expose
    private Integer starId;
    @SerializedName("comment")
    @Expose
    private String comment;

    /**
     * @return The starId
     */
    public Integer getStarId() {
        return starId;
    }

    /**
     * @param starId The star_id
     */
    public void setStarId(Integer starId) {
        this.starId = starId;
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