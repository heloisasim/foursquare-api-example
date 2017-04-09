package com.heloisasim.foursquareapi.networking;

import com.heloisasim.foursquareapi.model.VenueBaseClass;
import com.heloisasim.foursquareapi.model.VenuesBaseClass;

import retrofit2.Call;
import retrofit2.http.GET;
import retrofit2.http.Path;
import retrofit2.http.Query;

/**
 * Created by heloisasim on 07/04/17.
 */

public interface ApiService {

    @GET("venues/search")
    Call<VenuesBaseClass> getVenues(
            @Query("ll") String ll,
            @Query("client_id") String client_id,
            @Query("client_secret") String client_secret,
            @Query("v") String date);

    @GET("venues/{venue_id}")
    Call<VenueBaseClass> getVenue(
            @Path("venue_id") String venue_id,
            @Query("client_id") String client_id,
            @Query("client_secret") String client_secret,
            @Query("v") String date);
}
