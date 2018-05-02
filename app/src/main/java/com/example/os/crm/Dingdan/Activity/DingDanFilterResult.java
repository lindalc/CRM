package com.example.os.crm.Dingdan.Activity;

import android.content.Context;
import android.content.Intent;
import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;
import android.view.View;
import android.widget.AdapterView;
import android.widget.ListView;

import com.example.os.crm.Common.UserInfo;
import com.example.os.crm.Dingdan.Adapter.DkDjListAdapter;
import com.example.os.crm.Dingdan.Model.DkDjDetailInfoBean;
import com.example.os.crm.R;
import com.google.gson.Gson;

import org.json.JSONArray;
import org.json.JSONObject;

import java.util.ArrayList;
import java.util.List;

import butterknife.BindView;
import butterknife.ButterKnife;

public class DingDanFilterResult extends AppCompatActivity {

    @BindView(R.id.filter_list)
    ListView filterList;
    private String result;
    private String userId;

    private List<DkDjDetailInfoBean> dkDjDetailInfoBeanList = new ArrayList<>();
    private DkDjListAdapter dkDjListAdapter;

    public void startActivity(Context context, String filterResult){
        Intent intent = new Intent(context, DingDanFilterResult.class);
        intent.putExtra("result", filterResult);
        context.startActivity(intent);
    }

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_ding_dan_filter_result);
        ButterKnife.bind(this);
        Intent intent = getIntent();
        result = intent.getStringExtra("result");
        userId = UserInfo.userid;
        dkDjListAdapter = new DkDjListAdapter(this, dkDjDetailInfoBeanList);
        filterList.setAdapter(dkDjListAdapter);
        filterList.setOnItemClickListener(new AdapterView.OnItemClickListener() {
            @Override
            public void onItemClick(AdapterView<?> parent, View view, int position, long id) {
                DkDjDetailInfoBean dkDjDetailInfoBean = dkDjDetailInfoBeanList.get(position);
                new DingdanDetail().startActivity(DingDanFilterResult.this, dkDjDetailInfoBean);
            }
        });
        setData();
    }

    private void setData(){
        try{
            JSONObject jsonObject = new JSONObject(result);
            if (jsonObject.getInt("error") == 1){
                JSONArray jsonArray = jsonObject.getJSONArray("data");
                for (int i = 0; i< jsonArray.length(); i++){
                    DkDjDetailInfoBean dkDjDetailInfoBean = new Gson().fromJson(
                            jsonArray.getJSONObject(i).toString(),
                            DkDjDetailInfoBean.class);
                    dkDjDetailInfoBeanList.add(dkDjDetailInfoBean);
                }
                dkDjListAdapter.notifyDataSetChanged();
            }
        }catch (Exception e){
            e.printStackTrace();
        }
    }
}
