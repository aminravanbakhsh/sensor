package com.example.sensor;

import android.content.Context;
import android.hardware.Sensor;
import android.hardware.SensorManager;

import static android.content.Context.SENSOR_SERVICE;

public class PositionController implements PositionEventListener.PositionListener {

    private Alarm alarm;

    public PositionController(Alarm alarm) {
        this.alarm = alarm;

        SensorManager sensorManager = (SensorManager) alarm.getContext().getSystemService(SENSOR_SERVICE);
        Sensor gyroscope = sensorManager.getDefaultSensor(Sensor.TYPE_GYROSCOPE);

        PositionEventListener positionEventListener = new PositionEventListener();
        positionEventListener.setPositionListener(this);
        sensorManager.registerListener(positionEventListener, gyroscope, sensorManager.SENSOR_DELAY_NORMAL);
    }


    @Override
    public void position(boolean isBalance) {
        if (isBalance) {
            alarm.stop();
        }
    }
}