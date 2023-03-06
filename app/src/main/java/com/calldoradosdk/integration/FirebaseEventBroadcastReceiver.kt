package com.calldoradosdk.integration

import android.content.BroadcastReceiver
import android.content.Context
import android.content.Intent
import android.os.Bundle
import android.util.Log
import com.google.firebase.analytics.FirebaseAnalytics

//This class is for the UA events from Calldorado to be sent to Firebase for the UA(User Acquisition) team to invest in the app.
class FirebaseEventBroadcastReceiver : BroadcastReceiver() {
    override fun onReceive(context: Context, intent: Intent) {
        val action = intent.action
        if (action != null && action == "custom_firebase_event") {
            val mFirebaseAnalytics = FirebaseAnalytics.getInstance(context)
            val eventName = intent.getStringExtra("eventName")
            val imageName = intent.getStringExtra("imageName")
            val fullText = intent.getStringExtra("fullText")
            val eventType = intent.getStringExtra("eventType")
            if (mFirebaseAnalytics != null && eventName != null) {
                Log.d(
                    TAG, "logging firebase event.. eventName = " + eventName +
                            ", imageName = " + imageName + ", fullText = " + fullText + ", eventType = " + eventType
                )
                val params = Bundle()
                if (imageName != null) {
                    params.putString("image_name", imageName)
                }
                if (fullText != null) {
                    params.putString("full_text", fullText)
                }
                mFirebaseAnalytics.logEvent(eventName, params)
            }
        }
    }

    companion object {
        private const val TAG = "FirebaseEventB"
    }
}