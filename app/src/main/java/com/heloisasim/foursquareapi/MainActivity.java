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

import java.util.ArrayList;

import butterknife.BindView;
import butterknife.ButterKnife;

public class MainActivity extends AppCompatActivity implements MainContract.View, SwipeRefreshLayout.OnRefreshListener {

    private static final String RECYCLER_VIEW_TAG = "recycler_view";
    private static final String LOADING_VISIBILITY_TAG = "loading_visibility";
    private static final String RECYCLER_VIEW_VISIBILITY_TAG = "recycler_view_visibility";

    private static final String PRESENTER_TAG = "presenter";

    @BindView(R.id.recycler_view)
    RecyclerView mRecyclerView;

    @BindView(R.id.animation_view)
    LottieAnimationView mLoading;

    @BindView(R.id.swipe_refresh)
    SwipeRefreshLayout mSwipeRefresh;

    private VenuesAdapter mAdapter;
    private RecyclerView.LayoutManager mLayoutManager;
    private Parcelable mRecyclerViewState;
    private MainContract.Presenter mPresenter;

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
            mPresenter = savedInstanceState.getParcelable(PRESENTER_TAG);
            mPresenter.setView(this);

            mRecyclerViewState = savedInstanceState.getParcelable(RECYCLER_VIEW_TAG);
            mLoading.setVisibility(savedInstanceState.getInt(LOADING_VISIBILITY_TAG));
            mRecyclerView.setVisibility(savedInstanceState.getInt(RECYCLER_VIEW_VISIBILITY_TAG));
        } else {
            mPresenter = new MainPresenter(this);
        }
    }

    @Override
    protected void onSaveInstanceState(Bundle outState) {
        super.onSaveInstanceState(outState);
        Parcelable parcelable = mRecyclerView.getLayoutManager().onSaveInstanceState();
        outState.putParcelable(RECYCLER_VIEW_TAG, parcelable);
        outState.putInt(LOADING_VISIBILITY_TAG, mLoading.getVisibility());
        outState.putInt(RECYCLER_VIEW_VISIBILITY_TAG, mRecyclerView.getVisibility());
        outState.putParcelable(PRESENTER_TAG, (Parcelable) mPresenter);
    }

    @Override
    protected void onResume() {
        super.onResume();
        // restore recyclerView state, e.g. scrolled position
        if (mPresenter != null)
            mPresenter.onViewReady();

        if (mRecyclerViewState != null)
            mLayoutManager.onRestoreInstanceState(mRecyclerViewState);
    }

    @Override
    public void dismissAnimations() {

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
    public void showError(boolean isConnectionError) {
        int error = isConnectionError ? R.string.connection_error : R.string.generic_error;
        Snackbar.make(findViewById(android.R.id.content), error, Snackbar.LENGTH_LONG).show();
    }

    @Override
    public void updateList(ArrayList<Venue> venues) {
        mAdapter.updateVenues(venues);
    }

    @Override
    public void onRefresh() {
        if (mPresenter != null) {
            mPresenter.loadVenues();
        }
    }
}
