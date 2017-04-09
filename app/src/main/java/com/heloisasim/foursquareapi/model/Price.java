package com.heloisasim.foursquareapi.model;

import android.os.Parcel;
import android.os.Parcelable;

public class Price implements Parcelable {
	
    private String message;
    private double tier;
    private String currency;

    public String getMessage() {
        return this.message;
    }

    public void setMessage(String message) {
        this.message = message;
    }

    public double getTier() {
        return this.tier;
    }

    public void setTier(double tier) {
        this.tier = tier;
    }

    public String getCurrency() {
        return this.currency;
    }

    public void setCurrency(String currency) {
        this.currency = currency;
    }

    @Override
    public int describeContents() {
        return 0;
    }

    @Override
    public void writeToParcel(Parcel dest, int flags) {
        dest.writeString(this.message);
        dest.writeDouble(this.tier);
        dest.writeString(this.currency);
    }

    public Price() {
    }

    protected Price(Parcel in) {
        this.message = in.readString();
        this.tier = in.readDouble();
        this.currency = in.readString();
    }

    public static final Parcelable.Creator<Price> CREATOR = new Parcelable.Creator<Price>() {
        @Override
        public Price createFromParcel(Parcel source) {
            return new Price(source);
        }

        @Override
        public Price[] newArray(int size) {
            return new Price[size];
        }
    };
}
