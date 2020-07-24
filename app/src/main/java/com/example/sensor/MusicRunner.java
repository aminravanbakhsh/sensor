package com.example.sensor;

import android.content.BroadcastReceiver;
import android.content.Context;
import android.content.Intent;
import android.media.MediaPlayer;
import android.widget.Toast;

public class MusicRunner extends AlarmBroadcast {

    private Context context;
    static private MediaPlayer mp;


    public void setContext(Context context) {
        this.context = context;
    }

    @Override
    public void play() {
        mp = MediaPlayer.create(context, R.raw.rington);
        mp.start();
        Toast.makeText(context, "media", Toast.LENGTH_LONG);
    }

    @Override
    public void stop() {
        mp.stop();
    }

    @Override
    public void onReceive(Context context, Intent intent) {
        mp = MediaPlayer.create(context, R.raw.rington);
        mp.start();
        Toast.makeText(context, "Alarm....", Toast.LENGTH_LONG).show();
    }
}
