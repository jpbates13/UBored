package com.example.ubored;
import com.google.gson.*;

import java.io.FileReader;
import java.util.Arrays;
import java.util.Queue;


public class Glue {

    private Gson gson;
    private Queue<SocialEvent> eventQueue;

    public Glue(FileReader reader){
        gson = new Gson();
        SocialEvent[] eventArr = gson.fromJson(reader, SocialEvent[].class);
        for(int i = 0; i < eventArr.length; i++) {
            eventArr[i] = new SocialEvent(/*TODO*/);
        }
        eventQueue = (Queue) Arrays.asList(eventArr);

    }

    public Queue<SocialEvent> getEventQueue(){
        return eventQueue;
    }
}
