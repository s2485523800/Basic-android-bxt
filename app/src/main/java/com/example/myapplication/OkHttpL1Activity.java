package com.example.myapplication;

import android.nfc.Tag;
import android.util.Log;
import android.view.View;
import android.widget.Toast;
import androidx.appcompat.app.AppCompatActivity;
import android.os.Bundle;


import okhttp3.*;
import org.jetbrains.annotations.NotNull;

import java.io.IOException;

public class OkHttpL1Activity extends AppCompatActivity {
    OkHttpClient okHttpClient;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_ok_http_l1);
        okHttpClient = new OkHttpClient();
        findViewById(R.id.syncGet).setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                syncGet();
            }
        });
        findViewById(R.id.asyncGet).setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                asyncGet();
            }
        });
        findViewById(R.id.syncPost).setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                syncPost();
            }
        });
        findViewById(R.id.asyncPost).setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                asyncPost();
            }
        });
    }

    public void syncGet(){

        new Thread(){
            @Override
            public void run() {
                Request build = new Request.Builder()
                        .url("https://httpbin.org/get?a=1&b=2")
                        .build();
                Call call = okHttpClient.newCall(build);
                try {
                    Response response = call.execute();
                    Log.i("Tag",response.body().string());
                } catch (IOException e) {
                    throw new RuntimeException(e);
                }
            }
        }.start();

    }

    public void asyncGet(){
        new Thread(){
            @Override
            public void run() {
                Call call = okHttpClient.newCall(new Request.Builder().url("https://httpbin.org/get?a=1&b=2").build());
                call.enqueue(new Callback() {
                    @Override
                    public void onFailure(@NotNull Call call, @NotNull IOException e) {

                    }

                    @Override
                    public void onResponse(@NotNull Call call, @NotNull Response response) throws IOException {
                        if(response.isSuccessful()){
                            Log.i("tag",response.body().string()+"sync");
                        }
                    }
                });
            }
        }.start();
    }

    public void syncPost(){
    new Thread(){
        @Override
        public void run() {
            FormBody formBody = new FormBody.Builder().add("a", "1").add("b", "2").build();
            Request build = new Request.Builder()
                    .url("https://httpbin.org/post")
                    .post(formBody)
                    .build();
            Call call = okHttpClient.newCall(build);
            try {
                Response response = call.execute();
                Log.i("tag",response.body().string()+"sync");
            } catch (IOException e) {
                throw new RuntimeException(e);
            }
        }
    }.start();
    }

    public void asyncPost(){
        new Thread(){
            @Override
            public void run() {
                FormBody formBody = new FormBody.Builder().add("a", "1").add("b", "2").build();
                Request request = new Request.Builder().post(formBody)
                        .url("https://httpbin.org/post")
                        .build();
                Call call = okHttpClient.newCall(request);
                call.enqueue(new Callback() {
                    @Override
                    public void onFailure(@NotNull Call call, @NotNull IOException e) {

                    }

                    @Override
                    public void onResponse(@NotNull Call call, @NotNull Response response) throws IOException {
                        if(response.isSuccessful()){
                            Log.i("tag",response.body().string()+"async");
                        }
                    }
                });
            }
        }.start();
    }
}