package com.heloisasim.foursquareapi.model;

import android.os.Parcel;
import android.os.Parcelable;

import java.util.ArrayList;

public class Specials implements Parcelable {
	
    private double count;
    private ArrayList<String> items;

    public double getCount() {
        return this.count;
    }

    public void setCount(double count) {
        this.count = count;
    }

    public ArrayList<String> getItems() {
        return this.items;
    }

    public void setItems(ArrayList<String> items) {
        this.items = items;
    }

    @Override
    public int describeContents() {
        return 0;
    }

    @Override
    public void writeToParcel(Parcel dest, int flags) {
        dest.writeDouble(this.count);
        dest.writeStringList(this.items);
    }

    public Specials() {
    }

    protected Specials(Parcel in) {
        this.count = in.readDouble();
        this.items = in.createStringArrayList();
    }

    public static final Parcelable.Creator<Specials> CREATOR = new Parcelable.Creator<Specials>() {
        @Override
        public Specials createFromParcel(Parcel source) {
            return new Specials(source);
        }

        @Override
        public Specials[] newArray(int size) {
            return new Specials[size];
        }
    };
}
