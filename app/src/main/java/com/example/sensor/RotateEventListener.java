package com.example.sensor;

import android.hardware.Sensor;
import android.hardware.SensorEvent;
import android.hardware.SensorEventListener;
import android.util.Log;

public class RotateEventListener implements SensorEventListener {

    private RotateListener rListener;
    private float xl;
    private float yl;
    private float zl;

    public interface RotateListener{
        public void rotate(float wx, float wy, float wz);
    }

    public void setRotateListener(RotateListener Listener) {
        this.rListener = Listener;
        if (rListener != null) {
            Log.w("Tag", "pListener is not null");
        }
    }


    @Override
    public void onAccuracyChanged(Sensor sensor, int accuracy) {
//        ignore
    }

    @Override
    public void onSensorChanged(SensorEvent event) {
        if (rListener != null) {
            float wx = event.values[0];
            float wy = event.values[1];
            float wz = event.values[2];

            float dx = wx - xl;
            float dy = wy - yl;
            float dz = wz - zl;

//            this.xa += wx * 1 / SensorManager.SENSOR_DELAY_NORMAL;
//            this.ya += wy * 1 / SensorManager.SENSOR_DELAY_NORMAL;
//            this.za += wz * 1 / SensorManager.SENSOR_DELAY_NORMAL;

//            this.xa += wx;
//            this.ya += wy;
//            this.za += wz;

//            rListener.position(xa,ya,za);
            Log.w("Tag", "z: " + String.valueOf(wz) + " y: " + String.valueOf(wy) + " x: " + String.valueOf(wx));
            rListener.rotate(wx,wy,wz);
        }
    }
}
