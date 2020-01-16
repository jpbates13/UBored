package com.example.ubored;
import android.util.Log;

import com.google.gson.*;

import java.io.FileReader;
import java.io.Reader;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;
import java.util.Queue;


public class Glue {

    private Gson gson;
    private List<SocialEvent> eventList;

    public Glue(Reader reader){
        gson = new Gson();
        // TODO: Let's use an ArrayList<SocialEvent> object here instead

        SocialEvent[] eventArr = gson.fromJson(reader, SocialEvent[].class);

        for(int i = 0; i < eventArr.length; i++) {
            SocialEvent temp = new SocialEvent(eventArr[i].getEventTitle(), eventArr[i].getStartTime(),
                        eventArr[i].getEndTime(), eventArr[i].getCategory());
            //Log.d("j-term",eventArr[i].getEventTitle()+eventArr[i].getStartTime()+eventArr[i].getEndTime());
            eventArr[i] = temp;
        }
        eventList= Arrays.asList(eventArr);

        for(SocialEvent e : eventList) {
            Log.d("j-term", e.getEventTitle());
        }


    }

    public List<SocialEvent> getEventQueue(){
        return eventList;
    }


}
