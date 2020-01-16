package com.example.ubored;

import java.util.Date;

public class SocialEvent {
    private String eventTitle;
    private Date startTime;
    private Date endTime;
    private String category;

    public SocialEvent()
    {
        eventTitle=null;
    }

    public SocialEvent(String title)
    {
        this.eventTitle=title;
    }

    public SocialEvent(String title, Date startTime)
    {
        this.eventTitle=title;
        this.startTime=startTime;
    }

    public SocialEvent(String title, Date startTime, Date endTime)
    {
        this.eventTitle=title;
        this.startTime=startTime;
        this.endTime=endTime;
    }

    public SocialEvent(String title, Date startTime, Date endTime, String category)
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

    public Date getStartTime()
    {
        return startTime;
    }

    public Date getEndTime()
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

    public void setStartTime( Date startTime)
    {
        this.startTime=startTime;
    }

    public void setEndTime( Date endTime )
    {
        this.endTime=endTime;
    }

}
