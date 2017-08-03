package simplifii.framework.rest.responses;

import com.google.gson.annotations.Expose;
import com.google.gson.annotations.SerializedName;

import java.io.Serializable;

public class CostCenter implements Serializable{

    @SerializedName("cc_id")
    @Expose
    private Integer ccId;
    @SerializedName("cc_name")
    @Expose
    private String ccName;
    @SerializedName("is_active")
    @Expose
    private Integer isActive;

    private boolean isChecked;//Not a part of api response

    /**
     * @return The ccId
     */
    public Integer getCcId() {
        return ccId;
    }

    /**
     * @param ccId The cc_id
     */
    public void setCcId(Integer ccId) {
        this.ccId = ccId;
    }

    /**
     * @return The ccName
     */
    public String getCcName() {
        return ccName;
    }

    /**
     * @param ccName The cc_name
     */
    public void setCcName(String ccName) {
        this.ccName = ccName;
    }

    /**
     * @return The isActive
     */
    public Integer getIsActive() {
        return isActive;
    }

    /**
     * @param isActive The is_active
     */
    public void setIsActive(Integer isActive) {
        this.isActive = isActive;
    }

    public boolean isChecked() {
        return isChecked;
    }

    public void setChecked(boolean checked) {
        isChecked = checked;
    }
}