package com.example.sensor;

import android.app.Activity;
import android.app.AlarmManager;
import android.app.PendingIntent;
import android.content.BroadcastReceiver;
import android.content.Context;
import android.content.Intent;
import android.os.Bundle;

import android.widget.TextView;
import android.widget.Toast;

public class ShakeActivity extends Activity  {

    private TextView textView;
    private Alarm alarm;
    private ShakeController shakeController;
    private MusicRunner musicRunner;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_shake);

        Intent pIntent = getIntent();
        alarm = new Alarm(this);

        shakeController = new ShakeController(3, ShakeEventListener.Shake_Intensity.medium);

        musicRunner = new MusicRunner();
        musicRunner.setContext(alarm.getContext());
        alarm.setRunner(musicRunner);
        alarm.setTime(System.currentTimeMillis() + 5*1000);
        alarm.enableAlarm();
//        alarm.run();

//        Intent broadCastIntent = new Intent(this, MyBroadCast.class);
//        PendingIntent pendingIntent = PendingIntent.getBroadcast(this, 3934934, broadCastIntent, 0);
//        AlarmManager alarmManager = (AlarmManager) getSystemService(ALARM_SERVICE);
//        alarmManager.set(AlarmManager.RTC_WAKEUP, System.currentTimeMillis() + 2*1000, pendingIntent);
//        Toast.makeText(this, "media", Toast.LENGTH_LONG).show();




//        AlarmManager alarmManager = (AlarmManager) getSystemService(Context.ALARM_SERVICE);
//        Intent intent = new Intent(this, MyBroadCast.class);
////        intent.setAction(MyR.ACTION_ALARM_RECEIVER);//my custom string action name
//        PendingIntent pendingIntent = PendingIntent.getBroadcast(this, 1001, intent, PendingIntent.FLAG_CANCEL_CURRENT);//used unique ID as 1001
//        alarmManager.setRepeating(AlarmManager.RTC_WAKEUP, System.currentTimeMillis(), 6*1000, pendingIntent);//first start will start asap
//
//

    }
}
