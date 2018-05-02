package com.example.os.crm.Receiver;

import android.content.BroadcastReceiver;
import android.content.Context;
import android.content.Intent;
import android.util.Log;
import android.widget.Toast;

import com.example.os.crm.Common.UserInfo;
import com.example.os.crm.HttpUtil.HttpPost;

import org.json.JSONObject;

import java.io.IOException;

import okhttp3.Call;
import okhttp3.Callback;
import okhttp3.FormBody;

public class AlartManagerReceiver extends BroadcastReceiver {
    @Override
    public void onReceive(Context context, Intent intent) {
        Toast.makeText(context, intent.getStringExtra("msg"), Toast.LENGTH_SHORT).show();
        Log.i("----",intent.getStringExtra("msg"));
//        Login(context,"1","1");
    }

    private void Login(final Context context, String username, String password){
        String url = UserInfo.host + "/crm/login";

        FormBody.Builder builder = new FormBody.Builder()
                .add("username",username)
                .add("password",password);
        new HttpPost().postData(url, builder, new Callback() {
            @Override
            public void onFailure(Call call, IOException e) {
                Log.i("----","22222");
            }

            @Override
            public void onResponse(Call call, okhttp3.Response response) {
                try{
                    JSONObject jsonObject = new JSONObject(response.body().string());
                    if(jsonObject.getInt("error") == 1){
                        Log.i("----","22222");
                    }else{
                        Log.i("----","22222");
                    }
                }catch (Exception e){
                    e.printStackTrace();
                }
            }
        });
    }
}
