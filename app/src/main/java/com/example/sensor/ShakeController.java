package com.example.sensor;

import android.app.Activity;
import android.content.Context;
import android.hardware.Sensor;
import android.hardware.SensorManager;

import static android.content.Context.SENSOR_SERVICE;

public class ShakeController implements ShakeEventListener.OnShakeListener{

    private int count;
    private ShakeEventListener.Shake_Intensity shake_intensity;
    private Alarm alarm;

    public ShakeController(int count, ShakeEventListener.Shake_Intensity level) {
//        this.alarm = alarm;
        this.count = count;
        this.shake_intensity = level;
    }

    public void setAlarm(Alarm alarm) {
        this.alarm = alarm;
    }

    public void registerController() {
        SensorManager sensorManager = (SensorManager) alarm.getContext().getSystemService(SENSOR_SERVICE);
        Sensor accelerometerSensor = sensorManager.getDefaultSensor(Sensor.TYPE_ACCELEROMETER);

        ShakeEventListener shakeEventListener = new ShakeEventListener();
        shakeEventListener.setOnShakeListener(this, shake_intensity);
        sensorManager.registerListener(shakeEventListener, accelerometerSensor, SensorManager.SENSOR_DELAY_NORMAL);
    }

    @Override
    public void onShake(int count) {
        if (count >= this.count) {
            alarm.stop();
        }
    }
}