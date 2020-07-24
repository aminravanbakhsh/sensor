package com.example.sensor;

import android.annotation.SuppressLint;
import android.app.Activity;
import android.app.AlarmManager;
import android.app.PendingIntent;
import android.content.Context;
import android.content.Intent;
import android.icu.util.Calendar;
import android.os.Build;
import android.os.Bundle;
import android.util.Log;
import android.view.View;
import android.widget.Button;
import android.widget.TimePicker;
import android.widget.Toast;

import androidx.annotation.Nullable;
import androidx.annotation.RequiresApi;

public class SetAlarmActivity2 extends Activity {

    private Alarm alarm;
    private ShakeController shakeController;
    private MusicRunner musicRunner;
    private PendingIntent pendingIntent;
    private Context thisContext;
    final TimePicker timePicker = (TimePicker) findViewById(R.id.clock_timePicker);

    @RequiresApi(api = Build.VERSION_CODES.N)
    @Override
    protected void onCreate(@Nullable Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_set_alarm);
        final Context thisContext = this;

        final Intent intent = getIntent();
        final Calendar calendar = Calendar.getInstance();

//        alarm = new Alarm(this);
//        musicRunner = new MusicRunner();
//        musicRunner.setContext(this);
//        alarm.setRunner(musicRunner);


        Button alarm_on = (Button) findViewById(R.id.alarm_on2);
        alarm_on.setOnClickListener(new View.OnClickListener() {
            @SuppressLint("SetTextI18n")
            @Override
            public void onClick(View view) {
//                calendar.set(Calendar.HOUR_OF_DAY, timePicker.getHour());
//                calendar.set(Calendar.MINUTE, timePicker.getMinute());
//
//                int hour = timePicker.getHour();
//                int minute = timePicker.getMinute();
//
//                Intent boardCastIntent = new Intent(thisContext, MusicRunner.class);
//                alarm.setTime(calendar.getTimeInMillis());
//                alarm.enableAlarm();
                Intent backIntent = new Intent();
                startActivityForResult(backIntent, 3);


            }
        });

        Button mission_btn = (Button) findViewById(R.id.go_select_mission_btn);
        mission_btn.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent missionIntent = new Intent(thisContext, SelectMissionActivity.class);
                startActivityForResult(missionIntent, 10);

            }
        });

    }

    @RequiresApi(api = Build.VERSION_CODES.N)
    @Override
    protected void onActivityResult(int requestCode, int resultCode, Intent data) {
        super.onActivityResult(requestCode, resultCode, data);
        String mission = data.getStringExtra("mission");
        Log.w("Tag", "mission     complete  " + mission);
        switch (mission) {
            case "shake":
                Alarm shakeAlarm = new Alarm(getApplicationContext());
                Calendar calendar = Calendar.getInstance();
                calendar.set(Calendar.HOUR_OF_DAY, timePicker.getHour());
                calendar.set(Calendar.MINUTE, timePicker.getMinute());

                shakeAlarm.setTime(calendar.getTimeInMillis());
                ShakeController shakeController = new ShakeController(3, ShakeEventListener.Shake_Intensity.medium);
                shakeController.setAlarm(shakeAlarm);
                shakeAlarm.setRunner(musicRunner);

        }
    }
}
