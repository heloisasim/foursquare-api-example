package com.heloisasim.foursquareapi.networking;

import com.heloisasim.foursquareapi.model.BaseClass;

import retrofit2.Call;
import retrofit2.http.GET;
import retrofit2.http.Query;

/**
 * Created by heloisasim on 07/04/17.
 */

public interface ApiService {

    @GET("venues/search")
    Call<BaseClass> getVenues(
            @Query("ll") String ll,
            @Query("client_id") String client_id,
            @Query("client_secret") String client_secret,
            @Query("v") String date);
}
