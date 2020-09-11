package com.zeoharlem.gads.gads2020lb.Models;

import com.google.gson.annotations.Expose;
import com.google.gson.annotations.SerializedName;

public class SkillIQ {
    @SerializedName("name")
    @Expose
    public String name;

    @SerializedName("score")
    @Expose
    public int score;

    @SerializedName("country")
    @Expose
    public String country;

    @SerializedName("badgeUrl")
    @Expose
    public String badgeUrl;

    public int getScore() {
        return score;
    }

    public void setScore(int score) {
        this.score = score;
    }

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

    public String getCountry() {
        return country;
    }

    public void setCountry(String country) {
        this.country = country;
    }
}
