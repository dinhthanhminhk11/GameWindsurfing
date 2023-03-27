package com.example.gamewindsurfing;

import android.app.AlertDialog;
import android.content.Context;
import android.content.DialogInterface;
import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.view.animation.Animation;
import android.view.animation.AnimationUtils;
import android.widget.Button;
import android.widget.ImageView;

import androidx.appcompat.app.AppCompatActivity;

public class MainActivity extends AppCompatActivity {
    private Button aboutbutton;
    private Button startbutton;
    private ImageView cloud;
    private ImageView start;
    private Animation star, cloudAni;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        aboutbutton = (Button) findViewById(R.id.btnEnd);
        startbutton = (Button) findViewById(R.id.btnStart);
        cloud = (ImageView) findViewById(R.id.cloud);
        start = (ImageView) findViewById(R.id.start);

        star = AnimationUtils.loadAnimation(this , R.anim.amistart);
        cloudAni = AnimationUtils.loadAnimation(this , R.anim.amicloud);
        start.startAnimation(star);
        cloud.startAnimation(cloudAni);

        startbutton.setOnClickListener(new View.OnClickListener() {

            @Override
            public void onClick(View v) {
                Intent i = new Intent(MainActivity.this, GameActivity.class);
                startActivity(i);
            }
        });

        aboutbutton.setOnClickListener(new View.OnClickListener() {

            @Override
            public void onClick(View v) {
                Context c = MainActivity.this;
                new AlertDialog.Builder(c).setTitle("About Droid-Runner").setMessage("This is a simple game in which you have to avoid the potholes" + " in the road and eat the food by jumping on a " + "simple tap to gain more points.").setNegativeButton("OK", new DialogInterface.OnClickListener() {
                    @Override
                    public void onClick(DialogInterface dialog, int which) {
                    }
                }).show();

            }
        });
    }
}