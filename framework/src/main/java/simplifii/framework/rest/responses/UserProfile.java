/*
package simplifii.framework.rest.responses;

import simplifii.framework.model.BaseRecyclerModel;
import com.google.gson.annotations.Expose;
import com.google.gson.annotations.SerializedName;

import simplifii.framework.utility.AppConstants;

import java.io.Serializable;

*/
/**
 * Created by CANBRAND on 07-Aug-15.
 *//*


public class UserProfile extends BaseRecyclerModel implements Serializable {

    private boolean checked;

    public UserProfile(UserProfile userProfile) {
        this.id = userProfile.getId();
        this.name = userProfile.getName();
        this.avatar = userProfile.getAvatar();
        this.userRole = userProfile.getUserRole();
        this.dateOfJoining = userProfile.getDateOfJoining();
        this.designation = userProfile.getDesignation();
        this.mobile = userProfile.getMobile();
        this.email = userProfile.getEmail();
        this.isTerminated = userProfile.getIsTerminated();
        this.dateOfTermination = userProfile.getDateOfTermination();
    }

    @Override
    public int getViewType() {
        return AppConstants.VIEW_TYPE.USER_PROFILE_HEADER_INFO;
    }

    @SerializedName("id")
    @Expose
    private Integer id;
    @SerializedName("name")
    @Expose
    private String name;
    @SerializedName("user_role")
    @Expose
    private String userRole;
    @SerializedName("avatar")
    @Expose
    private String avatar;
    @SerializedName("email")
    @Expose
    private String email;
    @SerializedName("mobile")
    @Expose
    private String mobile;
    @SerializedName("designation")
    @Expose
    private String designation;
    @SerializedName("date_of_joining")
    @Expose
    private String dateOfJoining;
    @SerializedName("is_terminated")
    @Expose
    private Boolean isTerminated;
    @SerializedName("date_of_termination")
    @Expose
    private String dateOfTermination;


    */
/**
     * @return The id
     *//*

    public Integer getId() {
        return id;
    }

    */
/**
     * @param id The id
     *//*

    public void setId(Integer id) {
        this.id = id;
    }

    */
/**
     * @return The name
     *//*

    public String getName() {
        return name;
    }

    */
/**
     * @param name The name
     *//*

    public void setName(String name) {
        this.name = name;
    }

    */
/**
     * @return The avatar
     *//*

    public String getAvatar() {
        return avatar;
    }

    */
/**
     * @param avatar The avatar
     *//*

    public void setAvatar(String avatar) {
        this.avatar = avatar;
    }

    */
/**
     * @return The email
     *//*

    public String getEmail() {
        return email;
    }

    */
/**
     * @param email The email
     *//*

    public void setEmail(String email) {
        this.email = email;
    }

    */
/**
     * @return The mobile
     *//*

    public String getMobile() {
        return mobile;
    }

    */
/**
     * @param mobile The mobile
     *//*

    public void setMobile(String mobile) {
        this.mobile = mobile;
    }

    */
/**
     * @return The designation
     *//*

    public String getDesignation() {
        return designation;
    }

    */
/**
     * @param designation The designation
     *//*

    public void setDesignation(String designation) {
        this.designation = designation;
    }

    */
/**
     * @return The dateOfJoining
     *//*

    public String getDateOfJoining() {
        return dateOfJoining;
    }

    */
/**
     * @param dateOfJoining The date_of_joining
     *//*

    public void setDateOfJoining(String dateOfJoining) {
        this.dateOfJoining = dateOfJoining;
    }

    */
/**
     * @return The isTerminated
     *//*

    public Boolean getIsTerminated() {
        return isTerminated;
    }

    */
/**
     * @param isTerminated The is_terminated
     *//*

    public void setIsTerminated(Boolean isTerminated) {
        this.isTerminated = isTerminated;
    }

    */
/**
     * @return The dateOfTermination
     *//*

    public String getDateOfTermination() {
        return dateOfTermination;
    }

    */
/**
     * @param dateOfTermination The date_of_termination
     *//*

    public void setDateOfTermination(String dateOfTermination) {
        this.dateOfTermination = dateOfTermination;
    }

    public String getUserRole() {
        return userRole;
    }

    public void setUserRole(String userRole) {
        this.userRole = userRole;
    }

    public Boolean getTerminated() {
        return isTerminated;
    }

    public void setTerminated(Boolean terminated) {
        isTerminated = terminated;
    }

    public boolean isChecked() {
        return checked;
    }

    public void setChecked(boolean checked) {
        this.checked = checked;
    }
}
*/
