package simplifii.framework.rest.v2.responses;

import android.text.TextUtils;

import com.google.gson.annotations.Expose;
import com.google.gson.annotations.SerializedName;

import simplifii.framework.utility.AppConstants;
import simplifii.framework.utility.JsonUtil;
import simplifii.framework.utility.Preferences;

/**
 * Created by robin on 12/26/16.
 */

public class GetEmployeesApiResponse extends BaseApiResponse {

    @SerializedName("response")
    @Expose
    private EmployeeListContainer employeeListContainer;

    public EmployeeListContainer getEmployeeListContainer() {
        return employeeListContainer;
    }

    public void setEmployeeListContainer(EmployeeListContainer employeeListContainer) {
        this.employeeListContainer = employeeListContainer;
    }

    public static GetEmployeesApiResponse getInstance() {
        String json = Preferences.getData(AppConstants.PREF_KEYS.KEY_EMP_LIST_JSON, "");
        if (!TextUtils.isEmpty(json)) {
            return parseJson(json);
        }
        return null;
    }

    public static GetEmployeesApiResponse parseJson(String json) {
        GetEmployeesApiResponse response = (GetEmployeesApiResponse) JsonUtil.parseJson(json, GetEmployeesApiResponse.class);
        if (response != null) {
            Preferences.saveData(AppConstants.PREF_KEYS.KEY_EMP_LIST_JSON, json);
        }
        return response;
    }
}
