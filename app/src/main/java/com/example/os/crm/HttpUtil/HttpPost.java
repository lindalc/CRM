package com.example.os.crm.HttpUtil;

import java.util.concurrent.TimeUnit;

import okhttp3.Callback;
import okhttp3.FormBody;
import okhttp3.MultipartBody;
import okhttp3.OkHttpClient;
import okhttp3.Request;
import okhttp3.RequestBody;

/**
 * Created by OS on 2018/2/5.
 */

public class HttpPost {

    public void postData(String url ,MultipartBody.Builder builder, okhttp3.Callback callback){
        RequestBody requestBody = builder.build();
        final Request request = new Request.Builder()
                .url(url)
                .post(requestBody)
                .build();
        final OkHttpClient.Builder httpBuilder = new OkHttpClient.Builder();
        OkHttpClient okHttpClient = httpBuilder
                //设置超时
                .connectTimeout(30, TimeUnit.SECONDS)
                .writeTimeout(30, TimeUnit.SECONDS)
                .build();
        okHttpClient.newCall(request).enqueue(callback);
    }

    public void postData(String url , FormBody.Builder builder, okhttp3.Callback callback){
        RequestBody requestBody = builder.build();
        final Request request = new Request.Builder()
                .url(url)
                .post(requestBody)
                .build();
        final OkHttpClient.Builder httpBuilder = new OkHttpClient.Builder();
        OkHttpClient okHttpClient = httpBuilder
                //设置超时
                .connectTimeout(30, TimeUnit.SECONDS)
                .writeTimeout(30, TimeUnit.SECONDS)
                .build();
        okHttpClient.newCall(request).enqueue(callback);
    }

}
