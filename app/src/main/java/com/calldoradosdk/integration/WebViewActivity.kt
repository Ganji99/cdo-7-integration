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

        mywebview.getSettings().javaScriptEnabled = true
        mywebview.setWebViewClient(WebViewClient())
        val url = intent.getStringExtra("url")
        mywebview.loadUrl(url!!)
    }

    override fun onBackPressed() {
        if (mywebview.canGoBack()) {
            mywebview.goBack()
        } else {
            super.onBackPressed()
            finish()
        }
    }
}