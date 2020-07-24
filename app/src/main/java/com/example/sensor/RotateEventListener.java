package com.example.sensor;

import android.hardware.Sensor;
import android.hardware.SensorEvent;
import android.hardware.SensorEventListener;
import android.util.Log;

public class RotateEventListener implements SensorEventListener {

    private static final int ROTATE_SLOP_TIME_MS = 500;
    private static final int ROTATE_COUNT_RESET_TIME_MS = 3000;
    private static final float ROTATION_SPEED_THRESHOLD = 0.002f;

    private long mRotateTimestamp;
    private int mShakeCount;

    private RotateListener rListener;
    private float xl;
    private float yl;
    private float zl;

    public RotateEventListener() {
        xl = 0;
        yl = 0;
        zl = 0;
    }

    public interface RotateListener{
        public void rotate(int countX, int countY, int countZ);
    }

    void setRotateListener(RotateListener Listener) {
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

            Log.w("Tag", "z: " + String.valueOf(zl) + " y: " + String.valueOf(yl) + " x: " + String.valueOf(xl));


            Log.w("Tag", "rotate");
            if (rListener != null) {
                float w = (float) Math.sqrt(wx*wx + wy*wy + wz*wz);
                if (w > ROTATION_SPEED_THRESHOLD) {
                    final long now = System.currentTimeMillis();

                    if (mRotateTimestamp + ROTATE_COUNT_RESET_TIME_MS < now) {
                        xl = 0;
                        yl = 0;
                        zl = 0;
                    }

                    mRotateTimestamp = now;
                    xl += wx;
                    yl += wy;
                    zl += wz;

                    int countX = (int) (xl / (90.0f));
                    int countY = (int) (yl / (90.0f));
                    int countZ = (int) (zl / (90.0f));

                    rListener.rotate(countX, countY, countZ);
                }
            }
        }
    }
}
