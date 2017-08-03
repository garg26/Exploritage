package simplifii.framework.rest.requests;

import com.google.gson.annotations.Expose;
import com.google.gson.annotations.SerializedName;

/**
 * Created by robin on 11/23/16.
 */

public class WithdrawSelfLeaveRequest {
//    {
//        "remark": "optional remark"
//    }

    @SerializedName("remark")
    @Expose
    private String remark;

    public String getRemark() {
        return remark;
    }

    public void setRemark(String remark) {
        this.remark = remark;
    }
}
