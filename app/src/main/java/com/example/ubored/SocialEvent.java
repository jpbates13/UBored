package com.example.ubored;

import java.text.DateFormat;
import java.util.Date;

public class SocialEvent {
    private String eventTitle;
    private String startTime;
    private String endTime;
    private String category;

    public SocialEvent()
    {
        eventTitle=null;
    }

    public SocialEvent(String title)
    {
        this.eventTitle=title;
    }

    public SocialEvent(String title, String startTime)
    {
        this.eventTitle=title;
        this.startTime=startTime;
    }

    public SocialEvent(String title, String startTime, String endTime)
    {
        this.eventTitle=title;
        this.startTime=startTime;
        this.endTime=endTime;
    }

    public SocialEvent(String title, String startTime, String endTime, String category)
    {
        this.eventTitle=title;
        this.startTime=startTime;
        this.endTime=endTime;
        this.category=category;
    }

    public String getEventTitle()
    {
        return eventTitle;
    }

    public String getStartTime()
    {
        return startTime;
    }

    public String getEndTime()
    {
        return endTime;
    }

    public String getCategory()
    {
        return category;
    }

    public void setCategory(String category)
    {
        this.category=category;
    }

    public void setStartTime(String startTime)
    {
        this.startTime=startTime;
    }

    public void setEndTime(String endTime)
    {
        this.endTime=endTime;
    }

}
