package simplifii.framework.utility;

import simplifii.framework.rest.v2.enums.ActionTableType;
import simplifii.framework.rest.v2.responses.FeedCard;

/**
 * Created by robin on 12/27/16.
 */

public class FeedActionV2 {

    private int cardId;
    private String action;
    private ActionTableType actionTableType;
    private FeedCard feedCard;

    public FeedActionV2(int feedCardId, String action, ActionTableType actionTableType, FeedCard feedCard) {
        this.cardId = feedCardId;
        this.action = action;
        this.actionTableType = actionTableType;
        this.feedCard = feedCard;
    }

    public int getCardId() {
        return cardId;
    }

    public void setCardId(int cardId) {
        this.cardId = cardId;
    }

    public String getAction() {
        return action;
    }

    public void setAction(String action) {
        this.action = action;
    }

    public ActionTableType getActionTableType() {
        return actionTableType;
    }

    public void setActionTableType(ActionTableType actionTableType) {
        this.actionTableType = actionTableType;
    }

    public FeedCard getFeedCard() {
        return feedCard;
    }

    public void setFeedCard(FeedCard feedCard) {
        this.feedCard = feedCard;
    }
}
