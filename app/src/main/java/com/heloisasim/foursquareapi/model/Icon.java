package com.heloisasim.foursquareapi.model;

import android.os.Parcel;
import android.os.Parcelable;

public class Icon implements Parcelable {
	
    private String prefix;
    private String suffix;

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

    @Override
    public int describeContents() {
        return 0;
    }

    @Override
    public void writeToParcel(Parcel dest, int flags) {
        dest.writeString(this.prefix);
        dest.writeString(this.suffix);
    }

    public Icon() {
    }

    protected Icon(Parcel in) {
        this.prefix = in.readString();
        this.suffix = in.readString();
    }

    public static final Parcelable.Creator<Icon> CREATOR = new Parcelable.Creator<Icon>() {
        @Override
        public Icon createFromParcel(Parcel source) {
            return new Icon(source);
        }

        @Override
        public Icon[] newArray(int size) {
            return new Icon[size];
        }
    };
}
