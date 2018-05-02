package com.example.os.crm.HttpUtil;

import okhttp3.OkHttpClient;
import okhttp3.Request;

/**
 * Created by OS on 2018/1/29.
 */

public class HttpGet {

    public void getData(String url, okhttp3.Callback callback){
        OkHttpClient client = new OkHttpClient();
        Request request = new Request.Builder()
                .url(url)
                .build();
        client.newCall(request).enqueue(callback);
    }
}
