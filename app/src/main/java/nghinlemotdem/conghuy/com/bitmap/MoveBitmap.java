package nghinlemotdem.conghuy.com.bitmap;

import android.graphics.Bitmap;
import android.graphics.BitmapFactory;
import android.os.Bundle;
import android.support.annotation.Nullable;
import android.support.v7.app.AppCompatActivity;
import android.view.View;
import android.widget.Button;
import android.widget.LinearLayout;
import android.widget.RelativeLayout;

/**
 * Created by Huy on 12/8/2017.
 */

public class MoveBitmap extends AppCompatActivity {
    LinearLayout root;
    Button btnSave, btnRed, btnGreen;

    @Override
    protected void onCreate(@Nullable Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.move_layout);

        final Bitmap bm = BitmapFactory.decodeResource(getResources(),
                R.drawable.img_1);
        final Bitmap bmRed = BitmapFactory.decodeResource(getResources(),
                R.drawable.bm_red);
        final Bitmap bmGreen = BitmapFactory.decodeResource(getResources(),
                R.drawable.bm_green);
        final DrawingView drawingView = new DrawingView(this, bm);


        btnSave = (Button) findViewById(R.id.btnSave);
        btnSave.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Bitmap bitmap = drawingView.getBm();
                Const.storeImage(MoveBitmap.this, bitmap);
                drawingView.updateBm(bitmap);
                drawingView.clearBmChild();
            }
        });

        btnGreen = (Button) findViewById(R.id.btnGreen);
        btnGreen.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                drawingView.updateBmChild(bmGreen);
            }
        });


        btnRed = (Button) findViewById(R.id.btnRed);
        btnRed.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                drawingView.updateBmChild(bmRed);
            }
        });


        root = (LinearLayout) findViewById(R.id.root);
        root.addView(drawingView);
    }

}
