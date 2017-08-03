package simplifii.framework.rest.v2.responses;

import com.google.gson.annotations.Expose;
import com.google.gson.annotations.SerializedName;

import java.util.List;

/**
 * Created by robin on 12/26/16.
 */

public class GFileApiResponse extends BaseApiResponse {

    @SerializedName("response")
    @Expose
    private List<GFileContainer> gFileContainerList;

    public List<GFileContainer> getgFileContainerList() {
        return gFileContainerList;
    }

    public void setgFileContainerList(List<GFileContainer> gFileContainerList) {
        this.gFileContainerList = gFileContainerList;
    }
}
