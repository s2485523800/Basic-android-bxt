package com.example.myapplication;

import android.nfc.Tag;
import android.util.Log;
import android.view.View;
import android.widget.Toast;
import androidx.appcompat.app.AppCompatActivity;
import android.os.Bundle;


import com.example.Service.HttpBinService;
import okhttp3.*;
import org.jetbrains.annotations.NotNull;
import retrofit2.Retrofit;

import java.io.IOException;

public class OkHttpL1Activity extends AppCompatActivity {
    OkHttpClient okHttpClient;
    private Retrofit retrofit;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_ok_http_l1);
        okHttpClient = new OkHttpClient();

         retrofit = new Retrofit.Builder().baseUrl("https://httpbin.org/").build();

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

    public void RetroFitPostAsync(View view){
        HttpBinService httpBinService = retrofit.create(HttpBinService.class);
        retrofit2.Call<ResponseBody> call = httpBinService.get("s2485", "fsdsf");
        Request request = call.request();
        call.enqueue(new retrofit2.Callback<ResponseBody>() {
            @Override
            public void onResponse(retrofit2.Call<ResponseBody> call, retrofit2.Response<ResponseBody> response) {
                if(response.isSuccessful()){
                    try {
                        Log.i("log",response.body().string()+"retrofit");
                    } catch (IOException e) {
                        throw new RuntimeException(e);
                    }
                }
            }

            @Override
            public void onFailure(retrofit2.Call<ResponseBody> call, Throwable t) {

            }
        });
    }
}