package com.arunpn.photoapp.model;

import android.content.Context;
import android.text.format.DateUtils;

import com.google.gson.annotations.SerializedName;

import org.w3c.dom.Comment;

import java.util.Date;

import static java.lang.System.currentTimeMillis;

/**
 * Created by a1nagar on 10/10/15.
 */
public class Photo {
    Comments comments;
    long created_time;
    Likes likes;
    ImageDetails images;
    Caption caption;
    User user;
    @SerializedName("id")
    String mediaID;

    public String getMediaID() {
        return mediaID;
    }

    public void setMediaID(String mediaID) {
        this.mediaID = mediaID;
    }

    public Comments getComments() {
        return comments;
    }

    public void setComments(Comments comments) {
        this.comments = comments;
    }

    public long getCreated_time() {
        return created_time;
    }

    public void setCreated_time(long created_time) {
        this.created_time = created_time;
    }

    public Likes getLikes() {
        return likes;
    }

    public void setLikes(Likes likes) {
        this.likes = likes;
    }

    public ImageDetails getImages() {
        return images;
    }

    public void setImages(ImageDetails images) {
        this.images = images;
    }

    public Caption getCaption() {
        return caption;
    }

    public void setCaption(Caption caption) {
        this.caption = caption;
    }

    public User getUser() {
        return user;
    }

    public void setUser(User user) {
        this.user = user;
    }

    public String getRelativeDate() {
        CharSequence RelativeDate =DateUtils.getRelativeTimeSpanString(created_time*1000,System.currentTimeMillis(),DateUtils.SECOND_IN_MILLIS);
        return  RelativeDate.toString();
    }
}

