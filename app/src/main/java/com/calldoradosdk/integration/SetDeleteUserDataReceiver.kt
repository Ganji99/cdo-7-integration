package com.calldoradosdk.integration

import android.content.BroadcastReceiver
import android.content.Context
import android.content.Intent
import com.calldorado.sdk.Calldorado.deleteAppData
import com.calldorado.sdk.util.Constants

class SetDeleteUserDataReceiver : BroadcastReceiver() {
    override fun onReceive(context: Context, intent: Intent) {
        if (intent.action === Constants.CDO_DELETE_MY_DATA_INTENT) {
            //Stop your own third parties here if you need to
            deleteAppData()
        }
    }
}