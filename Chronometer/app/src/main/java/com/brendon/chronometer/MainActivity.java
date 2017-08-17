package com.brendon.chronometer;

import android.os.SystemClock;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.support.v7.widget.ButtonBarLayout;
import android.view.View;
import android.widget.Button;
import android.widget.Chronometer;

public class MainActivity extends AppCompatActivity {

    private Button startButton, pauseButton, stopButton;
    private Chronometer chronometer;
    private long lastPause = 0;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        chronometer = (Chronometer)findViewById(R.id.chronometer);
        startButton = (Button)findViewById(R.id.start_button);
        pauseButton = (Button)findViewById(R.id.pause_button);
        stopButton = (Button)findViewById(R.id.stop_button);
        startButton.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                start();
            }
        });

        pauseButton.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                pause();
            }
        });

        stopButton.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                stop();
            }
        });

        startButton.setEnabled(true);
        pauseButton.setEnabled(false);
        stopButton.setEnabled(false);
    }

    public void start(){
        startButton.setEnabled(false);
        pauseButton.setEnabled(true);
        stopButton.setEnabled(true);

        chronometer.setBase(SystemClock.elapsedRealtime() + lastPause);
        chronometer.start();
    }

    public void pause(){
        startButton.setEnabled(true);
        pauseButton.setEnabled(false);
        stopButton.setEnabled(true);

        lastPause = chronometer.getBase() - SystemClock.elapsedRealtime();
        chronometer.stop();
    }

    public void stop(){
        startButton.setEnabled(true);
        pauseButton.setEnabled(false);
        stopButton.setEnabled(false);

        lastPause = 0;
        chronometer.setBase(SystemClock.elapsedRealtime());
        chronometer.stop();
    }
}
