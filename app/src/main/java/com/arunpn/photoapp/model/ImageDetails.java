package com.arunpn.photoapp.model;

import com.google.gson.annotations.SerializedName;

/**
 * Created by a1nagar on 10/10/15.
 */
public class ImageDetails {
    @SerializedName("standard_resolution")
    Resolution resolution;

    public Resolution getResolution() {
        return resolution;
    }

    public void setResolution(Resolution resolution) {
        this.resolution = resolution;
    }
}
