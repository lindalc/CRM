package com.example.os.crm.Contact.Activity;

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

import com.example.os.crm.Common.UserInfo;
import com.example.os.crm.Contact.Adapter.CustomerAdapter;
import com.example.os.crm.Contact.Model.CustomerBean;
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

public class CustomerList extends AppCompatActivity {

    ListView listView;
    Button button;
    Handler handler;
    String userId;
    @BindView(R.id.admin_kehulist)
    ListView adminKehulist;
    @BindView(R.id.kehulist_newkehu)
    Button kehulistNewkehu;
    @BindView(R.id.title_back)
    Button title_back;
    @BindView(R.id.title_edit)
    Button title_edit;
    @BindView(R.id.title_text)
    TextView title_text;
    private List<CustomerBean> keHuDetails = new ArrayList<>();
    private int type;


    public void startActivity(Context context) {
        Intent intent = new Intent(context, CustomerList.class);
        context.startActivity(intent);
    }

    public void startActivity(Context context, int type) {
        Intent intent = new Intent(context, CustomerList.class);
        intent.putExtra("type", type);
        context.startActivity(intent);
    }

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_ke_hu_list);
        ButterKnife.bind(this);
        Intent intent = getIntent();
        this.type = intent.getIntExtra("type", 0);
        userId = UserInfo.userid;
        title_text.setText("客户信息");
        listView = (ListView) findViewById(R.id.admin_kehulist);
        button = (Button) findViewById(R.id.kehulist_newkehu);
        button.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent intent = new Intent(CustomerList.this, CreateUpdateCustomerInfo.class);
                intent.putExtra("userid", userId);
                intent.putExtra("kehuid", "");
                startActivity(intent);
            }
        });

        handler = new Handler() {
            public void handleMessage(Message msg) {
                switch (msg.what) {
                    case 0:
                        CreateKehuList(msg.obj.toString());
                        break;
                    case 1:

                        break;
                    default:
                        break;
                }
            }
        };
        get_kehuziliao();
    }

    @Override
    protected void onRestart() {
        super.onRestart();
        get_kehuziliao();
    }

    private void get_kehuziliao() {

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
        if (type == 1) {
            new HttpUtil().getCustomerXbZgList(callback);
        } else {
            new HttpUtil().getCustomerList(callback);
        }
    }

    private void CreateKehuList(String response) {
        listView.setAdapter(null);
        keHuDetails.clear();
        try {
            JSONObject jsonObject = new JSONObject(response);
            if (jsonObject.getInt("error") == 1) {
                JSONArray jsonArray = jsonObject.getJSONArray("data");
                Gson gson = new Gson();
                for (int i = 0; i < jsonArray.length(); i++) {
                    JSONObject jsonObject1 = jsonArray.getJSONObject(i);
                    CustomerBean customerBean = gson.fromJson(jsonObject1.toString(), CustomerBean.class);
                    keHuDetails.add(customerBean);
                }
                CustomerAdapter customerAdapter = new CustomerAdapter(this, keHuDetails);
                listView.setAdapter(customerAdapter);
                listView.setOnItemClickListener(new AdapterView.OnItemClickListener() {
                    @Override
                    public void onItemClick(AdapterView<?> parent, View view, int position, long id) {
                        CustomerBean customerBean = keHuDetails.get(position);
                        Intent intent = new Intent(CustomerList.this, CreateUpdateCustomerInfo.class);
                        intent.putExtra("kehuid", customerBean.getKehuid());
                        intent.putExtra("userid", userId);
                        startActivity(intent);
                    }
                });
            }
        } catch (Exception e) {
            e.printStackTrace();
        }
    }


}
