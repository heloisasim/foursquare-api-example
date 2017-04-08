package com.heloisasim.foursquareapi.model;

import android.os.Parcel;
import android.os.Parcelable;

import java.util.ArrayList;

public class Response implements Parcelable {
	
    private boolean confident;
    private ArrayList<Venues> venues;

    public boolean getConfident() {
        return this.confident;
    }

    public void setConfident(boolean confident) {
        this.confident = confident;
    }

    public ArrayList<Venues> getVenues() {
        return this.venues;
    }

    public void setVenues(ArrayList<Venues> venues) {
        this.venues = venues;
    }

    @Override
    public int describeContents() {
        return 0;
    }

    @Override
    public void writeToParcel(Parcel dest, int flags) {
        dest.writeByte(this.confident ? (byte) 1 : (byte) 0);
        dest.writeList(this.venues);
    }

    public Response() {
    }

    protected Response(Parcel in) {
        this.confident = in.readByte() != 0;
        this.venues = new ArrayList<Venues>();
        in.readList(this.venues, Venues.class.getClassLoader());
    }

    public static final Parcelable.Creator<Response> CREATOR = new Parcelable.Creator<Response>() {
        @Override
        public Response createFromParcel(Parcel source) {
            return new Response(source);
        }

        @Override
        public Response[] newArray(int size) {
            return new Response[size];
        }
    };
}
