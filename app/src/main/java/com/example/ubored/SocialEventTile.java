package com.example.ubored;

import android.content.ClipData;
import android.content.Context;
import android.graphics.Color;
import android.view.MotionEvent;
import android.view.View;
import android.widget.TextView;

import java.util.Date;

public class SocialEventTile extends TextView {

    public static final int TILE_SIZE = 600;
    private String eventTitle;
    private Date startTime;
    private Date endTime;
    private String category;

    public SocialEventTile(Context context, SocialEvent socialEvent)
    {
        super(context);
        this.eventTitle=socialEvent.getEventTitle();
        this.startTime = socialEvent.getStartTime();
        this.endTime=socialEvent.getEndTime();
        this.category=socialEvent.getCategory();
        setText(this.eventTitle);
        setTextAlignment(TEXT_ALIGNMENT_CENTER);
        setHeight(TILE_SIZE);
        setWidth(TILE_SIZE);
        setTextSize(30);
        setBackgroundColor(Color.rgb(255, 255, 255));
    }

    @Override
    public boolean onTouchEvent(MotionEvent motionEvent) {

        if(motionEvent.getAction()==MotionEvent.ACTION_DOWN)
        {
            //TODO
            View.DragShadowBuilder tmpView = new View.DragShadowBuilder(this);
            ClipData tmpClip = ClipData.newPlainText("","");
            startDrag(tmpClip, tmpView, this, 0);
            return true;
        }

        return super.onTouchEvent(motionEvent);
    }

    public void setStart(Date startTime)
    {
        this.startTime=startTime;
    }

    public Date getStartTime()
    {
        return startTime;
    }

    public void setEnd(Date endTime)
    {
        this.endTime=endTime;
    }

    public Date getEndTime()
    {
        return endTime;
    }

    public void setCategory(String category)
    {
        this.category=category;
    }

    public String getCategory()
    {
        return category;
    }
}
