package simplifii.framework.rest.v2.requests;

import com.google.gson.annotations.Expose;
import com.google.gson.annotations.SerializedName;

/**
 * Created by robin on 12/20/16.
 */

public class GenerateOtpRequest {

    @SerializedName("mobile")
    @Expose
    private String mobile;

    public GenerateOtpRequest(String mobile) {
        this.mobile = mobile;
    }

    public String getMobile() {
        return mobile;
    }

    public void setMobile(String mobile) {
        this.mobile = mobile;
    }

}
