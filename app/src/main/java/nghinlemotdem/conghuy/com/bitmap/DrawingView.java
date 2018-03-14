package nghinlemotdem.conghuy.com.bitmap;

import android.content.Context;
import android.graphics.Bitmap;
import android.graphics.BitmapFactory;
import android.graphics.Canvas;
import android.graphics.Color;
import android.graphics.Matrix;
import android.graphics.Paint;
import android.view.MotionEvent;
import android.view.View;

/**
 * Created by maidinh on 8/14/2017.
 */

public class DrawingView extends View {
    Bitmap bitmap = null, bmRoot = null;
    Paint paint;
    float x, y;
    int widthBm, heightBm, maxWidth, maxHeight;

    public DrawingView(Context context, Bitmap bmRoot) {
        super(context);
        setMaxValue(bmRoot);
        this.bmRoot = bmRoot;

//        bitmap = BitmapFactory.decodeResource(context.getResources(), R.drawable.ic_launcher);
        paint = new Paint();
        paint.setStyle(Paint.Style.FILL);
        paint.setColor(Color.CYAN);
    }

    public void setMaxValue(Bitmap bitmap) {
        maxWidth = bitmap.getWidth() / 2;
        maxHeight = bitmap.getHeight() / 2;
    }

    public void updateBmChild(Bitmap bitmap) {
        widthBm = bitmap.getWidth() / 2;
        heightBm = bitmap.getHeight() / 2;
        this.bitmap = bitmap;
        x = 0;
        y = 0;
        invalidate();
    }

    public void clearBmChild() {
        this.bitmap = null;
    }

    public void updateBm(Bitmap bmRoot) {
        setMaxValue(bmRoot);
        this.bmRoot = bmRoot;
        invalidate();
    }

    public Bitmap getBm() {
        Bitmap bmOverlay = Bitmap.createBitmap(bmRoot.getWidth(), bmRoot.getHeight(), bmRoot.getConfig());
        Canvas canvas = new Canvas(bmOverlay);
        canvas.drawBitmap(bmRoot, 0, 0, paint);
        if (bitmap != null)
            canvas.drawBitmap(bitmap, x, y, paint);
        return bmOverlay;
    }

    void limitValue() {
//        if (x < 0) x = 0;
//        if (y < 0) y = 0;
//        if (x > maxWidth) x = maxWidth;
//        if (y > maxHeight) y = maxHeight;
    }

    void setValue(MotionEvent event) {
        x = (int) event.getX();
        y = (int) event.getY();
    }

    public boolean onTouchEvent(MotionEvent event) {

        switch (event.getAction()) {
            case MotionEvent.ACTION_DOWN: {

            }
            break;

            case MotionEvent.ACTION_MOVE: {
                setValue(event);

                limitValue();

                invalidate();
            }

            break;
            case MotionEvent.ACTION_UP:
                setValue(event);

                limitValue();

                System.out.println(".................." + x + "......" + y); //x= 345 y=530
                invalidate();
                break;
        }
        return true;
    }

    @Override
    public void onDraw(Canvas canvas) {

        canvas.drawBitmap(bmRoot, 0, 0, paint);
        if (bitmap != null)
            canvas.drawBitmap(bitmap, x, y, paint);  //originally bitmap draw at x=o and y=0


    }
}
