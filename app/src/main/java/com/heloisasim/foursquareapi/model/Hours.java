package com.heloisasim.foursquareapi.model;

import android.os.Parcel;
import android.os.Parcelable;

import java.util.ArrayList;

public class Hours implements Parcelable {

    private String status;
    private boolean isOpen;
    private ArrayList<Timeframes> timeframes;

    public String getStatus() {
        return status;
    }

    public void setStatus(String status) {
        this.status = status;
    }

    public boolean isOpen() {
        return isOpen;
    }

    public void setOpen(boolean isOpen) {
        this.isOpen = isOpen;
    }

    public ArrayList<Timeframes> getTimeframes() {
        return timeframes;
    }

    public void setTimeframes(ArrayList<Timeframes> timeframes) {
        this.timeframes = timeframes;
    }

    @Override
    public int describeContents() {
        return 0;
    }

    @Override
    public void writeToParcel(Parcel dest, int flags) {
        dest.writeString(this.status);
        dest.writeByte(this.isOpen ? (byte) 1 : (byte) 0);
        dest.writeTypedList(this.timeframes);
    }

    public Hours() {
    }

    protected Hours(Parcel in) {
        this.status = in.readString();
        this.isOpen = in.readByte() != 0;
        this.timeframes = in.createTypedArrayList(Timeframes.CREATOR);
    }

    public static final Parcelable.Creator<Hours> CREATOR = new Parcelable.Creator<Hours>() {
        @Override
        public Hours createFromParcel(Parcel source) {
            return new Hours(source);
        }

        @Override
        public Hours[] newArray(int size) {
            return new Hours[size];
        }
    };
}
