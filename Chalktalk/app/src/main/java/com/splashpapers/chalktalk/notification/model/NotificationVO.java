package com.splashpapers.chalktalk.notification.model;

import java.io.Serializable;

/**
 * Created by my pc on 18-Nov-16.
 */

public class NotificationVO  implements Serializable{
    private String title;
    private String date;
    private String Image;

    public String getDate() {
        return date;
    }

    public void setDate(String date) {
        this.date = date;
    }

    public String getTitle() {
        return title;
    }

    public void setTitle(String title) {
        this.title = title;
    }

    public void setImage(String image) {
        Image = image;
    }

    public String getImage() {
        return Image;
    }


}
