package com.heloisasim.foursquareapi.model;

import android.os.Parcel;
import android.os.Parcelable;

public class LabeledLatLngs implements Parcelable {
	
    private String label;
    private double lat;
    private double lng;

    public String getLabel() {
        return this.label;
    }

    public void setLabel(String label) {
        this.label = label;
    }

    public double getLat() {
        return this.lat;
    }

    public void setLat(double lat) {
        this.lat = lat;
    }

    public double getLng() {
        return this.lng;
    }

    public void setLng(double lng) {
        this.lng = lng;
    }

    @Override
    public int describeContents() {
        return 0;
    }

    @Override
    public void writeToParcel(Parcel dest, int flags) {
        dest.writeString(this.label);
        dest.writeDouble(this.lat);
        dest.writeDouble(this.lng);
    }

    public LabeledLatLngs() {
    }

    protected LabeledLatLngs(Parcel in) {
        this.label = in.readString();
        this.lat = in.readDouble();
        this.lng = in.readDouble();
    }

    public static final Parcelable.Creator<LabeledLatLngs> CREATOR = new Parcelable.Creator<LabeledLatLngs>() {
        @Override
        public LabeledLatLngs createFromParcel(Parcel source) {
            return new LabeledLatLngs(source);
        }

        @Override
        public LabeledLatLngs[] newArray(int size) {
            return new LabeledLatLngs[size];
        }
    };
}
