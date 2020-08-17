package com.example.watch;

import android.content.Intent;
import android.os.Bundle;
import android.os.SystemClock;
import android.view.View;
import android.widget.Button;
import android.widget.Chronometer;
import android.widget.ProgressBar;
import android.widget.Toast;

import androidx.appcompat.app.AppCompatActivity;

public class MainActivity extends AppCompatActivity {
    private Chronometer chronometer;
    private long pauseoffset = 0;
    private boolean running;
    private Button button;

   
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        chronometer=findViewById(R.id.chronometer);
        button=(Button) findViewById(R.id.settings_btn);
        button.setOnClickListener(new View.OnClickListener(){

            @Override
            public void onClick(View view) {
                activity_settings_2();
            }
        });
        chronometer.setOnChronometerTickListener(new Chronometer.OnChronometerTickListener() {
            @Override
            public void onChronometerTick(Chronometer chronometer) {
                if((SystemClock.elapsedRealtime()-chronometer.getBase())>=1500000){
                   chronometer.setBase(SystemClock.elapsedRealtime());
                   Toast.makeText(MainActivity.this, "YOU DO IT", Toast.LENGTH_SHORT).show();
                }
            }
       });
    }

    private void activity_settings_2() {
        Intent intent=new Intent(this,settings_2.class);
        startActivity(intent);
    }

    public void startChronometer(View v) {
        if(!running){
            chronometer.setBase(SystemClock.elapsedRealtime()-pauseoffset);
            chronometer.start();
            running=true;
        }
    }
    public void pauseChronometer(View v) {
        if (running) {
            chronometer.stop();
            pauseoffset = SystemClock.elapsedRealtime() - chronometer.getBase();
            running = false;
        }
    }

        public void resetChronometer (View v){
            chronometer.setBase(SystemClock.elapsedRealtime());
            pauseoffset = 0;
        }


}