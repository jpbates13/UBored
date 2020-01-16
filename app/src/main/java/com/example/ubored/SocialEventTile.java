package com.example.ubored;

import android.content.ClipData;
import android.content.Context;
import android.graphics.Color;
import android.view.MotionEvent;
import android.view.View;
import androidx.appcompat.widget.AppCompatTextView;
import java.util.Date;

public class SocialEventTile extends AppCompatTextView {

    public static final int TILE_SIZE = 600;
    private SocialEvent socialEvent;

    public SocialEventTile(Context context, SocialEvent socialEvent)
    {
        super(context);
        this.socialEvent = socialEvent;
        setText(this.socialEvent.getEventTitle());
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
        this.socialEvent.setStartTime(startTime);
    }

    public Date getStartTime()
    {
        return socialEvent.getStartTime();
    }

    public void setEnd(Date endTime)
    {
        this.socialEvent.setEndTime(endTime);
    }

    public Date getEndTime()
    {
        return socialEvent.getEndTime();
    }

    public void setCategory(String category)
    {
        this.socialEvent.setCategory(category);
    }

    public String getCategory()
    {
        return socialEvent.getCategory();
    }
}
