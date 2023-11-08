package com.example.myapplication;

import android.view.View;
import android.widget.Toast;
import androidx.appcompat.app.AppCompatActivity;
import android.os.Bundle;

import static com.example.myapplication.R.id.*;

public class ButtonActivity extends AppCompatActivity implements View.OnClickListener {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_button);
        findViewById(R.id.btn1).setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
//                Toast.makeText(this,"1111",Toast.LENGTH_SHORT).show();
                Toast.makeText(ButtonActivity.this,"111",Toast.LENGTH_SHORT).show();
            }
        });
        findViewById(R.id.btn2).setOnClickListener(this);
    }

    public void click1(View view) {
        Toast.makeText(this,"hello world",Toast.LENGTH_SHORT).show();
    }

    @Override
    public void onClick(View v) {
        if(v.getId()==R.id.btn2){
            Toast.makeText(this,"2222",Toast.LENGTH_SHORT).show();
        }
    }
}