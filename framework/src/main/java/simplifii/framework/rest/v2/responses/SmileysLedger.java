package simplifii.framework.rest.v2.responses;

import com.google.gson.annotations.Expose;
import com.google.gson.annotations.SerializedName;

public class SmileysLedger {

    @SerializedName("given")
    @Expose
    private int given;
    @SerializedName("recd")
    @Expose
    private int recd;

    public int getGiven() {
        return given;
    }

    public void setGiven(int given) {
        this.given = given;
    }

    public int getRecd() {
        return recd;
    }

    public void setRecd(int recd) {
        this.recd = recd;
    }
}