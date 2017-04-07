package com.heloisasim.foursquareapi;

import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;
import android.support.v7.widget.RecyclerView;

import com.heloisasim.foursquareapi.model.BaseClass;
import com.heloisasim.foursquareapi.model.Venues;
import com.heloisasim.foursquareapi.networking.ApiService;
import com.heloisasim.foursquareapi.networking.RestClient;

import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.Date;
import java.util.Locale;

import butterknife.BindView;
import butterknife.ButterKnife;
import retrofit2.Call;
import retrofit2.Callback;
import retrofit2.Response;

public class MainActivity extends AppCompatActivity implements Callback<BaseClass> {

    @BindView(R.id.recycler_view)
    RecyclerView mRecyclerView;
    private VenuesAdapter mAdapter;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        ButterKnife.bind(this);

        mAdapter = new VenuesAdapter();
        mRecyclerView.setAdapter(mAdapter);

        getData();
    }

    private void getData() {
        SimpleDateFormat dateFormat = new SimpleDateFormat("yyyyMMdd", Locale.getDefault());
        String currentDate = dateFormat.format(new Date());

        RestClient restClient = new RestClient();
        ApiService apiService = restClient.getApiService();
        Call<BaseClass> call = apiService.getVenues(BuildConfig.foursquareStartLocation, BuildConfig.foursquareClientId, BuildConfig.foursquareClientSecret, currentDate);
        call.enqueue(this);
    }

    @Override
    public void onResponse(Call<BaseClass> call, Response<BaseClass> response) {
        if (response.isSuccessful()) {
            BaseClass body = response.body();
            ArrayList<Venues> venues = body.getResponse().getVenues();
            mAdapter.updateVenues(venues);
        } else {
            // TODO
        }
    }

    @Override
    public void onFailure(Call<BaseClass> call, Throwable t) {
        // TODO
    }
}
