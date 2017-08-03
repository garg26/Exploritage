package simplifii.framework.rest.v2.responses;

import com.google.gson.annotations.Expose;
import com.google.gson.annotations.SerializedName;

/**
 * Created by robin on 12/21/16.
 */

public class ValidateOtpApiResponse extends BaseApiResponse {

    @SerializedName("response")
    @Expose
    private UserProfile response;

    public UserProfile getResponse() {
        return response;
    }

    public void setResponse(UserProfile response) {
        this.response = response;
    }
}
