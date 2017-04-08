package com.heloisasim.foursquareapi.model;

import android.os.Parcel;
import android.os.Parcelable;

public class Categories implements Parcelable {
	
    private String id;
    private String pluralName;
    private Icon icon;
    private String name;
    private String shortName;
    private boolean primary;

    public String getId() {
        return this.id;
    }

    public void setId(String id) {
        this.id = id;
    }

    public String getPluralName() {
        return this.pluralName;
    }

    public void setPluralName(String pluralName) {
        this.pluralName = pluralName;
    }

    public Icon getIcon() {
        return this.icon;
    }

    public void setIcon(Icon icon) {
        this.icon = icon;
    }

    public String getName() {
        return this.name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public String getShortName() {
        return this.shortName;
    }

    public void setShortName(String shortName) {
        this.shortName = shortName;
    }

    public boolean getPrimary() {
        return this.primary;
    }

    public void setPrimary(boolean primary) {
        this.primary = primary;
    }

    @Override
    public int describeContents() {
        return 0;
    }

    @Override
    public void writeToParcel(Parcel dest, int flags) {
        dest.writeString(this.id);
        dest.writeString(this.pluralName);
        dest.writeParcelable(this.icon, flags);
        dest.writeString(this.name);
        dest.writeString(this.shortName);
        dest.writeByte(this.primary ? (byte) 1 : (byte) 0);
    }

    public Categories() {
    }

    protected Categories(Parcel in) {
        this.id = in.readString();
        this.pluralName = in.readString();
        this.icon = in.readParcelable(Icon.class.getClassLoader());
        this.name = in.readString();
        this.shortName = in.readString();
        this.primary = in.readByte() != 0;
    }

    public static final Parcelable.Creator<Categories> CREATOR = new Parcelable.Creator<Categories>() {
        @Override
        public Categories createFromParcel(Parcel source) {
            return new Categories(source);
        }

        @Override
        public Categories[] newArray(int size) {
            return new Categories[size];
        }
    };
}
