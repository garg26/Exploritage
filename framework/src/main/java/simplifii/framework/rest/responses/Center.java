package simplifii.framework.rest.responses;

import com.google.gson.annotations.Expose;
import com.google.gson.annotations.SerializedName;

import java.io.Serializable;

public class Center implements Serializable {

    @SerializedName("id")
    @Expose
    private Integer id;
    @SerializedName("code")
    @Expose
    private String code;
    @SerializedName("lat")
    @Expose
    private Double lat;
    @SerializedName("lng")
    @Expose
    private Double lng;
    @SerializedName("address_id")
    @Expose
    private Integer addressId;
    @SerializedName("is_mon_working")
    @Expose
    private Integer isMonWorking;
    @SerializedName("is_tue_working")
    @Expose
    private Integer isTueWorking;
    @SerializedName("is_wed_working")
    @Expose
    private Integer isWedWorking;
    @SerializedName("is_thu_working")
    @Expose
    private Integer isThuWorking;
    @SerializedName("is_fri_working")
    @Expose
    private Integer isFriWorking;
    @SerializedName("is_sat_working")
    @Expose
    private Integer isSatWorking;
    @SerializedName("is_sun_working")
    @Expose
    private Integer isSunWorking;
    @SerializedName("work_time_from")
    @Expose
    private String workTimeFrom;
    @SerializedName("work_time_till")
    @Expose
    private String workTimeTill;
    @SerializedName("CL")
    @Expose
    private Integer cL;
    @SerializedName("EL")
    @Expose
    private Integer eL;
    @SerializedName("SL")
    @Expose
    private Integer sL;
    @SerializedName("is_active")
    @Expose
    private Integer isActive;
    @SerializedName("created_at")
    @Expose
    private String createdAt;
    @SerializedName("updated_at")
    @Expose
    private String updatedAt;

    private boolean isChecked;//Not a part of api response

    /**
     * @return The id
     */
    public Integer getId() {
        return id;
    }

    /**
     * @param id The id
     */
    public void setId(Integer id) {
        this.id = id;
    }

    /**
     * @return The code
     */
    public String getCode() {
        return code;
    }

    /**
     * @param code The code
     */
    public void setCode(String code) {
        this.code = code;
    }

    /**
     * @return The lat
     */
    public Double getLat() {
        return lat;
    }

    /**
     * @param lat The lat
     */
    public void setLat(Double lat) {
        this.lat = lat;
    }

    /**
     * @return The lng
     */
    public Double getLng() {
        return lng;
    }

    /**
     * @param lng The lng
     */
    public void setLng(Double lng) {
        this.lng = lng;
    }

    /**
     * @return The addressId
     */
    public Integer getAddressId() {
        return addressId;
    }

    /**
     * @param addressId The address_id
     */
    public void setAddressId(Integer addressId) {
        this.addressId = addressId;
    }

    /**
     * @return The isMonWorking
     */
    public Integer getIsMonWorking() {
        return isMonWorking;
    }

    /**
     * @param isMonWorking The is_mon_working
     */
    public void setIsMonWorking(Integer isMonWorking) {
        this.isMonWorking = isMonWorking;
    }

    /**
     * @return The isTueWorking
     */
    public Integer getIsTueWorking() {
        return isTueWorking;
    }

    /**
     * @param isTueWorking The is_tue_working
     */
    public void setIsTueWorking(Integer isTueWorking) {
        this.isTueWorking = isTueWorking;
    }

    /**
     * @return The isWedWorking
     */
    public Integer getIsWedWorking() {
        return isWedWorking;
    }

    /**
     * @param isWedWorking The is_wed_working
     */
    public void setIsWedWorking(Integer isWedWorking) {
        this.isWedWorking = isWedWorking;
    }

    /**
     * @return The isThuWorking
     */
    public Integer getIsThuWorking() {
        return isThuWorking;
    }

    /**
     * @param isThuWorking The is_thu_working
     */
    public void setIsThuWorking(Integer isThuWorking) {
        this.isThuWorking = isThuWorking;
    }

    /**
     * @return The isFriWorking
     */
    public Integer getIsFriWorking() {
        return isFriWorking;
    }

    /**
     * @param isFriWorking The is_fri_working
     */
    public void setIsFriWorking(Integer isFriWorking) {
        this.isFriWorking = isFriWorking;
    }

    /**
     * @return The isSatWorking
     */
    public Integer getIsSatWorking() {
        return isSatWorking;
    }

    /**
     * @param isSatWorking The is_sat_working
     */
    public void setIsSatWorking(Integer isSatWorking) {
        this.isSatWorking = isSatWorking;
    }

    /**
     * @return The isSunWorking
     */
    public Integer getIsSunWorking() {
        return isSunWorking;
    }

    /**
     * @param isSunWorking The is_sun_working
     */
    public void setIsSunWorking(Integer isSunWorking) {
        this.isSunWorking = isSunWorking;
    }

    /**
     * @return The workTimeFrom
     */
    public String getWorkTimeFrom() {
        return workTimeFrom;
    }

    /**
     * @param workTimeFrom The work_time_from
     */
    public void setWorkTimeFrom(String workTimeFrom) {
        this.workTimeFrom = workTimeFrom;
    }

    /**
     * @return The workTimeTill
     */
    public String getWorkTimeTill() {
        return workTimeTill;
    }

    /**
     * @param workTimeTill The work_time_till
     */
    public void setWorkTimeTill(String workTimeTill) {
        this.workTimeTill = workTimeTill;
    }

    /**
     * @return The cL
     */
    public Integer getCL() {
        return cL;
    }

    /**
     * @param cL The CL
     */
    public void setCL(Integer cL) {
        this.cL = cL;
    }

    /**
     * @return The eL
     */
    public Integer getEL() {
        return eL;
    }

    /**
     * @param eL The EL
     */
    public void setEL(Integer eL) {
        this.eL = eL;
    }

    /**
     * @return The sL
     */
    public Integer getSL() {
        return sL;
    }

    /**
     * @param sL The SL
     */
    public void setSL(Integer sL) {
        this.sL = sL;
    }

    /**
     * @return The isActive
     */
    public Integer getIsActive() {
        return isActive;
    }

    /**
     * @param isActive The is_active
     */
    public void setIsActive(Integer isActive) {
        this.isActive = isActive;
    }

    /**
     * @return The createdAt
     */
    public String getCreatedAt() {
        return createdAt;
    }

    /**
     * @param createdAt The created_at
     */
    public void setCreatedAt(String createdAt) {
        this.createdAt = createdAt;
    }

    /**
     * @return The updatedAt
     */
    public String getUpdatedAt() {
        return updatedAt;
    }

    /**
     * @param updatedAt The updated_at
     */
    public void setUpdatedAt(String updatedAt) {
        this.updatedAt = updatedAt;
    }

    public boolean isChecked() {
        return isChecked;
    }

    public void setChecked(boolean checked) {
        isChecked = checked;
    }
}