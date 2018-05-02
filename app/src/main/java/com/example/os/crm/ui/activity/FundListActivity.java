package com.example.os.crm.ui.activity;

import android.content.Context;
import android.content.Intent;
import android.os.Bundle;
import android.os.Handler;
import android.os.Message;
import android.support.design.widget.AppBarLayout;
import android.support.v7.app.AppCompatActivity;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.support.v7.widget.Toolbar;
import android.util.Log;
import android.view.View;

import com.chad.library.adapter.base.BaseQuickAdapter;
import com.example.os.crm.HttpUtil.HttpUtil;
import com.example.os.crm.ui.adapter.FundListAdapter;
import com.example.os.crm.model.PaymentInfoBean;
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

/**
 * 回款记录，原PaymenHistory
 */
public class FundListActivity extends AppCompatActivity {


    @BindView(R.id.toolbar)
    Toolbar toolbar;
    @BindView(R.id.appbar)
    AppBarLayout appbar;
    @BindView(R.id.recyclerview)
    RecyclerView recyclerview;

    private String TAG = "FundListActivity";
    private List<PaymentInfoBean> paymentInfoBeanList = new ArrayList<>();
    private FundListAdapter fundListAdapter =
            new FundListAdapter(R.layout.payment_history_item, paymentInfoBeanList);
    private PaymentInfoBean paymentInfoBean;
    Handler handler;

    public void startActivity(Context context){
        Intent intent = new Intent(context, FundListActivity.class);
        context.startActivity(intent);
    }

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_payment_history);
        ButterKnife.bind(this);
        initUi();
        initHandler();
        getData();
    }

    @Override
    protected void onRestart(){
        super.onRestart();
        getData();
    }

    private void initHandler() {
        handler = new Handler(){
            public void handleMessage(Message msg){
                switch (msg.what){
                    case 0:
                        createList(msg.obj.toString());
                        break;
                    default:
                        break;
                }
            }
        };
    }

    private void initUi() {
        LinearLayoutManager linearLayoutManager = new LinearLayoutManager(this);
        recyclerview.setLayoutManager(linearLayoutManager);
        recyclerview.setAdapter(fundListAdapter);
    }

    private void getData(){
        new HttpUtil().GetPaymentHistory(new Callback() {
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

    private void createList(String response){
        paymentInfoBeanList.clear();
        try{
            Gson gson = new Gson();
            JSONObject jsonObject = new JSONObject(response);
            if (jsonObject.getInt("error") == 1){
                JSONArray jsonArray = jsonObject.getJSONArray("data");
                for (int i = 0; i < jsonArray.length(); i++){
                    PaymentInfoBean paymentInfoBean =
                            gson.fromJson(jsonArray.getJSONObject(i).toString(),
                                    PaymentInfoBean.class);
                    paymentInfoBeanList.add(paymentInfoBean);
                }
                fundListAdapter.notifyDataSetChanged();
                fundListAdapter.setOnItemClickListener(new BaseQuickAdapter.OnItemClickListener() {
                    @Override
                    public void onItemClick(BaseQuickAdapter adapter, View view, int position) {
                        paymentInfoBean = paymentInfoBeanList.get(position);
                        new FundDetailActivity().startActivity(FundListActivity.this, paymentInfoBean);
                    }
                });

                fundListAdapter.setOnItemLongClickListener(new BaseQuickAdapter.OnItemLongClickListener() {
                    @Override
                    public boolean onItemLongClick(BaseQuickAdapter adapter, View view, int position) {
                        PaymentInfoBean paymentInfoBean1 = paymentInfoBeanList.get(position);

                        return false;
                    }
                });
            }
        }catch (Exception e){

        }
    }
}
