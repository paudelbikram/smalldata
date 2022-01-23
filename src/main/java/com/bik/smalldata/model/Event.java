package com.bik.smalldata.model;

import java.time.LocalDate;

public class Event {
    private long id;

    private String eventName;
    private LocalDate eventTime;
    private String eventLocation;

    public long getId() {
        return id;
    }

    public void setId(long id) {
        this.id = id;
    }

    public String getEventName() {
        return eventName;
    }

    public void setEventName(String eventName) {
        this.eventName = eventName;
    }

    public LocalDate getEventTime() {
        return eventTime;
    }

    public void setEventTime(LocalDate eventTime) {
        this.eventTime = eventTime;
    }

    public String getEventLocation() {
        return eventLocation;
    }

    public void setEventLocation(String eventLocation) {
        this.eventLocation = eventLocation;
    }

    @Override
    public String toString() {
        return "Event{" +
                "id=" + id +
                ", eventName='" + eventName + '\'' +
                ", eventTime=" + eventTime +
                ", eventLocation='" + eventLocation + '\'' +
                '}';
    }
}
