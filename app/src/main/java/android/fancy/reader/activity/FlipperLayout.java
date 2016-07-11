package android.fancy.reader.activity;

import android.view.VelocityTracker;
import android.view.ViewGroup;
import android.widget.Scroller;

/**
 * Created by inx95 on 16-7-11.
 */
public class FlipperLayout extends ViewGroup {

    private int index = 1;
    private static final String TAG = "FlipperLayout";

    private Scroller mScroller;

    private VelocityTracker mVelocityTracker;

    private int mVelocityValue = 0;

    private int limitDistance = 0;

    private int screenWidth = 0;

    private static final int MOVE_TO_LEFT = 0;
    private static final int MOVE_TO_RIGHT = 1;
    private static final int MOVE_NO_RESULT = 2;

    private int mTouchResult = MOVE_NO_RESULT;
    private int mDirection = MOVE_NO_RESULT;

    private static final int MODE_NONE = 0;
    private static final int MODE_MOVE = 1;

    private int mMode = MODE_NONE;

    @Override
    protected void onLayout(boolean changed, int l, int t, int r, int b) {

    }
}
