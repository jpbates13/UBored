package com.example.ubored;

import androidx.appcompat.app.AppCompatActivity;

import android.os.Bundle;
import android.view.DragEvent;
import android.view.MotionEvent;
import android.view.View;

public class MainActivity extends AppCompatActivity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
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
    * the yes button to a particular event on the stack
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
