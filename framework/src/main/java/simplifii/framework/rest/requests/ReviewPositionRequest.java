package simplifii.framework.rest.requests;

import com.google.gson.annotations.Expose;
import com.google.gson.annotations.SerializedName;

public class ReviewPositionRequest extends WithdrawPositionRequest{

    @SerializedName("is_approved")
    @Expose
    private Boolean isApproved;
    @SerializedName("reason")
    @Expose
    private String reason;

    /**
     * @return The isApproved
     */
    public Boolean getIsApproved() {
        return isApproved;
    }

    /**
     * @param isApproved The is_approved
     */
    public void setIsApproved(Boolean isApproved) {
        this.isApproved = isApproved;
    }

    /**
     * @return The reason
     */
    public String getReason() {
        return reason;
    }

    /**
     * @param reason The reason
     */
    public void setReason(String reason) {
        this.reason = reason;
    }

}