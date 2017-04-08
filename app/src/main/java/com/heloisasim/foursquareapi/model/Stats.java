package com.heloisasim.foursquareapi.model;

import android.os.Parcel;
import android.os.Parcelable;

public class Stats implements Parcelable {
	
    private double tipCount;
    private double checkinsCount;
    private double usersCount;

    public double getTipCount() {
        return this.tipCount;
    }

    public void setTipCount(double tipCount) {
        this.tipCount = tipCount;
    }

    public double getCheckinsCount() {
        return this.checkinsCount;
    }

    public void setCheckinsCount(double checkinsCount) {
        this.checkinsCount = checkinsCount;
    }

    public double getUsersCount() {
        return this.usersCount;
    }

    public void setUsersCount(double usersCount) {
        this.usersCount = usersCount;
    }

    @Override
    public int describeContents() {
        return 0;
    }

    @Override
    public void writeToParcel(Parcel dest, int flags) {
        dest.writeDouble(this.tipCount);
        dest.writeDouble(this.checkinsCount);
        dest.writeDouble(this.usersCount);
    }

    public Stats() {
    }

    protected Stats(Parcel in) {
        this.tipCount = in.readDouble();
        this.checkinsCount = in.readDouble();
        this.usersCount = in.readDouble();
    }

    public static final Parcelable.Creator<Stats> CREATOR = new Parcelable.Creator<Stats>() {
        @Override
        public Stats createFromParcel(Parcel source) {
            return new Stats(source);
        }

        @Override
        public Stats[] newArray(int size) {
            return new Stats[size];
        }
    };
}
