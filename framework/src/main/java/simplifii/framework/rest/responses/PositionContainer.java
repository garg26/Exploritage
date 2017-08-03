package simplifii.framework.rest.responses;

import com.google.gson.annotations.Expose;
import com.google.gson.annotations.SerializedName;

public class PositionContainer {

    @SerializedName("position")
    @Expose
    private Position position;

    /**
     * @return The position
     */
    public Position getPosition() {
        return position;
    }

    /**
     * @param position The position
     */
    public void setPosition(Position position) {
        this.position = position;
    }

}