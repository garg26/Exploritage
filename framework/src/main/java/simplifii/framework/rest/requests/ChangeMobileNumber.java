package simplifii.framework.rest.requests;

import com.google.gson.annotations.Expose;
import com.google.gson.annotations.SerializedName;

/**
 * Created by robin on 11/14/16.
 */

public class ChangeMobileNumber {

    @SerializedName("mobile")
    @Expose
    private String mobile;
    @SerializedName("user_id")
    @Expose
    private Integer userId;

    public ChangeMobileNumber(String newNumber, Integer userId) {
        this.mobile = newNumber;
        this.userId = userId;
    }

    /**
     *
     * @return
     * The mobile
     */
    public String getMobile() {
        return mobile;
    }

    /**
     *
     * @param mobile
     * The mobile
     */
    public void setMobile(String mobile) {
        this.mobile = mobile;
    }

    /**
     *
     * @return
     * The userId
     */
    public Integer getUserId() {
        return userId;
    }

    /**
     *
     * @param userId
     * The user_id
     */
    public void setUserId(Integer userId) {
        this.userId = userId;
    }

}
