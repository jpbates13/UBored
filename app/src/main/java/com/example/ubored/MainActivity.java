package com.example.ubored;

import androidx.appcompat.app.AppCompatActivity;

import android.content.res.AssetManager;
import android.os.Bundle;
import android.util.Log;
import android.view.DragEvent;
import android.view.MotionEvent;
import android.view.View;
import android.view.Window;
import android.widget.Button;
import android.widget.LinearLayout;

import java.io.BufferedInputStream;
import java.io.FileReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.io.Reader;
import java.lang.reflect.Array;
import java.util.ArrayList;
import java.util.List;
import java.util.PriorityQueue;
import java.util.logging.SocketHandler;

public class MainActivity extends AppCompatActivity {

    // this stores all the events in a single list we can access later
    List<SocialEvent> eventList;

    ArrayList<SocialEvent> interestedEvents;

    // this item will display a queue of events on the main activity
    QueuedLayout eventsQueued;


    /*
     * TODO : if we have time, also create a priority queue of events by category
     */
    PriorityQueue<SocialEvent> preferredEvents;

    @Override
    protected void onCreate(Bundle savedInstanceState) {

        Reader reader;
        Glue data;
        BufferedInputStream buffStream;

        interestedEvents = new ArrayList<SocialEvent>(); // If we implement a "save" file implement opening that here

        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        eventsQueued = new QueuedLayout(this);

        try {
            reader = new InputStreamReader(getAssets().open("SampleData.json"));
            // pull the data from the JSON file and store the data in an ArrayList.
            data = new Glue(reader);
            eventList = data.getEventQueue();

//            for( SocialEvent e : eventList)
//                Log.d("mainEventList", e.getEventTitle());
        }
        catch(IOException e){
            Log.d("openingJson", "unable to open sampleData.json " + e);
        }

        try{
            // if the events have been loaded into the eventList, put them into the QueuedLayout
            LinearLayout verticalLayout = (LinearLayout) findViewById(R.id.eventTextDisplay);
            verticalLayout.addView(eventsQueued);
            for(int i=0; i<eventList.size();i++)
                eventsQueued.enqueue(new SocialEventTile(this, eventList.get(i)));
        }
        catch(NullPointerException n){
            Log.d("placingTiles", "unable to place the event tiles");
        }

    }

    private class TouchListener implements View.OnTouchListener{

        @Override
        public boolean onTouch(View v, MotionEvent event) {
            //TODO
            return true;
        }
    }

    private class DragListener implements View.OnDragListener {

        public boolean onDrag(View v, DragEvent event) {
            //TODO
            return true;
        }
    }
    /*
    * This function is called when the user swipes left or presses
    * the no button to a particular event on the stack
    */

    public boolean swipeLeft(View view){
        //TODO
        return true;
    }

    /*
    * This function is called when the user swipes right or presses
    * the yes button to a particular event on the queue
    */
    public boolean swipeRight(View view){
        if(!eventsQueued.isEmpty()) {
            SocialEvent temp = eventsQueued.dequeue().getSocialEvent();
            interestedEvents.add(temp);
            for (SocialEvent e : interestedEvents) {
                Log.d("favorites", e.getEventTitle());
            }
        }
        return true;
    }

    /*
    * This function is called when the event icon is pressed.
    * It will bring up the view that will display the details for that
    * particular event
    * */
    public boolean viewEventDetails(View view){
        //TODO
        return true;
    }





}
