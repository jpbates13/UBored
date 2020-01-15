package com.example.ubored;
import com.google.gson.*;

import java.io.FileReader;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;
import java.util.Queue;


public class Glue {

    private Gson gson;
    private List<SocialEvent> eventList;

    public Glue(FileReader reader){
        gson = new Gson();
        SocialEvent[] eventArr = gson.fromJson(reader, SocialEvent[].class);
        for(int i = 0; i < eventArr.length; i++) {
            eventArr[i] = new SocialEvent(/*TODO*/);
        }
        eventList= Arrays.asList(eventArr);

    }

    public List<SocialEvent> getEventQueue(){
        return eventList;
    }
}
