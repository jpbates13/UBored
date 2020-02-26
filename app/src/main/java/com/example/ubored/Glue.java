package com.example.ubored;
import android.content.Context;
import android.os.StrictMode;
import android.util.Log;
import android.view.View;
import android.view.ViewGroup;

import com.android.volley.Request;
import com.android.volley.RequestQueue;
import com.android.volley.Response;
import com.android.volley.VolleyError;
import com.android.volley.toolbox.StringRequest;
import com.android.volley.toolbox.Volley;
import com.google.gson.*;

import java.io.BufferedReader;
import java.io.File;
import java.io.FileInputStream;
import java.io.FileReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.io.Reader;
import java.net.HttpURLConnection;
import java.net.MalformedURLException;
import java.net.ProtocolException;
import java.net.URL;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;
import java.util.Queue;

import javax.net.ssl.HttpsURLConnection;


public class Glue {

    private String PRIVATE_API_TOKEN;
    private Gson gson;
    private List<SocialEvent> eventList;
    public String apiResponse = "";
    private Context context;
    Reader reader;
    ViewGroup verticalLayout;
    QueuedLayout eventsQueued;

    public Glue(Context context, Reader reader, ViewGroup verticalLayout, QueuedLayout eventsQueued, String lat, String lng){

        this.context = context;
        this.reader = reader;
        this.verticalLayout = verticalLayout;
        this.eventsQueued = eventsQueued;

        try{
            BufferedReader tokenReader = new BufferedReader(new InputStreamReader(context.getAssets().open("API_KEY.txt")));

            PRIVATE_API_TOKEN = tokenReader.readLine();
        } catch (IOException e) {
            e.printStackTrace();
        }
        Log.d("API", PRIVATE_API_TOKEN);

        refresh();

    }

    public List<SocialEvent> getEventQueue(){
        return eventList;
    }

    public void refresh(){
        String url = "https://api.eventful.com/json/events/search?app_key="+PRIVATE_API_TOKEN+"&location=Boston&date=Future";
        RequestQueue reqQueue = Volley.newRequestQueue(context);
        // Request a string response from the provided URL.
        StringRequest stringRequest = new StringRequest(Request.Method.GET, url,
                new Response.Listener<String>() {
                    @Override
                    public void onResponse(String response) {
                        // Display the first 500 characters of the response string.
                        Log.d("API","Response is: "+ response.substring(0,500));
                        apiResponse = response.substring(response.indexOf('['), response.indexOf(']'));
                        Log.d("API", apiResponse);
                        gson = new Gson();
                        RawAPIData[] eventArr = gson.fromJson( apiResponse + "]", RawAPIData[].class);

                        SocialEvent[] socEventArr = new SocialEvent[eventArr.length];
                        for(int i = 0; i < eventArr.length; i++){
                            socEventArr[i] = eventArr[i].toSocialEvent();
                        }
                        eventList= Arrays.asList(socEventArr);

                        for(SocialEvent e : eventList) {
                            Log.d("API", e.getEventTitle());
                        }

                        updateInterface();
                    }
                }, new Response.ErrorListener() {
            @Override
            public void onErrorResponse(VolleyError error) {
                Log.d("API","That didn't work!");
            }
        });

        reqQueue.add(stringRequest);



    }

    public void updateInterface(){
        try{
            // if the events have been loaded into the eventList, put them into the QueuedLayout
            verticalLayout.addView(eventsQueued);
            for(int i=0; i<eventList.size();i++)
                eventsQueued.enqueue(new SocialEventTile(context, eventList.get(i)));
        }
        catch(NullPointerException n){
            Log.d("placingTiles", "unable to place the event tiles");
        }
    }


}
