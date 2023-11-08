package com.example.myapplication;

import com.example.Service.WanAndroidService;
import com.example.bean.login;
import hu.akarnokd.rxjava3.retrofit.RxJava3CallAdapterFactory;
import io.reactivex.rxjava3.core.Flowable;
import io.reactivex.rxjava3.core.Scheduler;
import io.reactivex.rxjava3.functions.Consumer;
import io.reactivex.rxjava3.functions.Function;
import io.reactivex.rxjava3.plugins.RxJavaPlugins;
import io.reactivex.rxjava3.schedulers.Schedulers;
import okhttp3.*;
import org.jetbrains.annotations.NotNull;
import org.junit.Test;
import org.reactivestreams.Publisher;
import retrofit2.Call;
import retrofit2.Response;
import retrofit2.Retrofit;
import retrofit2.converter.gson.GsonConverterFactory;

import java.io.IOException;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

public class WanAndroidTest {
    Retrofit retrofit=new Retrofit
            .Builder()
            .baseUrl("https://www.wanandroid.com")
            .addConverterFactory(GsonConverterFactory.create())
            .build();
    WanAndroidService wanAndroidService=retrofit.create(WanAndroidService.class);
    Map<String,List<Cookie>> cookies=new HashMap<>();
    Retrofit retrofitWithRx=new Retrofit.Builder()
            .baseUrl("https://www.wanandroid.com")
            .client(new OkHttpClient.Builder()
                    .cookieJar(new CookieJar() {
                        @Override
                        public void saveFromResponse(@NotNull HttpUrl httpUrl, @NotNull List<Cookie> list) {
                            cookies.put(httpUrl.host(),list);
                        }

                        @NotNull
                        @Override
                        public List<Cookie> loadForRequest(@NotNull HttpUrl httpUrl) {
                            List<Cookie> cookieList = cookies.get(httpUrl.host());
                            return cookieList==null?new ArrayList<>():cookieList;
                        }
                    }).build())
            .addConverterFactory(GsonConverterFactory.create())
            .addCallAdapterFactory(RxJava3CallAdapterFactory.create())
            .build();
    WanAndroidService wanAndroidServiceWithRx=retrofitWithRx.create(WanAndroidService.class);
    @Test
    public void login() throws IOException {
        Call<login> call = wanAndroidService.login("s10086", "123123");
        Response<login> response = call.execute();
        System.out.println(response.body());

    }

    @Test
    public void loginUseRx(){
        wanAndroidServiceWithRx.loginUseRx("s10086", "123123")
                .flatMap(new Function<login, Publisher<ResponseBody>>() {
                    @Override
                    public Publisher<ResponseBody> apply(login login) throws Throwable {
                        return wanAndroidServiceWithRx.getArticle(0);
                    }
                })
                .observeOn(Schedulers.io())
                .subscribeOn(Schedulers.newThread())
                .subscribe(new Consumer<ResponseBody>() {
                    @Override
                    public void accept(ResponseBody responseBody) throws Throwable {
                        System.out.println(responseBody.string());
                    }
                });
        while(true){}

    }
}
