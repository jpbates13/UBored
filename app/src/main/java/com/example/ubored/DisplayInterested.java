package com.example.ubored;

import androidx.appcompat.app.AppCompatActivity;
import android.os.Bundle;
import android.util.Log;
import android.view.View;
import android.widget.ArrayAdapter;
import android.widget.ListView;


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
    }


    public void gotoList(View view)
    {
        super.onBackPressed();
    }
}
