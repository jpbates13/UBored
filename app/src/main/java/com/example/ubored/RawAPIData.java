package com.example.ubored;

public class RawAPIData {
    public String title;
    public String venue_name;
    public String start_time;
    public String description;

    public SocialEvent toSocialEvent(){
        return new SocialEvent(title, start_time, start_time, venue_name);
    }

    @Override
    public String toString(){
        return title;
    }

}
