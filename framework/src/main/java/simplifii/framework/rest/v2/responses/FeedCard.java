package simplifii.framework.rest.v2.responses;

import com.google.gson.annotations.Expose;
import com.google.gson.annotations.SerializedName;

import java.util.List;

import simplifii.framework.model.BaseRecyclerModel;

public class FeedCard {

    @SerializedName("id")
    @Expose
    private Integer id;
    @SerializedName("table")
    @Expose
    private String table;
    @SerializedName("table_row_id")
    @Expose
    private Integer tableRowId;
    @SerializedName("top_right_text")
    @Expose
    private String topRightText;
    @SerializedName("updater")
    @Expose
    private Updater updater;
    @SerializedName("title")
    @Expose
    private String title;
    @SerializedName("relevant_date_from")
    @Expose
    private String relevantDateFrom;
    @SerializedName("relevant_date_to")
    @Expose
    private String relevantDateTo;
    @SerializedName("meta_data")
    @Expose
    private MetaData metaData;
    @SerializedName("update_title")
    @Expose
    private String updateTitle;
    @SerializedName("update_comment")
    @Expose
    private String updateComment;
    @SerializedName("creator")
    @Expose
    private Creator creator;
    @SerializedName("receivers")
    @Expose
    private List<Receiver> receivers;
    @SerializedName("user_actions")
    @Expose
    private List<String> userActions;
    @SerializedName("created_at")
    @Expose
    private String createdAt;
    @SerializedName("updated_at")
    @Expose
    private String updatedAt;
    @SerializedName("star")
    @Expose
    private SupportedStarCardInfo supportedStarCardInfo;

    public Integer getId() {
        return id;
    }

    public void setId(Integer id) {
        this.id = id;
    }

    public String getTable() {
        return table;
    }

    public void setTable(String table) {
        this.table = table;
    }

    public Integer getTableRowId() {
        return tableRowId;
    }

    public void setTableRowId(Integer tableRowId) {
        this.tableRowId = tableRowId;
    }

    public String getTopRightText() {
        return topRightText;
    }

    public void setTopRightText(String topRightText) {
        this.topRightText = topRightText;
    }

    public Updater getUpdater() {
        return updater;
    }

    public void setUpdater(Updater updater) {
        this.updater = updater;
    }

    public String getTitle() {
        return title;
    }

    public void setTitle(String title) {
        this.title = title;
    }

    public String getRelevantDateFrom() {
        return relevantDateFrom;
    }

    public void setRelevantDateFrom(String relevantDateFrom) {
        this.relevantDateFrom = relevantDateFrom;
    }

    public String getRelevantDateTo() {
        return relevantDateTo;
    }

    public void setRelevantDateTo(String relevantDateTo) {
        this.relevantDateTo = relevantDateTo;
    }

    public MetaData getMetaData() {
        return metaData;
    }

    public void setMetaData(MetaData metaData) {
        this.metaData = metaData;
    }

    public String getUpdateTitle() {
        return updateTitle;
    }

    public void setUpdateTitle(String updateTitle) {
        this.updateTitle = updateTitle;
    }

    public String getUpdateComment() {
        return updateComment;
    }

    public void setUpdateComment(String updateComment) {
        this.updateComment = updateComment;
    }

    public Creator getCreator() {
        return creator;
    }

    public void setCreator(Creator creator) {
        this.creator = creator;
    }

    public List<Receiver> getReceivers() {
        return receivers;
    }

    public void setReceivers(List<Receiver> receivers) {
        this.receivers = receivers;
    }

    public List<String> getUserActions() {
        return userActions;
    }

    public void setUserActions(List<String> userActions) {
        this.userActions = userActions;
    }

    public String getCreatedAt() {
        return createdAt;
    }

    public void setCreatedAt(String createdAt) {
        this.createdAt = createdAt;
    }

    public String getUpdatedAt() {
        return updatedAt;
    }

    public void setUpdatedAt(String updatedAt) {
        this.updatedAt = updatedAt;
    }

    public SupportedStarCardInfo getSupportedStarCardInfo() {
        return supportedStarCardInfo;
    }

    public void setSupportedStarCardInfo(SupportedStarCardInfo supportedStarCardInfo) {
        this.supportedStarCardInfo = supportedStarCardInfo;
    }
}