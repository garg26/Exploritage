package simplifii.framework.rest.v2.responses;

import com.google.gson.annotations.Expose;
import com.google.gson.annotations.SerializedName;

/**
 * Created by robin on 12/20/16.
 */

public class GenerateOtpApiResponse extends BaseApiResponse {

    @SerializedName("response")
    @Expose
    private OtpContainer response;

    public OtpContainer getResponse() {
        return response;
    }

    public void setResponse(OtpContainer response) {
        this.response = response;
    }
}
