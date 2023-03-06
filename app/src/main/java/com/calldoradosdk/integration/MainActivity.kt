package com.calldoradosdk.integration

import android.Manifest
import android.annotation.SuppressLint
import android.content.Context
import android.content.Intent
import android.content.pm.PackageManager
import android.os.Build
import android.os.Bundle
import android.provider.Settings
import android.view.View
import android.widget.Button
import android.widget.TextView
import androidx.annotation.RequiresApi
import androidx.appcompat.app.AppCompatActivity
import androidx.core.app.ActivityCompat
import com.calldorado.sdk.Calldorado.acceptConditions

class MainActivity : AppCompatActivity() {
    var termsPolicy: TextView? = null
    var buttonContinue: Button? = null
    private val url = "https://legal.appvestor.com/privacy-policy/"
    var overlayPermissionManager: OverlayPermissionManager? = null

    @SuppressLint("MissingInflatedId")
    @RequiresApi(api = Build.VERSION_CODES.M)
    override fun onCreate(savedInstanceState: Bundle?) {

        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_main)
        overlayPermissionManager = OverlayPermissionManager(this)
        if (overlayPermissionManager!!.isGranted && ActivityCompat.checkSelfPermission(
                this,
                Manifest.permission.READ_PHONE_STATE
            ) == PackageManager.PERMISSION_GRANTED
        ) {
            secondActivity()
        }
        val buttonContinue = findViewById<Button>(R.id.buttonAccept)
        val termsPolicy = findViewById<TextView>(R.id.privacy_policy)
        termsPolicy.setOnClickListener(View.OnClickListener {
            val intent = Intent(this@MainActivity, WebViewActivity::class.java)
            intent.putExtra("url", url)
            startActivity(intent)
        })
        buttonContinue.setOnClickListener(View.OnClickListener {
            acceptConditions(this@MainActivity, true)
            requestCdoPermissions()
        })
    }

    @RequiresApi(api = Build.VERSION_CODES.M)
    private fun requestCdoPermissions() {
        val permissionList = ArrayList<String>()
        permissionList.add(Manifest.permission.READ_PHONE_STATE)
        permissionList.add(Manifest.permission.CALL_PHONE)
        permissionList.add(Manifest.permission.ANSWER_PHONE_CALLS)
        ActivityCompat.requestPermissions(
            this,
            permissionList.toTypedArray(),
            PHONE_STATE_PERMISSION_CODE
        )
    }

    @RequiresApi(api = Build.VERSION_CODES.M)
    fun checkDrawOverlayPermission(context: Context?) {
        // check if we already  have permission to draw over other apps
        if (Settings.canDrawOverlays(context)) {
            secondActivity()
        } else {
            acceptConditions(applicationContext, true)
            overlayPermissionManager = OverlayPermissionManager(this)
            if (!overlayPermissionManager!!.isGranted) {
                overlayPermissionManager!!.requestOverlay()
            }
        }
    }

    private fun secondActivity() {
        val intent = Intent(this@MainActivity, SecondActivity::class.java)
        intent.flags = Intent.FLAG_ACTIVITY_NEW_TASK or Intent.FLAG_ACTIVITY_CLEAR_TASK
        startActivity(intent)
    }

    @RequiresApi(api = Build.VERSION_CODES.M)
    override fun onRequestPermissionsResult(
        requestCode: Int,
        permissions: Array<String>,
        grantResults: IntArray
    ) {
        super.onRequestPermissionsResult(requestCode, permissions, grantResults)
        if (requestCode == PHONE_STATE_PERMISSION_CODE) {
            checkDrawOverlayPermission(applicationContext)
            overlayPermissionManager = OverlayPermissionManager(this)
            if (overlayPermissionManager!!.isGranted) {
                secondActivity()
            }
        } else {
            secondActivity()
        }
    }

    companion object {
        private const val PHONE_STATE_PERMISSION_CODE = 100
    }
}