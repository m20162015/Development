package com.splashpapers.chalktalk.timetable.model;

import java.io.Serializable;

/**
 * Created by manishsharma on 11/24/16.
 */
public class TimeTableVO implements Serializable {

    private String time;
    private String name;
    private String subjet;

    public String getTime() {
        return time;
    }

    public void setTime(String time) {
        this.time = time;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public String getSubjet() {
        return subjet;
    }

    public void setSubjet(String subjet) {
        this.subjet = subjet;
    }
}
