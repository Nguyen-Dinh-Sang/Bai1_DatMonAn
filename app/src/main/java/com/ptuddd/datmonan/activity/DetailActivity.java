package com.ptuddd.datmonan.activity;

import android.os.Bundle;
import android.os.PersistableBundle;
import android.util.Log;
import android.view.MenuItem;
import android.view.View;
import android.webkit.WebView;
import android.webkit.WebViewClient;
import android.widget.ImageView;

import androidx.annotation.NonNull;
import androidx.annotation.Nullable;
import androidx.appcompat.app.AppCompatActivity;

import com.ptuddd.datmonan.Cons;
import com.ptuddd.datmonan.R;

import java.util.Objects;

public class DetailActivity extends BaseActivity {
    private WebView webView;
    private ImageView iv_nointernet;

    @Override
    protected void onCreate(@Nullable Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_detail);
        setBackButtonToolbar(true);
        initView();
        initAction();

    }

    private void initAction() {
        if(isNetworkConnected()) {
            showDialogLoading("Vui lòng đợi trang web tải xong");
            Log.d("nhatnhat", "onCreate: " + getIntent().getStringExtra(Cons.url));
            webView.loadUrl(getIntent().getStringExtra(Cons.url));
            webView.setWebViewClient(new WebViewClient() {
                @Override
                public boolean shouldOverrideUrlLoading(WebView view, String url) {
                    view.loadUrl(url);
                    return true;
                }

                @Override
                public void onPageFinished(WebView view, String url) {
                    super.onPageFinished(view, url);
                    cancleDialogLoading();
                }
            });
        }else {
            webView.setVisibility(View.GONE);
            iv_nointernet.setVisibility(View.VISIBLE);
        }
    }

    private void initView() {
        webView = findViewById(R.id.wv);
        iv_nointernet = findViewById(R.id.iv_no_internet);
    }


    @Override
    public boolean onOptionsItemSelected(@NonNull MenuItem item) {
        if(item.getItemId()==android.R.id.home){
            finish();
        }
        return true;
    }
}
