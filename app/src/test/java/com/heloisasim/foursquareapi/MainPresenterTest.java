package com.heloisasim.foursquareapi;

import com.google.gson.Gson;
import com.heloisasim.foursquareapi.model.VenuesBaseClass;
import com.heloisasim.foursquareapi.networking.ApiService;

import org.junit.Before;
import org.junit.Test;
import org.mockito.ArgumentCaptor;
import org.mockito.Captor;
import org.mockito.Mock;
import org.mockito.MockitoAnnotations;

import okhttp3.ResponseBody;
import retrofit2.Call;
import retrofit2.Callback;
import retrofit2.Response;

import static org.mockito.Mockito.verify;
import static org.mockito.Mockito.when;

/**
 * Created by heloisasim on 19/04/17.
 */
public class MainPresenterTest {

    private static final String VENUE_RESPONSE = "{\"meta\":{\"code\":200,\"requestId\":\"58f49f889fb6b74955de736d\"},\"response\":{\"venues\":[{\"id\":\"560d09a0498e04e7a4318441\",\"name\":\"evenly HQ\",\"contact\":{\"phone\":\"+493060984272\",\"formattedPhone\":\"+49 30 60984272\",\"twitter\":\"evenly_io\"},\"location\":{\"address\":\"Oranienstraße 6\",\"lat\":52.500365,\"lng\":13.424904,\"labeledLatLngs\":[{\"label\":\"display\",\"lat\":52.500365,\"lng\":13.424904}],\"distance\":18,\"postalCode\":\"10997\",\"cc\":\"DE\",\"city\":\"Berlin\",\"state\":\"Berlin\",\"country\":\"Germany\",\"formattedAddress\":[\"Oranienstraße 6\",\"10997 Berlin\",\"Germany\"]},\"categories\":[{\"id\":\"4bf58dd8d48988d125941735\",\"name\":\"Tech Startup\",\"pluralName\":\"Tech Startups\",\"shortName\":\"Tech Startup\",\"icon\":{\"prefix\":\"https://ss3.4sqi.net/img/categories_v2/shops/technology_\",\"suffix\":\".png\"},\"primary\":true}],\"verified\":false,\"stats\":{\"checkinsCount\":414,\"usersCount\":44,\"tipCount\":0},\"venueRatingBlacklisted\":true,\"beenHere\":{\"lastCheckinExpiredAt\":0},\"specials\":{\"count\":0,\"items\":[]},\"hereNow\":{\"count\":0,\"summary\":\"Nobody here\",\"groups\":[]},\"referralId\":\"v-1492426632\",\"venueChains\":[],\"hasPerk\":false}],\"confident\":true}}";

    private MainPresenter mMainPresenter;
    private VenuesBaseClass mVenuesBaseClass;

    @Mock
    MainContract.View mMainView;

    @Mock
    ApiService mService;

    @Mock
    Call<VenuesBaseClass> mMockCall;

    @Mock
    ResponseBody mResponseBody;

    @Captor
    ArgumentCaptor<Callback<VenuesBaseClass>> argumentCaptor;

    @Before
    public void setUp() throws Exception {
        MockitoAnnotations.initMocks(this);

        mMainPresenter = new MainPresenter(mMainView, mService);

        Gson gson = new Gson();
        mVenuesBaseClass = gson.fromJson(VENUE_RESPONSE, VenuesBaseClass.class);

    }

    @Test
    public void loadVenues_shouldGetVenues_whenSuccessfulRequest() {
        when(mService.getVenues(
                BuildConfig.foursquareStartLocation,
                BuildConfig.foursquareClientId,
                BuildConfig.foursquareClientSecret,
                BuildConfig.foursquareVersion)
        ).thenReturn(mMockCall);

        mMainPresenter.loadVenues();

        Response<VenuesBaseClass> response = Response.success(mVenuesBaseClass);
        verify(mMockCall).enqueue(argumentCaptor.capture());
        argumentCaptor.getValue().onResponse(null, response);

        verify(mMainView).updateList(mVenuesBaseClass.getResponse().getVenues());
    }

    @Test
    public void loadVenues_shouldDoNothing_whenBadRequest() {
    }

    @Test
    public void loadVenues_shouldShowError_whenFailedRequest() {
        when(mService.getVenues(
                BuildConfig.foursquareStartLocation,
                BuildConfig.foursquareClientId,
                BuildConfig.foursquareClientSecret,
                BuildConfig.foursquareVersion)
        ).thenReturn(mMockCall);

        Throwable throwable = new Throwable( new RuntimeException());
        mMainPresenter.loadVenues();

        verify(mMockCall).enqueue(argumentCaptor.capture());
        argumentCaptor.getValue().onFailure(null, throwable);

        verify(mMainView).showError(false);
    }
}