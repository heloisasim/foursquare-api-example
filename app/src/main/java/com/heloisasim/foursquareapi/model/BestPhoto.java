package com.heloisasim.foursquareapi.model;

import android.os.Parcel;
import android.os.Parcelable;

public class BestPhoto implements Parcelable {

    private String prefix;
    private String suffix;
    private String id;
    private int height;
    private int width;
    private String visibility;

    public String getPrefix() {
        return this.prefix;
    }

    public void setPrefix(String prefix) {
        this.prefix = prefix;
    }

    public String getSuffix() {
        return this.suffix;
    }

    public void setSuffix(String suffix) {
        this.suffix = suffix;
    }

    public String getId() {
        return this.id;
    }

    public void setId(String id) {
        this.id = id;
    }

    public int getHeight() {
        return this.height;
    }

    public void setHeight(int height) {
        this.height = height;
    }

    public int getWidth() {
        return this.width;
    }

    public void setWidth(int width) {
        this.width = width;
    }

    public String getVisibility() {
        return this.visibility;
    }

    public void setVisibility(String visibility) {
        this.visibility = visibility;
    }

    @Override
    public int describeContents() {
        return 0;
    }

    @Override
    public void writeToParcel(Parcel dest, int flags) {
        dest.writeString(this.prefix);
        dest.writeString(this.suffix);
        dest.writeString(this.id);
        dest.writeInt(this.height);
        dest.writeInt(this.width);
        dest.writeString(this.visibility);
    }

    public BestPhoto() {
    }

    protected BestPhoto(Parcel in) {
        this.prefix = in.readString();
        this.suffix = in.readString();
        this.id = in.readString();
        this.height = in.readInt();
        this.width = in.readInt();
        this.visibility = in.readString();
    }

    public static final Parcelable.Creator<BestPhoto> CREATOR = new Parcelable.Creator<BestPhoto>() {
        @Override
        public BestPhoto createFromParcel(Parcel source) {
            return new BestPhoto(source);
        }

        @Override
        public BestPhoto[] newArray(int size) {
            return new BestPhoto[size];
        }
    };
}
