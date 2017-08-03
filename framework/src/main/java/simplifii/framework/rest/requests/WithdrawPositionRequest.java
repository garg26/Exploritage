package simplifii.framework.rest.requests;

import com.google.gson.annotations.Expose;
import com.google.gson.annotations.SerializedName;

public class WithdrawPositionRequest {

    @SerializedName("position_id")
    @Expose
    private Integer positionId;

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

}