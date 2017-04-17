package com.heloisasim.foursquareapi.networking;

import com.google.gson.Gson;
import com.heloisasim.foursquareapi.BuildConfig;
import com.heloisasim.foursquareapi.model.VenueBaseClass;
import com.heloisasim.foursquareapi.model.VenuesBaseClass;

import okhttp3.OkHttpClient;
import retrofit2.Call;
import retrofit2.Retrofit;
import retrofit2.converter.gson.GsonConverterFactory;

/**
 * Created by heloisasim on 07/04/17.
 */

public class RestClient {

    ApiService mService;
    OkHttpClient client;

    public static String BASE_URL = BuildConfig.foursquareUrl;
    private static RestClient instance;

    public static RestClient getInstance() {
        if (instance == null) {
            instance = new RestClient();
        }

        return instance;
    }

    private RestClient() {
        Retrofit retrofit = new Retrofit.Builder()
                .baseUrl(BASE_URL)
                .client(getOkHttpClient())
                .addConverterFactory(GsonConverterFactory.create(new Gson()))
                .build();

        mService = retrofit.create(ApiService.class);
    }

    public OkHttpClient getOkHttpClient() {
        if (client == null) {
            client = new OkHttpClient.Builder().build();
        }

        return client;
    }

    public Call<VenuesBaseClass> prepareVenuesRequest() {
        return mService.getVenues(BuildConfig.foursquareStartLocation, BuildConfig.foursquareClientId, BuildConfig.foursquareClientSecret, BuildConfig.foursquareVersion);
    }

    public Call<VenueBaseClass> prepareVenueRequest(String venueId) {
        return mService.getVenue(venueId, BuildConfig.foursquareClientId, BuildConfig.foursquareClientSecret, BuildConfig.foursquareVersion);
    }
}
