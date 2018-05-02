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
import android.widget.TextView;

import com.example.os.crm.Bonus.Adapter.BonusOrderAdapter;
import com.example.os.crm.Bonus.Beans.CalcBonus;
import com.example.os.crm.Common.Calculator;
import com.example.os.crm.Common.UserInfo;
import com.example.os.crm.Contact.Model.ContactBean;
import com.example.os.crm.Contact.Activity.ContactForOne;
import com.example.os.crm.Dingdan.Model.DkDjDetailInfoBean;
import com.example.os.crm.HttpUtil.HttpUtil;
import com.example.os.crm.ui.widget.ChooseContact;
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
import okhttp3.FormBody;
import okhttp3.Response;

public class BonusOrder extends AppCompatActivity {

    @BindView(R.id.recyclerview)
    RecyclerView recyclerview;
    @BindView(R.id.heji)
    TextView heji;
    @BindView(R.id.tijnfafhufqk)
    Button tijnfafhufqk;
    @BindView(R.id.choose_contact)
    ChooseContact chooseContact;

    private String TAG = "BonusOrder";

    private List<DkDjDetailInfoBean> dkDjDetailInfoBeanList = new ArrayList<>();
    private BonusOrderAdapter bonusOrderAdapter = new BonusOrderAdapter(R.layout.bonus_order_item,
            dkDjDetailInfoBeanList);

    private Handler handler;

    public void startActivity(Context context) {
        Intent intent = new Intent(context, BonusOrder.class);
        context.startActivity(intent);
    }

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_bonus);
        ButterKnife.bind(this);
        initRecyler();
        initHandler();
        getData();
        chooseContact.setFootClick(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                new ContactForOne().startActivity(BonusOrder.this, 1);
            }
        });
    }

    @Override
    protected void onActivityResult(int requestCode, int resultCode, Intent data) {
        if (resultCode == RESULT_OK) {
            switch (requestCode){
                case 1:
                    ContactBean contactBean = (ContactBean) data.getParcelableExtra("data");
                    chooseContact.addData(contactBean);
                    break;
            }
        }
    }

    private void initHandler() {
        handler = new Handler() {
            public void handleMessage(Message msg) {
                switch (msg.what) {
                    case 0:
                        createList(msg.obj.toString());
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
        recyclerview.setAdapter(bonusOrderAdapter);
    }

    private void getData() {
        new HttpUtil().GetTiigDkdjList(new Callback() {
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

    private void createList(String response) {
        try {
            JSONObject jsonObject = new JSONObject(response);
            if (jsonObject.getInt("error") == 1) {
                JSONArray jsonArray = jsonObject.getJSONArray("data");
                Gson gson = new Gson();
                for (int i = 0; i < jsonArray.length(); i++) {
                    DkDjDetailInfoBean dkDjDetailInfoBean =
                            gson.fromJson(jsonArray.get(i).toString(), DkDjDetailInfoBean.class);
                    dkDjDetailInfoBeanList.add(dkDjDetailInfoBean);
                }
                bonusOrderAdapter.notifyDataSetChanged();
                String zsee = "0";
                for (DkDjDetailInfoBean dkDjDetailInfoBean : dkDjDetailInfoBeanList) {
                    CalcBonus calcBonus = new CalcBonus(dkDjDetailInfoBean);
                    if (dkDjDetailInfoBean.getVltd().getWjig() == 1) {
                        zsee = new Calculator().add(zsee, calcBonus.getJibentiig());
                        zsee = new Calculator().add(zsee, calcBonus.getVibcjbtiig());
                    } else {
                        zsee = new Calculator().add(zsee, calcBonus.getJibentiig());
                    }
                }
                heji.setText(zsee);
            }
        } catch (Exception e) {
            e.printStackTrace();
        }
    }

    @OnClick(R.id.tijnfafhufqk)
    public void onViewClicked() {
        postData();
    }

    private void postData(){
        if (isValid()){
            return;
        }
        FormBody.Builder builder = new FormBody.Builder();
        builder.add("fuzeyewu", UserInfo.userid)
                .add("tiigjbee", heji.getText().toString())
                .add("dkdj", getDkdjList())
                .add("ufpi", chooseContact.getUfPiRf());
        new HttpUtil().PostTiigFaFhUfqk(builder, new Callback() {
            @Override
            public void onFailure(Call call, IOException e) {
                e.printStackTrace();
            }

            @Override
            public void onResponse(Call call, Response response) throws IOException {
                String data = response.body().string();
                Log.d(TAG, "onResponse: " + data);
                BonusOrder.this.finish();
            }
        });
    }

    private boolean isValid(){
        if (dkDjDetailInfoBeanList.size() == 0){
            return true;
        }
        if (chooseContact.getCount() == 0){
            return true;
        }
        return false;
    }

    private String getDkdjList(){
        StringBuilder builder = new StringBuilder();
        builder.append("[");
        for (DkDjDetailInfoBean dkDjDetailInfoBean: dkDjDetailInfoBeanList){
            builder.append("\"")
                    .append(dkDjDetailInfoBean.getDkdjxbxi().getDkdjbmhc())
                    .append("\"")
                    .append(",");
        }
        builder.deleteCharAt(builder.length() - 1);
        builder.append("]");
        return builder.toString();
    }
}
