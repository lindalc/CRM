package com.example.os.crm.ui.activity;

import android.content.DialogInterface;
import android.content.Intent;
import android.os.Handler;
import android.os.Message;
import android.support.v7.app.AlertDialog;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.util.Log;
import android.view.View;
import android.widget.Button;
import android.widget.TextView;

import com.example.os.crm.Common.UserInfo;
import com.example.os.crm.R;
import com.squareup.okhttp.FormEncodingBuilder;
import com.squareup.okhttp.OkHttpClient;
import com.squareup.okhttp.Request;
import com.squareup.okhttp.RequestBody;
import com.squareup.okhttp.Response;

import org.json.JSONArray;
import org.json.JSONObject;

import java.io.IOException;

public class HuiKuanWeiShenPiActivity extends AppCompatActivity implements View.OnClickListener{

    TextView textView_dingdanbianhao;
    TextView textView_kehumingcheng;
    TextView textView_yewuxingming;
    TextView textView_zongjia;
    TextView textView_weishou;
    TextView textView_benci;
    TextView textView_riqi;
    Button button_tongyi;
    Button button_foujue;
    Button button_fanhui;

    String dingdanbianhao;
    String timestamp;
    Handler handler;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_hui_kuan_wei_shen_pi);
        Intent intent = getIntent();
        dingdanbianhao = intent.getStringExtra("dingdanbianhao");
        timestamp = intent.getStringExtra("timestamp");
        handler = new Handler(){
            public void handleMessage(Message msg){
                switch (msg.what){
                    case 0:
                        setUi(msg.obj.toString());
                        break;
                    case 1:
                        run_alert();
                        break;
                    default:
                        break;
                }
            }
        };

        init_ui();
    }

    private void init_ui(){
        textView_dingdanbianhao = (TextView) findViewById(R.id.huikuan_shenpi_dingdanbianhao);
        textView_kehumingcheng = (TextView) findViewById(R.id.huikuan_shenpi_kehumingcheng);
        textView_yewuxingming = (TextView) findViewById(R.id.huikuan_shenpi_yewuxingming);
        textView_zongjia = (TextView) findViewById(R.id.huikuan_shenpi_zongjia);
        textView_weishou = (TextView) findViewById(R.id.huikuan_shenpi_weishou);
        textView_benci = (TextView) findViewById(R.id.huikuan_shenpi_benci);
        textView_riqi = (TextView) findViewById(R.id.huikuan_shenpi_riqi);

        button_tongyi = (Button) findViewById(R.id.huikuan_tongyi);
        button_tongyi.setOnClickListener(this);
        button_foujue = (Button) findViewById(R.id.huikuan_foujue);
        button_foujue.setOnClickListener(this);
        button_fanhui = (Button) findViewById(R.id.huikuan_fanhui);
        button_fanhui.setOnClickListener(this);

        getData();
    }

    @Override
    public void onClick(View v){
        switch (v.getId()){
            case R.id.huikuan_tongyi:
                new Thread(new Runnable() {
                    @Override
                    public void run() {
                        try
                        {
                            postData(1);
                        }
                        catch (Exception e)
                        {
                            e.printStackTrace();
                        }
                    }
                }).start();
                this.finish();
                break;
            case R.id.huikuan_foujue:
                new Thread(new Runnable() {
                    @Override
                    public void run() {
                        try
                        {
                            postData(2);
                        }
                        catch (Exception e)
                        {
                            e.printStackTrace();
                        }
                    }
                }).start();
                this.finish();
                break;
            case R.id.huikuan_fanhui:
                this.finish();
                break;
            default:
                break;
        }
    }

    private void getData(){
        new Thread(){
            @Override
            public void run(){
                super.run();
                String url = UserInfo.host + "/crm/huikuandetail/" + dingdanbianhao;
                OkHttpClient client = new OkHttpClient();
                Request request = new Request.Builder().url(url).build();
                Response response;
                String responseData;
                try{
                    response = client.newCall(request).execute();
                    responseData = response.body().string();
                    Message msg = new Message();
                    msg.what = 0;
                    msg.obj = responseData;
                    handler.sendMessage(msg);
                    Log.d("serData", responseData);
                }
                catch(IOException e ){
                    Message msg = new Message();
                    msg.what = 1;
                    handler.sendMessage(msg);
                    //即使你能保证读取能成功，你也要做好读取错误后的处理，否则通不过编译
                    Log.e("SetData", "setData: 读取失败" );
                }
            }
        }.start();
    }

    private void setUi(String response){
        try{
            JSONObject jsonObject = new JSONObject(response);
            if (jsonObject.getInt("error") == 1){
                textView_dingdanbianhao.setText(jsonObject.getString("dingdanbianhao"));
                textView_weishou.setText(jsonObject.getString("weishoukuan"));
                textView_zongjia.setText(jsonObject.getString("yingshoukuan"));
                textView_yewuxingming.setText(jsonObject.getString("yewuxingming"));
                textView_kehumingcheng.setText(jsonObject.getString("kehumingcheng"));
                JSONArray jsonArray = jsonObject.getJSONArray("detail");
                for (int i = 0; i < jsonArray.length(); i++){
                    JSONObject jsonObject1 = jsonArray.getJSONObject(i);
                    if (jsonObject1.getString("timestamp").equals(timestamp)){
                        textView_riqi.setText(jsonObject1.getString("riqi"));
                        textView_benci.setText(jsonObject1.getString("jine"));
                        break;
                    }
                }
            }
        }catch (Exception e){
            e.printStackTrace();
        }
    }

    private void postData(int status){
        Log.d("setData", "setData: 运行成功");
        OkHttpClient client = new OkHttpClient();
        RequestBody requestBody = new FormEncodingBuilder()
                .add("dingdanbianhao", dingdanbianhao)
                .add("timestamp", timestamp)
                .add("shenpizhuangtai", status + "")
                .build();
        Request request = new Request.Builder()
                .url(UserInfo.host + "/crm/admin/huikuanshenpi")
                .post(requestBody)
                .build();
        Response response;
        String responseData;
        try{
            response = client.newCall(request).execute();
            responseData = response.body().string();
            Log.d("postdata", responseData);
        }
        catch(IOException e ){
            //即使你能保证读取能成功，你也要做好读取错误后的处理，否则通不过编译
            Message msg = new Message();
            msg.what = 1;
            handler.sendMessage(msg);
            e.printStackTrace();
            Log.e("postdata", "setData: 读取失败" );
        }
    }

    private void run_alert(){
        AlertDialog.Builder dialog = new AlertDialog.Builder(HuiKuanWeiShenPiActivity.this);
        dialog.setTitle("警告");
        dialog.setMessage("网络连接失败，请重试，如果多次重试仍不能解决，请联系服务器管理员");
        dialog.setCancelable(false);
        dialog.setPositiveButton("知道了", new DialogInterface.OnClickListener() {
            @Override
            public void onClick(DialogInterface dialog, int which) {

            }
        });
        dialog.show();
    }
}
