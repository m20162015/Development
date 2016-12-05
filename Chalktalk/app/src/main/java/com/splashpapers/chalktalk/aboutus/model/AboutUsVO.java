package com.splashpapers.chalktalk.aboutus.model;

import java.io.Serializable;

/**
 * Created by manishsharma on 11/30/16.
 */
public class AboutUsVO implements Serializable{

    private String aboutUsText;
    private String location;
    private String imageURL;

    public String getAboutUsText() {
        return aboutUsText;
    }

    public void setAboutUsText(String aboutUsText) {
        this.aboutUsText = aboutUsText;
    }

    public String getLocation() {
        return location;
    }

    public void setLocation(String location) {
        this.location = location;
    }

    public String getImageURL() {
        return imageURL;
    }

    public void setImageURL(String imageURL) {
        this.imageURL = imageURL;
    }
}
