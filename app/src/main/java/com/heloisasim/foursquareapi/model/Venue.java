package com.heloisasim.foursquareapi.model;

import android.os.Parcel;
import android.os.Parcelable;

import java.util.ArrayList;

public class Venue implements Parcelable {

    private BestPhoto bestPhoto;
    private Location location;
    private ArrayList<String> tags;
    private String shortUrl;
    private Contact contact;
    private String name;
    private String id;
    private Price price;
    private ArrayList<Categories> categories;
    private Hours hours;

    public BestPhoto getBestPhoto() {
        return this.bestPhoto;
    }

    public void setBestPhoto(BestPhoto bestPhoto) {
        this.bestPhoto = bestPhoto;
    }

    public Location getLocation() {
        return this.location;
    }

    public void setLocation(Location location) {
        this.location = location;
    }


    public ArrayList<String> getTags() {
        return this.tags;
    }

    public void setTags(ArrayList<String> tags) {
        this.tags = tags;
    }

    public String getShortUrl() {
        return this.shortUrl;
    }

    public void setShortUrl(String shortUrl) {
        this.shortUrl = shortUrl;
    }

    public Contact getContact() {
        return this.contact;
    }

    public void setContact(Contact contact) {
        this.contact = contact;
    }

    public String getName() {
        return this.name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public String getId() {
        return this.id;
    }

    public void setId(String id) {
        this.id = id;
    }

    public Price getPrice() {
        return this.price;
    }

    public void setPrice(Price price) {
        this.price = price;
    }

    public ArrayList<Categories> getCategories() {
        return this.categories;
    }

    public void setCategories(ArrayList<Categories> categories) {
        this.categories = categories;
    }

    public Hours getHours() {
        return hours;
    }

    public void setHours(Hours hours) {
        this.hours = hours;
    }

    @Override
    public int describeContents() {
        return 0;
    }

    @Override
    public void writeToParcel(Parcel dest, int flags) {
        dest.writeParcelable(this.bestPhoto, flags);
        dest.writeParcelable(this.location, flags);
        dest.writeStringList(this.tags);
        dest.writeString(this.shortUrl);
        dest.writeParcelable(this.contact, flags);
        dest.writeString(this.name);
        dest.writeString(this.id);
        dest.writeParcelable(this.price, flags);
        dest.writeTypedList(this.categories);
        dest.writeParcelable(this.hours, flags);
    }

    public Venue() {
    }

    protected Venue(Parcel in) {
        this.bestPhoto = in.readParcelable(BestPhoto.class.getClassLoader());
        this.location = in.readParcelable(Location.class.getClassLoader());
        this.tags = in.createStringArrayList();
        this.shortUrl = in.readString();
        this.contact = in.readParcelable(Contact.class.getClassLoader());
        this.name = in.readString();
        this.id = in.readString();
        this.price = in.readParcelable(Price.class.getClassLoader());
        this.categories = in.createTypedArrayList(Categories.CREATOR);
        this.hours = in.readParcelable(Hours.class.getClassLoader());
    }

    public static final Parcelable.Creator<Venue> CREATOR = new Parcelable.Creator<Venue>() {
        @Override
        public Venue createFromParcel(Parcel source) {
            return new Venue(source);
        }

        @Override
        public Venue[] newArray(int size) {
            return new Venue[size];
        }
    };
}
