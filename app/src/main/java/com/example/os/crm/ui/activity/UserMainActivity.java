package com.example.os.crm.ui.activity;

import android.content.Intent;
import android.os.Bundle;
import android.os.Handler;
import android.os.Message;
import android.support.v7.app.AppCompatActivity;
import android.util.Log;
import android.view.View;
import android.widget.Button;
import android.widget.LinearLayout;
import android.widget.TextView;

import com.example.os.crm.Common.UserInfo;
import com.example.os.crm.Contact.Activity.CustomerList;
import com.example.os.crm.Contract.Activity.HeTongList;
import com.example.os.crm.Dingdan.Activity.CreateNew;
import com.example.os.crm.Dingdan.Activity.History;
import com.example.os.crm.HttpUtil.HttpGet;
import com.example.os.crm.R;

import org.json.JSONObject;

import java.io.IOException;

import butterknife.BindView;
import butterknife.ButterKnife;
import butterknife.OnClick;
import okhttp3.Call;
import okhttp3.Callback;
import okhttp3.Response;


public class UserMainActivity extends AppCompatActivity {

    @BindView(R.id.yewuyuanbianhao)
    TextView yewuyuanbianhao;
    @BindView(R.id.xiugaimima)
    Button xiugaimima;
    @BindView(R.id.dingdanzonge)
    TextView dingdanzonge;
    @BindView(R.id.dingdanxiangqing)
    Button dingdanxiangqing;
    @BindView(R.id.weishouhuokuan)
    TextView weishouhuokuan;
    @BindView(R.id.chakanweishou)
    Button chakanweishou;
    @BindView(R.id.tichengzonge)
    TextView tichengzonge;
    @BindView(R.id.chakanticheng)
    Button chakanticheng;
    @BindView(R.id.guanlikehuziliao)
    Button guanlikehuziliao;
    @BindView(R.id.hetongmanage)
    Button hetongmanage;
    @BindView(R.id.createnew)
    Button createnew;
    @BindView(R.id.shenpi)
    Button shenpi;
    @BindView(R.id.yewupage)
    LinearLayout yewupage;

    private Handler handler;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_admin_main);
        ButterKnife.bind(this);
        yewuyuanbianhao.setText(UserInfo.xkmk);
        chakanweishou.setVisibility(View.INVISIBLE);
        getData();
        handler = new Handler(){
            public void handleMessage(Message msg) {
                switch (msg.what) {
                    case 0:
                        setUiData(msg.obj.toString());
                        break;
                }
            }
        };
    }

    @Override
    protected void onRestart(){
        super.onRestart();
        yewuyuanbianhao.setText(UserInfo.xkmk);
        getData();
    }

    private void getData(){
        String url = UserInfo.host + "/crm/getuserinfo/" + UserInfo.userid;
        if (UserInfo.userid.equals(UserInfo.root)){
            url = UserInfo.host + "/crm/getadmininfo";
        }
        new HttpGet().getData(url, new Callback() {
            @Override
            public void onFailure(Call call, IOException e) {
                e.printStackTrace();
            }

            @Override
            public void onResponse(Call call, Response response) throws IOException {
                String data = response.body().string();
                Log.d("UserMain", "onResponse: " + data);
                Message msg = new Message();
                msg.what = 0;
                msg.obj = data;
                handler.sendMessage(msg);
            }
        });
    }

    private void setUiData(String data){
        try{
            JSONObject jsonObject = new JSONObject(data);
            if (jsonObject.getInt("error") == 1){
                dingdanzonge.setText(jsonObject.getString("zonge"));
                weishouhuokuan.setText(jsonObject.getString("weishou"));
                tichengzonge.setText(jsonObject.getString("ticheng"));
            }
        }catch (Exception e){
            e.printStackTrace();
        }
    }

    @OnClick({R.id.xiugaimima, R.id.dingdanxiangqing, R.id.chakanweishou, R.id.chakanticheng, R.id.guanlikehuziliao, R.id.createnew, R.id.shenpi, R.id.hetongmanage})
    public void onViewClicked(View view) {
        Intent intent;
        switch (view.getId()) {
            case R.id.xiugaimima:
                break;
            case R.id.dingdanxiangqing:
                intent = new Intent(UserMainActivity.this, History.class);
                startActivity(intent);
                break;
            case R.id.chakanweishou:
                break;
            case R.id.chakanticheng:
                break;
            case R.id.guanlikehuziliao:
                intent = new Intent(UserMainActivity.this, CustomerList.class);
                startActivity(intent);
                break;
            case R.id.createnew:
                intent = new Intent(UserMainActivity.this, CreateNew.class);
                startActivity(intent);
                break;
            case R.id.shenpi:
                intent = new Intent(UserMainActivity.this, Approval_WaitActivity.class);
                startActivity(intent);
                break;
            case R.id.hetongmanage:
                intent = new Intent(UserMainActivity.this, HeTongList.class);
                startActivity(intent);
                break;
        }
    }
}
