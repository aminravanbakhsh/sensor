package com.example.sensor;

import android.app.Activity;
import android.content.Intent;
import android.os.Bundle;
import android.util.Log;
import android.view.View;
import android.widget.AdapterView;
import android.widget.ArrayAdapter;
import android.widget.Button;
import android.widget.Spinner;
import android.widget.TextView;
import android.widget.Toast;

import androidx.annotation.Nullable;

public class SetShakeActivity extends Activity implements AdapterView.OnItemSelectedListener {

    TextView countText;
    TextView levelText;

    private String[] shakeLevels = {"low", "medium", "high"};
    private int[] shakeNum = {5, 10, 20, 30, 40, 50};


    private Spinner levelSpinner;
    private Spinner countSpinner;


    @Override
    protected void onCreate(@Nullable Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_set_shake);

        Intent intent = getIntent();

        countText = findViewById(R.id.count_shake);
        levelText = findViewById(R.id.level_shake);

        levelSpinner = (Spinner) findViewById(R.id.shake_level_spinner);
        levelSpinner.setOnItemSelectedListener(this);
        ArrayAdapter<CharSequence> adapter = ArrayAdapter.createFromResource(this, R.array.shake_level, android.R.layout.simple_spinner_item);
        adapter.setDropDownViewResource(android.R.layout.simple_spinner_dropdown_item);
        levelSpinner.setAdapter(adapter);

        countSpinner = (Spinner) findViewById(R.id.shake_count_spinner);
        countSpinner.setOnItemSelectedListener(this);


        Button button = findViewById(R.id.set_shake_ok_btn);
        button.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                goBack();
            }
        });

    }

    private void goBack() {
        Bundle bundle = new Bundle();
        Intent bundleIntent = new Intent(getApplicationContext(), SelectMissionActivity.class);
        bundle.putInt("count", Integer.parseInt(countText.getText().toString()));
        bundle.putInt("position", levelSpinner.getSelectedItemPosition());
        bundle.putString("level", levelText.getText().toString());
        bundleIntent.putExtras(bundle);
        setResult(Activity.RESULT_OK, bundleIntent);
        finish();
    }

    @Override
    public void onItemSelected(AdapterView<?> parent, View view, int position, long id) {
        Log.w("Tag", String.valueOf(position));
        Toast.makeText(this, String.valueOf(position), Toast.LENGTH_LONG).show();
    }

    @Override
    public void onNothingSelected(AdapterView<?> parent) {

    }
}
