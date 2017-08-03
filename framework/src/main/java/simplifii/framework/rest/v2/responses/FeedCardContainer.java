package simplifii.framework.rest.v2.responses;

import com.google.gson.annotations.Expose;
import com.google.gson.annotations.SerializedName;

import java.util.List;

/**
 * Created by robin on 12/21/16.
 */

public class FeedCardContainer {

    @SerializedName("data")
    @Expose
    private List<FeedCard> feedCardList;

    public List<FeedCard> getFeedCardList() {
        return feedCardList;
    }

    public void setFeedCardList(List<FeedCard> feedCardList) {
        this.feedCardList = feedCardList;
    }
}
