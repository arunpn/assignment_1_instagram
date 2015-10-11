package com.arunpn.photoapp.model;

import com.google.gson.annotations.SerializedName;

/**
 * Created by a1nagar on 10/10/15.
 */
public class User {
    String username;
    @SerializedName("profile_picture")
    String profilePicture;

    public String getUsername() {
        return username;
    }

    public void setUsername(String username) {
        this.username = username;
    }

    public String getProfilePicture() {
        return profilePicture;
    }

    public void setProfilePicture(String profilePicture) {
        this.profilePicture = profilePicture;
    }
}
