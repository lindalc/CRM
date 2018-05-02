package com.example.os.crm.Contact.Activity;

import android.app.Activity;
import android.content.Intent;
import android.os.Bundle;
import android.os.Handler;
import android.os.Message;
import android.support.v7.app.AppCompatActivity;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.util.Log;
import android.view.View;
import android.widget.CheckBox;
import android.widget.EditText;

import com.chad.library.adapter.base.BaseQuickAdapter;
import com.example.os.crm.Contact.Adapter.ContactForMultyAdapter;
import com.example.os.crm.Contact.Model.ContactBean;
import com.example.os.crm.HttpUtil.HttpUtil;
import com.example.os.crm.R;
import com.google.gson.Gson;

import org.json.JSONArray;
import org.json.JSONObject;

import java.io.IOException;
import java.io.Serializable;
import java.util.ArrayList;
import java.util.Collections;
import java.util.Comparator;
import java.util.List;

import butterknife.BindView;
import butterknife.ButterKnife;
import okhttp3.Call;
import okhttp3.Callback;
import okhttp3.Response;

public class ContactForMulty extends AppCompatActivity {

    @BindView(R.id.search)
    EditText search;
    @BindView(R.id.contact_list)
    RecyclerView contactList;
    private String TAG = "ContactForMulty";

    private List<ContactBean> contactBeanList = new ArrayList<>();
    private ContactForMultyAdapter contactForMultyAdapter = new ContactForMultyAdapter(R.layout.contact_item_for_multy, contactBeanList);


    Handler handler;
    public void startActivity(Activity activity, int reqCode){
        Intent intent = new Intent(activity, ContactForMulty.class);
        activity.startActivityForResult(intent, reqCode);
    }

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.contact_for_multy_activity);
        ButterKnife.bind(this);
        contactForMultyAdapter.bindToRecyclerView(contactList);
        contactList.setLayoutManager(new LinearLayoutManager(this));
        contactList.setAdapter(contactForMultyAdapter);
        initHandler();
        getData();
        initAdapterClick();
    }

    private void initHandler() {
        handler = new Handler(){
            public void handleMessage(Message msg){
                switch (msg.what){
                    case 0:
                        initData(msg.obj.toString());
                        break;
                    default:
                        break;
                }
            }
        };
    }

    @Override
    public void onBackPressed(){
        setResultData();
        finish();
    }

    private void setResultData() {
        List<ContactBean> contactBeans = new ArrayList<>();
        for (int i = 0; i < contactBeanList.size(); i ++){
            if (((CheckBox) contactForMultyAdapter.getViewByPosition(i, R.id.checkbox)).isChecked()){
                contactBeans.add(contactBeanList.get(i));
            }
        }
        Intent intent = new Intent();
        if (contactBeans.size() > 0){
            intent.putExtra("data", (Serializable) contactBeans);
            setResult(RESULT_OK, intent);
        }
        else{
            setResult(RESULT_CANCELED, intent);
        }
    }

    private void initData(String response){
        try{
            JSONObject jsonObject = new JSONObject(response);
            if (jsonObject.getInt("error") == 1){
                JSONArray jsonArray = jsonObject.getJSONArray("data");
                Gson gson = new Gson();
                for (int i = 0; i < jsonArray.length(); i++){
                    JSONObject jsonObject1 = jsonArray.getJSONObject(i);
                    ContactBean contactBean = gson.fromJson(jsonObject1.toString(), ContactBean.class);
                    contactBeanList.add(contactBean);
                }
                Collections.sort(contactBeanList, new Comparator<ContactBean>() {
                    @Override
                    public int compare(ContactBean o1, ContactBean o2) {
                        return o1.getJibf().getFirstChar() - o2.getJibf().getFirstChar();
                    }
                });
                contactForMultyAdapter.notifyDataSetChanged();
            }
        }catch (Exception e){
            e.printStackTrace();
        }
    }

    private void getData(){
        new HttpUtil().getContactList(new Callback() {
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

    private void initAdapterClick(){
        contactForMultyAdapter.setOnItemClickListener(new BaseQuickAdapter.OnItemClickListener() {
            @Override
            public void onItemClick(BaseQuickAdapter adapter, View view, int position) {
                changeSelectedRadioButton(position);
            }
        });
        contactForMultyAdapter.setOnItemChildClickListener(new BaseQuickAdapter.OnItemChildClickListener() {
            @Override
            public void onItemChildClick(BaseQuickAdapter adapter, View view, int position) {
                changeSelectedRadioButton(position);
            }
        });
    }

    private void changeSelectedRadioButton(int position) {
        CheckBox checkBox = (CheckBox) contactForMultyAdapter.getViewByPosition(position, R.id.checkbox);
        if (checkBox.isChecked()){
            checkBox.setChecked(false);
        }
        else{
            checkBox.setChecked(true);
        }
    }
}
