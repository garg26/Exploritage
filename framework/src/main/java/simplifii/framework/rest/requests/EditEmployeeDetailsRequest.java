package simplifii.framework.rest.requests;

import com.google.gson.annotations.Expose;
import com.google.gson.annotations.SerializedName;

public class EditEmployeeDetailsRequest {

    @SerializedName("employee_id")
    @Expose
    private Integer employeeId;
    @SerializedName("role_description")
    @Expose
    private String roleDescription;
    @SerializedName("dept_id")
    @Expose
    private Integer deptId;
    @SerializedName("cc_id")
    @Expose
    private Integer ccId;
    @SerializedName("designation")
    @Expose
    private String designation;
    @SerializedName("role_type")
    @Expose
    private String roleType;
    @SerializedName("center_id")
    @Expose
    private Integer centerId;

    /**
     * @return The employeeId
     */
    public Integer getEmployeeId() {
        return employeeId;
    }

    /**
     * @param employeeId The employee_id
     */
    public void setEmployeeId(Integer employeeId) {
        this.employeeId = employeeId;
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

    public Integer getCenterId() {
        return centerId;
    }

    public void setCenterId(Integer centerId) {
        this.centerId = centerId;
    }

    public String getDesignation() {
        return designation;
    }

    public void setDesignation(String designation) {
        this.designation = designation;
    }

    public String getRoleType() {
        return roleType;
    }

    public void setRoleType(String roleType) {
        this.roleType = roleType;
    }
}