package com.example.sensor;

import android.hardware.Sensor;
import android.hardware.SensorEvent;
import android.hardware.SensorEventListener;
import android.hardware.SensorManager;
import android.util.Log;

//use accelerometer sensor

public class ShakeEventListener implements SensorEventListener {

    enum Shake_Intensity {
        low,
        medium,
        high
    }

    static public Shake_Intensity getShakeIntensityLevel(String level) {
        Shake_Intensity shake_intensity;
        switch (level) {
            case "low":
                shake_intensity = Shake_Intensity.low;
                break;
            case "medium":
                shake_intensity = Shake_Intensity.medium;
                break;
            case "high":
                shake_intensity = Shake_Intensity.high;
                break;
            default:
                shake_intensity = null;
        }
        return shake_intensity;
    }

    private static final float LOW_INTENSITY = 1.2F;
    private static final float MEDIUM_INTENSITY = 1.2F;
    private static final float HIGH_INTENSITY = 1.2F;

    private static float shake_threshold_gravity;
    private static final int SHAKE_SLOP_TIME_MS = 500;
    private static final int SHAKE_COUNT_RESET_TIME_MS = 3000;

    private OnShakeListener mListener;
    private long mShakeTimestamp;
    private int mShakeCount;

    void setOnShakeListener(OnShakeListener listener, Shake_Intensity level) {
        this.mListener = listener;
        switch (level){
            case low:
                shake_threshold_gravity = LOW_INTENSITY;
                break;
            case medium:
                shake_threshold_gravity = MEDIUM_INTENSITY;
                break;
            case high:
                shake_threshold_gravity = HIGH_INTENSITY;
                break;
        }
    }

//    public abstract void onShake(int count);

    public interface OnShakeListener {
        public void onShake(int count);
    }

    @Override
    public void onAccuracyChanged(Sensor sensor, int accuracy) {
        // ignore
    }

    @Override
    public void onSensorChanged(SensorEvent event) {

//        Log.w("Tag", "hoo");
        if (mListener != null) {

            float x = event.values[0];
            float y = event.values[1];
            float z = event.values[2];

            float gX = x / SensorManager.GRAVITY_EARTH;
            float gY = y / SensorManager.GRAVITY_EARTH;
            float gZ = z / SensorManager.GRAVITY_EARTH;

            // gForce will be close to 1 when there is no movement.
            float gForce = (float)Math.sqrt(gX * gX + gY * gY + gZ * gZ);
//            Log.w("Tag", String.valueOf(gForce));

            if (gForce > shake_threshold_gravity) {
                final long now = System.currentTimeMillis();
                // ignore shake events too close to each other (500ms)
                if (mShakeTimestamp + SHAKE_SLOP_TIME_MS > now) {
                    return;
                }

                // reset the shake count after 3 seconds of no shakes
                if (mShakeTimestamp + SHAKE_COUNT_RESET_TIME_MS < now) {
                    mShakeCount = 0;
                }

                mShakeTimestamp = now;
                mShakeCount++;

                mListener.onShake(mShakeCount);
            }
        }
    }
}