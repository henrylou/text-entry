package com.example.hylou.text_entry;

import android.view.GestureDetector;
import android.view.MotionEvent;

public class DetectSwipeGestureListener extends GestureDetector.SimpleOnGestureListener {
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

        // Only when swipe distance between minimal and maximal distance value then we treat it as effective swipe
        if((deltaXAbs >= MIN_SWIPE_DISTANCE_X) && (deltaXAbs <= MAX_SWIPE_DISTANCE_X))
        {
            if(deltaX > 0)
            {
                this.activity.displayMessage("left");
            }else
            {
                this.activity.displayMessage("right");
            }
        }

        if((deltaYAbs >= MIN_SWIPE_DISTANCE_Y) && (deltaYAbs <= MAX_SWIPE_DISTANCE_Y))
        {
            if(deltaY > 0)
            {
                this.activity.displayMessage("up");
            }else
            {
                this.activity.displayMessage("down");
            }
        }

        if((deltaXAbs >= MIN_SWIPE_DISTANCE_X) && (deltaYAbs >= MIN_SWIPE_DISTANCE_Y))
        {
            if(deltaX > 0 && deltaY > 0) {
                this.activity.displayMessage("upper left");
            } else if (deltaX > 0 && deltaY < 0) {
                this.activity.displayMessage("lower left");
            } else if (deltaX < 0 && deltaY > 0) {
                this.activity.displayMessage("upper right");
            } else {
                this.activity.displayMessage("lower right");
            }
        }


        return true;
    }

    // Invoked when single tap screen.
    @Override
    public boolean onSingleTapConfirmed(MotionEvent e) {
        this.activity.displayMessage("Single tap");
        return true;
    }

    // Invoked when double tap screen.
    @Override
    public boolean onDoubleTap(MotionEvent e) {
        this.activity.displayMessage("Double tap");
        return true;
    }
}

