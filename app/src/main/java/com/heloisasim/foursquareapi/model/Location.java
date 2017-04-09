package com.heloisasim.foursquareapi.model;

import android.os.Parcel;
import android.os.Parcelable;

import java.util.ArrayList;

public class Location implements Parcelable {

    private String state;
    private String crossStreet;
    private double lat;
    private String address;
    private ArrayList<String> formattedAddress;
    private String city;
    private String postalCode;
    private String cc;
    private double lng;
    private int distance;
    private String country;

    public String getState() {
        return this.state;
    }

    public void setState(String state) {
        this.state = state;
    }

    public String getCrossStreet() {
        return this.crossStreet;
    }

    public void setCrossStreet(String crossStreet) {
        this.crossStreet = crossStreet;
    }

    public double getLat() {
        return this.lat;
    }

    public void setLat(double lat) {
        this.lat = lat;
    }

    public String getAddress() {
        return this.address;
    }

    public void setAddress(String address) {
        this.address = address;
    }

    public ArrayList<String> getFormattedAddress() {
        return this.formattedAddress;
    }

    public void setFormattedAddress(ArrayList<String> formattedAddress) {
        this.formattedAddress = formattedAddress;
    }

    public String getCity() {
        return this.city;
    }

    public void setCity(String city) {
        this.city = city;
    }

    public String getPostalCode() {
        return this.postalCode;
    }

    public void setPostalCode(String postalCode) {
        this.postalCode = postalCode;
    }

    public String getCc() {
        return this.cc;
    }

    public void setCc(String cc) {
        this.cc = cc;
    }

    public double getLng() {
        return this.lng;
    }

    public void setLng(double lng) {
        this.lng = lng;
    }

    public int getDistance() {
        return this.distance;
    }

    public void setDistance(int distance) {
        this.distance = distance;
    }

    public String getCountry() {
        return this.country;
    }

    public void setCountry(String country) {
        this.country = country;
    }

    /**
     * concat all address information
     */
    public String getFullAddress() {
        String fullAddress = "";
        for (String address : formattedAddress) {
            fullAddress += address + "\n";
        }
        return fullAddress;
    }

    @Override
    public int describeContents() {
        return 0;
    }

    @Override
    public void writeToParcel(Parcel dest, int flags) {
        dest.writeString(this.state);
        dest.writeString(this.crossStreet);
        dest.writeDouble(this.lat);
        dest.writeString(this.address);
        dest.writeStringList(this.formattedAddress);
        dest.writeString(this.city);
        dest.writeString(this.postalCode);
        dest.writeString(this.cc);
        dest.writeDouble(this.lng);
        dest.writeInt(this.distance);
        dest.writeString(this.country);
    }

    public Location() {
    }

    protected Location(Parcel in) {
        this.state = in.readString();
        this.crossStreet = in.readString();
        this.lat = in.readDouble();
        this.address = in.readString();
        this.formattedAddress = in.createStringArrayList();
        this.city = in.readString();
        this.postalCode = in.readString();
        this.cc = in.readString();
        this.lng = in.readDouble();
        this.distance = in.readInt();
        this.country = in.readString();
    }

    public static final Parcelable.Creator<Location> CREATOR = new Parcelable.Creator<Location>() {
        @Override
        public Location createFromParcel(Parcel source) {
            return new Location(source);
        }

        @Override
        public Location[] newArray(int size) {
            return new Location[size];
        }
    };
}
