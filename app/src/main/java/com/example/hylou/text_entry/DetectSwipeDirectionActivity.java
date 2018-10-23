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

    private TextView cell11 = null;
    private TextView cell12 = null;
    private TextView cell13 = null;

    private TextView cell21 = null;
    private TextView cell22 = null;
    private TextView cell23 = null;

    private TextView cell31 = null;
    private TextView cell32 = null;
    private TextView cell33 = null;

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

        cell11 = (TextView)findViewById(R.id.cell11);
        cell12 = (TextView)findViewById(R.id.cell12);
        cell13 = (TextView)findViewById(R.id.cell13);

        cell21 = (TextView)findViewById(R.id.cell21);
        cell22 = (TextView)findViewById(R.id.cell22);
        cell23 = (TextView)findViewById(R.id.cell23);

        cell31 = (TextView)findViewById(R.id.cell31);
        cell32 = (TextView)findViewById(R.id.cell32);
        cell33 = (TextView)findViewById(R.id.cell33);

        // Create a common gesture listener object.
        DetectSwipeGestureListener gestureListener = new DetectSwipeGestureListener();

        // Set activity in the listener.
        gestureListener.setActivity(this);

        // Create the gesture detector with the gesture listener.
        gestureDetectorCompat = new GestureDetectorCompat(this, gestureListener);

        // Initialize the keyboard
        initKeyboard();
    }

    @Override
    public boolean onTouchEvent(MotionEvent event) {
        // Pass activity on touch event to the gesture detector.
        gestureDetectorCompat.onTouchEvent(event);
        // Return true to tell android OS that event has been consumed, do not pass it to other event listeners.
        return true;
    }

    public void displayMessage(String message) {
        if (textView != null) {
            // Display text in the text view.
            textView.setText(message);
        }
    }

    public void finiteStateMachine(int input) {
        if (stateOne == -1) {
            stateOne = input;

            Log.d("FSM","State1:" + stateOne);

            // UI Shows the selected chunk of square
            updateKeyboard();
        }
        else if (stateOne != -1 && (input == 0 || input == 2 || input == 4 || input == 6)) {
            stateTwo = input / 2;
            Log.d("FSM","State1: " + stateOne + " State2: " + stateTwo);

            // update text on screen
            addToTextField(stringTable[stateOne][stateTwo]);

            stateOne = -1;
            stateTwo = -1;
            initKeyboard();
        }
    }

    public void initKeyboard() {
        cell11.setText("qwer");
        cell12.setText("\\ntyg");
        cell13.setText("uoif");

        cell21.setText("asdf");
        cell22.setText("");
        cell23.setText("hkjl");

        cell31.setText("zx@c");
        cell32.setText(",v\".");
        cell33.setText("bn_m");
    }

    public void updateKeyboard() {
        cell13.setText("");
        cell11.setText("");
        cell22.setText("");
        cell31.setText("");
        cell33.setText("");

        cell12.setText(stringTable[stateOne][0].equals("\n") ? "\\n" : stringTable[stateOne][0]);
        cell21.setText(stringTable[stateOne][1]);
        cell23.setText(stringTable[stateOne][3]);
        cell32.setText(stringTable[stateOne][2]);
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
        initKeyboard();
    }
}

