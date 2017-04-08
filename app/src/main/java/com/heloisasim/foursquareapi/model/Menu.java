package com.heloisasim.foursquareapi.model;

import android.os.Parcel;
import android.os.Parcelable;

public class Menu implements Parcelable {
	
    private String anchor;
    private String label;
    private String mobileUrl;
    private String type;
    private String url;
    private String externalUrl;

    public String getAnchor() {
        return this.anchor;
    }

    public void setAnchor(String anchor) {
        this.anchor = anchor;
    }

    public String getLabel() {
        return this.label;
    }

    public void setLabel(String label) {
        this.label = label;
    }

    public String getMobileUrl() {
        return this.mobileUrl;
    }

    public void setMobileUrl(String mobileUrl) {
        this.mobileUrl = mobileUrl;
    }

    public String getType() {
        return this.type;
    }

    public void setType(String type) {
        this.type = type;
    }

    public String getUrl() {
        return this.url;
    }

    public void setUrl(String url) {
        this.url = url;
    }

    public String getExternalUrl() {
        return this.externalUrl;
    }

    public void setExternalUrl(String externalUrl) {
        this.externalUrl = externalUrl;
    }

    @Override
    public int describeContents() {
        return 0;
    }

    @Override
    public void writeToParcel(Parcel dest, int flags) {
        dest.writeString(this.anchor);
        dest.writeString(this.label);
        dest.writeString(this.mobileUrl);
        dest.writeString(this.type);
        dest.writeString(this.url);
        dest.writeString(this.externalUrl);
    }

    public Menu() {
    }

    protected Menu(Parcel in) {
        this.anchor = in.readString();
        this.label = in.readString();
        this.mobileUrl = in.readString();
        this.type = in.readString();
        this.url = in.readString();
        this.externalUrl = in.readString();
    }

    public static final Parcelable.Creator<Menu> CREATOR = new Parcelable.Creator<Menu>() {
        @Override
        public Menu createFromParcel(Parcel source) {
            return new Menu(source);
        }

        @Override
        public Menu[] newArray(int size) {
            return new Menu[size];
        }
    };
}
