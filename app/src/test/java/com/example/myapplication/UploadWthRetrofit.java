package com.example.myapplication;

import com.example.Service.UploadService;
import hu.akarnokd.rxjava3.retrofit.RxJava3CallAdapterFactory;
import io.reactivex.rxjava3.functions.Consumer;
import io.reactivex.rxjava3.functions.Function;
import okhttp3.MediaType;
import okhttp3.MultipartBody;
import okhttp3.RequestBody;
import okhttp3.ResponseBody;
import org.junit.Test;
import retrofit2.Call;
import retrofit2.Response;
import retrofit2.Retrofit;
import retrofit2.converter.gson.GsonConverterFactory;

import java.io.File;
import java.io.FileOutputStream;
import java.io.IOException;
import java.io.InputStream;

public class UploadWthRetrofit {
    Retrofit retrofit=new Retrofit
            .Builder()
            .baseUrl("https://www.httpbin.org")
            .addConverterFactory(GsonConverterFactory.create())
            .build();
    Retrofit retrofitWithRx=new Retrofit
            .Builder()
            .baseUrl("https://www.httpbin.org")
            .addConverterFactory(GsonConverterFactory.create())
            .addCallAdapterFactory(RxJava3CallAdapterFactory.create())
            .build();
    UploadService uploadService=retrofit.create(UploadService.class);
    UploadService uploadServiceWithRx=retrofitWithRx.create(UploadService.class);
    @Test
    public void upload() throws IOException {
        File file=new File("E:\\OneDrive - bupt.edu.cn\\MyApplication\\app\\src\\main\\res\\layout\\activity_alert_dialog.xml");
        MultipartBody.Part data = MultipartBody.Part.createFormData("file", file.getName(), RequestBody.create(file, MediaType.parse("text/xml")));
        Call<ResponseBody> call = uploadService.upload(data);
        Response<ResponseBody> response = call.execute();
        System.out.println(response.body().string());
    }

    @Test
    public void download() throws IOException {
        Response<ResponseBody> response = uploadService.download("https://bistu-disaster.oss-cn-beijing.aliyuncs.com/background2.jpeg")
                .execute();
        InputStream inputStream = response.body().byteStream();
        FileOutputStream fileOutputStream = new FileOutputStream("E:\\OneDrive - bupt.edu.cn\\MyApplication\\app\\src\\main\\res\\drawable\\static.jpeg");
        int len=0;
        byte[] buffer=new byte[4096];
        while((len=inputStream.read(buffer))!=-1){
            fileOutputStream.write(buffer,0,len);
        }
        fileOutputStream.close();
        inputStream.close();
    }
    @Test
    public void downloadWithRx(){
        uploadServiceWithRx.downloadWithRx("https://bistu-disaster.oss-cn-beijing.aliyuncs.com/background2.jpeg")
                .map(new Function<ResponseBody, File>() {
                    @Override
                    public File apply(ResponseBody responseBody) throws Throwable {
                        InputStream inputStream = responseBody.byteStream();
                        File file=new File("E:\\OneDrive - bupt.edu.cn\\MyApplication\\app\\src\\main\\\\res\\drawable\\testDownload.jpeg");
                        FileOutputStream fileOutputStream = new FileOutputStream(file);
                        int len=0;
                        byte[] buffer=new byte[4096];
                        while((len=inputStream.read(buffer))!=-1){
                            fileOutputStream.write(buffer,0,len);
                        }
                        fileOutputStream.close();
                        inputStream.close();
                        return file;
                    }
                }).subscribe(new Consumer<File>() {
                    @Override
                    public void accept(File file) throws Throwable {
                        System.out.println(file.getName());
                    }
                });
        while (true){}
    }
}
