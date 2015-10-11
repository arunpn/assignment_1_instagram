package com.arunpn.photoapp.rest;

import com.google.gson.Gson;
import com.google.gson.GsonBuilder;
import com.google.gson.internal.bind.DateTypeAdapter;

import java.util.Date;

import retrofit.RestAdapter;

/**
 * Created by a1nagar on 10/10/15.
 */
public class RestClient {
    private ApiService apiService;
    public static final String ENDPOINT = "https://api.instagram.com/v1";

    public RestClient() {

        Gson gson = new GsonBuilder()
                .registerTypeAdapter(Date.class, new DateTypeAdapter())
                .disableHtmlEscaping()
                .create();

        RestAdapter restAdapter = new RestAdapter
                .Builder()
                .setEndpoint(ENDPOINT)
                .setConverter( new DefaultGSONConverter(gson))
                .setLogLevel(RestAdapter.LogLevel.FULL)
                .build();

        this.apiService = restAdapter.create(ApiService.class);
    }

    public ApiService getApiService() {
        return apiService;
    }
}
