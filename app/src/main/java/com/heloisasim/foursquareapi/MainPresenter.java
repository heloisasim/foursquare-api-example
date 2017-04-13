package com.heloisasim.foursquareapi;

import android.os.Parcel;
import android.os.Parcelable;

import com.heloisasim.foursquareapi.model.Venue;
import com.heloisasim.foursquareapi.model.VenuesBaseClass;
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

public class MainPresenter implements MainContract.Presenter, Callback<VenuesBaseClass>, Parcelable {

    private WeakReference<MainContract.View> mView;
    private ArrayList<Venue> mVenues;

    public MainPresenter(MainContract.View view) {
        mView = new WeakReference<>(view);
    }

    @Override
    public void onViewReady() {
        if (mVenues != null) {
            if (mView.get() != null)
                mView.get().updateList(mVenues);
        } else {
            loadVenues();
        }
    }

    @Override
    public void loadVenues() {
        // prepare call to foursquare API
        RestClient restClient = new RestClient();
        Call<VenuesBaseClass> mCallVenues = restClient.prepareVenuesRequest();
        // do request to foursquare API
        mCallVenues.enqueue(this);
    }

    @Override
    public void setView(MainContract.View view) {
        mView = new WeakReference<>(view);
    }

    @Override
    public void onResponse(Call<VenuesBaseClass> call, Response<VenuesBaseClass> response) {
        // check response
        if (response.isSuccessful()) {
            VenuesBaseClass body = response.body();
            mVenues = body.getResponse().getVenues();

            if (mView.get() != null) {
                mView.get().dismissAnimations();
                mView.get().updateList(mVenues);
            }
        } else {
            if (mView.get() != null) {
                mView.get().dismissAnimations();
                mView.get().showError(false);
            }
        }

    }

    @Override
    public void onFailure(Call<VenuesBaseClass> call, Throwable t) {
        if (mView.get() != null) {
            mView.get().dismissAnimations();
            mView.get().showError(t instanceof IOException);
        }
    }

    @Override
    public int describeContents() {
        return 0;
    }

    @Override
    public void writeToParcel(Parcel dest, int flags) {
        dest.writeTypedList(this.mVenues);
    }

    protected MainPresenter(Parcel in) {
        this.mVenues = in.createTypedArrayList(Venue.CREATOR);
    }

    public static final Parcelable.Creator<MainPresenter> CREATOR = new Parcelable.Creator<MainPresenter>() {
        @Override
        public MainPresenter createFromParcel(Parcel source) {
            return new MainPresenter(source);
        }

        @Override
        public MainPresenter[] newArray(int size) {
            return new MainPresenter[size];
        }
    };
}
