package com.shahram.noorani.arcsample;

import android.graphics.Color;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;

import com.shahram.noorani.arcprogress.ArcProgress;
import com.shahram.noorani.arcprogress.NotSupportListner;

public class MainActivity extends AppCompatActivity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        ArcProgress arcProgress=findViewById(R.id.arc);

        arcProgress.setMax(180);
        arcProgress.setmin(50);
        arcProgress.setProgress(10);
        arcProgress.setEmptyColor(Color.parseColor("#57ca87"));
        arcProgress.setFillColor(Color.parseColor("#f26c4f"));
        arcProgress.setBackgroundCircleColor(Color.WHITE);
        arcProgress.setTextColor(Color.BLACK);
        arcProgress.setTextColorCurentProgress(Color.BLACK);
        arcProgress.setTextSizeMinMax(20);
        arcProgress.setTextSizeCurentProgress(40);
        arcProgress.setNotSupportAndroidVersionListner(new NotSupportListner() {
            @Override
            public void notSupport() {

            }
        });




    }
}
