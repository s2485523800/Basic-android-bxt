package com.example.myapplication;

import android.app.AlertDialog;
import android.view.View;
import androidx.appcompat.app.AppCompatActivity;
import android.os.Bundle;

public class AlertDialogActivity extends AppCompatActivity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_alert_dialog);
    }

    public void normDialog(View view) {
        AlertDialog alertDialog = new AlertDialog.Builder(this)
                .setTitle("提示")
                .setMessage("确定要登录吗")
                .setPositiveButton("确定", null)
                .setNegativeButton("取消", null)
                .create();
        alertDialog.show();
    }
}