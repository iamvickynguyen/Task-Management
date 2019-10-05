package com.example.admin.quanlicongviec;

import android.content.Intent;
import android.graphics.Typeface;
import android.graphics.drawable.AnimationDrawable;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.animation.AnimationUtils;
import android.widget.ImageView;
import android.widget.TextView;

public class greattingActivity extends AppCompatActivity {

    ImageView iv;
    TextView wc;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_greatting);

        iv=findViewById(R.id.imgview);
        iv.setBackgroundResource(R.drawable.hoatcanh);
        wc = findViewById(R.id.txtwelcome);
        Typeface font = Typeface.createFromAsset(getAssets(),"slimtony");
        wc.setTypeface(font);
        wc.setText("Welcome");


        Thread splash= new Thread(){
            public void run()
            {
                try {
                    Thread.sleep(2500);
                } catch (InterruptedException e) {
                    // TODO Auto-generated catch block
                    e.printStackTrace();
                }
                finally{
                    Intent i = new Intent(getApplicationContext(),MainActivity.class);
                    startActivity(i);
                }finish();
            }
        };
        splash.start();
    }

    @Override
    public void onWindowFocusChanged(boolean hasFocus){
        super.onWindowFocusChanged(hasFocus);
        AnimationDrawable hh=(AnimationDrawable)iv.getBackground();
        if(hasFocus==true)
            hh.start();
        else
            hh.stop();
    }
}
