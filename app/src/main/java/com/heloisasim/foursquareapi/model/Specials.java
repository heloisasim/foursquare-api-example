package com.heloisasim.foursquareapi.model;

import java.util.ArrayList;

public class Specials {
	
    private double count;
    private ArrayList<String> items;

    public double getCount() {
        return this.count;
    }

    public void setCount(double count) {
        this.count = count;
    }

    public ArrayList<String> getItems() {
        return this.items;
    }

    public void setItems(ArrayList<String> items) {
        this.items = items;
    }

}
