package simplifii.framework.rest.v2.responses;

import java.util.List;

import com.google.gson.annotations.Expose;
import com.google.gson.annotations.SerializedName;

public class MetaData {

    @SerializedName("comment")
    @Expose
    private String comment;

    private String lat;
    private String lng;
    @SerializedName("creator")
    @Expose
    private Integer creator;
    @SerializedName("recipients")
    @Expose
    private List<Integer> recipients;

    @SerializedName("status")
    @Expose
    private String status;
    @SerializedName("due_date")
    @Expose
    private String dueDate;
    @SerializedName("account_name")
    @Expose
    private String accountName;
    @SerializedName("last_working_day")
    @Expose
    private String lastWorkingDay;
    @SerializedName("last_working_day_of")
    @Expose
    private Integer lastWorkingDayOf;
    @SerializedName("remark_from_creator")
    @Expose
    private String remarkFromCreator;
    @SerializedName("amount")
    @Expose
    private String amount;

    public String getLastWorkingDay() {
        return lastWorkingDay;
    }

    public void setLastWorkingDay(String lastWorkingDay) {
        this.lastWorkingDay = lastWorkingDay;
    }

    public Integer getLastWorkingDayOf() {
        return lastWorkingDayOf;
    }

    public void setLastWorkingDayOf(Integer lastWorkingDayOf) {
        this.lastWorkingDayOf = lastWorkingDayOf;
    }

    public String getRemarkFromCreator() {
        return remarkFromCreator;
    }

    public void setRemarkFromCreator(String remarkFromCreator) {
        this.remarkFromCreator = remarkFromCreator;
    }

    public String getStatus() {
        return status;
    }

    public void setStatus(String status) {
        this.status = status;
    }

    public Integer getCreator() {
        return creator;
    }

    public void setCreator(Integer creator) {
        this.creator = creator;
    }

    public String getDueDate() {
        return dueDate;
    }

    public void setDueDate(String dueDate) {
        this.dueDate = dueDate;
    }

    public String getAccountName() {
        return accountName;
    }

    public void setAccountName(String accountName) {
        this.accountName = accountName;
    }

    public String getLat() {
        return lat;
    }

    public void setLat(String lat) {
        this.lat = lat;
    }

    public String getLng() {
        return lng;
    }

    public void setLng(String lng) {
        this.lng = lng;
    }

    public String getComment() {
        return comment;
    }

    public void setComment(String comment) {
        this.comment = comment;
    }

    public List<Integer> getRecipients() {
        return recipients;
    }

    public void setRecipients(List<Integer> recipients) {
        this.recipients = recipients;
    }

    public String getAmount() {
        return amount;
    }

    public void setAmount(String amount) {
        this.amount = amount;
    }
}