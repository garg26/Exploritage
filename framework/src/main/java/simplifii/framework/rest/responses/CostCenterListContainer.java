package simplifii.framework.rest.responses;

import com.google.gson.annotations.Expose;
import com.google.gson.annotations.SerializedName;

import java.util.ArrayList;
import java.util.List;

public class CostCenterListContainer {

    @SerializedName("data")
    @Expose
    private List<CostCenter> costCenterList = new ArrayList<CostCenter>();

    /**
     * @return The costCenterList
     */
    public List<CostCenter> getCostCenterList() {
        return costCenterList;
    }

    /**
     * @param costCenterList The costCenterList
     */
    public void setCostCenterList(List<CostCenter> costCenterList) {
        this.costCenterList = costCenterList;
    }

}