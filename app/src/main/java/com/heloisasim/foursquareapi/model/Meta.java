package com.heloisasim.foursquareapi.model;

import android.os.Parcel;
import android.os.Parcelable;

public class Meta implements Parcelable {
	
    private String requestId;
    private double code;

    public String getRequestId() {
        return this.requestId;
    }

    public void setRequestId(String requestId) {
        this.requestId = requestId;
    }

    public double getCode() {
        return this.code;
    }

    public void setCode(double code) {
        this.code = code;
    }

    @Override
    public int describeContents() {
        return 0;
    }

    @Override
    public void writeToParcel(Parcel dest, int flags) {
        dest.writeString(this.requestId);
        dest.writeDouble(this.code);
    }

    public Meta() {
    }

    protected Meta(Parcel in) {
        this.requestId = in.readString();
        this.code = in.readDouble();
    }

    public static final Parcelable.Creator<Meta> CREATOR = new Parcelable.Creator<Meta>() {
        @Override
        public Meta createFromParcel(Parcel source) {
            return new Meta(source);
        }

        @Override
        public Meta[] newArray(int size) {
            return new Meta[size];
        }
    };
}
