package com.calldoradosdk.integration;

import android.content.BroadcastReceiver;
import android.content.Context;
import android.content.Intent;

import com.calldorado.sdk.Calldorado;
import com.calldorado.sdk.util.Constants;

public class SetDeleteUserDataReceiver extends BroadcastReceiver {
    @Override
    public void onReceive(Context context, Intent intent) {
        if (intent.getAction() == Constants.CDO_DELETE_MY_DATA_INTENT) {
            //Stop your own third parties here if you need to
            Calldorado.deleteAppData();
        }
    }
}
