package simplifii.framework.rest.responses;

import com.google.gson.annotations.Expose;
import com.google.gson.annotations.SerializedName;

import java.util.ArrayList;
import java.util.List;

public class DepartmentListContainer {

    @SerializedName("data")
    @Expose
    private List<Department> departmentList = new ArrayList<Department>();

    /**
     * @return The departmentList
     */
    public List<Department> getDepartmentList() {
        return departmentList;
    }

    /**
     * @param departmentList The departmentList
     */
    public void setDepartmentList(List<Department> departmentList) {
        this.departmentList = departmentList;
    }

}