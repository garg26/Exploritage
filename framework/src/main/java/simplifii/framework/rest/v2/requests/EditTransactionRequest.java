package simplifii.framework.rest.v2.requests;

import com.google.gson.annotations.Expose;
import com.google.gson.annotations.SerializedName;

public class EditTransactionRequest {

    @SerializedName("card_id")
    @Expose
    private Integer cardId;
    @SerializedName("due_date")
    @Expose
    private String dueDate;
    @SerializedName("account_id")
    @Expose
    private Integer accountId;
    @SerializedName("amount")
    @Expose
    private Double amount;
    @SerializedName("status")
    @Expose
    private String status;
    @SerializedName("comment")
    @Expose
    private String comment;

    public EditTransactionRequest(Integer cardId, String dueDate, Integer accountId, Double amount, String status, String comment) {
        this.cardId = cardId;
        this.dueDate = dueDate;
        this.accountId = accountId;
        this.amount = amount;
        this.status = status;
        this.comment = comment;
    }

    public Integer getCardId() {
        return cardId;
    }

    public void setCardId(Integer cardId) {
        this.cardId = cardId;
    }

    public String getDueDate() {
        return dueDate;
    }

    public void setDueDate(String dueDate) {
        this.dueDate = dueDate;
    }

    public Integer getAccountId() {
        return accountId;
    }

    public void setAccountId(Integer accountId) {
        this.accountId = accountId;
    }

    public Double getAmount() {
        return amount;
    }

    public void setAmount(Double amount) {
        this.amount = amount;
    }

    public String getStatus() {
        return status;
    }

    public void setStatus(String status) {
        this.status = status;
    }

    public String getComment() {
        return comment;
    }

    public void setComment(String comment) {
        this.comment = comment;
    }

}