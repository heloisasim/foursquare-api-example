package com.heloisasim.foursquareapi.model;

import java.util.ArrayList;

public class Location {

    private String state;
    private ArrayList<LabeledLatLngs> labeledLatLngs;
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

    public ArrayList<LabeledLatLngs> getLabeledLatLngs() {
        return this.labeledLatLngs;
    }

    public void setLabeledLatLngs(ArrayList<LabeledLatLngs> labeledLatLngs) {
        this.labeledLatLngs = labeledLatLngs;
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

}
