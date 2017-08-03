package simplifii.framework.rest.v2.responses;

import com.google.gson.annotations.Expose;
import com.google.gson.annotations.SerializedName;

/**
 * Created by robin on 11/19/16.
 */

public class BaseApiResponse {

    @SerializedName("msg")
    @Expose
    private String msg;
    @SerializedName("status")
    @Expose
    private Integer status;
    @SerializedName("token")
    @Expose
    private String token;
//    @SerializedName("response")
//    @Expose
//    private T response;

    private boolean error;

    /**
     *
     * @return
     * The msg
     */
    public String getMsg() {
        return msg;
    }

    /**
     *
     * @param msg
     * The msg
     */
    public void setMsg(String msg) {
        this.msg = msg;
    }

    /**
     *
     * @return
     * The status
     */
    public Integer getStatus() {
        return status;
    }

    /**
     *
     * @param status
     * The status
     */
    public void setStatus(Integer status) {
        this.status = status;
    }

    public boolean isError() {
        if(status != null && status != 0){
            return true;
        }
        return false;
    }

    public String getToken() {
        return token;
    }

    public void setToken(String token) {
        this.token = token;
    }

//    public T getResponse() {
//        return response;
//    }
//
//    public void setResponse(T response) {
//        this.response = response;
//    }
}
