package com.heloisasim.foursquareapi.networking;

import com.google.gson.Gson;
import com.heloisasim.foursquareapi.BuildConfig;
import com.heloisasim.foursquareapi.model.BaseClass;

import java.text.SimpleDateFormat;
import java.util.Date;
import java.util.Locale;

import retrofit2.Call;
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

    public Call<BaseClass> prepareVenuesRequest() {
        SimpleDateFormat dateFormat = new SimpleDateFormat("yyyyMMdd", Locale.getDefault());
        String currentDate = dateFormat.format(new Date());
        return mService.getVenues(BuildConfig.foursquareStartLocation, BuildConfig.foursquareClientId, BuildConfig.foursquareClientSecret, currentDate);
    }
}
