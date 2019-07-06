package com.iunium.timerforstudying;

import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.widget.Button;
import android.view.View;
import android.widget.TextView;


public class MainActivity extends AppCompatActivity {
    long temp;
    PersonalTimer pt;
    boolean isPaused;
    Long time;
    Long interval;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        TextView tv = findViewById(R.id.textView);
        isPaused = false;
        time = (long) 25*60*1000;
        interval = (long) 1000;
        temp = time;
        pt = new PersonalTimer(time, interval, tv);
        pt.c = getApplicationContext();
        Button b = findViewById(R.id.button2);
        b.setClickable(false);
    }

    public void startTimer(View v){
        TextView tv = findViewById(R.id.textView);
        Button b = findViewById(R.id.button2);
        b.setClickable(true);
        if(!isPaused) {
            pt.time = temp;
            pt.start();
            isPaused = true;
        }
        else{
            temp = pt.cancel();
            tv.setText("pause");
            isPaused = false;
        }
    }
    public void resetTimer(View v){
        TextView tv = findViewById(R.id.textView);
        temp = (long) 25*60*1000;
        time = (long) 25*60*1000;
        if(tv.getText() != "pause")
        pt.cancel();
        isPaused = false;
        tv.setText("reset");
    }

}
