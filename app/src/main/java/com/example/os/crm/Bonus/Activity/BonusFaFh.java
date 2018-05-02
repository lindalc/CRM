package com.example.os.crm.Bonus.Activity;

import android.content.Context;
import android.content.Intent;
import android.os.Bundle;
import android.os.Handler;
import android.os.Message;
import android.support.v7.app.AppCompatActivity;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.util.Log;
import android.view.View;
import android.widget.Button;

import com.example.os.crm.Bonus.Adapter.BonusFafhAdapter;
import com.example.os.crm.Bonus.Beans.BonusInfoBean;
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
import butterknife.OnClick;
import okhttp3.Call;
import okhttp3.Callback;
import okhttp3.Response;

public class BonusFaFh extends AppCompatActivity {

    @BindView(R.id.recyclerview)
    RecyclerView recyclerview;
    @BindView(R.id.tsyi)
    Button tsyi;
    @BindView(R.id.bohv)
    Button bohv;

    private String TAG = "BonusFaFh";

    private List<BonusInfoBean> bonusInfoBeanList = new ArrayList<>();
    private BonusFafhAdapter bonusFafhAdapter =
            new BonusFafhAdapter(R.layout.bonus_fafh_item, bonusInfoBeanList);

    private Handler handler;

    public void startActivity(Context context) {
        Intent intent = new Intent(context, BonusFaFh.class);
        context.startActivity(intent);
    }

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_bonus_fafh);
        ButterKnife.bind(this);
        initRecyler();
        initHandler();
        getData();
    }

    private void initHandler() {
        handler = new Handler() {
            public void handleMessage(Message msg) {
                switch (msg.what) {
                    case 0:
                        CreateList(msg.obj.toString());
                        break;
                    default:
                        break;
                }
            }
        };
    }

    private void initRecyler() {
        LinearLayoutManager linearLayoutManager = new LinearLayoutManager(this);
        recyclerview.setLayoutManager(linearLayoutManager);
        recyclerview.setAdapter(bonusFafhAdapter);
    }

    private void getData() {
        new HttpUtil().GetTiigFafhUfqk(new Callback() {
            @Override
            public void onFailure(Call call, IOException e) {
                e.printStackTrace();
            }

            @Override
            public void onResponse(Call call, Response response) throws IOException {
                String data = response.body().string();
                Log.d(TAG, "onResponse: " + data);
                Message msg = new Message();
                msg.what = 0;
                msg.obj = data;
                handler.sendMessage(msg);
            }
        });
    }

    private void CreateList(String response) {
        try {
            JSONObject jsonObject = new JSONObject(response);
            if (jsonObject.getInt("error") == 1) {
                JSONArray jsonArray = jsonObject.getJSONArray("data");
                Gson gson = new Gson();
                for (int i = 0; i < jsonArray.length(); i++) {
                    BonusInfoBean bonusInfoBean = gson.fromJson(jsonArray.get(i).toString(),
                            BonusInfoBean.class);
                    bonusInfoBeanList.add(bonusInfoBean);
                }
                bonusFafhAdapter.notifyDataSetChanged();
            }
        } catch (Exception e) {
            e.printStackTrace();
        }
    }

    @OnClick({R.id.tsyi, R.id.bohv})
    public void onViewClicked(View view) {
        //TODO 同意或驳回提成发放申请的功能没做
        switch (view.getId()) {
            case R.id.tsyi:

                break;
            case R.id.bohv:
                break;
        }
    }
}
