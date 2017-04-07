package com.heloisasim.foursquareapi.model;

import java.util.ArrayList;

public class Venues {
	
    private String id;
    private ArrayList<Categories> categories;
    private Stats stats;
    private ArrayList<String> venueChains;
    private VenuePage venuePage;
    private Contact contact;
    private boolean verified;
    private boolean venueRatingBlacklisted;
    private String referralId;
    private String storeId;
    private String url;
    private BeenHere beenHere;
    private Location location;
    private boolean allowMenuUrlEdit;
    private Specials specials;
    private boolean hasPerk;
    private Menu menu;
    private String name;

    public String getId() {
        return this.id;
    }

    public void setId(String id) {
        this.id = id;
    }

    public ArrayList<Categories> getCategories() {
        return this.categories;
    }

    public void setCategories(ArrayList<Categories> categories) {
        this.categories = categories;
    }

    public Stats getStats() {
        return this.stats;
    }

    public void setStats(Stats stats) {
        this.stats = stats;
    }

    public ArrayList<String> getVenueChains() {
        return this.venueChains;
    }

    public void setVenueChains(ArrayList<String> venueChains) {
        this.venueChains = venueChains;
    }

    public VenuePage getVenuePage() {
        return this.venuePage;
    }

    public void setVenuePage(VenuePage venuePage) {
        this.venuePage = venuePage;
    }

    public Contact getContact() {
        return this.contact;
    }

    public void setContact(Contact contact) {
        this.contact = contact;
    }

    public boolean getVerified() {
        return this.verified;
    }

    public void setVerified(boolean verified) {
        this.verified = verified;
    }

    public boolean getVenueRatingBlacklisted() {
        return this.venueRatingBlacklisted;
    }

    public void setVenueRatingBlacklisted(boolean venueRatingBlacklisted) {
        this.venueRatingBlacklisted = venueRatingBlacklisted;
    }

    public String getReferralId() {
        return this.referralId;
    }

    public void setReferralId(String referralId) {
        this.referralId = referralId;
    }

    public String getStoreId() {
        return this.storeId;
    }

    public void setStoreId(String storeId) {
        this.storeId = storeId;
    }

    public String getUrl() {
        return this.url;
    }

    public void setUrl(String url) {
        this.url = url;
    }

    public BeenHere getBeenHere() {
        return this.beenHere;
    }

    public void setBeenHere(BeenHere beenHere) {
        this.beenHere = beenHere;
    }

    public Location getLocation() {
        return this.location;
    }

    public void setLocation(Location location) {
        this.location = location;
    }

    public boolean getAllowMenuUrlEdit() {
        return this.allowMenuUrlEdit;
    }

    public void setAllowMenuUrlEdit(boolean allowMenuUrlEdit) {
        this.allowMenuUrlEdit = allowMenuUrlEdit;
    }

    public Specials getSpecials() {
        return this.specials;
    }

    public void setSpecials(Specials specials) {
        this.specials = specials;
    }

    public boolean getHasPerk() {
        return this.hasPerk;
    }

    public void setHasPerk(boolean hasPerk) {
        this.hasPerk = hasPerk;
    }

    public Menu getMenu() {
        return this.menu;
    }

    public void setMenu(Menu menu) {
        this.menu = menu;
    }

    public String getName() {
        return this.name;
    }

    public void setName(String name) {
        this.name = name;
    }


    
}
