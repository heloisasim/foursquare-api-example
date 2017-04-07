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

public class VenuesAdapter extends RecyclerView.Adapter<VenuesAdapter.ViewHolder> {

    private List<Venues> mVenues = new ArrayList<>();

    @Override
    public ViewHolder onCreateViewHolder(ViewGroup parent, int viewType) {
        View view = LayoutInflater.from(parent.getContext()).inflate(R.layout.venue_row, parent, false);
        return new ViewHolder(view);
    }

    @Override
    public void onBindViewHolder(ViewHolder holder, int position) {
        holder.onBind(mVenues.get(position));
    }

    @Override
    public int getItemCount() {
        return mVenues.size();
    }

    public void updateVenues(List<Venues> venues) {
        mVenues = venues;
        notifyDataSetChanged();
    }

    class ViewHolder extends RecyclerView.ViewHolder {

        @BindView(R.id.row_venue_name)
        TextView mName;

        @BindView(R.id.row_venue_address)
        TextView mAddress;

        @BindView(R.id.row_venue_distance)
        TextView mDistance;

        public ViewHolder(View itemView) {
            super(itemView);
            ButterKnife.bind(this, itemView);
        }

        public void onBind(Venues venue) {
            mName.setText(venue.getName());
            Location location = venue.getLocation();
            mAddress.setText(location.getFormattedAddress().toString());
            mDistance.setText(String.valueOf(location.getDistance()) + "m");
        }
    }
}
