package android.fancy.reader.activity.view;

import android.content.Context;
import android.graphics.Paint;
import android.graphics.Rect;
import android.util.AttributeSet;
import android.view.MotionEvent;
import android.widget.TextView;

/**
 * Created by inx95 on 16-7-12.
 */
public class ReadView extends TextView {
    @Override
    public boolean onTouchEvent(MotionEvent event) {
        return super.onTouchEvent(event);
    }

    public ReadView(Context context) {
        super(context);
    }

    public ReadView(Context context, AttributeSet attrs) {
        super(context, attrs);
    }

    public ReadView(Context context, AttributeSet attrs, int defStyleAttr) {
        super(context, attrs, defStyleAttr);
    }

    public ReadView(Context context, AttributeSet attrs, int defStyleAttr, int defStyleRes) {
        super(context, attrs, defStyleAttr, defStyleRes);
    }

    @Override
    protected void onSizeChanged(int w, int h, int oldw, int oldh) {
        super.onSizeChanged(w, h, oldw, oldh);
    }

    public int getCharCountForS(String s) {
//        int a = ((int) (screenWidth / getTextSize()));
//        return a;
        Rect bounds = new Rect();
        Paint paint = getPaint();
        paint.getTextBounds(s, 0,s.length() , bounds);

        int width = (int) Math.ceil((float) bounds.width() / paint.getTextSize());
        return width;
    }

    public int getCharCountPerLine() {
        int screenWidth = getResources().getDisplayMetrics().widthPixels;
        int b = ((int) getPaint().getTextSize());
        return  (int)Math.floor(screenWidth / b);
    }
//获取为了显示s需要花费多少行
    public int getSCostLineShowCount(String s) {
        return (int)Math.ceil((double) getCharCountForS(s) / getCharCountPerLine());
//        return getCharCountPerLine();
    }

    public int getLinePerPageInTv() {

        return  (int)Math.floor((getHeight()-getPaddingTop()-getPaddingBottom())/(getPaint().getFontSpacing()) );
    }
}
