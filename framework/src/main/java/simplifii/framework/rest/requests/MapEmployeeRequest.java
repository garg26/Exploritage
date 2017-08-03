package simplifii.framework.rest.requests;

import com.google.gson.annotations.Expose;
import com.google.gson.annotations.SerializedName;

public class MapEmployeeRequest {

    @SerializedName("position_id")
    @Expose
    private Integer positionId;
    @SerializedName("name")
    @Expose
    private String name;
    @SerializedName("mobile")
    @Expose
    private Long mobile;
    @SerializedName("date_of_joining")
    @Expose
    private String dateOfJoining;
    @SerializedName("email")
    @Expose
    private String email;

    /**
     * @return The positionId
     */
    public Integer getPositionId() {
        return positionId;
    }

    /**
     * @param positionId The position_id
     */
    public void setPositionId(Integer positionId) {
        this.positionId = positionId;
    }

    /**
     * @return The name
     */
    public String getName() {
        return name;
    }

    /**
     * @param name The name
     */
    public void setName(String name) {
        this.name = name;
    }

    /**
     * @return The mobile
     */
    public Long getMobile() {
        return mobile;
    }

    /**
     * @param mobile The mobile
     */
    public void setMobile(Long mobile) {
        this.mobile = mobile;
    }

    /**
     * @return The dateOfJoining
     */
    public String getDateOfJoining() {
        return dateOfJoining;
    }

    /**
     * @param dateOfJoining The date_of_joining
     */
    public void setDateOfJoining(String dateOfJoining) {
        this.dateOfJoining = dateOfJoining;
    }

    /**
     * @return The email
     */
    public String getEmail() {
        return email;
    }

    /**
     * @param email The email
     */
    public void setEmail(String email) {
        this.email = email;
    }

}