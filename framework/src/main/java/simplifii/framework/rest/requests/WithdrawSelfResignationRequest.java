package simplifii.framework.rest.requests;

import com.google.gson.annotations.Expose;
import com.google.gson.annotations.SerializedName;

/**
 * Created by robin on 11/23/16.
 */

public class WithdrawSelfResignationRequest {

    @SerializedName("resignation_id")
    @Expose
    private Integer resignationId;

    public Integer getResignationId() {
        return resignationId;
    }

    public void setResignationId(Integer resignationId) {
        this.resignationId = resignationId;
    }


}
