package com.calldoradosdk.integration

import android.annotation.SuppressLint
import android.app.Application
import android.content.BroadcastReceiver
import android.content.IntentFilter
import android.util.Log
import androidx.compose.animation.ExperimentalAnimationApi
import androidx.compose.material.ExperimentalMaterialApi
import androidx.compose.ui.ExperimentalComposeUiApi
import androidx.localbroadcastmanager.content.LocalBroadcastManager
import androidx.work.Configuration
import androidx.work.WorkManager
import com.calldorado.sdk.Calldorado
import com.calldorado.sdk.Calldorado.startCalldorado
import com.calldorado.sdk.util.Constants

class MyApplication : Application() {
    var setDeleteUserDataReceiver: BroadcastReceiver = SetDeleteUserDataReceiver()
    var localBroadcastManager: LocalBroadcastManager? = null
    var intentFilter: IntentFilter? = null

    @OptIn(
        ExperimentalAnimationApi::class, ExperimentalMaterialApi::class,
        ExperimentalComposeUiApi::class
    )
    @SuppressLint("UnsafeOptInUsageError")
    override fun onCreate() {
        super.onCreate()
        val myConfig = Configuration.Builder()
            .setMinimumLoggingLevel(Log.INFO)
            .build()
        WorkManager.initialize(this, myConfig)

        //It is super important to initialize the Caller SDK in the Application class Main Thread and Caller SDK should only be initialized once
        Calldorado.startCalldorado(this)

        localBroadcastManager = LocalBroadcastManager.getInstance(this)
        intentFilter = IntentFilter(Constants.CDO_DELETE_MY_DATA_INTENT)
        localBroadcastManager!!.registerReceiver(setDeleteUserDataReceiver, intentFilter!!)
    }
}