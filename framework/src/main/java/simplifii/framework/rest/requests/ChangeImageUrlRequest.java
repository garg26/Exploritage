package simplifii.framework.rest.requests;

import com.google.gson.annotations.Expose;
import com.google.gson.annotations.SerializedName;

public class ChangeImageUrlRequest {

    @SerializedName("profile_image")
    @Expose
    private String profileImage;

    /**
     * @return The profileImage
     */
    public String getProfileImage() {
        return profileImage;
    }

    /**
     * @param profileImage The profile_image
     */
    public void setProfileImage(String profileImage) {
        this.profileImage = profileImage;
    }

}