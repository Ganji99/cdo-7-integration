package com.calldoradosdk.integration;

import android.annotation.SuppressLint;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;

import androidx.appcompat.app.AppCompatActivity;

import com.calldorado.sdk.Calldorado;

public class SecondActivity extends AppCompatActivity {
    Button settings, lastCall;

    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.second_activity);
        settings = findViewById(R.id.settings);
        lastCall = findViewById(R.id.latestcall);

        settings.setOnClickListener(new View.OnClickListener() {
            @SuppressLint("UnsafeOptInUsageError")
            @Override
            public void onClick(View view) {
                Calldorado.showSettings(SecondActivity.this);
            }
        });

        lastCall.setOnClickListener(new View.OnClickListener() {
            @SuppressLint("UnsafeOptInUsageError")
            @Override
            public void onClick(View view) {
               // The below line of code will show an Aftercall screen containing information from the latest phone call.
                // If there is no call that has been made after the initialization of the Caller SDK then it will
                // show a toast message “no latest call information”.
                Calldorado.showAftercall(SecondActivity.this);
            }
        });
    }
    @Override
    public void onBackPressed() {
        finish();
    }


}
