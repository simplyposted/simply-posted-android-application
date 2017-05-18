package com.qedum.simplyposted.model.api;

import android.content.SharedPreferences;

import com.google.gson.annotations.SerializedName;

/**
 * Created by bogdan.aksonenko on 5/17/17.
 */

public class UserResponse {
    @SerializedName("pk")
    private Long id;

    @SerializedName("username")
    private String username;

    @SerializedName("email")
    private String email;

    @SerializedName("first_name")
    private String firstname;

    @SerializedName("last_name")
    private String lastname;

    public Long getId() {
        return id;
    }

    public void setId(Long id) {
        this.id = id;
    }

    public String getUsername() {
        return username;
    }

    public void setUsername(String username) {
        this.username = username;
    }

    public String getEmail() {
        return email;
    }

    public void setEmail(String email) {
        this.email = email;
    }

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

    public UserResponse() {
    }

    public UserResponse(Long id, String username, String email, String firstname, String lastname) {
        this.id = id;
        this.username = username;
        this.email = email;
        this.firstname = firstname;
        this.lastname = lastname;
    }


}
