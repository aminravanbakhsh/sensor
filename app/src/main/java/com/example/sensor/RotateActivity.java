package com.example.sensor;

import android.annotation.SuppressLint;
import android.app.Activity;
import android.hardware.Sensor;
import android.hardware.SensorManager;
import android.os.Bundle;
import android.util.Log;
import android.widget.TextView;

import androidx.annotation.Nullable;

public class RotateActivity extends Activity implements RotateEventListener.RotateListener {

    private TextView textView;

    @Override
    protected void onCreate(@Nullable Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_rotate);

        SensorManager sensorManager = (SensorManager) getSystemService(SENSOR_SERVICE);
        assert sensorManager != null;
        Sensor gyroscopeSensor = sensorManager.getDefaultSensor(Sensor.TYPE_GYROSCOPE);

        RotateEventListener rotateEventListener = new RotateEventListener();
        rotateEventListener.setRotateListener(this);

        sensorManager.registerListener(rotateEventListener, gyroscopeSensor, SensorManager.SENSOR_DELAY_NORMAL);

        textView = (TextView) findViewById(R.id.rotate_text);
    }

    @SuppressLint("SetTextI18n")
    @Override
    public void rotate(float wx, float wy, float wz) {
        Log.w("Tag", "hooo");
        textView.setText("x: " + String.valueOf(wx) + " y: " + String.valueOf(wy) + " z: " + String.valueOf(wz));
    }

}