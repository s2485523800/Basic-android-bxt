package com.example.Service;

import com.example.bean.login;
import io.reactivex.rxjava3.core.Flowable;
import okhttp3.ResponseBody;
import retrofit2.Call;
import retrofit2.http.*;

public interface WanAndroidService {
    @POST("user/login")
    @FormUrlEncoded
    Call<login> login(@Field("username") String username, @Field("password") String password);

    @POST("user/login")
    @FormUrlEncoded
    Flowable<login> loginUseRx(@Field("username")String username,@Field("password") String password);

    @GET("lg/collect/list/{pageNum}/json")
    Flowable<ResponseBody> getArticle(@Path("pageNum") int pageNum);
}
