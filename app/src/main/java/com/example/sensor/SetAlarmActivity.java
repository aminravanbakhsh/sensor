package com.example.sensor;

import android.annotation.SuppressLint;
import android.app.AlarmManager;
import android.app.PendingIntent;
import android.content.Context;
import android.content.Intent;
import android.icu.util.Calendar;
import android.os.Build;
import android.os.Bundle;
import android.view.View;
import android.widget.AdapterView;
import android.widget.ArrayAdapter;
import android.widget.Button;
import android.widget.Spinner;
import android.widget.TextView;
import android.widget.TimePicker;

import androidx.annotation.RequiresApi;
import androidx.appcompat.app.AppCompatActivity;

@SuppressLint("Registered")
public class SetAlarmActivity extends AppCompatActivity implements AdapterView.OnItemSelectedListener {

    AlarmManager alarm_manager;
    TimePicker alarm_timepicker;
    TextView alarm_state;
    Context context;
    PendingIntent pending_intent;
    int sound_select;

    @RequiresApi(api = Build.VERSION_CODES.N)
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_set);

        Intent intent = getIntent();

        final AlarmManager alarm_manager = (AlarmManager) getSystemService(ALARM_SERVICE);
        final TimePicker alarm_timepicker = (TimePicker) findViewById(R.id.timePicker);

        @SuppressLint("CutPasteId") final TextView alarm_state = (TextView) findViewById(R.id.alarm_state);

        final Calendar calendar = Calendar.getInstance();

        final Intent my_intent = new Intent(SetAlarmActivity.this, AlarmReceiver.class);

        @SuppressLint("CutPasteId") Spinner spinner = (Spinner) findViewById(R.id.spinner);
        ArrayAdapter<CharSequence> adapter = ArrayAdapter.createFromResource
                (this, R.array.stepbrothers_array, android.R.layout.simple_spinner_item);
        adapter.setDropDownViewResource(android.R.layout.simple_spinner_dropdown_item);
        spinner.setAdapter(adapter);
        spinner.setOnItemSelectedListener(this);


        @SuppressLint("CutPasteId") Button alarm_on = (Button) findViewById(R.id.alarm_on);
        alarm_on.setOnClickListener(new View.OnClickListener() {
            @SuppressLint("SetTextI18n")
            @Override
            public void onClick(View view) {
                calendar.set(Calendar.HOUR_OF_DAY, alarm_timepicker.getHour());
                calendar.set(Calendar.MINUTE, alarm_timepicker.getMinute());

                int hour = alarm_timepicker.getHour();
                int minute = alarm_timepicker.getMinute();

                String hour_string = String.valueOf(hour);
                String minute_string = String.valueOf(minute);

                if(hour > 12) hour_string = String.valueOf(hour - 12);
                if(minute < 10) minute_string = "0" + String.valueOf(minute);

                alarm_state.setText("Alarm set to: " + hour_string + ":" + minute_string);

                my_intent.putExtra("extra", "alarm on");

                my_intent.putExtra("sound_choice", sound_select);


                pending_intent = PendingIntent.getBroadcast
                        (SetAlarmActivity.this, 0, my_intent, PendingIntent.FLAG_UPDATE_CURRENT);

                assert alarm_manager != null;
                alarm_manager.set(AlarmManager.RTC_WAKEUP, calendar.getTimeInMillis(), pending_intent);

            }
        });

        @SuppressLint("CutPasteId") Button alarm_off = (Button) findViewById(R.id.alarm_off);
        alarm_off.setOnClickListener(new View.OnClickListener() {
            @SuppressLint("SetTextI18n")
            @Override
            public void onClick(View view) {
                alarm_state.setText("Alarm Off!");

                assert alarm_manager != null;
                alarm_manager.cancel(pending_intent);

                my_intent.putExtra("extra", "alarm off");

                my_intent.putExtra("sound_choice", sound_select);

                sendBroadcast(my_intent);
            }
        });
    }

    @Override
    public void onItemSelected(AdapterView<?> adapterView, View view, int position, long id) {
        sound_select = (int) id;
    }

    @Override
    public void onNothingSelected(AdapterView<?> adapterView) {

    }
}