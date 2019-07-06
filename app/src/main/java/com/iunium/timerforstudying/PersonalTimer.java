package com.iunium.timerforstudying;

import android.content.Context;
import android.media.MediaPlayer;
import android.net.Uri;
import android.os.CountDownTimer;
import android.provider.MediaStore;
import android.widget.TextView;

import java.util.concurrent.TimeUnit;

public class PersonalTimer {
    public MediaPlayer mediaPlayer;
    public Context c;
    public TextView tv;
    public CountDownTimer cdt;
    public Long time;
    public Long interval;
    public Long mins;
    public Long secs;
    public boolean isFinished;


    public PersonalTimer(Long time, Long interval, TextView tv){
        this.time = time;
        this.interval = interval;
        this.isFinished = false;
        this.mins = (long)0;
        this.secs = (long)0;
        this.cdt = null;
        this.tv = tv;
    }

    public void timer(){
        cdt = new CountDownTimer(time, interval) {
            @Override
            public void onTick(long millisUntilFinished) {
                time = millisUntilFinished;
                mins = TimeUnit.MILLISECONDS.toMinutes(millisUntilFinished);
                secs = TimeUnit.MILLISECONDS.toSeconds(millisUntilFinished) - TimeUnit.MINUTES.toSeconds(TimeUnit.MILLISECONDS.toMinutes(millisUntilFinished));
                tv.setText(mins.toString() + ":" + secs.toString());
            }

            @Override
            public void onFinish() {
                isFinished = true;
                tv.setText("finito");
                mediaPlayer = (MediaPlayer) MediaPlayer.create( c, R.raw.pew);
                mediaPlayer.start();
                time = (long)25*60*1000;
            }

        };
    }
    public void start(){
        timer();
        cdt.start();
    }
    public long cancel(){
        long temp;
        cdt.cancel();
        temp = time;
        cdt = null;
        return temp;

    }
    public String getMins(){
        return this.mins.toString();
    }

    public String getSecs(){
        return this.secs.toString();
    }

    public Long getMillis(){
        return this.time;
    }
}
