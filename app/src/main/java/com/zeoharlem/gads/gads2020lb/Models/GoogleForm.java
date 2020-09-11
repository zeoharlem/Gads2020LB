package com.zeoharlem.gads.gads2020lb.Models;

import com.google.gson.annotations.Expose;
import com.google.gson.annotations.SerializedName;

public class GoogleForm {

    @SerializedName("firstname")
    @Expose
    public String firstname;

    @SerializedName("lastname")
    @Expose
    public String lastname;

    @SerializedName("email")
    @Expose
    public String email;

    @SerializedName("githubUrl")
    @Expose
    public String githubUrl;

    public String getFirstname() {
        return firstname;
    }

    public void setFirstname(String firstname) {
        this.firstname = firstname;
    }

    public String getLastname() {
        return lastname;
    }

    public void setLastname(String lastname) {
        this.lastname = lastname;
    }

    public String getEmail() {
        return email;
    }

    public void setEmail(String email) {
        this.email = email;
    }

    public String getGithubUrl() {
        return githubUrl;
    }

    public void setGithubUrl(String githubUrl) {
        this.githubUrl = githubUrl;
    }
}
