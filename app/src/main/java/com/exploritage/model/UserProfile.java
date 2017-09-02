package com.exploritage.model;

import android.text.TextUtils;

import com.google.gson.Gson;
import com.google.gson.annotations.SerializedName;

import java.io.Serializable;

import simplifii.framework.utility.AppConstants;
import simplifii.framework.utility.Preferences;

/**
 * Created by kartikeya on 26/7/17.
 */

public class UserProfile implements Serializable {
    String name = "";
    String email = "";
    @SerializedName("mobile_no")
    String mobile = "";
    @SerializedName("image_url")
    String imageUrl = "";
    String coverImageUrl ="";
    @SerializedName("account_no")
    String accountId="";
    String location;
    String fcmToken;

    public String getCoverImageUrl() {
        return coverImageUrl;
    }

    public String getLocation() {
        return location;
    }

    public String getAccountId() {
        return accountId;
    }

    public void setAccountId(String accountId) {
        this.accountId = accountId;
    }

    public static UserProfile getInstance() {
        Gson gson=new Gson();
        String jsonString = Preferences.getData(AppConstants.PREF_KEYS.USER_INSTANCE, "");
        if(!TextUtils.isEmpty(jsonString)){
            UserProfile userProfile = gson.fromJson(jsonString, UserProfile.class);
            return userProfile;
        }
        return null;
    }

    public String getImageUrl() {
        return imageUrl;
    }

    public void setImageUrl(String imageUrl) {
        this.imageUrl = imageUrl;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public String getEmail() {
        return email;
    }

    public void setEmail(String email) {
        this.email = email;
    }

    public String getMobile() {
        return mobile;
    }

    public void setMobile(String mobile) {
        this.mobile = mobile;
    }

    public String getFcmToken() {
        return fcmToken;
    }

    public void setFcmToken(String fcmToken) {
        this.fcmToken = fcmToken;
    }

    public static void saveUser(UserProfile data) {
        Gson gson=new Gson();
        String toJson = gson.toJson(data);
        Preferences.saveData(AppConstants.PREF_KEYS.USER_INSTANCE,toJson);
    }
}
