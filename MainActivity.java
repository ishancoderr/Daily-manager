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

import static android.os.SystemClock.elapsedRealtime;
import static com.example.watch.settings_2.*;

public class MainActivity extends AppCompatActivity {
    private Chronometer chronometer;
    private long pauseoffset = 0;
    private boolean running;
    private Button button;
    private long mProgressStatus =0;
    private ProgressBar progressBar;
    private int n=1;


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        chronometer=findViewById(R.id.chronometer);
        progressBar=findViewById(R.id.progressBar_id);
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
                int new_w= 0;
                int new_interval=0;


                Intent intent=getIntent();

                String new_worktime=intent.getStringExtra(settings_2.EXTRA_NAME);
                String interveltime=intent.getStringExtra(settings_2.EXTRA_NAME1);
                int interval_time=Integer.parseInt(interveltime);
                int w_time = Integer.parseInt(new_worktime);

                new_w =w_time*60000 ;
                new_interval=interval_time*60000;

                    if(n==1){
                    if ((elapsedRealtime() - chronometer.getBase()) >= new_w) {

                        Toast.makeText(MainActivity.this, "YOU DO IT & INTERVAL START", Toast.LENGTH_SHORT).show();
                        chronometer.setBase(elapsedRealtime());

                        n=n+1;


                    }}else if(n==2){
                        if ((elapsedRealtime() - chronometer.getBase()) >= new_interval) {

                            Toast.makeText(MainActivity.this, "interval was finished", Toast.LENGTH_SHORT).show();
                            chronometer.setBase(elapsedRealtime());
                            n=n-1;


                        }

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
            chronometer.setBase(elapsedRealtime()-pauseoffset);
            chronometer.start();
            running=true;


        }
    }
    public void pauseChronometer(View v) {
        if (running) {
            chronometer.stop();
            pauseoffset = elapsedRealtime() - chronometer.getBase();
            running = false;
        }
    }

        public void resetChronometer (View v){
            chronometer.setBase(elapsedRealtime());
            pauseoffset = 0;
        }






}