package nghinlemotdem.conghuy.com.bitmap;

import android.content.Intent;
import android.os.Bundle;
import android.support.v4.view.GravityCompat;
import android.support.v4.widget.DrawerLayout;
import android.support.v7.app.AppCompatActivity;
import android.support.v7.widget.Toolbar;
import android.view.View;
import android.widget.Button;

public class MainActivity extends AppCompatActivity implements View.OnClickListener{

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);


        init();
    }

    Button grayscale_bitmap,btnMove;

    void init() {
        grayscale_bitmap = (Button) findViewById(R.id.grayscale_bitmap);
        grayscale_bitmap.setOnClickListener(this);

        btnMove = (Button) findViewById(R.id.btnMove);
        btnMove.setOnClickListener(this);

    }

    @Override
    public void onBackPressed() {
        super.onBackPressed();
    }



    void go(Class<?> c) {
        startActivity(new Intent(this, c));
    }

    @Override
    public void onClick(View v) {
        if (v == grayscale_bitmap) {
            go(GrayscaleBitmapActivity.class);
        }  if (v == btnMove) {
            go(MoveBitmap.class);
        }

    }
}
