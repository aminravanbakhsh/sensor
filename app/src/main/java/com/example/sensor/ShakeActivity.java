package com.example.sensor;

import android.app.Activity;
import android.content.Intent;
import android.hardware.Sensor;
import android.hardware.SensorManager;
import android.os.Bundle;

import android.util.Log;
import android.widget.TextView;

public class ShakeActivity extends Activity implements ShakeEventListener.OnShakeListener {

    private SensorManager sensorManager;
    private Sensor accelerometerSensor;
    private ShakeEventListener shakeEventListener;
    private TextView textView;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_shake);

        Intent intent = getIntent();

        sensorManager = (SensorManager) getSystemService(SENSOR_SERVICE);
        accelerometerSensor = sensorManager.getDefaultSensor(Sensor.TYPE_ACCELEROMETER);
        textView = (TextView) findViewById(R.id.count_text);

        shakeEventListener = new ShakeEventListener();
        shakeEventListener.setOnShakeListener(this);

        sensorManager.registerListener(shakeEventListener, accelerometerSensor, SensorManager.SENSOR_DELAY_NORMAL);

    }

    @Override
    public void onShake(int count) {
        Log.w("Tag", "on shake");
        textView.setText(String.valueOf(count));
    }
}
