package com.heloisasim.foursquareapi.model;

import android.os.Parcel;
import android.os.Parcelable;

public class BeenHere implements Parcelable {
	
    private double lastCheckinExpiredAt;

    public double getLastCheckinExpiredAt() {
        return this.lastCheckinExpiredAt;
    }

    public void setLastCheckinExpiredAt(double lastCheckinExpiredAt) {
        this.lastCheckinExpiredAt = lastCheckinExpiredAt;
    }

    @Override
    public int describeContents() {
        return 0;
    }

    @Override
    public void writeToParcel(Parcel dest, int flags) {
        dest.writeDouble(this.lastCheckinExpiredAt);
    }

    public BeenHere() {
    }

    protected BeenHere(Parcel in) {
        this.lastCheckinExpiredAt = in.readDouble();
    }

    public static final Parcelable.Creator<BeenHere> CREATOR = new Parcelable.Creator<BeenHere>() {
        @Override
        public BeenHere createFromParcel(Parcel source) {
            return new BeenHere(source);
        }

        @Override
        public BeenHere[] newArray(int size) {
            return new BeenHere[size];
        }
    };
}
