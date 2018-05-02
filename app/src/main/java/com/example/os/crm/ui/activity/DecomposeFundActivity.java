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
import android.view.View;
import android.widget.Button;
import android.widget.CheckBox;
import android.widget.EditText;
import android.widget.TextView;
import android.widget.Toast;

import com.example.os.crm.Common.GetDataDoneListener;
import com.example.os.crm.Common.TimeUtil;
import com.example.os.crm.Common.UserInfo;
import com.example.os.crm.Contact.Model.Contact;
import com.example.os.crm.Contact.Model.ContactBean;
import com.example.os.crm.Contact.Activity.ContactForOne;
import com.example.os.crm.HttpUtil.HttpUtil;
import com.example.os.crm.ui.adapter.DecomposeAdapter;
import com.example.os.crm.model.HvKrDkDjBean;
import com.example.os.crm.model.ModelHvkrDkdjBean;
import com.example.os.crm.model.PaymentInfoBean;
import com.example.os.crm.ui.widget.ChooseContact;
import com.example.os.crm.R;

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

/**
 * 分解回款
 */
public class DecomposeFundActivity extends AppCompatActivity {

    @BindView(R.id.toolbar)
    Toolbar toolbar;
    @BindView(R.id.appbar)
    AppBarLayout appbar;
    @BindView(R.id.recyclerview)
    RecyclerView recyclerview;
    @BindView(R.id.choose_contact)
    ChooseContact chooseContact;
    @BindView(R.id.tijnufqk)
    Button tijnufqk;
    @BindView(R.id.heji)
    TextView heji;

    private int benciheji;

    private PaymentInfoBean paymentInfoBean;
    private List<HvKrDkDjBean> hvKrDkDjBeanList;
    private ModelHvkrDkdjBean modelHvkrDkdjBean;
    private DecomposeAdapter decomposeAdapter;
    private Contact contact = Contact.getInstance();
    private List<String> ufpirf = new ArrayList<>();


    private Handler handler;
    private int[] vltd = {0, 2, 3};

    public void startActivity(Context context, PaymentInfoBean paymentInfoBean) {
        Intent intent = new Intent(context, DecomposeFundActivity.class);
        intent.putExtra("data", paymentInfoBean);
        context.startActivity(intent);
    }

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_decompose_payment);
        ButterKnife.bind(this);
        Intent intent = getIntent();
        paymentInfoBean = intent.getParcelableExtra("data");
        benciheji =Integer.parseInt(paymentInfoBean.getJbee());
        LinearLayoutManager linearLayoutManager = new LinearLayoutManager(this);
        recyclerview.setLayoutManager(linearLayoutManager);
        initData();
        initHandler();
    }

    private void initHandler() {
        handler = new Handler(){
            public void handleMessage(Message msg){
                switch (msg.what){
                    case 0:
                        hvKrDkDjBeanList = modelHvkrDkdjBean.getWithCustomerId(paymentInfoBean.getCustomerId(), vltd);
                        decomposeAdapter.notifyDataSetChanged();
                        break;
                    default:
                        break;
                }
            }
        };
    }

    private void initData() {
        modelHvkrDkdjBean = ModelHvkrDkdjBean.getInstance();
        modelHvkrDkdjBean.setListener(new GetDataDoneListener() {
            @Override
            public void onFinish() {
                Message msg = new Message();
                msg.what = 0;
                handler.sendMessage(msg);
            }
        });
        hvKrDkDjBeanList = modelHvkrDkdjBean.getWithCustomerId(paymentInfoBean.getCustomerId(), vltd);
        decomposeAdapter = new DecomposeAdapter(R.layout.yewuhvkr, hvKrDkDjBeanList);
        recyclerview.setAdapter(decomposeAdapter);
        decomposeAdapter.bindToRecyclerView(recyclerview);
        heji.setText(benciheji + "元");
        chooseContact.setFootClick(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                new ContactForOne().startActivity(DecomposeFundActivity.this, 1);
            }
        });
        ufpirf.add("yrzlm");
        ufpirf.add("yrzzq");
        chooseContact.addData(contact.getContact(ufpirf));
    }

    @Override
    protected void onActivityResult(int requestCode, int resultCode, Intent data) {
        if (resultCode == RESULT_OK){
            switch (requestCode) {
                case 1:
                    ContactBean contactBean = (ContactBean) data.getParcelableExtra("data");
                    chooseContact.addData(contactBean);
                    break;
                default:
                    break;
            }
        }
    }

    @OnClick(R.id.tijnufqk)
    public void onViewClicked() {
        if (isValid()){
            return;
        }
        postData();
    }

    private boolean isValid() {
        int heji = 0;
        for (int i = 0; i< decomposeAdapter.getItemCount(); i++){
            CheckBox checkBox = (CheckBox) decomposeAdapter.getViewByPosition(i, R.id.checkbox);
            EditText editText = (EditText) decomposeAdapter.getViewByPosition(i, R.id.benci);
            if (checkBox.isChecked()){
                heji += Integer.parseInt(editText.getText().toString());
            }
        }
        if (heji > benciheji){
            Toast.makeText(this,
                    "金额合计超过本次回款金额，本次回款金额"+ benciheji+"元",
                    Toast.LENGTH_LONG).show();
            return true;
        }
        else if(heji < benciheji){
            Toast.makeText(this,
                    "金额合计小于本次回款金额，本次回款金额"+ benciheji+"元",
                    Toast.LENGTH_LONG).show();
            return true;
        }
        if (chooseContact.getCount() == 0){
            Toast.makeText(this, "没有选择审批人", Toast.LENGTH_SHORT).show();
            return true;
        }
        return false;
    }

    private void postData(){
        FormBody.Builder builder = new FormBody.Builder();
        builder.add("userid", UserInfo.userid)
                .add("uijmio", paymentInfoBean.getUijm())
                .add("ufpi", chooseContact.getUfPiRf())
                .add("riqi", new TimeUtil().getCurrentDate())
                .add("data", getHvkrXbxi());
        new HttpUtil().PostMultyHuiKuan(builder, new Callback() {
            @Override
            public void onFailure(Call call, IOException e) {
                e.printStackTrace();
            }

            @Override
            public void onResponse(Call call, Response response) throws IOException {
                String data = response.body().string();
                try{
                    JSONObject jsonObject = new JSONObject(data);
                    if (jsonObject.getInt("error") == 1){
                        DecomposeFundActivity.this.finish();
                    }
                }catch (Exception e){
                    e.printStackTrace();
                }

            }
        });
    }

    private String getHvkrXbxi(){
        StringBuilder stringBuilder = new StringBuilder();
        stringBuilder.append("[");
        HvKrDkDjBean hvKrDkDjBean;
        for (int i = 0; i< decomposeAdapter.getItemCount(); i++){
            hvKrDkDjBean = decomposeAdapter.getItem(i);
            CheckBox checkBox = (CheckBox) decomposeAdapter.getViewByPosition(i, R.id.checkbox);
            EditText editText = (EditText) decomposeAdapter.getViewByPosition(i, R.id.benci);
            if (checkBox.isChecked()){
//                {"dkdjbmhc": "YR111111","hvkrjbee":"1000"}

                stringBuilder.append("{\"dkdjbmhc\":\"")
                        .append(hvKrDkDjBean.getDkdj().getDkdjxbxi().getDkdjbmhc())
                        .append("\",\"hvkrjbee\":")
                        .append(editText.getText().toString())
                        .append("},");
            }
        }
        stringBuilder.deleteCharAt(stringBuilder.length()-1);
        stringBuilder.append("]");
        return stringBuilder.toString();
    }
}
