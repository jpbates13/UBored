package com.example.ubored;

import android.content.Context;
import android.util.Log;
import android.widget.LinearLayout;


import java.util.LinkedList;
import java.util.Queue;
import java.util.concurrent.LinkedBlockingQueue;

public class QueuedLayout extends LinearLayout {

    private Queue<SocialEventTile> sEvents = new LinkedList<>();

    public QueuedLayout(Context context) {
        super(context);
    }

    /*
    * Insert the SocialEvent object socialEvent to the end of the LinkedList
    * */
    public void enqueue(SocialEventTile socialEventTile){
        SocialEvent sc = socialEventTile.getSocialEvent();
        Log.d("enqueingTile", sc.getEventTitle());
        if(sEvents.isEmpty())
        {
            sEvents.add(socialEventTile);
        }
        else
        {
            removeView(sEvents.peek());
            sEvents.add(socialEventTile);
        }
        addView(sEvents.peek());
    }

    private void makeTopVisible(){
        if(sEvents.size()>0)
            addView(sEvents.peek());
    }

    /*
    * If the queue is not empty, remove the item at the front of the queue and return it
    * */
    public SocialEventTile dequeue(){
        SocialEventTile tile;
        if(sEvents.size()!=0)
        {
            tile = sEvents.remove();
            removeView(tile);
            if(sEvents.size() != 0) {
                addView(sEvents.peek());
            }
            return tile;
        }
        return null;
    }

    /*
    * Return the current size of the queue
    * */
    public int queueSize(){
        return sEvents.size();
    }

    /*
    * Return the social event currently at the front of the queue without removing it from the queue.
    * */
    public SocialEventTile peek(){
        return sEvents.peek();
    }

    /*
    * Return true is the queue is empty
    * */
    public boolean isEmpty(){
        return sEvents.isEmpty();
    }

}
