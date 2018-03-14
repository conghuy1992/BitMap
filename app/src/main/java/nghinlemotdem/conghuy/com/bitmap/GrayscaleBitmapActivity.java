package nghinlemotdem.conghuy.com.bitmap;

import android.graphics.Bitmap;
import android.graphics.BitmapFactory;
import android.graphics.Canvas;
import android.graphics.ColorFilter;
import android.graphics.ColorMatrix;
import android.graphics.ColorMatrixColorFilter;
import android.graphics.Matrix;
import android.graphics.Paint;
import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.ImageView;

public class GrayscaleBitmapActivity extends AppCompatActivity {
    EditText edit_query;
    ImageView image1, image2;
    Button set, overlay, saveImage;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.grayscale_bitmap_layout);
        image1 = (ImageView) findViewById(R.id.image1);
        image2 = (ImageView) findViewById(R.id.image2);

        edit_query = (EditText) findViewById(R.id.edit_query);
        final Bitmap bm = BitmapFactory.decodeResource(getResources(),
                R.drawable.ic_launcher);

        image1.setImageBitmap(bm);
        set = (Button) findViewById(R.id.set);
        set.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                bmRoot = createGrayscale(bm, Float.parseFloat(edit_query.getText().toString()));
                image2.setImageBitmap(bmRoot);
            }
        });

        overlay = (Button) findViewById(R.id.overlay);

        overlay.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Bitmap bm = BitmapFactory.decodeResource(getResources(),
                        R.drawable.ic_visibility_black_24dp);
                bmOverLay=putOverlay(bmRoot, bm);
                image2.setImageBitmap(bmOverLay);
            }
        });

        saveImage = (Button) findViewById(R.id.saveImage);
        saveImage.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Const.storeImage(GrayscaleBitmapActivity.this, bmOverLay);
            }
        });
    }

    Bitmap bmRoot,bmOverLay;

    private Bitmap putOverlay(Bitmap bmp1, Bitmap bmp2) {
        Bitmap bmOverlay = Bitmap.createBitmap(bmp1.getWidth(), bmp1.getHeight(), bmp1.getConfig());
        Canvas canvas = new Canvas(bmOverlay);
        canvas.drawBitmap(bmp1, new Matrix(), null);
        canvas.drawBitmap(bmp2, new Matrix(), null);
        return bmOverlay;
    }

    private Bitmap createGrayscale(Bitmap src, float Saturation) {
        ColorMatrix colorMatrix_Sat0 = new ColorMatrix();
        colorMatrix_Sat0.setSaturation(Saturation);
        ColorFilter ColorFilter_Grayscale = new ColorMatrixColorFilter(
                colorMatrix_Sat0);

        Bitmap bitmap = Bitmap.createBitmap(src.getWidth(), src.getHeight(),
                Bitmap.Config.ARGB_8888);
        Canvas canvas = new Canvas(bitmap);

        Paint paint = new Paint();

        paint.setColorFilter(ColorFilter_Grayscale);
        canvas.drawBitmap(src, 0, 0, paint);

        return bitmap;
    }

}