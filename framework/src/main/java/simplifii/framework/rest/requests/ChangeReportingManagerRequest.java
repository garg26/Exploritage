package simplifii.framework.rest.requests;

import com.google.gson.annotations.Expose;
import com.google.gson.annotations.SerializedName;

public class ChangeReportingManagerRequest {

    @SerializedName("employee_id")
    @Expose
    private Integer employeeId;
    @SerializedName("reports_to")
    @Expose
    private Integer reportsTo;

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
     * @return The reportsTo
     */
    public Integer getReportsTo() {
        return reportsTo;
    }

    /**
     * @param reportsTo The reports_to
     */
    public void setReportsTo(Integer reportsTo) {
        this.reportsTo = reportsTo;
    }

}