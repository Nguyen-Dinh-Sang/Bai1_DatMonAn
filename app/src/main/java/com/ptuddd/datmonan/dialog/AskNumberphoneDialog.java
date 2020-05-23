package com.ptuddd.datmonan.dialog;

import android.app.AlertDialog;
import android.content.Context;
import android.content.DialogInterface;
import android.text.InputType;
import android.text.TextUtils;
import android.widget.EditText;
import android.widget.Toast;

public class AskNumberphoneDialog {
    public static AskNumberphoneDialog instance;

    public static AskNumberphoneDialog getInstance() {
        if(instance==null)
            instance = new AskNumberphoneDialog();
        return instance;
    }
    public AskNumberphoneDialog(){

    }
    public void showDialog(final AskNumberphoneListener askNumberphoneListener, final Context context){
        final AlertDialog.Builder builder = new AlertDialog.Builder(context);
        builder.setTitle("Nhap vao so dien thoai");

        final EditText input = new EditText(context);
        input.setInputType(InputType.TYPE_CLASS_TEXT);
        builder.setView(input);
        builder.setPositiveButton("OK", new DialogInterface.OnClickListener() {
            @Override
            public void onClick(DialogInterface dialog, int which) {
                if(TextUtils.isEmpty(input.getText().toString())){
                    Toast.makeText(context, "vui long nhap vao sdt chinh xac", Toast.LENGTH_SHORT).show();
                }else {
                    askNumberphoneListener.onOkeClick(input.getText().toString());
                    dialog.cancel();
                }
            }
        });
        builder.setNegativeButton("Cancel", new DialogInterface.OnClickListener() {
            @Override
            public void onClick(DialogInterface dialog, int which) {
                dialog.cancel();
            }
        });

        builder.show();

    }

    public interface AskNumberphoneListener{
        void onOkeClick(String numberphone);
    }
}
