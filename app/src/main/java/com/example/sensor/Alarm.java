package com.example.sensor;

import android.app.AlarmManager;
import android.app.PendingIntent;
import android.content.Context;
import android.content.Intent;
import android.os.Parcel;
import android.os.Parcelable;
import android.widget.Toast;

public class Alarm implements Parcelable {


    private int mData;
    private Context context;
    private long time;
    private AlarmRun runner;

    public Alarm(Parcel in) {
        this.time = in.readLong();
    }

    public Alarm(Context context) {
        this.context = context;
    }

    public void run() {
        runner.play();
    }

    public void stop() {
        runner.stop();
    }

    public void setRunner(AlarmRun runner) {
        this.runner = runner;
    }

    public void setTime(long time) {
        this.time = time;
    }

    public void enableAlarm() {
        Intent intent = new Intent(this.context, runner.getClass());
        PendingIntent pendingIntent = PendingIntent.getBroadcast(context, 0, intent, PendingIntent.FLAG_UPDATE_CURRENT);
        AlarmManager alarmManager = (AlarmManager) this.context.getSystemService(Context.ALARM_SERVICE);
        alarmManager.set(AlarmManager.RTC_WAKEUP, time, pendingIntent);
    }

    public Context getContext() {
        return context;
    }

    @Override
    public int describeContents() {
        return 0;
    }

    @Override
    public void writeToParcel(Parcel dest, int flags) {
        dest.writeInt(mData);
    }

    public static final Parcelable.Creator<Alarm> CREATOR
            = new Parcelable.Creator<Alarm>() {
        public Alarm createFromParcel(Parcel in) {
            return new Alarm(in);
        }

        public Alarm[] newArray(int size) {
            return new Alarm[size];
        }
    };
}
