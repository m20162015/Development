package com.splashpapers.chalktalk.myprofile.model;

import java.io.Serializable;

/**
 * Created by manishsharma on 11/30/16.
 */
public class MyProfileVO implements Serializable{

    private String name;
    private String email;
    private String phone;
    private String imageURL;
    private String address;

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public String getEmail() {
        return email;
    }

    public void setEmail(String email) {
        this.email = email;
    }

    public String getPhone() {
        return phone;
    }

    public void setPhone(String phone) {
        this.phone = phone;
    }

    public String getImageURL() {
        return imageURL;
    }

    public void setImageURL(String imageURL) {
        this.imageURL = imageURL;
    }

    public String getAddress() {
        return address;
    }

    public void setAddress(String address) {
        this.address = address;
    }
}
