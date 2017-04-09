package com.heloisasim.foursquareapi;

import android.content.Intent;
import android.support.v7.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;
import android.widget.TextView;

import com.bumptech.glide.Glide;
import com.heloisasim.foursquareapi.model.Categories;
import com.heloisasim.foursquareapi.model.Icon;
import com.heloisasim.foursquareapi.model.Location;
import com.heloisasim.foursquareapi.model.Venue;

import java.util.ArrayList;
import java.util.List;

import butterknife.BindView;
import butterknife.ButterKnife;

public class VenuesAdapter extends RecyclerView.Adapter<RecyclerView.ViewHolder> {

    private static final int TYPE_EMPTY = 0;
    private static final int TYPE_VENUE = 1;
    private static final int ICON_SIZE = 44;

    private final String mUrlParam;
    private List<Venue> mVenues = new ArrayList<>();

    public VenuesAdapter() {
        mUrlParam = "?client_id=" + BuildConfig.foursquareClientId + "&client_secret=" + BuildConfig.foursquareClientSecret + "&v=" + BuildConfig.foursquareVersion;
    }

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

    public void updateVenues(List<Venue> venues) {
        mVenues = venues;
        notifyDataSetChanged();
    }

    class VenueViewHolder extends RecyclerView.ViewHolder {

        @BindView(R.id.row_category)
        ImageView mRowCategory;

        @BindView(R.id.row_venue_name)
        TextView mName;

        @BindView(R.id.row_venue_address)
        TextView mAddress;

        @BindView(R.id.row_venue_distance)
        TextView mDistance;

        VenueViewHolder(final View itemView) {
            super(itemView);
            ButterKnife.bind(this, itemView);
            itemView.setOnClickListener(new View.OnClickListener() {
                @Override
                public void onClick(View v) {
                    Intent i = new Intent(v.getContext(), DetailActivity.class);
                    i.putExtra(DetailActivity.VENUE_EXTRA, (Venue) v.getTag());
                    v.getContext().startActivity(i);
                }
            });
        }

        void onBind(Venue venue) {
            ArrayList<Categories> categories = venue.getCategories();
            if (categories != null && !categories.isEmpty()) {
                Icon icon = categories.get(0).getIcon();
                Glide.with(itemView.getContext()).load(icon.getPrefix() + ICON_SIZE + icon.getSuffix() + mUrlParam).into(mRowCategory);
            }
            mName.setText(venue.getName());
            Location location = venue.getLocation();
            mAddress.setText(location.getFormattedAddress().toString());
            mDistance.setText(String.valueOf(location.getDistance()) + "m");
            itemView.setTag(venue);
        }
    }

    class EmptyViewHolder extends RecyclerView.ViewHolder {

        EmptyViewHolder(View itemView) {
            super(itemView);
        }

    }
}
