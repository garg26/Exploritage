package simplifii.framework.rest.responses;

import com.google.gson.annotations.Expose;
import com.google.gson.annotations.SerializedName;

public class AddPositionResponse {

    @SerializedName("msg")
    @Expose
    private String msg;
    @SerializedName("response")
    @Expose
    private PositionContainer response;
    @SerializedName("status")
    @Expose
    private Integer status;

    /**
     * @return The msg
     */
    public String getMsg() {
        return msg;
    }

    /**
     * @param msg The msg
     */
    public void setMsg(String msg) {
        this.msg = msg;
    }

    /**
     * @return The response
     */
    public PositionContainer getResponse() {
        return response;
    }

    /**
     * @param response The response
     */
    public void setResponse(PositionContainer response) {
        this.response = response;
    }

    /**
     * @return The status
     */
    public Integer getStatus() {
        return status;
    }

    /**
     * @param status The status
     */
    public void setStatus(Integer status) {
        this.status = status;
    }

}