package com.example.os.crm.Contract.Util;

import android.os.Message;
import android.util.Log;

import com.example.os.crm.Common.UserInfo;
import com.squareup.okhttp.OkHttpClient;
import com.squareup.okhttp.Request;
import com.squareup.okhttp.Response;

import java.io.IOException;
import java.util.concurrent.TimeUnit;

/**
 * Created by OS on 2018/1/29.
 */

public class HeTongUtil {

    String dingdanbianhao;

    public void delHeTong(){
        new Thread(new Runnable() {
            @Override
            public void run() {
                String url = UserInfo.host + "/crm/delhetong/" + dingdanbianhao;
                OkHttpClient client = new OkHttpClient();
                client.setConnectTimeout(10, TimeUnit.SECONDS);
                Request request = new Request.Builder().url(url).build();
                Response response;
                String responseData;
                try{
                    response = client.newCall(request).execute();
                    responseData = response.body().string();
                    Message msg = new Message();
                    msg.what = 0;
                    msg.obj = responseData;
                    Log.d("serData", responseData);
                }
                catch(IOException e ){
                    //即使你能保证读取能成功，你也要做好读取错误后的处理，否则通不过编译
                    Log.e("SetData", "setData: 读取失败" );
                }
            }
        }).start();
    }

    public void setDingdanbianhao(String temp){
        this.dingdanbianhao = temp;
    }
}
