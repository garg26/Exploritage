package simplifii.framework.rest.v2.responses;

import com.google.gson.annotations.Expose;
import com.google.gson.annotations.SerializedName;

/**
 * Created by robin on 12/21/16.
 */

public class GetFeedCardApiResponse extends BaseApiResponse {

    @SerializedName("response")
    @Expose
    private FeedCardContainer feedCardContainer;

    public FeedCardContainer getFeedCardContainer() {
        return feedCardContainer;
    }

    public void setFeedCardContainer(FeedCardContainer feedCardContainer) {
        this.feedCardContainer = feedCardContainer;
    }

}
