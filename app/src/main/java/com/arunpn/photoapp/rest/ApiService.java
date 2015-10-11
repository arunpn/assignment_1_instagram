package com.arunpn.photoapp.rest;

import com.arunpn.photoapp.model.CommentDetail;
import com.arunpn.photoapp.model.Photo;

import java.util.List;

import retrofit.Callback;
import retrofit.http.GET;
import retrofit.http.Path;
import retrofit.http.Query;

/**
 * Created by a1nagar on 10/10/15.
 */
public interface ApiService {

    @GET("/media/popular")
    public void getPopularPhotos(@Query("client_id") String clientId, Callback<List<Photo>> callback);

    @GET("/media/{mediaId}/comments")
    public void getComments(@Path("mediaId") String media_id,@Query("client_id") String clientId, Callback<List<CommentDetail>> callback);

}
