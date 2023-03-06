package com.calldoradosdk.integration;

import android.annotation.SuppressLint;
import android.app.Application;
import android.content.BroadcastReceiver;
import android.content.IntentFilter;
import android.util.Log;

import androidx.localbroadcastmanager.content.LocalBroadcastManager;
import androidx.work.WorkManager;

import com.calldorado.sdk.Calldorado;
import com.calldorado.sdk.util.Constants;

public class MyApplication extends Application {

    BroadcastReceiver setDeleteUserDataReceiver = new SetDeleteUserDataReceiver();
    LocalBroadcastManager localBroadcastManager;
    IntentFilter intentFilter;

    @SuppressLint("UnsafeOptInUsageError")
    public void onCreate() {
        super.onCreate();

        androidx.work.Configuration myConfig = new androidx.work.Configuration.Builder()
                .setMinimumLoggingLevel(Log.INFO)
                .build();
        WorkManager.initialize(this, myConfig);
        Calldorado.startCalldorado(this);


        localBroadcastManager = LocalBroadcastManager.getInstance(this);
        intentFilter = new IntentFilter(Constants.CDO_DELETE_MY_DATA_INTENT);
        localBroadcastManager.registerReceiver(setDeleteUserDataReceiver, intentFilter);

    }


}
