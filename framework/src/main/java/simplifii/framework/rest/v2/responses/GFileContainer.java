package simplifii.framework.rest.v2.responses;

import java.util.List;

import com.google.gson.annotations.Expose;
import com.google.gson.annotations.SerializedName;

public class GFileContainer {

    @SerializedName("id")
    @Expose
    private Integer id;
    @SerializedName("basic_profile")
    @Expose
    private BasicProfile basicProfile;
    @SerializedName("reports_to")
    @Expose
    private ReportsTo reportsTo;
    @SerializedName("attendance_summary")
    @Expose
    private AttendanceSummary attendanceSummary;
    @SerializedName("smileys_ledger")
    @Expose
    private SmileysLedger smileysLedger;
    @SerializedName("received_star_cards")
    @Expose
    private List<Object> receivedStarCards = null;
    @SerializedName("total_transactions")
    @Expose
    private Integer totalTransactions;
    @SerializedName("latest_transactions")
    @Expose
    private List<Transaction> transactions = null;

    public Integer getId() {
        return id;
    }

    public void setId(Integer id) {
        this.id = id;
    }

    public BasicProfile getBasicProfile() {
        return basicProfile;
    }

    public void setBasicProfile(BasicProfile basicProfile) {
        this.basicProfile = basicProfile;
    }

    public AttendanceSummary getAttendanceSummary() {
        return attendanceSummary;
    }

    public void setAttendanceSummary(AttendanceSummary attendanceSummary) {
        this.attendanceSummary = attendanceSummary;
    }

    public SmileysLedger getSmileysLedger() {
        return smileysLedger;
    }

    public void setSmileysLedger(SmileysLedger smileysLedger) {
        this.smileysLedger = smileysLedger;
    }

    public List<Object> getReceivedStarCards() {
        return receivedStarCards;
    }

    public void setReceivedStarCards(List<Object> receivedStarCards) {
        this.receivedStarCards = receivedStarCards;
    }

    public Integer getTotalTransactions() {
        return totalTransactions;
    }

    public void setTotalTransactions(Integer totalTransactions) {
        this.totalTransactions = totalTransactions;
    }

    public List<Transaction> getTransactions() {
        return transactions;
    }

    public void setTransactions(List<Transaction> transactions) {
        this.transactions = transactions;
    }

    public ReportsTo getReportsTo() {
        return reportsTo;
    }

    public void setReportsTo(ReportsTo reportsTo) {
        this.reportsTo = reportsTo;
    }
}