package com.example.sensor;

import android.content.BroadcastReceiver;
import android.content.Context;
import android.content.Intent;
import android.media.MediaPlayer;

public class AlarmBroadcast extends BroadcastReceiver implements AlarmRun {

    @Override
    public void onReceive(Context context, Intent intent) {}

    @Override
    public void play() {}

    @Override
    public void stop() {}

//    public interface AlarmRun {
//        public void stop();
//        public void run();
//    }
}
