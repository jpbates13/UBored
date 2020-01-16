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

import java.io.BufferedInputStream;
import java.io.FileReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.io.Reader;
import java.util.ArrayList;
import java.util.PriorityQueue;

public class MainActivity extends AppCompatActivity {

    // this stores all the events in a single list we can access later
    ArrayList<SocialEvent> eventList;

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
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        try {
            reader = new InputStreamReader(getAssets().open("SampleData.json"));
            // pull the data from the JSON file and store the data in queue.
            // TODO: process the data from sampleData.json into the Glue object
            data = new Glue(reader);

        }
        catch(IOException e){
            Log.d("openingJson", "unable to open sampleData.json " + e);
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
        //TODO
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
