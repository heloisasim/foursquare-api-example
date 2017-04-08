package com.heloisasim.foursquareapi.model;

import android.os.Parcel;
import android.os.Parcelable;

public class Contact implements Parcelable {
	
    private String phone;
    private String twitter;
    private String facebook;
    private String formattedPhone;
    private String facebookUsername;
    private String facebookName;

    public String getPhone() {
        return this.phone;
    }

    public void setPhone(String phone) {
        this.phone = phone;
    }

    public String getTwitter() {
        return this.twitter;
    }

    public void setTwitter(String twitter) {
        this.twitter = twitter;
    }

    public String getFacebook() {
        return this.facebook;
    }

    public void setFacebook(String facebook) {
        this.facebook = facebook;
    }

    public String getFormattedPhone() {
        return this.formattedPhone;
    }

    public void setFormattedPhone(String formattedPhone) {
        this.formattedPhone = formattedPhone;
    }

    public String getFacebookUsername() {
        return this.facebookUsername;
    }

    public void setFacebookUsername(String facebookUsername) {
        this.facebookUsername = facebookUsername;
    }

    public String getFacebookName() {
        return this.facebookName;
    }

    public void setFacebookName(String facebookName) {
        this.facebookName = facebookName;
    }

    @Override
    public int describeContents() {
        return 0;
    }

    @Override
    public void writeToParcel(Parcel dest, int flags) {
        dest.writeString(this.phone);
        dest.writeString(this.twitter);
        dest.writeString(this.facebook);
        dest.writeString(this.formattedPhone);
        dest.writeString(this.facebookUsername);
        dest.writeString(this.facebookName);
    }

    public Contact() {
    }

    protected Contact(Parcel in) {
        this.phone = in.readString();
        this.twitter = in.readString();
        this.facebook = in.readString();
        this.formattedPhone = in.readString();
        this.facebookUsername = in.readString();
        this.facebookName = in.readString();
    }

    public static final Parcelable.Creator<Contact> CREATOR = new Parcelable.Creator<Contact>() {
        @Override
        public Contact createFromParcel(Parcel source) {
            return new Contact(source);
        }

        @Override
        public Contact[] newArray(int size) {
            return new Contact[size];
        }
    };
}
