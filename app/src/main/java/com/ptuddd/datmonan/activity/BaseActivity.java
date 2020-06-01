package com.ptuddd.datmonan.activity;

import android.app.ProgressDialog;
import android.content.Context;
import android.net.ConnectivityManager;
import android.os.Bundle;

import androidx.annotation.Nullable;
import androidx.appcompat.app.AppCompatActivity;

import java.util.Objects;

public class BaseActivity extends AppCompatActivity {
    private ProgressDialog dialog;

    @Override
    protected void onCreate(@Nullable Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
    }

    public boolean isNetworkConnected() {
        ConnectivityManager cm = (ConnectivityManager) getSystemService(Context.CONNECTIVITY_SERVICE);

        return cm.getActiveNetworkInfo() != null && cm.getActiveNetworkInfo().isConnected();
    }
    public void setBackButtonToolbar(boolean b){
        Objects.requireNonNull(getSupportActionBar()).setDisplayHomeAsUpEnabled(b);
    }

    public void showDialogLoading(String loadingmessage){
        dialog = ProgressDialog.show(this, "",
                loadingmessage, true);
    }
    public void cancleDialogLoading(){
        if(dialog.isShowing())
            dialog.cancel();
    }
}
