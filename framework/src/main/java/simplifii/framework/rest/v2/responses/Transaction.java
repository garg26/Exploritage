package simplifii.framework.rest.v2.responses;

import com.google.gson.annotations.Expose;
import com.google.gson.annotations.SerializedName;

public class Transaction {

    @SerializedName("booked_on")
    @Expose
    private String bookedOn;
    @SerializedName("updated_on")
    @Expose
    private String updatedOn;
    @SerializedName("amount")
    @Expose
    private Double amount;
    @SerializedName("account_id")
    @Expose
    private Object accountId;
    @SerializedName("status")
    @Expose
    private String status;
    @SerializedName("due_date")
    @Expose
    private String dueDate;
    @SerializedName("remark")
    @Expose
    private String remark;

    public String getBookedOn() {
        return bookedOn;
    }

    public void setBookedOn(String bookedOn) {
        this.bookedOn = bookedOn;
    }

    public String getUpdatedOn() {
        return updatedOn;
    }

    public void setUpdatedOn(String updatedOn) {
        this.updatedOn = updatedOn;
    }

    public Double getAmount() {
        return amount;
    }

    public void setAmount(Double amount) {
        this.amount = amount;
    }

    public Object getAccountId() {
        return accountId;
    }

    public void setAccountId(Object accountId) {
        this.accountId = accountId;
    }

    public String getStatus() {
        return status;
    }

    public void setStatus(String status) {
        this.status = status;
    }

    public String getDueDate() {
        return dueDate;
    }

    public void setDueDate(String dueDate) {
        this.dueDate = dueDate;
    }

    public String getRemark() {
        return remark;
    }

    public void setRemark(String remark) {
        this.remark = remark;
    }

}