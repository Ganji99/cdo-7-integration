package com.calldoradosdk.integration;

import android.annotation.SuppressLint;
import android.os.Bundle;
import android.webkit.WebViewClient;

import androidx.appcompat.app.AppCompatActivity;

public class WebViewActivity extends AppCompatActivity {
    android.webkit.WebView webView;


    @SuppressLint("MissingInflatedId")
    protected void onCreate(Bundle savedInstanceState) {

        super.onCreate(savedInstanceState);
        setContentView(R.layout.web_view);
        webView = findViewById(R.id.webView1);
        webView.getSettings().setJavaScriptEnabled(true);
        webView.setWebViewClient(new WebViewClient());

        String url = getIntent().getStringExtra("url");
        webView.loadUrl(url);

    }

    @Override
    public void onBackPressed() {
        if (webView.canGoBack()) {
            webView.goBack();
        } else {
            super.onBackPressed();
            finish();
        }
    }
}
