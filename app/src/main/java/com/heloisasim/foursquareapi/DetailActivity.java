package com.heloisasim.foursquareapi;

import android.graphics.Color;
import android.os.Bundle;
import android.support.design.widget.CollapsingToolbarLayout;
import android.support.design.widget.CoordinatorLayout;
import android.support.design.widget.Snackbar;
import android.support.v7.app.AppCompatActivity;
import android.view.View;
import android.view.WindowManager;
import android.widget.ImageView;
import android.widget.TextView;

import com.airbnb.lottie.LottieAnimationView;
import com.bumptech.glide.Glide;
import com.heloisasim.foursquareapi.model.Hours;
import com.heloisasim.foursquareapi.model.Timeframes;
import com.heloisasim.foursquareapi.model.Venue;
import com.heloisasim.foursquareapi.model.VenueBaseClass;
import com.heloisasim.foursquareapi.networking.RestClient;

import java.io.IOException;
import java.util.ArrayList;

import butterknife.BindView;
import butterknife.ButterKnife;
import retrofit2.Call;
import retrofit2.Callback;
import retrofit2.Response;

public class DetailActivity extends AppCompatActivity implements Callback<VenueBaseClass> {

    public static final String VENUE_EXTRA = "venue";
    private static final String LOADING_VISIBILITY_TAG = "loading_visibility";
    private static final String COORDINATOR_LAYOUT_VISIBILITY_TAG = "coordinator_layout_visibility";

    @BindView(R.id.detail_animation_view)
    LottieAnimationView mLoading;

    @BindView(R.id.detail_coordinator_layout)
    CoordinatorLayout mCoordinatorLayout;

    @BindView(R.id.detail_collapsing_toolbar)
    CollapsingToolbarLayout mTitle;

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

    private Venue mVenue;

    @SuppressWarnings("WrongConstant")
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_detail);
        ButterKnife.bind(this);

        mVenue = getIntent().getParcelableExtra(VENUE_EXTRA);
        if (savedInstanceState != null) {
            mVenue = savedInstanceState.getParcelable(VENUE_EXTRA);
            mLoading.setVisibility(savedInstanceState.getInt(LOADING_VISIBILITY_TAG));
            mCoordinatorLayout.setVisibility(savedInstanceState.getInt(COORDINATOR_LAYOUT_VISIBILITY_TAG));
            showVenueDetails();
        } else {
            getVenueDetail();
        }
    }

    @Override
    protected void onSaveInstanceState(Bundle outState) {
        super.onSaveInstanceState(outState);
        outState.putParcelable(VENUE_EXTRA, mVenue);
        outState.putInt(COORDINATOR_LAYOUT_VISIBILITY_TAG, mCoordinatorLayout.getVisibility());
        outState.putInt(LOADING_VISIBILITY_TAG, mLoading.getVisibility());
    }

    private void getVenueDetail() {
        // prepare call to foursquare API
        RestClient restClient = new RestClient();
        Call<VenueBaseClass> mCallVenue = restClient.prepareVenueRequest(mVenue.getId());
        // do request to foursquare API
        mCallVenue.enqueue(this);
    }


    @Override
    public void onResponse(Call<VenueBaseClass> call, Response<VenueBaseClass> response) {
        dismissAnimations();

        if (response.isSuccessful()) {
            VenueBaseClass body = response.body();
            mVenue = body.getResponse().getVenue();
        } else {
            Snackbar.make(findViewById(android.R.id.content), R.string.generic_error, Snackbar.LENGTH_LONG).show();
        }

        showVenueDetails();
    }

    /**
     * Shows the venue information. If the request with full information did not succeed,
     * then shows the basic information received via Extra.
     */
    private void showVenueDetails() {

        if (mVenue.getBestPhoto() != null) {
            Glide.with(this).load(mVenue.getBestPhoto().getUrl(getWindowManager())).into(mImageView);
        }

        mTitle.setTitle(mVenue.getName());
        showContactCard();
        showGeneralInfoCard();

    }

    private void showContactCard() {
        if (mVenue.getContact() != null) {
            mPhone.setText(mVenue.getContact().getFormattedPhone());
        } else {
            mPhone.setVisibility(View.GONE);
        }

        if (mVenue.getLocation() != null) {
            mAddress.setText(mVenue.getLocation().getFullAddress());
        } else {
            mAddress.setVisibility(View.GONE);
        }

    }

    private void showGeneralInfoCard() {
        Hours hours = mVenue.getHours();
        if (hours != null) {
            showOpeningHoursInfo(hours);
        } else {
            mStatus.setText(getString(R.string.empty_info));
            mDays.setVisibility(View.GONE);
            mHour.setVisibility(View.GONE);
        }
    }

    /**
     * Shows status, days of the week that are open and hours
     *
     * @param hours
     */
    private void showOpeningHoursInfo(Hours hours) {
        mStatus.setText(hours.getStatus());
        mStatus.setTextColor(hours.isOpen() ? Color.GREEN : Color.RED);
        ArrayList<Timeframes> timeFrames = hours.getTimeframes();

        // Checks null objects and empty lists to prevent errors
        if (timeFrames != null && !timeFrames.isEmpty()) {

            mDays.setText(timeFrames.get(0).getDays());

            if (timeFrames.get(0).getOpen() != null && !timeFrames.get(0).getOpen().isEmpty())
                mHour.setText(timeFrames.get(0).getOpen().get(0).getRenderedTime());
        }
    }

    private void dismissAnimations() {
        // stop animation
        mLoading.loop(false);
        mLoading.setVisibility(View.GONE);
        mCoordinatorLayout.setVisibility(View.VISIBLE);
    }

    @Override
    public void onFailure(Call<VenueBaseClass> call, Throwable t) {
        dismissAnimations();
        int error = (t instanceof IOException) ? R.string.connection_error : R.string.generic_error;
        Snackbar.make(findViewById(android.R.id.content), error, Snackbar.LENGTH_LONG).show();
        showVenueDetails();
    }
}
