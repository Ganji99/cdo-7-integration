package com.calldoradosdk.integration

import android.annotation.SuppressLint
import android.os.Bundle
import android.webkit.WebView
import android.webkit.WebViewClient
import androidx.appcompat.app.AppCompatActivity

class WebViewActivity : AppCompatActivity() {

    lateinit var mywebview: WebView

    @SuppressLint("MissingInflatedId", "SetJavaScriptEnabled")
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.web_view)
        mywebview = findViewById<WebView>(R.id.webView1)

        mywebview.settings.javaScriptEnabled = true
        mywebview.webViewClient = WebViewClient()
        val url = intent.getStringExtra("url")
        mywebview.loadUrl(url!!)
    }

    @Deprecated("Deprecated in Java")
    override fun onBackPressed() {
        if (mywebview.canGoBack()) {
            mywebview.goBack()
        } else {
            super.getOnBackPressedDispatcher()
            finish()
        }
    }
}