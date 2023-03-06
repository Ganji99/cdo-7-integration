package com.calldoradosdk.integration

import android.os.Bundle
import android.view.View
import android.widget.Button
import androidx.appcompat.app.AppCompatActivity
import androidx.compose.animation.ExperimentalAnimationApi
import androidx.compose.material.ExperimentalMaterialApi
import androidx.compose.ui.ExperimentalComposeUiApi
import com.calldorado.sdk.Calldorado
import com.calldorado.sdk.Calldorado.showAftercall
import com.calldorado.sdk.Calldorado.showSettings

class SecondActivity : AppCompatActivity() {

    @OptIn(
        ExperimentalAnimationApi::class, ExperimentalMaterialApi::class,
        ExperimentalComposeUiApi::class
    )
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.second_activity)
        val settings = findViewById<Button>(R.id.settings)
        val lastCall = findViewById<Button>(R.id.latestcall)
        settings.setOnClickListener(View.OnClickListener { Calldorado.showSettings(this@SecondActivity) })
        lastCall.setOnClickListener(View.OnClickListener {
            // The below line of code will show an Aftercall screen containing information from the latest phone call.
            // If there is no call that has been made after the initialization of the Caller SDK then it will
            // show a toast message “no latest call information”.
            Calldorado.showAftercall(this@SecondActivity)
        })
    }

    override fun onBackPressed() {
        finish()
    }
}