package simplifii.framework.rest.responses;

import com.google.gson.annotations.Expose;
import com.google.gson.annotations.SerializedName;

import java.util.ArrayList;
import java.util.List;

public class CentersListContainer {

    @SerializedName("data")
    @Expose
    private List<Office> centerList = new ArrayList<Office>();

    /**
     * @return The centerList
     */
    public List<Office> getCenterList() {
        return centerList;
    }

    /**
     * @param centerList The centerList
     */
    public void setCenterList(List<Office> centerList) {
        this.centerList = centerList;
    }

}