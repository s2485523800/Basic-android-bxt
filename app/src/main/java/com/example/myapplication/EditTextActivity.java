package com.example.myapplication;

import android.view.View;
import android.widget.EditText;
import android.widget.Toast;
import androidx.appcompat.app.AppCompatActivity;
import android.os.Bundle;

public class EditTextActivity extends AppCompatActivity {
    EditText password;
    EditText username;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_edit_text);
        username=findViewById(R.id.username);
        password=findViewById(R.id.password);
    }

    public void login(View view) {

        Toast.makeText(this,password.getText().toString(),Toast.LENGTH_SHORT).show();
    }
}