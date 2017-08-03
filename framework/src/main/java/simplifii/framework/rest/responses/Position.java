package simplifii.framework.rest.responses;

import com.google.gson.annotations.Expose;
import com.google.gson.annotations.SerializedName;

public class Position {

    @SerializedName("position_id")
    @Expose
    private Integer positionId;
    @SerializedName("designation")
    @Expose
    private String designation;
    @SerializedName("role_description")
    @Expose
    private String roleDescription;
    @SerializedName("role_type")
    @Expose
    private String roleType;
    @SerializedName("center_id")
    @Expose
    private Integer centerId;
    @SerializedName("dept_id")
    @Expose
    private Integer deptId;
    @SerializedName("cc_id")
    @Expose
    private Integer ccId;
    @SerializedName("shall_report_to")
    @Expose
    private Integer shallReportTo;
    @SerializedName("status")
    @Expose
    private String status;

    /**
     * @return The positionId
     */
    public Integer getPositionId() {
        return positionId;
    }

    /**
     * @param positionId The position_id
     */
    public void setPositionId(Integer positionId) {
        this.positionId = positionId;
    }

    /**
     * @return The designation
     */
    public String getDesignation() {
        return designation;
    }

    /**
     * @param designation The designation
     */
    public void setDesignation(String designation) {
        this.designation = designation;
    }

    /**
     * @return The roleDescription
     */
    public String getRoleDescription() {
        return roleDescription;
    }

    /**
     * @param roleDescription The role_description
     */
    public void setRoleDescription(String roleDescription) {
        this.roleDescription = roleDescription;
    }

    /**
     * @return The roleType
     */
    public String getRoleType() {
        return roleType;
    }

    /**
     * @param roleType The role_type
     */
    public void setRoleType(String roleType) {
        this.roleType = roleType;
    }

    /**
     * @return The centerId
     */
    public Integer getCenterId() {
        return centerId;
    }

    /**
     * @param centerId The center_id
     */
    public void setCenterId(Integer centerId) {
        this.centerId = centerId;
    }

    /**
     * @return The deptId
     */
    public Integer getDeptId() {
        return deptId;
    }

    /**
     * @param deptId The dept_id
     */
    public void setDeptId(Integer deptId) {
        this.deptId = deptId;
    }

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
     * @return The shallReportTo
     */
    public Integer getShallReportTo() {
        return shallReportTo;
    }

    /**
     * @param shallReportTo The shall_report_to
     */
    public void setShallReportTo(Integer shallReportTo) {
        this.shallReportTo = shallReportTo;
    }

    /**
     * @return The status
     */
    public String getStatus() {
        return status;
    }

    /**
     * @param status The status
     */
    public void setStatus(String status) {
        this.status = status;
    }

}