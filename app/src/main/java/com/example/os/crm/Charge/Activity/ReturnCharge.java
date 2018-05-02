package com.example.os.crm.Charge.Activity;

import android.content.Context;
import android.content.Intent;
import android.os.Bundle;
import android.os.Handler;
import android.os.Message;
import android.support.v7.app.AppCompatActivity;
import android.view.View;
import android.widget.AdapterView;
import android.widget.Button;
import android.widget.ListView;
import android.widget.TextView;

import com.example.os.crm.Charge.Model.NewChargeBean;
import com.example.os.crm.Common.UserInfo;
import com.example.os.crm.Contact.Activity.CreateUpdateCustomerInfo;
import com.example.os.crm.Contact.Adapter.CustomerAdapter;
import com.example.os.crm.HttpUtil.HttpUtil;
import com.example.os.crm.R;
import com.google.gson.Gson;

import org.json.JSONArray;
import org.json.JSONObject;

import java.io.IOException;
import java.util.ArrayList;
import java.util.List;

import butterknife.BindView;
import butterknife.ButterKnife;
import okhttp3.Call;
import okhttp3.Callback;
import okhttp3.Response;

public class ReturnCharge extends AppCompatActivity {

    ListView listView;
    Button button;
    Handler handler;
    String userId;
    @BindView(R.id.title_back)
    Button title_back;
    @BindView(R.id.title_edit)
    Button title_edit;
    @BindView(R.id.title_text)
    TextView title_text;
    private List<NewChargeBean> fwysDetails = new ArrayList<>();
    private int type;

    public void startActivity(Context context) {
        Intent intent = new Intent(context, ReturnCharge.class);
        context.startActivity(intent);
    }

    public void startActivity(Context context, int type) {
        Intent intent = new Intent(context, ReturnCharge.class);
        intent.putExtra("type", type);
        context.startActivity(intent);
    }

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_return_charge);
        ButterKnife.bind(this);
        Intent intent = getIntent();
        this.type = intent.getIntExtra("type", 0);
        userId = UserInfo.userid;
        title_text.setText("费用申请记录");
        listView = (ListView) findViewById(R.id.fwyslish);
        button = (Button) findViewById(R.id.fwyslist_newfwys);
        button.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent intent = new Intent(ReturnCharge.this, NewChargeActivity.class);
                intent.putExtra("userid", userId);
                intent.putExtra("kehuid", "");
                startActivity(intent);
            }
        });

        handler = new Handler() {
            public void handleMessage(Message msg) {
                switch (msg.what) {
                    case 0:
                        CreateFwysList(msg.obj.toString());
                        break;
                    case 1:

                        break;
                    default:
                        break;
                }
            }
        };
        get_fwysjilv();
    }

    @Override
    protected void onRestart() {
        super.onRestart();
        get_fwysjilv();
    }

    private void get_fwysjilv() {

        Callback callback = new Callback() {
            @Override
            public void onFailure(Call call, IOException e) {
                Message msg = new Message();
                msg.what = 1;
                handler.sendMessage(msg);
            }

            @Override
            public void onResponse(Call call, Response response) throws IOException {
                String responseData = response.body().string();
                Message msg = new Message();
                msg.what = 0;
                msg.obj = responseData;
                handler.sendMessage(msg);
            }
        };
        if (type == 1) new HttpUtil().getFwysList(callback);
    //    else {
           // new HttpUtil(),get_fwysjilv(callback);
    //    }
    }

    private void CreateFwysList(String response) {
        listView.setAdapter(null);
        fwysDetails.clear();
        try {
            JSONObject jsonObject = new JSONObject(response);
            if (jsonObject.getInt("error") == 1) {
                JSONArray jsonArray = jsonObject.getJSONArray("data");
                Gson gson = new Gson();
                for (int i = 0; i < jsonArray.length(); i++) {
                    JSONObject jsonObject1 = jsonArray.getJSONObject(i);
                    NewChargeBean newchargeBean = gson.fromJson(jsonObject1.toString(), NewChargeBean.class);
                    fwysDetails.add(newchargeBean);
                }
                FwysAdapter fwysAdapter = new FwysAdapter(this, fwysDetails);
                listView.setAdapter(fwysAdapter);
            }
        } catch (Exception e) {
            e.printStackTrace();
        }
    }
}
