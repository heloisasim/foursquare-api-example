package com.heloisasim.foursquareapi;

import android.os.Bundle;
import android.os.Parcelable;
import android.support.design.widget.Snackbar;
import android.support.v4.widget.SwipeRefreshLayout;
import android.support.v7.app.AppCompatActivity;
import android.support.v7.widget.RecyclerView;
import android.view.View;

import com.airbnb.lottie.LottieAnimationView;
import com.heloisasim.foursquareapi.model.Venue;
import com.heloisasim.foursquareapi.model.VenuesBaseClass;
import com.heloisasim.foursquareapi.networking.RestClient;

import java.io.IOException;
import java.util.ArrayList;

import butterknife.BindView;
import butterknife.ButterKnife;
import retrofit2.Call;
import retrofit2.Callback;
import retrofit2.Response;

public class MainActivity extends AppCompatActivity implements Callback<VenuesBaseClass>, SwipeRefreshLayout.OnRefreshListener {

    private static final String RECYCLER_VIEW_TAG = "recycler_view";
    private static final String VENUE_TAG = "venue";
    private static final String LOADING_VISIBILITY_TAG = "loading_visibility";
    private static final String RECYCLER_VIEW_VISIBILITY_TAG = "recycler_view_visibility";

    @BindView(R.id.recycler_view)
    RecyclerView mRecyclerView;

    @BindView(R.id.animation_view)
    LottieAnimationView mLoading;

    @BindView(R.id.swipe_refresh)
    SwipeRefreshLayout mSwipeRefresh;

    private VenuesAdapter mAdapter;
    private ArrayList<Venue> mVenues;
    private RecyclerView.LayoutManager mLayoutManager;
    private Parcelable mRecyclerViewState;

    @SuppressWarnings("WrongConstant")
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        ButterKnife.bind(this);

        mAdapter = new VenuesAdapter();
        mRecyclerView.setAdapter(mAdapter);
        mLayoutManager = mRecyclerView.getLayoutManager();
        mSwipeRefresh.setOnRefreshListener(this);

        if (savedInstanceState != null) {
            mVenues = savedInstanceState.getParcelableArrayList(VENUE_TAG);
            mRecyclerViewState = savedInstanceState.getParcelable(RECYCLER_VIEW_TAG);
            mLoading.setVisibility(savedInstanceState.getInt(LOADING_VISIBILITY_TAG));
            mRecyclerView.setVisibility(savedInstanceState.getInt(RECYCLER_VIEW_VISIBILITY_TAG));
            mAdapter.updateVenues(mVenues);
        } else {
            getVenues();
        }
    }

    @Override
    protected void onSaveInstanceState(Bundle outState) {
        super.onSaveInstanceState(outState);
        Parcelable parcelable = mRecyclerView.getLayoutManager().onSaveInstanceState();
        outState.putParcelable(RECYCLER_VIEW_TAG, parcelable);
        outState.putParcelableArrayList(VENUE_TAG, mVenues);
        outState.putInt(LOADING_VISIBILITY_TAG, mLoading.getVisibility());
        outState.putInt(RECYCLER_VIEW_VISIBILITY_TAG, mRecyclerView.getVisibility());
    }

    private void getVenues() {
        // prepare call to foursquare API
        RestClient restClient = new RestClient();
        Call<VenuesBaseClass> mCallVenues = restClient.prepareVenuesRequest();
        // do request to foursquare API
        mCallVenues.enqueue(this);
    }

    @Override
    protected void onResume() {
        super.onResume();
        // restore recyclerView state, e.g. scrolled position
        if (mRecyclerViewState != null)
            mLayoutManager.onRestoreInstanceState(mRecyclerViewState);
    }

    private void dismissAnimations() {
        // stop swipe refresh
        if (mSwipeRefresh.isRefreshing()) {
            mSwipeRefresh.setRefreshing(false);
        }

        // stop animation
        mLoading.loop(false);
        mLoading.setVisibility(View.GONE);

        mRecyclerView.setVisibility(View.VISIBLE);

    }

    @Override
    public void onResponse(Call<VenuesBaseClass> call, Response<VenuesBaseClass> response) {
        dismissAnimations();
        // check response
        if (response.isSuccessful()) {
            VenuesBaseClass body = response.body();
            mVenues = body.getResponse().getVenues();
            mAdapter.updateVenues(mVenues);
        } else {
            Snackbar.make(findViewById(android.R.id.content), R.string.generic_error, Snackbar.LENGTH_LONG).show();
        }
    }

    @Override
    public void onFailure(Call<VenuesBaseClass> call, Throwable t) {
        dismissAnimations();
        int error = (t instanceof IOException) ? R.string.connection_error : R.string.generic_error;
        Snackbar.make(findViewById(android.R.id.content), error, Snackbar.LENGTH_LONG).show();
    }

    @Override
    public void onRefresh() {
        getVenues();
    }
}
