package com.heloisasim.foursquareapi;

import android.content.Intent;
import android.graphics.Point;
import android.net.Uri;
import android.os.Bundle;
import android.support.design.widget.CollapsingToolbarLayout;
import android.support.design.widget.CoordinatorLayout;
import android.support.design.widget.Snackbar;
import android.support.v7.app.AppCompatActivity;
import android.support.v7.widget.Toolbar;
import android.view.MenuItem;
import android.view.View;
import android.view.WindowManager;
import android.widget.Button;
import android.widget.ImageView;
import android.widget.TextView;

import com.airbnb.lottie.LottieAnimationView;
import com.bumptech.glide.Glide;
import com.heloisasim.foursquareapi.model.BestPhoto;
import com.heloisasim.foursquareapi.model.Venue;

import butterknife.BindView;
import butterknife.ButterKnife;
import butterknife.OnClick;

public class DetailActivity extends AppCompatActivity implements DetailContract.View {

    public static final String VENUE_EXTRA = "venue";
    private static final String LOADING_VISIBILITY_TAG = "loading_visibility";
    private static final String COORDINATOR_LAYOUT_VISIBILITY_TAG = "coordinator_layout_visibility";
    private static final String PRESENTER_TAG = "presenter";

    @BindView(R.id.detail_animation_view)
    LottieAnimationView mLoading;

    @BindView(R.id.detail_coordinator_layout)
    CoordinatorLayout mCoordinatorLayout;

    @BindView(R.id.detail_collapsing_toolbar)
    CollapsingToolbarLayout mTitle;

    @BindView(R.id.detail_toolbar)
    Toolbar mToolbar;

    @BindView(R.id.detail_top_image)
    ImageView mImageView;

    @BindView(R.id.detail_phone)
    TextView mPhone;

    @BindView(R.id.detail_address)
    TextView mAddress;

    @BindView(R.id.detail_status)
    TextView mStatus;

    @BindView(R.id.detail_days)
    TextView mDays;

    @BindView(R.id.detail_hours)
    TextView mHour;

    @BindView(R.id.detail_directions)
    Button mDirections;

    private DetailPresenter mPresenter;

    @OnClick(R.id.detail_directions)
    public void getDirections() {
        mPresenter.onGetDirectionsClicked();
    }

    @SuppressWarnings("WrongConstant")
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_detail);
        ButterKnife.bind(this);

        setSupportActionBar(mToolbar);
        getSupportActionBar().setDisplayHomeAsUpEnabled(true);

        if (savedInstanceState != null) {
            mLoading.setVisibility(savedInstanceState.getInt(LOADING_VISIBILITY_TAG));
            mCoordinatorLayout.setVisibility(savedInstanceState.getInt(COORDINATOR_LAYOUT_VISIBILITY_TAG));

            mPresenter = savedInstanceState.getParcelable(PRESENTER_TAG);
            mPresenter.setView(this);
        } else {
            Venue venue = getIntent().getExtras().getParcelable(VENUE_EXTRA);
            mPresenter = new DetailPresenter(this, venue);
        }
    }

    @Override
    protected void onSaveInstanceState(Bundle outState) {
        super.onSaveInstanceState(outState);
        outState.putInt(COORDINATOR_LAYOUT_VISIBILITY_TAG, mCoordinatorLayout.getVisibility());
        outState.putInt(LOADING_VISIBILITY_TAG, mLoading.getVisibility());
        outState.putParcelable(PRESENTER_TAG, mPresenter);

    }

    @Override
    public boolean onOptionsItemSelected(MenuItem item) {
        switch (item.getItemId()) {
            case android.R.id.home:
                finish();
        }
        return super.onOptionsItemSelected(item);
    }

    @Override
    public void dismissAnimations() {
        // stop animation
        mLoading.loop(false);
        mLoading.setVisibility(View.GONE);
        mCoordinatorLayout.setVisibility(View.VISIBLE);
    }

    @Override
    public void showError(boolean isConnectionError) {
        int error = (isConnectionError) ? R.string.connection_error : R.string.generic_error;
        Snackbar.make(findViewById(android.R.id.content), error, Snackbar.LENGTH_LONG).show();
    }

    @Override
    public void showName(String name) {
        mTitle.setTitle(name);
    }

    @Override
    public void showHeaderPicture(String url) {
        Point size = new Point();
        getWindowManager().getDefaultDisplay().getSize(size);
        // image width size = screen size
        url = String.format(url, size.x);
        Glide.with(this).load(url).into(mImageView);
    }

    @Override
    public void showPhone(String phone) {
        mPhone.setText(phone);
    }

    @Override
    public void hidePhone() {
        mPhone.setVisibility(View.GONE);
    }

    @Override
    public void showAddress(String address) {
        mAddress.setText(address);
    }

    @Override
    public void hideAddress() {
        mAddress.setVisibility(View.GONE);
        mDirections.setVisibility(View.GONE);
    }

    @Override
    public void showEmptyInfo() {
        mStatus.setText(getString(R.string.empty_info));
        mDays.setVisibility(View.GONE);
        mHour.setVisibility(View.GONE);
    }

    @Override
    public void showDays(String days) {
        mDays.setText(days);
    }

    @Override
    public void showHours(String hours) {
        mHour.setText(hours);
    }

    @Override
    public void showStatus(String status, int color) {
        mStatus.setText(status);
        mStatus.setTextColor(color);
    }

    @Override
    public void openMaps(String uri) {
        Uri gmmIntentUri = Uri.parse(uri);
        Intent mapIntent = new Intent(Intent.ACTION_VIEW, gmmIntentUri);
        mapIntent.setPackage("com.google.android.apps.maps");
        startActivity(mapIntent);
    }

    @Override
    protected void onResume() {
        super.onResume();
        if (mPresenter != null)
            mPresenter.loadVenue();
    }
}
