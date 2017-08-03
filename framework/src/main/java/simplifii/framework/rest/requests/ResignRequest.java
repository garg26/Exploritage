package simplifii.framework.rest.requests;

import com.google.gson.annotations.Expose;
import com.google.gson.annotations.SerializedName;

public class ResignRequest {

    @SerializedName("last_working_day")
    @Expose
    private String lastWorkingDay;
    @SerializedName("comment")
    @Expose
    private String comment;

    /**
     * @return The lastWorkingDay
     */
    public String getLastWorkingDay() {
        return lastWorkingDay;
    }

    /**
     * @param lastWorkingDay The last_working_day
     */
    public void setLastWorkingDay(String lastWorkingDay) {
        this.lastWorkingDay = lastWorkingDay;
    }

    public String getComment() {
        return comment;
    }

    public void setComment(String comment) {
        this.comment = comment;
    }
}

