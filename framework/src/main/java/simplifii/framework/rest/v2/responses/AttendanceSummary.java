package simplifii.framework.rest.v2.responses;

import com.google.gson.annotations.Expose;
import com.google.gson.annotations.SerializedName;

public class AttendanceSummary {

    @SerializedName("total")
    @Expose
    private int total;
    @SerializedName("attended")
    @Expose
    private int attended;
    @SerializedName("on_time")
    @Expose
    private int onTime;

    public int getTotal() {
        return total;
    }

    public void setTotal(int total) {
        this.total = total;
    }

    public int getAttended() {
        return attended;
    }

    public void setAttended(int attended) {
        this.attended = attended;
    }

    public int getOnTime() {
        return onTime;
    }

    public void setOnTime(int onTime) {
        this.onTime = onTime;
    }
}