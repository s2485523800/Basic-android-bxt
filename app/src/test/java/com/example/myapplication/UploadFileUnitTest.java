package com.example.myapplication;

import okhttp3.*;
import org.junit.Test;

import java.io.File;
import java.io.IOException;

public class UploadFileUnitTest {
    @Test
    public void uploadFile() {
        OkHttpClient okHttpClient = new OkHttpClient();
        File file1 = new File("E:\\OneDrive - bupt.edu.cn\\MyApplication\\app\\src\\main\\res\\mipmap-anydpi-v26\\ic_launcher.xml");
        File file2 = new File("E:\\OneDrive - bupt.edu.cn\\MyApplication\\app\\src\\main\\res\\mipmap-anydpi-v26\\ic_launcher_round.xml");
        MultipartBody multipartBody = new MultipartBody.Builder()
                .addFormDataPart("file1", file1.getName(), RequestBody.create(file1, MediaType.parse("text/xml")))
                .addFormDataPart("file2", file2.getName(), RequestBody.create(file2, MediaType.parse("text/xml"))).build();
        Request request = new Request.Builder().url("https://httpbin.org/post").post(multipartBody).build();
        Call call = okHttpClient.newCall(request);
        try {
            System.out.println(call.execute().body().string());
        } catch (IOException e) {
            throw new RuntimeException(e);
        }
    }
}
