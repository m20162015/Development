package com.splashpapers.chalktalk.notices.model;

import java.io.Serializable;

/**
 * Created by manishsharma on 11/22/16.
 */
public class NoticesVO implements Serializable {

    private String title;
    private String desc;
    private String date;

    public String getTitle() {
        return title;
    }

    public void setTitle(String title) {
        this.title = title;
    }

    public String getDesc() {
        return desc;
    }

    public void setDesc(String desc) {
        this.desc = desc;
    }

    public String getDate() {
        return date;
    }

    public void setDate(String date) {
        this.date = date;
    }
}
