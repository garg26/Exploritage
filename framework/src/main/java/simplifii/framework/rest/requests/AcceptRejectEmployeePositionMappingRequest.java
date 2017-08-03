package simplifii.framework.rest.requests;

import com.google.gson.annotations.Expose;
import com.google.gson.annotations.SerializedName;

public class AcceptRejectEmployeePositionMappingRequest {

    @SerializedName("position_id")
    @Expose
    private Integer positionId;
    @SerializedName("is_approved")
    @Expose
    private Boolean isApproved;

    /**
     * @return The positionId
     */
    public Integer getPositionId() {
        return positionId;
    }

    /**
     * @param positionId The position_id
     */
    public void setPositionId(Integer positionId) {
        this.positionId = positionId;
    }

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

}