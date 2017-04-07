package com.heloisasim.foursquareapi.model;

public class Contact {
	
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

}
