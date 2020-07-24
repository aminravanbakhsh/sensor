package com.example.sensor;

import android.hardware.Sensor;
import android.hardware.SensorEvent;
import android.hardware.SensorEventListener;
import android.util.Log;

//use gyroscope sensor

public class PositionEventListener implements SensorEventListener {

    private static final float X_THRESHOLD_GRAVITY = 0.985F;
    private PositionListener pListener;

    public interface PositionListener{
        public void position(boolean isBalance);

    }

    void setPositionListener(PositionListener Listener) {
        this.pListener = Listener;
        if (pListener != null) {
            Log.w("Tag", "pListener is not null");
        }
    }

    @Override
    public void onAccuracyChanged(Sensor sensor, int accuracy) {
        //ignore
    }

    @Override
    public void onSensorChanged(SensorEvent event) {

        if (pListener != null) {
            float z = event.values[0];
            float y = event.values[1];
            float x = event.values[2];
            float g = (float) Math.sqrt(x*x + y*y + z*z);

            boolean isBalance = false;
            if (Math.abs(x/g) > X_THRESHOLD_GRAVITY) {
                isBalance = true;
            }

            Log.w("Tag", "z: " + String.valueOf(z) + " y: " + String.valueOf(y) + " x: " + String.valueOf(x));

            pListener.position(isBalance);
        }

    }
}
