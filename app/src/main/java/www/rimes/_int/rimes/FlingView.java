package www.rimes._int.rimes;

import android.content.Context;
import android.view.GestureDetector;
import android.view.MotionEvent;
import android.webkit.WebView;
import android.widget.Toast;

/**
 * Created by seasonal on 12/29/2016.
 */

class FlingView extends WebView {

    Context context;
    GestureDetector gestureDetector;

    public FlingView (Context context) {
        super(context);

        this.context = context;
        gestureDetector = new GestureDetector(context, simpleOnGestureListener);
    }

    @Override
    public boolean onTouchEvent(MotionEvent event) {
        return gestureDetector.onTouchEvent(event);
    }

    GestureDetector.SimpleOnGestureListener simpleOnGestureListener = new GestureDetector.SimpleOnGestureListener() {
        public boolean onDown(MotionEvent event) {
            return true;
        }

        public boolean onFling(MotionEvent event1, MotionEvent event2, float velocityX, float velocityY) {
            if (event1.getRawX() > event2.getRawX()) {
                show_toast("swipe left");
            } else {
                show_toast("swipe right");
            }
            return true;
        }
    };

    void show_toast(final String text) {
        Toast t = Toast.makeText(context, text, Toast.LENGTH_SHORT);
        t.show();
    }
}
