package simplifii.framework.rest.v2.responses;

import com.google.gson.annotations.Expose;
import com.google.gson.annotations.SerializedName;
//import com.mycardboarddreams.autocompletebubbletext.MultiSelectItem;

public class Employee {

    @SerializedName("id")
    @Expose
    protected Integer id;
    @SerializedName("name")
    @Expose
    private String name;
    @SerializedName("designation")
    @Expose
    private String designation;
    @SerializedName("profile_image")
    @Expose
    private String profileImage;

    private boolean selected;

    public boolean isSelected() {
        return selected;
    }

    public void setSelected(boolean selected) {
        this.selected = selected;
    }

    public Integer getId() {
        return id;
    }

    public void setId(Integer id) {
        this.id = id;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public String getDesignation() {
        return designation;
    }

    public void setDesignation(String designation) {
        this.designation = designation;
    }

    public String getProfileImage() {
        return profileImage;
    }

    public void setProfileImage(String profileImage) {
        this.profileImage = profileImage;
    }

}