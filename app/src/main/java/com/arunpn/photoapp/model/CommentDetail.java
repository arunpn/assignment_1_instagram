package com.arunpn.photoapp.model;

import android.text.format.DateUtils;

import com.google.gson.annotations.SerializedName;

/**
 * Created by a1nagar on 10/10/15.
 */
public class CommentDetail {
    @SerializedName("created_time")
    long createdTime;
    @SerializedName("text")
    String commentText;
    @SerializedName("from")
    User user;

    public long getCreatedTime() {
        return createdTime;
    }

    public void setCreatedTime(long createdTime) {
        this.createdTime = createdTime;
    }

    public String getCommentText() {
        return commentText;
    }

    public void setCommentText(String commentText) {
        this.commentText = commentText;
    }

    public User getUser() {
        return user;
    }

    public void setUser(User user) {
        this.user = user;
    }

    public String getRelativeTime() {
        String relativeTS = DateUtils.getRelativeTimeSpanString(createdTime*1000,System.currentTimeMillis(),DateUtils.MINUTE_IN_MILLIS,0).toString();
        return relativeTS;
    }
}
