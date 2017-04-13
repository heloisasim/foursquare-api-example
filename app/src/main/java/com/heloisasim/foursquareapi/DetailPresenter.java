package com.heloisasim.foursquareapi;

import android.graphics.Color;
import android.os.Parcel;
import android.os.Parcelable;
import android.text.TextUtils;

import com.heloisasim.foursquareapi.model.Hours;
import com.heloisasim.foursquareapi.model.Timeframes;
import com.heloisasim.foursquareapi.model.Venue;
import com.heloisasim.foursquareapi.model.VenueBaseClass;
import com.heloisasim.foursquareapi.networking.RestClient;

import java.io.IOException;
import java.lang.ref.WeakReference;
import java.util.ArrayList;

import retrofit2.Call;
import retrofit2.Callback;
import retrofit2.Response;

/**
 * Created by heloisasim on 13/04/17.
 */

public class DetailPresenter implements DetailContract.Presenter, Callback<VenueBaseClass>, Parcelable {

    private WeakReference<DetailContract.View> mView;
    private Venue mVenue;
    private boolean mShouldLoadDetails;

    public DetailPresenter(DetailContract.View view, Venue venue) {
        mView = new WeakReference<>(view);
        mVenue = venue;
        mShouldLoadDetails = true;
    }

    @Override
    public void onViewReady() {
        if (mShouldLoadDetails) {
            loadVenue();
        } else {
            showVenue();
        }
    }

    @Override
    public void setView(DetailContract.View view) {
        mView = new WeakReference<>(view);
    }

    @Override
    public void loadVenue() {
        // prepare call to foursquare API
        RestClient restClient = new RestClient();
        Call<VenueBaseClass> mCallVenue = restClient.prepareVenueRequest(mVenue.getId());
        // do request to foursquare API
        mCallVenue.enqueue(this);
    }

    @Override
    public void onGetDirectionsClicked() {
        if (mVenue.getLocation() != null) {
            mView.get().openMaps("google.navigation:q=" + mVenue.getLocation().getLat() + "," + mVenue.getLocation().getLng() + "&mode=w");
        }
    }

    @Override
    public void setLoadRequest(boolean shouldLoadDetails) {
        mShouldLoadDetails = shouldLoadDetails;
    }

    @Override
    public void onResponse(Call<VenueBaseClass> call, Response<VenueBaseClass> response) {

        if (mView.get() != null)
            mView.get().dismissAnimations();

        if (response.isSuccessful()) {
            VenueBaseClass body = response.body();
            mVenue = body.getResponse().getVenue();
        } else {
            if (mView.get() != null)
                mView.get().showError(false);
        }

        showVenue();
    }

    @Override
    public void onFailure(Call<VenueBaseClass> call, Throwable t) {
        if (mView.get() != null) {
            mView.get().dismissAnimations();
            mView.get().showError(t instanceof IOException);
        }
    }

    /**
     * Shows the venue information. If the request with full information did not succeed,
     * then shows the basic information received via Extra.
     */
    private void showVenue() {

        if (mView.get() != null) {
            showHeader();
            showContactCard();
            showGeneralInfoCard();
        }
    }

    private void showHeader() {
        if (mVenue.getBestPhoto() != null) {
            // get the photo url and params for foursquare api
            String url = new StringBuilder()
                    .append(mVenue.getBestPhoto().getPrefix())
                    .append("width%d")
                    .append(mVenue.getBestPhoto().getSuffix())
                    .append("?client_id=")
                    .append(BuildConfig.foursquareClientId)
                    .append("&client_secret=")
                    .append(BuildConfig.foursquareClientSecret)
                    .append("&v=")
                    .append(BuildConfig.foursquareVersion)
                    .toString();
            mView.get().showHeaderPicture(url);
        }

        mView.get().showName(mVenue.getName());
    }

    private void showContactCard() {

        if (mVenue.getContact() != null && !TextUtils.isEmpty(mVenue.getContact().getFormattedPhone())) {
            mView.get().showPhone(mVenue.getContact().getFormattedPhone());
        } else {
            mView.get().hidePhone();
        }

        if (mVenue.getLocation() != null) {
            mView.get().showAddress(mVenue.getLocation().getFullAddress());
        } else {
            mView.get().hideAddress();
        }

    }

    private void showGeneralInfoCard() {
        Hours hours = mVenue.getHours();
        if (hours != null) {
            showOpeningHoursInfo(hours);
        } else {
            mView.get().showEmptyInfo();
        }
    }

    /**
     * Shows status, days of the week that are open and hours
     *
     * @param hours
     */
    private void showOpeningHoursInfo(Hours hours) {
        mView.get().showStatus(hours.getStatus(), hours.isOpen() ? Color.GREEN : Color.RED);

        // Checks null objects and empty lists to prevent errors
        ArrayList<Timeframes> timeFrames = hours.getTimeframes();
        if (timeFrames != null && !timeFrames.isEmpty()) {
            mView.get().showDays(timeFrames.get(0).getDays());
            if (timeFrames.get(0).getOpen() != null && !timeFrames.get(0).getOpen().isEmpty())
                mView.get().showHours(timeFrames.get(0).getOpen().get(0).getRenderedTime());
        }
    }

    @Override
    public int describeContents() {
        return 0;
    }

    @Override
    public void writeToParcel(Parcel dest, int flags) {
        dest.writeParcelable(this.mVenue, flags);
    }

    protected DetailPresenter(Parcel in) {
        this.mVenue = in.readParcelable(Venue.class.getClassLoader());
        this.mShouldLoadDetails = false;
    }

    public static final Parcelable.Creator<DetailPresenter> CREATOR = new Parcelable.Creator<DetailPresenter>() {
        @Override
        public DetailPresenter createFromParcel(Parcel source) {
            return new DetailPresenter(source);
        }

        @Override
        public DetailPresenter[] newArray(int size) {
            return new DetailPresenter[size];
        }
    };
}
