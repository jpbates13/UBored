package com.example.ubored;

import android.content.ClipData;
import android.content.Context;
import android.graphics.Color;
import android.view.MotionEvent;
import android.view.View;
import android.widget.TextView;

import java.util.Date;

public class SocialEvent extends TextView {

    public static final int TILE_SIZE = 600;
    private String eventTitle;
    private Date startTime;
    private Date endTime;

    public SocialEvent(Context context, String eventTitle)
    {
        super(context);
        this.eventTitle=eventTitle;
        setText(eventTitle);
        setTextAlignment(TEXT_ALIGNMENT_CENTER);
        setHeight(TILE_SIZE);
        setWidth(TILE_SIZE);
        setTextSize(30);
        // TODO set colors to match the chosen UI design
        setBackgroundColor(Color.rgb(255, 255, 255));
    }

    @Override
    public boolean onTouchEvent(MotionEvent motionEvent) {

        if(motionEvent.getAction()==MotionEvent.ACTION_DOWN)
        {
            //TODO
//            View.DragShadowBuilder tmpView = new View.DragShadowBuilder(this);
////            ClipData tmpClip = ClipData.newPlainText("","");
////            startDrag(tmpClip, tmpView, this, 0);
            return true;
        }

        return super.onTouchEvent(motionEvent);
    }

    public void setStart(Date startTime)
    {
        this.startTime=startTime;
    }

    public void setEnd(Date endTime)
    {
        this.endTime=endTime;
    }
}
