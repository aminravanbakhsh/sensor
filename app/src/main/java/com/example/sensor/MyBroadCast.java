package com.example.sensor;

import android.content.BroadcastReceiver;
import android.content.Context;
import android.content.Intent;
import android.media.MediaPlayer;
import android.widget.Toast;

public class MyBroadCast extends BroadcastReceiver {

    static private MediaPlayer mp;

    @Override
    public void onReceive(Context context, Intent intent) {
        mp = MediaPlayer.create(context, R.raw.rington);
        mp.start();
        Toast.makeText(context, "okkk", Toast.LENGTH_SHORT).show();
    }

    static public void stop() {
        mp.stop();
    }
}
