package com.zeoharlem.gads.gads2020lb.Models;

import com.google.gson.annotations.Expose;
import com.google.gson.annotations.SerializedName;

public class LearnersBoard {

    @SerializedName("name")
    @Expose
    public String name;

    @SerializedName("hours")
    @Expose
    public int hours;

    @SerializedName("country")
    @Expose
    public String country;

    @SerializedName("badgeUrl")
    @Expose
    public String badgeUrl;

    public String getBadgeUrl() {
        return badgeUrl;
    }

    public void setBadgeUrl(String badgeUrl) {
        this.badgeUrl = badgeUrl;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public int getHours() {
        return hours;
    }

    public void setHours(int hours) {
        this.hours = hours;
    }

    public String getCountry() {
        return country;
    }

    public void setCountry(String country) {
        this.country = country;
    }
}
