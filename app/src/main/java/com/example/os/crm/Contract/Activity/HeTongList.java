/*
 * Copyright 2017 Zhihu Inc.
 *
 * Licensed under the Apache License, Version 2.0 (the "License");
 * you may not use this file except in compliance with the License.
 * You may obtain a copy of the License at
 *
 *      http://www.apache.org/licenses/LICENSE-2.0
 *
 * Unless required by applicable law or agreed to in writing, software
 * distributed under the License is distributed on an "AS IS" BASIS,
 * WITHOUT WARRANTIES OR CONDITIONS OF ANY KIND, either express or implied.
 * See the License for the specific language governing permissions and
 * limitations under the License.
 */
package com.example.os.crm.Contract.Activity;

import android.content.Context;
import android.content.Intent;
import android.os.Bundle;
import android.os.Handler;
import android.os.Message;
import android.support.v7.app.AppCompatActivity;
import android.util.Log;
import android.view.View;
import android.widget.AdapterView;
import android.widget.Button;
import android.widget.ListView;
import android.widget.Toast;

import com.example.os.crm.Common.UserInfo;
import com.example.os.crm.Contract.Adapter.HeTongAdapter;
import com.example.os.crm.Contract.Model.ContractDetailBean;
import com.example.os.crm.HttpUtil.HttpGet;
import com.example.os.crm.R;

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


public class HeTongList extends AppCompatActivity {


    @BindView(R.id.addnewhetong)
    Button addnewhetong;
    @BindView(R.id.shaixuanhetong)
    Button shaixuanhetong;
    @BindView(R.id.listview)
    ListView listview;
    HeTongAdapter heTongAdapter;

    String userId;
    private String TAG = "合同列表";

    Handler handler;

    private List<ContractDetailBean> contractDetailBeans = new ArrayList<>();

    public void startActivity(Context context){
        Intent intent = new Intent(context, HeTongList.class);
        context.startActivity(intent);
    }

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_he_tong_list);
        ButterKnife.bind(this);
        userId = UserInfo.userid;
        Log.d(TAG, "onCreate: " + userId);

        heTongAdapter = new HeTongAdapter(HeTongList.this, contractDetailBeans);
        listview.setAdapter(heTongAdapter);

        handler = new Handler() {
            public void handleMessage(Message msg) {
                switch (msg.what) {
                    case 0:
                        setData(msg.obj.toString());
                        break;
                    default:
                        break;
                }
            }
        };
        getData();
    }

    @OnClick({R.id.addnewhetong, R.id.shaixuanhetong})
    public void onViewClicked(View view) {
        switch (view.getId()) {
            case R.id.addnewhetong:
                new AddContract().startActivity(this);
                break;
            case R.id.shaixuanhetong:
                //TODO 添加合同筛选功能
                Toast.makeText(this, "功能未上线......", Toast.LENGTH_SHORT).show();
                break;
        }
    }

    private void getData() {
        String url = UserInfo.host + "/crm/gethetonglist/" + userId;
        HttpGet httpGet = new HttpGet();
        httpGet.getData(url, new Callback() {
            @Override
            public void onResponse(Call call, Response response) throws IOException {
                String responseData = response.body().string();
                try {
                    JSONObject jsonObject = new JSONObject(responseData);
                    if (jsonObject.getInt("error") == 1) {
                        Message msg = new Message();
                        msg.what = 0;
                        msg.obj = responseData;
                        handler.sendMessage(msg);
                    }
                } catch (Exception e) {
                    e.printStackTrace();
                }
            }

            @Override
            public void onFailure(Call call, IOException e) {
                e.printStackTrace();
            }
        });
    }

    private void setData(String response) {
        listview.setAdapter(null);
        contractDetailBeans.clear();
        try {
            JSONObject jsonObject = new JSONObject(response);
            JSONArray jsonArray = jsonObject.getJSONArray("data");
            for (int i = 0; i < jsonArray.length(); i++) {
                JSONObject jsonObject1 = jsonArray.getJSONObject(i);
                ContractDetailBean contractDetailBean = new ContractDetailBean();
                contractDetailBean.setDingdanbianhao(jsonObject1.getString("dingdanbianhao"));
                contractDetailBean.setDingdanzonge(jsonObject1.getString("dingdanzonge"));
                contractDetailBean.setHetongbianhao(jsonObject1.getString("hetongbianhao"));
                contractDetailBean.setKehumingcheng(jsonObject1.getString("kehumingcheng"));
                contractDetailBean.setQiandingriqi(jsonObject1.getString("qiandingriqi"));
                contractDetailBeans.add(contractDetailBean);
            }
            listview.setAdapter(heTongAdapter);
            listview.setOnItemClickListener(new AdapterView.OnItemClickListener() {
                @Override
                public void onItemClick(AdapterView<?> parent, View view, int position, long id) {
                    ContractDetailBean contractDetailBean = contractDetailBeans.get(position);
                    String dingdanbianhao = contractDetailBean.getDingdanbianhao();
                    new HeTongFilesList().actionStart(HeTongList.this, dingdanbianhao);
                }
            });
        } catch (Exception e) {
            e.printStackTrace();
        }
    }
}
