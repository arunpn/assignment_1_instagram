package com.arunpn.photoapp.model;

import com.google.gson.annotations.SerializedName;

/**
 * Created by a1nagar on 10/10/15.
 */
public class Caption {
    @SerializedName("text")
    String captionText;
    @SerializedName("from")
    User user;

    public String getCaptionText() {
        return captionText;
    }

    public void setCaptionText(String captionText) {
        this.captionText = captionText;
    }

    public User getUser() {
        return user;
    }

    public void setUser(User user) {
        this.user = user;
    }
}
