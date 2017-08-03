package simplifii.framework.rest.requests;

import com.google.gson.annotations.Expose;
import com.google.gson.annotations.SerializedName;

public class TerminateRequest {

    @SerializedName("employee_id")
    @Expose
    private Integer employeeId;
    @SerializedName("last_working_day")
    @Expose
    private String lastWorkingDay;

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
     * @return The lastWorkingDay
     */
    public String getLastWorkingDay() {
        return lastWorkingDay;
    }

    /**
     * @param lastWorkingDay The last_working_day
     */
    public void setLastWorkingDay(String lastWorkingDay) {
        this.lastWorkingDay = lastWorkingDay;
    }

}