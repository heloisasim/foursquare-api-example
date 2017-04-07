package com.heloisasim.foursquareapi.networking;

import com.google.gson.Gson;
import com.heloisasim.foursquareapi.BuildConfig;

import retrofit2.Retrofit;
import retrofit2.converter.gson.GsonConverterFactory;

/**
 * Created by heloisasim on 07/04/17.
 */

public class RestClient {

    ApiService mService;

    public RestClient() {

        Retrofit retrofit = new Retrofit.Builder()
                .baseUrl(BuildConfig.foursquareUrl)
                .addConverterFactory(GsonConverterFactory.create(new Gson()))
                .build();

        mService = retrofit.create(ApiService.class);
    }

    public ApiService getApiService() {
        return mService;
    }
}
