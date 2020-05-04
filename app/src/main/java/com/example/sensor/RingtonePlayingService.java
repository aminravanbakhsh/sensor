package com.example.sensor;

import android.annotation.SuppressLint;
import android.app.Notification;
import android.app.NotificationManager;
import android.app.PendingIntent;
import android.app.Service;
import android.content.Intent;
import android.media.MediaPlayer;
import android.os.IBinder;
import android.util.Log;

import androidx.annotation.Nullable;

import java.util.Random;

@SuppressLint("Registered")
public class RingtonePlayingService extends Service {

    MediaPlayer media_song;
    int startId;
    boolean isRunning;

    @Nullable
    @Override
    public IBinder onBind(Intent intent) {
        return null;
    }

    @Override
    public int onStartCommand(Intent intent, int flags, int startId) {

        String state = intent.getExtras().getString("extra");

        int sound_id = intent.getExtras().getInt("sound_choice");

        NotificationManager notify_manager =
                (NotificationManager)getSystemService(NOTIFICATION_SERVICE);
        Intent intent_main_activity = new Intent(this.getApplicationContext(), MainActivity.class);

        PendingIntent pending_intent_main_activity =
                PendingIntent.getActivity(this, 0, intent_main_activity, 0);

        Notification notify_popup =
                new Notification.Builder(this).setContentTitle("An alarm is going off!")
                        .setContentText("Click me!")
                        .setContentIntent(pending_intent_main_activity)
                        .setAutoCancel(true).build();



        assert state != null;
        switch (state) {
            case "alarm on":
                startId = 1;
                break;
            case "alarm off":
                startId = 0;
                Log.e("Start ID is ", state);
                break;
            default:
                startId = 0;
                break;
        }

        if(!this.isRunning && startId == 1) {

            this.isRunning = true;
            this.startId = 0;

            assert notify_manager != null;
            notify_manager.notify(0, notify_popup);

            media_song = MediaPlayer.create(this, R.raw.kalimba);
            media_song.start();

            if(sound_id == 0) {
                int min = 1, max = 5;
                Random rand = new Random();
                int sound_number = rand.nextInt(max + min);

                if(sound_number == 1) {
                    media_song = MediaPlayer.create(this, R.raw.catalina_wine_mixer);
                    media_song.start();
                } else if(sound_number == 2) {
                    media_song = MediaPlayer.create(this, R.raw.inclement_weather);
                    media_song.start();
                } else if(sound_number == 3) {
                    media_song = MediaPlayer.create(this, R.raw.johnny_hopkins);
                    media_song.start();
                } else if(sound_number == 4) {
                    media_song = MediaPlayer.create(this, R.raw.lightning_bolt);
                    media_song.start();
                } else if(sound_number == 5) {
                    media_song = MediaPlayer.create(this, R.raw.robert_better_not_get_in_my_face);
                    media_song.start();
                } else {
                    media_song = MediaPlayer.create(this, R.raw.butt_buddy);
                    media_song.start();
                }
            } else if(sound_id == 1) {
                media_song = MediaPlayer.create(this, R.raw.catalina_wine_mixer);
                media_song.start();
            } else if(sound_id == 2) {
                media_song = MediaPlayer.create(this, R.raw.inclement_weather);
                media_song.start();
            } else if(sound_id == 3) {
                media_song = MediaPlayer.create(this, R.raw.johnny_hopkins);
                media_song.start();
            } else if(sound_id == 4) {
                media_song = MediaPlayer.create(this, R.raw.lightning_bolt);
                media_song.start();
            } else if(sound_id == 5) {
                media_song = MediaPlayer.create(this, R.raw.robert_better_not_get_in_my_face);
                media_song.start();
            } else {
                media_song = MediaPlayer.create(this, R.raw.butt_buddy);
                media_song.start();
            }


        } else if(this.isRunning && startId == 0) {

            media_song.stop();
            media_song.reset();

            this.isRunning = false;
            this.startId = 0;

        } else if(!this.isRunning && startId == 0) {
            this.isRunning = false;
            startId = 0;

        } else if(this.isRunning && startId == 1) {
            this.isRunning = true;
            this.startId = 1;

        }

        return START_NOT_STICKY;
    }

    @Override
    public void onDestroy() {

        super.onDestroy();
        this.isRunning = false;
    }

}
