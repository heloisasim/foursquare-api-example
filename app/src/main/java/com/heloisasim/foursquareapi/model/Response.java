package com.heloisasim.foursquareapi.model;

import java.util.ArrayList;

public class Response {
	
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


    
}
