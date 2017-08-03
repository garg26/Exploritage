package simplifii.framework.rest.responses;

import com.google.gson.annotations.Expose;
import com.google.gson.annotations.SerializedName;

import java.io.Serializable;

import simplifii.framework.model.BaseRecyclerModel;

public class Role extends BaseRecyclerModel implements Serializable {


    public Role(Role role){
        this.roleId = role.getRoleId();
        this.designation = role.getDesignation();
        this.isApproved = role.getIsApproved();
    }

    @SerializedName("role_id")
    @Expose
    private Integer roleId;
    @SerializedName("designation")
    @Expose
    private String designation;
    @SerializedName("is_approved")
    @Expose
    private Boolean isApproved;


    /**
     * @return The roleId
     */
    public Integer getRoleId() {
        return roleId;
    }

    /**
     * @param roleId The role_id
     */
    public void setRoleId(Integer roleId) {
        this.roleId = roleId;
    }

    /**
     * @return The designation
     */
    public String getDesignation() {
        return designation;
    }

    /**
     * @param designation The designation
     */
    public void setDesignation(String designation) {
        this.designation = designation;
    }

    /**
     * @return The isApproved
     */
    public Boolean getIsApproved() {
        return isApproved;
    }

    /**
     * @param isApproved The is_approved
     */
    public void setIsApproved(Boolean isApproved) {
        this.isApproved = isApproved;
    }

}