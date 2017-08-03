package simplifii.framework.rest.responses;

import com.google.gson.annotations.Expose;
import com.google.gson.annotations.SerializedName;

import java.util.ArrayList;
import java.util.List;

public class FeedCardResponse {

    @SerializedName("title")
    @Expose
    private String title;
    @SerializedName("card_icon")
    @Expose
    private String cardIcon;
    @SerializedName("initiator")
    @Expose
    private Initiator initiator;
    @SerializedName("timestamp")
    @Expose
    private String timestamp;
    @SerializedName("card_title")
    @Expose
    private String cardTitle;
    @SerializedName("description")
    @Expose
    private String description;
    @SerializedName("respondents")
    @Expose
    private List<Object> respondents = new ArrayList<Object>();

    /**
     * @return The title
     */
    public String getTitle() {
        return title;
    }

    /**
     * @param title The title
     */
    public void setTitle(String title) {
        this.title = title;
    }

    /**
     * @return The cardIcon
     */
    public String getCardIcon() {
        return cardIcon;
    }

    /**
     * @param cardIcon The card_icon
     */
    public void setCardIcon(String cardIcon) {
        this.cardIcon = cardIcon;
    }

    /**
     * @return The initiator
     */
    public Initiator getInitiator() {
        return initiator;
    }

    /**
     * @param initiator The initiator
     */
    public void setInitiator(Initiator initiator) {
        this.initiator = initiator;
    }

    /**
     * @return The timestamp
     */
    public String getTimestamp() {
        return timestamp;
    }

    /**
     * @param timestamp The timestamp
     */
    public void setTimestamp(String timestamp) {
        this.timestamp = timestamp;
    }

    /**
     * @return The cardTitle
     */
    public String getCardTitle() {
        return cardTitle;
    }

    /**
     * @param cardTitle The card_title
     */
    public void setCardTitle(String cardTitle) {
        this.cardTitle = cardTitle;
    }

    /**
     * @return The description
     */
    public String getDescription() {
        return description;
    }

    /**
     * @param description The description
     */
    public void setDescription(String description) {
        this.description = description;
    }

    /**
     * @return The respondents
     */
    public List<Object> getRespondents() {
        return respondents;
    }

    /**
     * @param respondents The respondents
     */
    public void setRespondents(List<Object> respondents) {
        this.respondents = respondents;
    }

}