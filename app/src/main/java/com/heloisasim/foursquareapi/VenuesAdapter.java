package com.heloisasim.foursquareapi;

import android.support.v7.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.TextView;

import com.heloisasim.foursquareapi.model.Location;
import com.heloisasim.foursquareapi.model.Venues;

import java.util.ArrayList;
import java.util.List;

import butterknife.BindView;
import butterknife.ButterKnife;

public class VenuesAdapter extends RecyclerView.Adapter<RecyclerView.ViewHolder> {

    private static final int TYPE_EMPTY = 0;
    private static final int TYPE_VENUE = 1;

    private List<Venues> mVenues = new ArrayList<>();

    @Override
    public RecyclerView.ViewHolder onCreateViewHolder(ViewGroup parent, int viewType) {
        switch (viewType) {
            case TYPE_VENUE:
                return new VenueViewHolder(LayoutInflater.from(parent.getContext()).inflate(R.layout.venue_row, parent, false));
            default:
                return new EmptyViewHolder(LayoutInflater.from(parent.getContext()).inflate(R.layout.empty_row, parent, false));
        }
    }

    @Override
    public void onBindViewHolder(RecyclerView.ViewHolder holder, int position) {
        if (holder instanceof VenueViewHolder)
            ((VenueViewHolder) holder).onBind(mVenues.get(position));
    }

    @Override
    public int getItemCount() {
        // return 1 because of empty view
        return (mVenues != null && mVenues.size() > 0) ? mVenues.size() : 1;
    }

    @Override
    public int getItemViewType(int position) {
        if (mVenues == null || mVenues.size() == 0)
            return TYPE_EMPTY;
        return TYPE_VENUE;
    }

    public void updateVenues(List<Venues> venues) {
        mVenues = venues;
        notifyDataSetChanged();
    }

    class VenueViewHolder extends RecyclerView.ViewHolder {

        @BindView(R.id.row_venue_name)
        TextView mName;

        @BindView(R.id.row_venue_address)
        TextView mAddress;

        @BindView(R.id.row_venue_distance)
        TextView mDistance;

        VenueViewHolder(View itemView) {
            super(itemView);
            ButterKnife.bind(this, itemView);
        }

        void onBind(Venues venue) {
            mName.setText(venue.getName());
            Location location = venue.getLocation();
            mAddress.setText(location.getFormattedAddress().toString());
            mDistance.setText(String.valueOf(location.getDistance()) + "m");
        }
    }

    class EmptyViewHolder extends RecyclerView.ViewHolder {

        EmptyViewHolder(View itemView) {
            super(itemView);
        }

    }
}
