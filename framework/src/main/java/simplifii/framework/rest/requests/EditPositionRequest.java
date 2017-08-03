package simplifii.framework.rest.requests;

import com.google.gson.annotations.Expose;
import com.google.gson.annotations.SerializedName;

public class EditPositionRequest extends AddPositionRequest{

    @SerializedName("position_id")
    @Expose
    private Integer positionId;

    public Integer getPositionId() {
        return positionId;
    }

    public void setPositionId(Integer positionId) {
        this.positionId = positionId;
    }
}