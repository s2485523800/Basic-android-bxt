package com.example.Service;

import io.reactivex.rxjava3.core.Flowable;
import okhttp3.MultipartBody;
import okhttp3.ResponseBody;
import retrofit2.Call;
import retrofit2.http.*;

public interface UploadService {
    @POST("post")
    @Multipart
    Call<ResponseBody> upload(@Part MultipartBody.Part multipart);

    @GET
    Call<ResponseBody> download(@Url String url);

    @GET
    Flowable<ResponseBody> downloadWithRx(@Url String url);
}
