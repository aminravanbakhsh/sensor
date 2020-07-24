package com.example.sensor;

import android.content.Context;
import android.hardware.Sensor;
import android.hardware.SensorManager;

import static android.content.Context.SENSOR_SERVICE;

public class RotateController implements RotateEventListener.RotateListener {

    private int rotateCount;
    private Alarm alarm;

    public RotateController(Alarm alarm, int rotateCount) {
        this.alarm = alarm;
        this.rotateCount = rotateCount;

        SensorManager sensorManager = (SensorManager) alarm.getContext().getSystemService(SENSOR_SERVICE);
        Sensor gyroscope = sensorManager.getDefaultSensor(Sensor.TYPE_GYROSCOPE);

        RotateEventListener rotateEventListener = new RotateEventListener();
        rotateEventListener.setRotateListener(this);
        sensorManager.registerListener(rotateEventListener, gyroscope, SensorManager.SENSOR_DELAY_NORMAL);
    }

    @Override
    public void rotate(int countX, int countY, int countZ) {
        if (countZ >= rotateCount) {
            alarm.stop();

        }
    }
}
