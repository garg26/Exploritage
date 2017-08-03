package simplifii.framework.rest.v2.responses;

import com.google.gson.annotations.Expose;
import com.google.gson.annotations.SerializedName;

public class BasicProfile {

    @SerializedName("name")
    @Expose
    private String name;
    @SerializedName("designation")
    @Expose
    private String designation;
    @SerializedName("department")
    @Expose
    private String department;
    @SerializedName("pic")
    @Expose
    private String pic;
    @SerializedName("date_of_joining")
    @Expose
    private String dateOfJoining;

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

    public String getDepartment() {
        return department;
    }

    public void setDepartment(String department) {
        this.department = department;
    }

    public String getPic() {
        return pic;
    }

    public void setPic(String pic) {
        this.pic = pic;
    }

    public String getDateOfJoining() {
        return dateOfJoining;
    }

    public void setDateOfJoining(String dateOfJoining) {
        this.dateOfJoining = dateOfJoining;
    }

}