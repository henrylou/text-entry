package com.example.hylou.text_entry;

import android.os.Bundle;
import android.support.v4.view.GestureDetectorCompat;
import android.support.v7.app.AppCompatActivity;
import android.util.Log;
import android.view.MotionEvent;
import android.widget.TextView;

public class DetectSwipeDirectionActivity extends AppCompatActivity {

    public final static int UP = 0;
    public final static int UPPER_LEFT = 1;
    public final static int LEFT = 2;
    public final static int LOWER_LEFT = 3;
    public final static int DOWN = 4;
    public final static int LOWER_RIGHT = 5;
    public final static int RIGHT = 6;
    public final static int UPPER_RIGHT = 7;

    // This textview is used to display swipe or tap status info.
    private TextView textView = null;
    private TextView inputField = null;
    private String text = "";
    // This is the gesture detector compat instance.
    private GestureDetectorCompat gestureDetectorCompat = null;

    private int stateOne = -1;
    private int stateTwo = -1;

    //string array
    String stringTable[][] =    {{"\n", "t", "g", "y"},
                                 {"w", "q", "e", "r"},
                                 {"s", "a", "d", "f"},
                                 {"x", "z", "@", "c"},
                                 {"v", ",", "\"", "."},
                                 {"n", "b", "_", "m"},
                                 {"k", "h", "j", "l"},
                                 {"o", "u", "i", "f"}};

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_detect_swipe_direction);

        setTitle("Detect Android Swipe Direction Example.");

        // Get the text view.
        textView = (TextView)findViewById(R.id.detect_swipe_direction_textview);
        inputField = (TextView)findViewById(R.id.inputField);

        // Create a common gesture listener object.
        DetectSwipeGestureListener gestureListener = new DetectSwipeGestureListener();

        // Set activity in the listener.
        gestureListener.setActivity(this);

        // Create the gesture detector with the gesture listener.
        gestureDetectorCompat = new GestureDetectorCompat(this, gestureListener);
    }

    @Override
    public boolean onTouchEvent(MotionEvent event) {
        // Pass activity on touch event to the gesture detector.
        gestureDetectorCompat.onTouchEvent(event);
        // Return true to tell android OS that event has been consumed, do not pass it to other event listeners.
        return true;
    }

    public void displayMessage(String message)
    {
        if(textView!=null)
        {
            // Display text in the text view.
            textView.setText(message);
        }
    }

    public void finiteStateMachine(int input) {
        if (stateOne == -1) {
            stateOne = input;

            Log.d("FSM","State1:" + stateOne);

            //UI Shows the selected chunk of square

        } else if (stateOne != -1 && (input == 0 || input == 2 || input == 4 || input == 6)) {
            stateTwo = input/2;
            Log.d("FSM","State1: " + stateOne + " State2: " + stateTwo);

            /*

            UI Shows the selected character

            */



            //update text on screen
            addToTextField(stringTable[stateOne][stateTwo]);

            stateOne = -1;
            stateTwo = -1;
        }
    }

    public void addToTextField(String newElement) {
        text += newElement;
        inputField.setText(text);
    }

    public void deleteOneCharacter() {
        if (text.length() >= 1 && stateOne == -1) {
            text = text.substring(0, text.length() - 1);
            inputField.setText(text);
        }
        stateOne = -1;
        stateTwo = -1;
    }
}

