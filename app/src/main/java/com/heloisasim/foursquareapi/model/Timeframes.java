package com.heloisasim.foursquareapi.model;

import android.os.Parcel;
import android.os.Parcelable;

import java.util.ArrayList;

public class Timeframes implements Parcelable {

    private String days;
    private ArrayList<Open> open;

    public ArrayList<Open> getOpen() {
        return open;
    }

    public void setOpen(ArrayList<Open> open) {
        this.open = open;
    }

    public String getDays() {
        return days;
    }

    public void setDays(String days) {
        this.days = days;
    }

    @Override
    public int describeContents() {
        return 0;
    }

    @Override
    public void writeToParcel(Parcel dest, int flags) {
        dest.writeString(this.days);
        dest.writeTypedList(this.open);
    }

    public Timeframes() {
    }

    protected Timeframes(Parcel in) {
        this.days = in.readString();
        this.open = in.createTypedArrayList(Open.CREATOR);
    }

    public static final Parcelable.Creator<Timeframes> CREATOR = new Parcelable.Creator<Timeframes>() {
        @Override
        public Timeframes createFromParcel(Parcel source) {
            return new Timeframes(source);
        }

        @Override
        public Timeframes[] newArray(int size) {
            return new Timeframes[size];
        }
    };
}
