package com.calldoradosdk.integration;

import android.Manifest;
import android.annotation.SuppressLint;
import android.content.Context;
import android.content.Intent;
import android.content.pm.PackageManager;
import android.os.Build;
import android.os.Bundle;
import android.provider.Settings;
import android.view.View;
import android.widget.Button;
import android.widget.TextView;

import androidx.annotation.NonNull;
import androidx.annotation.RequiresApi;
import androidx.appcompat.app.AppCompatActivity;
import androidx.core.app.ActivityCompat;

import com.calldorado.sdk.Calldorado;

import java.util.ArrayList;

public class MainActivity extends AppCompatActivity {

    private static final int PHONE_STATE_PERMISSION_CODE = 100;
    TextView termsPolicy;
    Button buttonContinue;
    private String url = "https://legal.appvestor.com/privacy-policy/";
    OverlayPermissionManager overlayPermissionManager;


    @SuppressLint("MissingInflatedId")
    @RequiresApi(api = Build.VERSION_CODES.M)
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
         overlayPermissionManager = new OverlayPermissionManager(this);
        if (overlayPermissionManager.isGranted() && ActivityCompat.checkSelfPermission(this, Manifest.permission.READ_PHONE_STATE) == PackageManager.PERMISSION_GRANTED) {
            secondActivity();
        }

        buttonContinue  = findViewById(R.id.buttonAccept);

        termsPolicy = findViewById(R.id.privacy_policy);
        termsPolicy.setOnClickListener(new View.OnClickListener() {
           @Override
           public void onClick(View view) {
               Intent intent = new Intent(MainActivity.this, WebViewActivity.class);
               intent.putExtra("url", url);
               startActivity(intent);

           }
       });


        buttonContinue.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Calldorado.acceptConditions(MainActivity.this, true);
                requestCdoPermissions();
            }
        });

    }

    @RequiresApi(api = Build.VERSION_CODES.M)
    private void requestCdoPermissions() {
        ArrayList<String> permissionList = new ArrayList<>();
        permissionList.add(Manifest.permission.READ_PHONE_STATE);
        permissionList.add(Manifest.permission.CALL_PHONE);
        permissionList.add(Manifest.permission.ANSWER_PHONE_CALLS);
        ActivityCompat.requestPermissions(this, permissionList.toArray(new String[0]), PHONE_STATE_PERMISSION_CODE);

    }

    @RequiresApi(api = Build.VERSION_CODES.M)
    public void checkDrawOverlayPermission(Context context) {
        // check if we already  have permission to draw over other apps
        if (Settings.canDrawOverlays(context)) {
            secondActivity();
        } else {
            Calldorado.acceptConditions(getApplicationContext(), true);
             overlayPermissionManager = new OverlayPermissionManager(this);
            if (!overlayPermissionManager.isGranted()) {
                overlayPermissionManager.requestOverlay();
            }
        }
    }



    private void secondActivity(){
        Intent intent = new Intent(MainActivity.this,SecondActivity.class);
        intent.setFlags(Intent.FLAG_ACTIVITY_NEW_TASK | Intent.FLAG_ACTIVITY_CLEAR_TASK);
        startActivity(intent);
    }

    @RequiresApi(api = Build.VERSION_CODES.M)
    @Override
    public void onRequestPermissionsResult(int requestCode, @NonNull String[] permissions, @NonNull int[] grantResults) {
        super.onRequestPermissionsResult(requestCode, permissions, grantResults);
        if (requestCode == PHONE_STATE_PERMISSION_CODE) {
            checkDrawOverlayPermission(getApplicationContext());
             overlayPermissionManager = new OverlayPermissionManager(this);
            if (overlayPermissionManager.isGranted()) {
                secondActivity();
            }
            } else {
               secondActivity();
            }
    }

}
