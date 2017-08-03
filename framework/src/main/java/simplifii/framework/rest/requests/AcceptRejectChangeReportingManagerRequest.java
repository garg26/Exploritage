package simplifii.framework.rest.requests;

import com.google.gson.annotations.Expose;
import com.google.gson.annotations.SerializedName;

public class AcceptRejectChangeReportingManagerRequest {

    @SerializedName("proposed_changes_id")
    @Expose
    private Integer proposedChangesId;
    @SerializedName("is_approved")
    @Expose
    private Boolean isApproved;

    /**
     * @return The proposedChangesId
     */
    public Integer getProposedChangesId() {
        return proposedChangesId;
    }

    /**
     * @param proposedChangesId The proposed_changes_id
     */
    public void setProposedChangesId(Integer proposedChangesId) {
        this.proposedChangesId = proposedChangesId;
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