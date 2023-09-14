package com.practice.mydevelopment.core.beans;

import java.text.SimpleDateFormat;
import java.util.Date;

public class SelectiveDropdownEventsBean {

    private  String eventName;
    private String location;
    private Date eventDate;

    public SelectiveDropdownEventsBean(String eventName, String location, Date eventDate) {
        this.eventName = eventName;
        this.location = location;
        this.eventDate = eventDate;
    }

    public String getEventName() {
        return eventName;
    }

    public String getLocation() {
        return location;
    }

    public String getEventDate() {
        SimpleDateFormat formatter = new SimpleDateFormat("dd-MM-yyyy");
        return formatter.format(eventDate);
    }
}
