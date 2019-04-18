package com.hanks.retfoit.utils.http;


import java.util.concurrent.TimeUnit;

import okhttp3.OkHttpClient;
import okhttp3.logging.HttpLoggingInterceptor;
import retrofit2.Retrofit;
import retrofit2.adapter.rxjava.RxJavaCallAdapterFactory;
import retrofit2.converter.gson.GsonConverterFactory;


/**
 * @author 侯建军 47466436@qq.com
 * @date 2018/6/4
 * @description 请填写描述
 */
public class HttpAdapter {

    /**
     * 所有接口api
     */
    private static HttpApis apis;

    /**
     * 所有接口的单例
     *
     * @return
     */
    public static HttpApis getApis() {
        if (apis == null) {
            apis = initApis(HttpApis.Url2);
        }
        return apis;
    }

    /**
     * 初始化okhttp
     *
     * @param baseUrl
     * @return
     */
    private static HttpApis initApis(String baseUrl) {
        OkHttpClient client = new OkHttpClient();
        client = client.newBuilder()
                .addInterceptor(new HttpLoggingInterceptor().setLevel(HttpLoggingInterceptor.Level.BODY))
                .addInterceptor(new MoreBaseUrlInterceptor()) //处理多BaseUrl,添加应用拦截器
                .connectTimeout(5, TimeUnit.SECONDS)
                .readTimeout(30, TimeUnit.SECONDS)
                .writeTimeout(30, TimeUnit.SECONDS)
                .build();
        Retrofit retrofit = new Retrofit.Builder()
                .baseUrl(baseUrl)
                .addConverterFactory(GsonConverterFactory.create())
                .addCallAdapterFactory(RxJavaCallAdapterFactory.create())
                .client(client)
                .build();
        return retrofit.create(HttpApis.class);
    }


}
