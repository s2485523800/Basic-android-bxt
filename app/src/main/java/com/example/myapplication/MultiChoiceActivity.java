package com.example.myapplication;

import android.app.AlertDialog;
import android.content.DialogInterface;
import android.view.View;
import android.widget.Toast;
import androidx.appcompat.app.AppCompatActivity;
import android.os.Bundle;

public class MultiChoiceActivity extends AppCompatActivity {

    private String[] hobbyArr=new String[] {"唱歌","跳舞","篮球","...."};
    private boolean[] checkArr=new boolean[] {false,false,false,false};
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_multi_choice);
    }

    public void showMultiChoiceDialog(View view) {
        new AlertDialog.Builder(this)
                .setTitle("兴趣爱好")
                .setMultiChoiceItems(hobbyArr,  checkArr, new DialogInterface.OnMultiChoiceClickListener() {
                    @Override
                    public void onClick(DialogInterface dialog, int which, boolean isChecked) {
                        checkArr[which]=isChecked;
                    }
                })
                .setPositiveButton("确定", new DialogInterface.OnClickListener() {
                    @Override
                    public void onClick(DialogInterface dialog, int which) {
                        StringBuffer stringBuffer = new StringBuffer();
                        for (int i = 0; i < checkArr.length; i++) {
                            if(checkArr[i]){
                                stringBuffer.append(hobbyArr[i]).append(" ");
                            }
                        }
                        Toast.makeText(MultiChoiceActivity.this, stringBuffer.toString(), Toast.LENGTH_SHORT).show();
                    }
                })
                .setNegativeButton("取消",null)
                .create()
                .show();
    }
}