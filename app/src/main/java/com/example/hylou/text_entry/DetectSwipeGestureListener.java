package com.example.hylou.text_entry;

import android.view.GestureDetector;
import android.view.MotionEvent;

public class DetectSwipeGestureListener extends GestureDetector.SimpleOnGestureListener {

    public final static int UP = 0;
    public final static int UPPER_LEFT = 1;
    public final static int LEFT = 2;
    public final static int LOWER_LEFT = 3;
    public final static int DOWN = 4;
    public final static int LOWER_RIGHT = 5;
    public final static int RIGHT = 6;
    public final static int UPPER_RIGHT = 7;

    // Minimal x and y axis swipe distance.
    private static int MIN_SWIPE_DISTANCE_X = 50;
    private static int MIN_SWIPE_DISTANCE_Y = 50;

    // Maximal x and y axis swipe distance.
    private static int MAX_SWIPE_DISTANCE_X = 1000;
    private static int MAX_SWIPE_DISTANCE_Y = 1000;

    // Source activity that display message in text view.
    private DetectSwipeDirectionActivity activity = null;

    public DetectSwipeDirectionActivity getActivity() {
        return activity;
    }

    public void setActivity(DetectSwipeDirectionActivity activity) {
        this.activity = activity;
    }

    /* This method is invoked when a swipe gesture happened. */
    @Override
    public boolean onFling(MotionEvent e1, MotionEvent e2, float velocityX, float velocityY) {

        // Get swipe delta value in x axis.
        float deltaX = e1.getX() - e2.getX();

        // Get swipe delta value in y axis.
        float deltaY = e1.getY() - e2.getY();

        // Get absolute value.
        float deltaXAbs = Math.abs(deltaX);
        float deltaYAbs = Math.abs(deltaY);

        if((deltaXAbs >= MIN_SWIPE_DISTANCE_X) && (deltaYAbs >= MIN_SWIPE_DISTANCE_Y))
        {
            if(deltaX > 0 && deltaY > 0) {
                this.activity.displayMessage("upper left");
                this.activity.finiteStateMachine(UPPER_LEFT);
            } else if (deltaX > 0 && deltaY < 0) {
                this.activity.displayMessage("lower left");
                this.activity.finiteStateMachine(LOWER_LEFT);
            } else if (deltaX < 0 && deltaY > 0) {
                this.activity.displayMessage("upper right");
                this.activity.finiteStateMachine(UPPER_RIGHT);
            } else {
                this.activity.displayMessage("lower right");
                this.activity.finiteStateMachine(LOWER_RIGHT);
            }
        } else if((deltaXAbs >= MIN_SWIPE_DISTANCE_X) && (deltaXAbs <= MAX_SWIPE_DISTANCE_X)) {
            if(deltaX > 0)
            {
                this.activity.displayMessage("left");
                this.activity.finiteStateMachine(LEFT);
            }else
            {
                this.activity.displayMessage("right");
                this.activity.finiteStateMachine(RIGHT);
            }
        } else if((deltaYAbs >= MIN_SWIPE_DISTANCE_Y) && (deltaYAbs <= MAX_SWIPE_DISTANCE_Y)) {
            if(deltaY > 0)
            {
                this.activity.displayMessage("up");
                this.activity.finiteStateMachine(UP);
            }else
            {
                this.activity.displayMessage("down");
                this.activity.finiteStateMachine(DOWN);
            }
        }

        return true;
    }

    // Invoked when single tap screen.
    @Override
    public boolean onSingleTapConfirmed(MotionEvent e) {
        this.activity.displayMessage("Single tap");
        this.activity.addToTextField(" ");
        return true;
    }

    // Invoked when double tap screen.
    @Override
    public boolean onDoubleTap(MotionEvent e) {
        this.activity.displayMessage("Double tap");
        this.activity.deleteOneCharacter();
        return true;
    }
}

