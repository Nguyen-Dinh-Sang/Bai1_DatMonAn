package com.ptuddd.datmonan;

import android.os.Bundle;
import android.os.PersistableBundle;
import android.util.Log;
import android.webkit.WebView;
import android.webkit.WebViewClient;

import androidx.annotation.Nullable;
import androidx.appcompat.app.AppCompatActivity;

public class DetailActivity extends AppCompatActivity {
    private WebView webView;

    @Override
    protected void onCreate(@Nullable Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_detail);
        webView = findViewById(R.id.wv);
        Log.d("nhatnhat", "onCreate: "+getIntent().getStringExtra(Cons.url));
        webView.loadUrl(getIntent().getStringExtra(Cons.url));
        webView.setWebViewClient(new WebViewClient() {
            @Override
            public boolean shouldOverrideUrlLoading(WebView view, String url) {
                view.loadUrl(url);
                return true;
            }
        });
    }
}
