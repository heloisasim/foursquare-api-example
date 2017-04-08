package com.heloisasim.foursquareapi.model;

import android.os.Parcel;
import android.os.Parcelable;

import java.util.ArrayList;

public class Groups implements Parcelable {

    private double count;
    private String type;
    private String name;
    private ArrayList<String> items;

    public double getCount() {
        return this.count;
    }

    public void setCount(double count) {
        this.count = count;
    }

    public String getType() {
        return this.type;
    }

    public void setType(String type) {
        this.type = type;
    }

    public String getName() {
        return this.name;
    }

    public void setName(String name) {
        this.name = name;
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
        dest.writeString(this.type);
        dest.writeString(this.name);
        dest.writeStringList(this.items);
    }

    public Groups() {
    }

    protected Groups(Parcel in) {
        this.count = in.readDouble();
        this.type = in.readString();
        this.name = in.readString();
        this.items = in.createStringArrayList();
    }

    public static final Parcelable.Creator<Groups> CREATOR = new Parcelable.Creator<Groups>() {
        @Override
        public Groups createFromParcel(Parcel source) {
            return new Groups(source);
        }

        @Override
        public Groups[] newArray(int size) {
            return new Groups[size];
        }
    };
}
