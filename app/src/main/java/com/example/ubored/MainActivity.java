package com.example.ubored;

import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.graphics.Color;
import android.graphics.drawable.ColorDrawable;
import android.os.Build;
import android.os.Bundle;
import android.util.Log;
import android.view.DragEvent;
import android.view.Gravity;
import android.view.LayoutInflater;
import android.view.MotionEvent;
import android.view.View;
import android.view.ViewGroup;
import android.widget.LinearLayout;
import android.widget.PopupWindow;
import android.widget.TextView;

import org.w3c.dom.Text;

import java.io.BufferedInputStream;
import java.io.IOException;
import java.io.InputStreamReader;
import java.io.Reader;
import java.util.ArrayList;
import java.util.List;
import java.util.PriorityQueue;

public class MainActivity extends AppCompatActivity {

    private float x1,x2;
    static final int MIN_DISTANCE = 500;
    // this stores all the events in a single list we can access later
    List<SocialEvent> eventList;

    ArrayList<SocialEvent> interestedEvents;

    // this item will display a queue of events on the main activity
    QueuedLayout eventsQueued;
    final String NO_MORE_EVENTS_TEXT = "No more events. Check back later.";

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

        TextView emptyText = findViewById(R.id.emptyText);
        emptyText.setText("");

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
            ViewGroup verticalLayout = findViewById(R.id.touchZone);
            verticalLayout.addView(eventsQueued);
            for(int i=0; i<eventList.size();i++)
                eventsQueued.enqueue(new SocialEventTile(this, eventList.get(i)));
        }
        catch(NullPointerException n){
            Log.d("placingTiles", "unable to place the event tiles");
        }

        //findViewById(R.id.touchZone).setBackgroundColor(Color.CYAN);
        findViewById(R.id.accept).setOnDragListener(new View.OnDragListener() {
            @Override
            public boolean onDrag(View v, DragEvent event) {
                if (event.getAction() == DragEvent.ACTION_DROP && event.getLocalState() instanceof SocialEventTile) {
                    swipeRight(eventsQueued);
                }

                return true;
            }
        });
        findViewById(R.id.reject).setOnDragListener(new View.OnDragListener() {
            @Override
            public boolean onDrag(View v, DragEvent event) {
                if (event.getAction() == DragEvent.ACTION_DROP && event.getLocalState() instanceof SocialEventTile) {
                    swipeLeft(eventsQueued);
                }

                return true;
            }
        });

    }

    private class TouchListener implements View.OnTouchListener{

        @Override
        public boolean onTouch(View v, MotionEvent event) {

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
        if(!eventsQueued.isEmpty()){
            eventsQueued.dequeue();
        }
        checkEmpty();
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
        checkEmpty();
        return true;
    }

    public void gotoList(View view)
    {

        Intent i = new Intent(MainActivity.this, DisplayInterested.class);
        Log.d("passing", Integer.toString(interestedEvents.size()));
        i.putExtra("interested",interestedEvents);
        startActivity(i);

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

    public boolean checkEmpty(){
        if(eventsQueued.isEmpty()){
            TextView emptyText = findViewById(R.id.emptyText);
            emptyText.setText(NO_MORE_EVENTS_TEXT);
        }
        return true;
    }

    public void onButtonShowPopupWindowClick(View view) {

        // inflate the layout of the popup window
        LayoutInflater inflater = (LayoutInflater)
                getSystemService(LAYOUT_INFLATER_SERVICE);
        View popupView = inflater.inflate(R.layout.popup_window, null);

        // create the popup window
        int width = LinearLayout.LayoutParams.WRAP_CONTENT;
        int height = 1500;
        boolean focusable = true; // lets taps outside the popup also dismiss it
        final PopupWindow popupWindow = new PopupWindow(popupView, width, height, focusable);

        // show the popup window
        // which view you pass in doesn't matter, it is only used for the window tolken
        popupWindow.showAtLocation(view, Gravity.CENTER, 0, 0);

        // dismiss the popup window when touched
        popupView.setOnTouchListener(new View.OnTouchListener() {
            @Override
            public boolean onTouch(View v, MotionEvent event) {
                popupWindow.dismiss();
                return true;
            }
        });
        popupWindow.setBackgroundDrawable(new ColorDrawable(Color.WHITE));
        if (Build.VERSION.SDK_INT >= Build.VERSION_CODES.LOLLIPOP) {
            popupWindow.setElevation(20);
        }
        TextView popupTitle = popupView.findViewById(R.id.popupTitle);
        TextView popupStarttime = popupView.findViewById(R.id.popupStarttime);
        TextView popupEndtime = popupView.findViewById(R.id.popupEndtime);
        TextView popupCategory= popupView.findViewById(R.id.popupCategory);
        if(!eventsQueued.isEmpty()){
            SocialEvent temp = eventsQueued.peek().getSocialEvent();
            Log.d("j-term", temp.getEventTitle()+"\n"+temp.getStartTime() + "\n-\n" + temp.getEndTime());
            popupTitle.setText(temp.getEventTitle());
            popupStarttime.setText("Start: "+temp.getStartTime());
            popupEndtime.setText("End: "+temp.getEndTime());
            popupCategory.setText("Category: "+temp.getCategory());

        } else {
            popupTitle.setText("No details to display");
            popupStarttime.setText("");
            popupEndtime.setText("");
            popupCategory.setText("");

        }

    }
}
