package com.example.ubored;

import androidx.appcompat.app.AppCompatActivity;

import android.graphics.Color;
import android.graphics.drawable.ColorDrawable;
import android.os.Build;
import android.os.Bundle;
import android.util.Log;
import android.view.Gravity;
import android.view.LayoutInflater;
import android.view.MotionEvent;
import android.view.View;
import android.widget.AdapterView;
import android.widget.ArrayAdapter;
import android.widget.LinearLayout;
import android.widget.ListView;
import android.widget.PopupWindow;
import android.widget.TextView;


import java.util.ArrayList;


public class DisplayInterested extends AppCompatActivity
{

    ListView listView;
    ArrayList<String> eventTitles;
    ArrayList<SocialEvent> interestedEvents;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_display_interested);

        listView = (ListView) findViewById(R.id.SocialEventListView);

        eventTitles = new ArrayList<>();

        interestedEvents = (ArrayList<SocialEvent>) getIntent().getSerializableExtra("interested");

        Log.d("passedEventLength",Integer.toString(interestedEvents.size()));

        for(int i = 0; i < interestedEvents.size(); i++)
        {
            eventTitles.add(interestedEvents.get(i).getEventTitle());
        }

        ArrayAdapter arrayAdapter = new ArrayAdapter(this, R.layout.interestedlistcard, eventTitles);
        listView.setAdapter(arrayAdapter);

        listView.setOnItemClickListener(new AdapterView.OnItemClickListener() {

            @Override
            public void onItemClick(AdapterView<?> arg0, View arg1, int arg2,
                                    long arg3) {
                // TODO Auto-generated method stub
                Log.d("############","Items " +  interestedEvents.get(arg2).getEventTitle() );
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
                popupWindow.showAtLocation(arg1, Gravity.CENTER, 0, 0);

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
                if(!interestedEvents.isEmpty()){
                    SocialEvent temp = interestedEvents.get(arg2);
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

        });

    }


    public void gotoList(View view)
    {
        super.onBackPressed();
    }
}
