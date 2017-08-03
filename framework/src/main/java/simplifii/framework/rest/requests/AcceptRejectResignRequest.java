package simplifii.framework.rest.requests;

import com.google.gson.annotations.Expose;
import com.google.gson.annotations.SerializedName;

public class AcceptRejectResignRequest extends WithdrawSelfResignationRequest {

    @SerializedName("last_working_day")
    @Expose
    private String lastWorkingDay;
    @SerializedName("is_approved")
    @Expose
    private Boolean isApproved;

    /**
     * @return The lastWorkingDay
     */
    public String getLastWorkingDay() {
        return lastWorkingDay;
    }

    /**
     * @param lastWorkingDay The last_working_day
     */
    public void setLastWorkingDay(String lastWorkingDay) {
        this.lastWorkingDay = lastWorkingDay;
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