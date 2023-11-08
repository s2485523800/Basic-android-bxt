package com.example.myapplication;

import android.app.AlertDialog;
import android.content.DialogInterface;
import android.view.View;
import androidx.appcompat.app.AppCompatActivity;
import android.os.Bundle;

public class SingleChoiceActivity extends AppCompatActivity {
    private final String[] strArr=new String[]{"小","默认","大"};

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_single_choice);
    }

    public void showSingleChoiceDialog(View view) {
        new AlertDialog.Builder(this)
                .setTitle("选择字体大小")
                .setSingleChoiceItems(strArr, 0, new DialogInterface.OnClickListener() {
                    @Override
                    public void onClick(DialogInterface dialog, int which) {

                    }
                })
                .setPositiveButton("确定",null)
                .setNegativeButton("取消",null)
                .create().show();
    }
}