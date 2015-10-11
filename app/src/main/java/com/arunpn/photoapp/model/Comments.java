package com.arunpn.photoapp.model;

import java.util.List;

/**
 * Created by a1nagar on 10/10/15.
 */
public class Comments {
    int count;
    List<CommentDetail> data;

    public int getCount() {
        return count;
    }

    public void setCount(int count) {
        this.count = count;
    }

    public List<CommentDetail> getData() {
        return data;
    }

    public void setData(List<CommentDetail> data) {
        this.data = data;
    }
}
