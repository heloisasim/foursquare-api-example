package com.heloisasim.foursquareapi.model;

import android.os.Parcel;
import android.os.Parcelable;

public class Open implements Parcelable {

    private String renderedTime;

    public String getRenderedTime() {
        return renderedTime;
    }

    public void setRenderedTime(String renderedTime) {
        this.renderedTime = renderedTime;
    }

    @Override
    public int describeContents() {
        return 0;
    }

    @Override
    public void writeToParcel(Parcel dest, int flags) {
        dest.writeString(this.renderedTime);
    }

    public Open() {
    }

    protected Open(Parcel in) {
        this.renderedTime = in.readString();
    }

    public static final Parcelable.Creator<Open> CREATOR = new Parcelable.Creator<Open>() {
        @Override
        public Open createFromParcel(Parcel source) {
            return new Open(source);
        }

        @Override
        public Open[] newArray(int size) {
            return new Open[size];
        }
    };
}
