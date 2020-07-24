package com.example.sensor;

import android.app.Activity;
import android.content.Context;
import android.content.Intent;
import android.os.Bundle;
import android.util.Log;
import android.view.View;
import android.widget.Button;

import androidx.annotation.Nullable;

public class SelectMissionActivity extends Activity {

    @Override
    protected void onCreate(@Nullable Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_select_mission);

        Intent intent = getIntent();

        Button shakeMission = findViewById(R.id.set_shake_btn);
        shakeMission.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
//                startActivityForResult();
                Bundle bundle = new Bundle();


                Intent shakeIntent = new Intent(getApplicationContext(), SetShakeActivity.class);

                shakeIntent.putExtra("shakeBundle", bundle);
                startActivityForResult(shakeIntent, 2);
            }
        });

    }

    @Override
    protected void onActivityResult(int requestCode, int resultCode, Intent data) {
        super.onActivityResult(requestCode, resultCode, data);

        String level = data.getStringExtra("level");
        int count = data.getIntExtra("count", 0);
        int levelPosition = data.getIntExtra("position", -1);
        Log.w("Tag", level + " " + String.valueOf(count) + " " + String.valueOf(levelPosition));

        Intent backIntent = new Intent();
        Bundle bundle = new Bundle();
        bundle.putString("mission", "shake");
        backIntent.putExtras(bundle);
        setResult(Activity.RESULT_OK, backIntent);
        finish();
    }
}