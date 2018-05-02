package com.example.os.crm.Contact.Activity;

import android.content.Context;
import android.content.Intent;
import android.os.Bundle;
import android.os.Handler;
import android.os.Message;
import android.support.v7.app.AppCompatActivity;
import android.util.Log;
import android.widget.TextView;

import com.example.os.crm.Common.UserInfo;
import com.example.os.crm.HttpUtil.HttpGet;
import com.example.os.crm.R;

import org.json.JSONObject;

import java.io.IOException;

import butterknife.BindView;
import butterknife.ButterKnife;
import okhttp3.Call;
import okhttp3.Callback;
import okhttp3.Response;


public class CustomerDetail extends AppCompatActivity {

    @BindView(R.id.kehu_mingcheng)
    TextView kehuMingcheng;
    @BindView(R.id.kehu_lianxiren)
    TextView kehuLianxiren;
    @BindView(R.id.kehu_dianhua)
    TextView kehuDianhua;
    @BindView(R.id.kehu_email)
    TextView kehuEmail;
    @BindView(R.id.kehu_qq)
    TextView kehuQq;
    @BindView(R.id.kehu_wechat)
    TextView kehuWechat;
    @BindView(R.id.kehu_dizhi)
    TextView kehuDizhi;
    @BindView(R.id.kehu_hangye)
    TextView kehuHangye;
    @BindView(R.id.kehu_beizhu)
    TextView kehuBeizhu;

    private String kehuId;
    private Handler handler;

    public void startActivity(Context context, String kehuid){
        Intent intent = new Intent(context, CustomerDetail.class);
        intent.putExtra("kehuid", kehuid);
        context.startActivity(intent);

    }

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_ke_hu_zi_ln);
        ButterKnife.bind(this);
        initParam();
        initHandler();
        getData();
    }

    private void initHandler() {
        handler = new Handler(){
            public void handleMessage(Message msg){
                switch (msg.what){
                    case 0:
                        initUiData(msg.obj.toString());
                        break;
                    case 1:
                        break;
                    default:
                        break;
                }
            }
        };
    }

    private void initParam() {
        Intent intent= getIntent();
        kehuId = intent.getStringExtra("kehuid");
    }

    private void getData(){
        String url = UserInfo.host + "/crm/getkehudetail/" + kehuId;
        new HttpGet().getData(url, new Callback() {
            @Override
            public void onFailure(Call call, IOException e) {

            }

            @Override
            public void onResponse(Call call, Response response) throws IOException {
                String data = response.body().string();
                Log.d("获取客户资料", "onResponse: " + data);
                try{
                    JSONObject jsonObject = new JSONObject(data);
                    if(jsonObject.getInt("error") == 1){
                        Message msg = new Message();
                        msg.what = 0;
                        msg.obj = data;
                        handler.sendMessage(msg);
                    }
                }catch (Exception e){
                    e.printStackTrace();
                }
            }
        });
    }

    private void initUiData(String string){
        try{
            JSONObject jsonObject = new JSONObject(string);
            if (jsonObject.getInt("error") == 1){
                JSONObject jsonObject1 = jsonObject.getJSONObject("data");
                kehuMingcheng.setText(jsonObject1.getString("kehumingcheng"));
                kehuBeizhu.setText(jsonObject1.getString("beizhu"));
                kehuHangye.setText(jsonObject1.getString("kehuhangye"));
                kehuDizhi.setText(jsonObject1.getString("kehudizhi"));
                kehuWechat.setText(jsonObject1.getString("lianxirenwechat"));
                kehuQq.setText(jsonObject1.getString("lianxirenqq"));
                kehuLianxiren.setText(jsonObject1.getString("kehulianxiren"));
                kehuDianhua.setText(jsonObject1.getString("lianxirendianhua"));
                kehuEmail.setText(jsonObject1.getString("lianxirenemail"));
            }

        }catch (Exception e){
            e.printStackTrace();
        }
    }
}
