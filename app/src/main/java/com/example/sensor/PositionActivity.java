package com.example.sensor;

import android.app.Activity;
import android.content.Intent;
import android.hardware.Sensor;
import android.hardware.SensorManager;
import android.os.Bundle;
import android.util.Log;
import android.widget.TextView;

import androidx.annotation.Nullable;

public class PositionActivity extends Activity implements PositionEventListener.PositionListener {

    private SensorManager sensorManager;
    private Sensor gravitySensor;
    private PositionEventListener positionEventListener;
    private TextView textView;


    @Override
    protected void onCreate(@Nullable Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_position);
        Intent intent = getIntent();

        textView = (TextView) findViewById(R.id.or_btn);

        sensorManager = (SensorManager) getSystemService(SENSOR_SERVICE);
        gravitySensor = sensorManager.getDefaultSensor(Sensor.TYPE_GRAVITY);

        positionEventListener = new PositionEventListener();
        positionEventListener.setPositionListener(this);

        sensorManager.registerListener(positionEventListener, gravitySensor, SensorManager.SENSOR_DELAY_NORMAL);
    }

    @Override
    public void position(boolean isBalance) {
//        Log.w("Tag",String.valueOf(isBalance));
        this.textView.setText(String.valueOf(isBalance));
    }
}


