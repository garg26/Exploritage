package simplifii.framework.rest.v2.responses;

import java.util.List;

import com.google.gson.annotations.Expose;
import com.google.gson.annotations.SerializedName;

public class SupportedStarCardInfo {

    @SerializedName("supporters")
    @Expose
    private List<Integer> supporters = null;

    public List<Integer> getSupporters() {
        return supporters;
    }

    public void setSupporters(List<Integer> supporters) {
        this.supporters = supporters;
    }

}