package com.example.Service;


import okhttp3.ResponseBody;
import retrofit2.Call;
import retrofit2.http.*;

public interface HttpBinService {
    @GET("get")
    Call<ResponseBody> get(@Query("username") String username,@Query("password") String password);

    @POST("post")
    @FormUrlEncoded
    Call<ResponseBody> post(@Field("username") String username, @Field("password") String password);
}
