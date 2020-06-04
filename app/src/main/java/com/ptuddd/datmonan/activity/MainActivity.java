package com.ptuddd.datmonan.activity;

import androidx.annotation.Nullable;
import androidx.annotation.RequiresApi;
import androidx.appcompat.app.AppCompatActivity;
import androidx.core.app.ActivityCompat;
import androidx.core.content.ContextCompat;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import android.Manifest;
import android.app.AlertDialog;
import android.content.DialogInterface;
import android.content.Intent;
import android.content.pm.PackageManager;
import android.net.Uri;
import android.os.Build;
import android.os.Bundle;
import android.provider.Settings;
import android.telephony.SmsManager;
import android.text.InputType;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.Toast;

import com.ptuddd.datmonan.Cons;
import com.ptuddd.datmonan.R;
import com.ptuddd.datmonan.adapter.AdapterMonAn;
import com.ptuddd.datmonan.data.MonAn;
import com.ptuddd.datmonan.dialog.AskNumberphoneDialog;

import java.util.ArrayList;
import java.util.List;

public class MainActivity extends AppCompatActivity implements AdapterMonAn.OnItemClickListener {
    private static final int MY_PERMISSIONS_REQUEST_SEND_SMS =1111 ;
    private static final int REQUEST_PERMISSION_SETTING = 1112;
    private RecyclerView recyclerView;
    private AdapterMonAn adapter;
    private List<MonAn> listMonAn;
    private Button btn_submit;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        initView();
        initEvent();
    }

    private void initEvent() {
        btn_submit.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                boolean hasOne=false;
                for (MonAn m:listMonAn) {
                    if(m.isChoose()){
                       hasOne=true;
                       break;
                    }
                }
                if(hasOne) {
                    requestPermission();
                }else {
                    Toast.makeText(MainActivity.this, "Vui long chon it nhat 1 mon", Toast.LENGTH_SHORT).show();
                }
            }
        });
    }

    private void initView() {
        recyclerView = findViewById(R.id.rv_main);
        btn_submit = findViewById(R.id.btn_submit);
        listMonAn = new ArrayList<>();
        listMonAn.add(new MonAn("Mì Kim chi bò","10.7567109,106.678678","http://micaysasin.vn/menu/mi-kim-chi-bo-71.html",R.drawable.kimchibomy));
        listMonAn.add(new MonAn("Mì Kim cho hải sản ","10.7567109,106.678678","http://micaysasin.vn/menu/mi-kim-chi-hai-san-67.html",R.drawable.kimchihaisan));
        listMonAn.add(new MonAn("Cơm gà xối mỡ","10.7481871,106.6987255","http://tamkygroup.com/mon-bo/bo-luc-lac.html",R.drawable.com_ga_xoi_mo));
        listMonAn.add(new MonAn("Mì gói xào bò","10.7481871,106.6987255","http://tamkygroup.com/mi-goi/mi-goi-xao-bo.html",R.drawable.migoixaobo));
        listMonAn.add(new MonAn("Mì gói xào hải sản","10.7481871,106.6987255","http://tamkygroup.com/mi-goi/mi-goi-xao-hai-san.html",R.drawable.migoixaohaisan));
        listMonAn.add(new MonAn("Cơm bò bít tết","10.7481871,106.6987255","http://tamkygroup.com/com-mon-bo/com-bo-bit-tet.html",R.drawable.combobittet));
        listMonAn.add(new MonAn("Cơm bò xào thập cẩm","10.7481871,106.6987255","http://tamkygroup.com/com-mon-bo/com-bo-xao-thap-cam.html",R.drawable.comboxaothapcam));
        listMonAn.add(new MonAn("Phở gà","10.7648944,106.6855169","http://phohung.com.vn/pho-hung/pho-ga/",R.drawable.pho_ga));

        RecyclerView recyclerView = findViewById(R.id.rv_main);
        recyclerView.setLayoutManager(new LinearLayoutManager(this));
        adapter = new AdapterMonAn(this, listMonAn);
        recyclerView.setAdapter(adapter);
    }

    @Override
    public void onLocationClick(String location) {
        Intent browserIntent = new Intent(Intent.ACTION_VIEW, Uri.parse("https://www.google.com/maps/search/?api=1&query="+location));
        startActivity(browserIntent);
//        Uri uri = Uri.parse(location);
//        Intent intent = new Intent(android.content.Intent.ACTION_VIEW, uri);
//        intent.setPackage("com.google.android.apps.maps");
//        startActivity(intent);
    }

    @Override
    public void onWebsiteClick(String website) {
        Intent intent = new Intent(this,DetailActivity.class);
        intent.putExtra(Cons.url,website);
        startActivity(intent);
//        Intent browserIntent = new Intent(Intent.ACTION_VIEW, Uri.parse(website));
//        startActivity(browserIntent);
    }


    private void requestPermission() {
        if (ContextCompat.checkSelfPermission(this,
                Manifest.permission.SEND_SMS)
                != PackageManager.PERMISSION_GRANTED) {
            ActivityCompat.requestPermissions(this,
                    new String[]{Manifest.permission.SEND_SMS},
                    MY_PERMISSIONS_REQUEST_SEND_SMS);
        }else{
            sendSMS();
        }
    }
    @RequiresApi(api = Build.VERSION_CODES.M)
    @Override
    public void onRequestPermissionsResult(int requestCode,String permissions[], int[] grantResults) {
//        switch (requestCode) {
//            case MY_PERMISSIONS_REQUEST_SEND_SMS: {
//                if (grantResults.length > 0
//                        && grantResults[0] == PackageManager.PERMISSION_GRANTED) {
//                    sendSMS();
//                  }
//            }
//        }
        if (requestCode == MY_PERMISSIONS_REQUEST_SEND_SMS) {
            for (int i = 0, len = permissions.length; i < len; i++) {
                String permission = permissions[i];
                if (grantResults[i] == PackageManager.PERMISSION_DENIED) {
                    boolean showRationale = shouldShowRequestPermissionRationale( permission );
                    if (! showRationale) {
                        final android.app.AlertDialog.Builder builder = new AlertDialog.Builder(this);
                        builder.setTitle("Quyền này là cần thiết để gửi sms");
                        builder.setPositiveButton("Đồng Ý", new DialogInterface.OnClickListener() {
                            @Override
                            public void onClick(DialogInterface dialog, int which) {
                                Intent intent = new Intent(Settings.ACTION_APPLICATION_DETAILS_SETTINGS);
                                Uri uri = Uri.fromParts("package", getPackageName(), null);
                                intent.setData(uri);
                                startActivityForResult(intent, REQUEST_PERMISSION_SETTING);
                            }
                        });
                        builder.setNegativeButton("Hủy", new DialogInterface.OnClickListener() {
                            @Override
                            public void onClick(DialogInterface dialog, int which) {
                                dialog.cancel();
                            }
                        });

                        builder.show();
                    } else if (Manifest.permission.SEND_SMS.equals(permission)) {
                        final android.app.AlertDialog.Builder builder = new AlertDialog.Builder(this);
                        builder.setTitle("Quyền này là cần thiết để gửi sms");
                        builder.setPositiveButton("Đồng Ý", new DialogInterface.OnClickListener() {
                            @Override
                            public void onClick(DialogInterface dialog, int which) {
                                requestPermission();
                            }
                        });
                        builder.setNegativeButton("Hủy", new DialogInterface.OnClickListener() {
                            @Override
                            public void onClick(DialogInterface dialog, int which) {
                                dialog.cancel();
                            }
                        });
                    }
                }else {
                    sendSMS();
                }
            }
        }else {
            requestPermission();
        }

    }

    private void sendSMS() {
        AskNumberphoneDialog.getInstance().showDialog(new AskNumberphoneDialog.AskNumberphoneListener() {
            @Override
            public void onOkeClick(String numberphone) {
                    String sms = "";
                    for (MonAn m : listMonAn) {
                        if (m.isChoose()) {
                            sms += m.getTenMonAn() + "\n";
                        }
                    }

                    SmsManager
                            .getDefault()
                            .sendTextMessage(numberphone, null,
                                    sms,
                                    null, null);
                Toast.makeText(MainActivity.this, "Đã gửi sms đến sdt :"+numberphone, Toast.LENGTH_SHORT).show();
            }
        },this);



    }

}
