package com.example.myapplication;

import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.view.Window;
import android.view.WindowManager;
import android.widget.NumberPicker;

public class MainActivity extends AppCompatActivity {

    private NumberPicker numberPickerMinutes;// = (NumberPicker) findViewById(R.id.number_picker_minutes);
    private NumberPicker numberPickerSeconds;// = (NumberPicker) findViewById(R.id.number_picker_seconds);

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        supportRequestWindowFeature(Window.FEATURE_NO_TITLE);
        this.getWindow().setFlags(WindowManager.LayoutParams.FLAG_FULLSCREEN,
                WindowManager.LayoutParams.FLAG_FULLSCREEN);

        setContentView(R.layout.activity_main);

        numberPickerMinutes = (NumberPicker) findViewById(R.id.number_picker_minutes);
        numberPickerSeconds = (NumberPicker) findViewById(R.id.number_picker_seconds);

        numberPickerSeconds.setMaxValue(59);
        numberPickerSeconds.setMinValue(0);
        numberPickerSeconds.setWrapSelectorWheel(true);
        numberPickerSeconds.setValue(0);

        numberPickerMinutes.setMaxValue(30);
        numberPickerMinutes.setMinValue(0);
        numberPickerMinutes.setWrapSelectorWheel(true);
        numberPickerMinutes.setValue(10);
    }

    public void startGame(View view){
        Intent intent = new Intent(this, MainTimer.class);

        intent.putExtra("minutes", numberPickerMinutes.getValue());
        intent.putExtra("seconds", numberPickerSeconds.getValue());
        startActivity(intent);
    }
}