package com.example.os.crm.Contact.Activity;

import android.content.Context;
import android.content.DialogInterface;
import android.content.Intent;
import android.os.Bundle;
import android.os.Handler;
import android.os.Message;
import android.support.v7.app.AlertDialog;
import android.support.v7.app.AppCompatActivity;
import android.util.Log;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;

import com.example.os.crm.Common.UserInfo;
import com.example.os.crm.Contact.Model.ContactBean;
import com.example.os.crm.Contact.Model.CustomerBean;
import com.example.os.crm.HttpUtil.HttpUtil;
import com.example.os.crm.ui.widget.ChooseContact;
import com.example.os.crm.R;
import com.google.gson.Gson;

import org.json.JSONObject;

import java.io.IOException;

import butterknife.BindView;
import butterknife.ButterKnife;
import butterknife.OnClick;
import okhttp3.Call;
import okhttp3.Callback;
import okhttp3.FormBody;
import okhttp3.Response;

public class CreateUpdateCustomerInfo extends AppCompatActivity{


    @BindView(R.id.kehu_mingcheng)
    EditText kehuMingcheng;
    @BindView(R.id.kehu_lianxiren)
    EditText kehuLianxiren;
    @BindView(R.id.kehu_dianhua)
    EditText kehuDianhua;
    @BindView(R.id.kehu_email)
    EditText kehuEmail;
    @BindView(R.id.kehu_qq)
    EditText kehuQq;
    @BindView(R.id.kehu_wechat)
    EditText kehuWechat;
    @BindView(R.id.kehu_dizhi)
    EditText kehuDizhi;
    @BindView(R.id.kehu_hangye)
    EditText kehuHangye;
    @BindView(R.id.kehu_beizhu)
    EditText kehuBeizhu;
    @BindView(R.id.addufpi)
    ChooseContact addufpi;
    @BindView(R.id.kehu_tijiao)
    Button kehuTijiao;
    @BindView(R.id.kehu_quxiao)
    Button kehuQuxiao;

    private String userId;
    private String kehuId;
    private Handler handler;

    public void startActivity(Context context, String kehuid){
        Intent intent = new Intent(context, CreateUpdateCustomerInfo.class);
        intent.putExtra("kehuid", kehuid);
        context.startActivity(intent);
    }

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_new_ke_hu);
        ButterKnife.bind(this);
        Intent intent = getIntent();
        userId = UserInfo.userid;
        kehuId = intent.getStringExtra("kehuid");

        initHandler();
        init_ui();
        if (kehuId.length() > 0) {
            get_kehu_data();
        }
    }

    private void initHandler() {
        handler = new Handler() {

            public void handleMessage(Message msg) {
                switch (msg.what) {
                    case 0:
                        run_alert("创建客户资料成功！", true);
                        kehuTijiao.setClickable(true);
                        break;
                    case 1:
                        run_alert("创建客户资料失败，请检查网络并重试！", false);
                        break;
                    case 2:
                        close_activity();
                        break;
                    case 3:
                        set_kehu_data(msg.obj.toString());
                        break;
                    default:
                        break;
                }
            }
        };
    }

    private void close_activity() {
        this.finish();
    }

    private void init_ui() {
        addufpi.setFootClick(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                new ContactForOne().startActivity(CreateUpdateCustomerInfo.this, 1);
            }
        });
        if (kehuId.length() > 0) {
            addufpi.setVisibility(View.GONE);
            kehuTijiao.setText("更新用户信息");
        }
    }

    @Override
    protected void onActivityResult(int requestCode, int resultCode, Intent data){
        switch (requestCode){
            case 1:
                if (resultCode == RESULT_OK){
                    ContactBean contactBean = data.getParcelableExtra("data");
                    addufpi.addData(contactBean);
                }
        }
    }

    private void post_new_kehu() {

        if (!isValid()) {
            return;
        }

        FormBody.Builder builder = new FormBody.Builder();
        builder.add("kehuid", create_id())
                .add("userid", userId)
                .add("kehumingcheng", kehuMingcheng.getText().toString())
                .add("kehudizhi", kehuDizhi.getText().toString())
                .add("kehulianxiren", kehuLianxiren.getText().toString())
                .add("lianxirendianhua", kehuDianhua.getText().toString())
                .add("lianxirenqq", kehuQq.getText().toString())
                .add("lianxirenemail", kehuEmail.getText().toString())
                .add("lianxirenwechat", kehuWechat.getText().toString())
                .add("kehuhangye", kehuHangye.getText().toString())
                .add("beizhu", kehuBeizhu.getText().toString())
                .add("ufpi", addufpi.getUfPiRf());
        new HttpUtil().createNewCustomer(builder, new Callback() {
            @Override
            public void onFailure(Call call, IOException e) {
                e.printStackTrace();
                Message msg = new Message();
                msg.what = 1;
                handler.sendMessage(msg);
                Log.e("postdata", "setData: 读取失败");
            }

            @Override
            public void onResponse(Call call, Response response) throws IOException {
                String responseData = response.body().string();
                Message msg = new Message();
                msg.what = 0;
                msg.obj = responseData;
                handler.sendMessage(msg);
                Log.d("postdata", responseData);
            }
        });
    }

    private String create_id() {
        if (kehuId.length() > 0) {
            return kehuId;
        } else {
            long timeSeconds = System.currentTimeMillis();
            return timeSeconds + "";
        }
    }

    private void run_alert(String info, final boolean bool) {
        AlertDialog.Builder dialog = new AlertDialog.Builder(CreateUpdateCustomerInfo.this);
        dialog.setTitle("警告");
        dialog.setMessage(info);
        dialog.setCancelable(false);
        dialog.setPositiveButton("知道了", new DialogInterface.OnClickListener() {
            @Override
            public void onClick(DialogInterface dialog, int which) {
                if (bool) {
                    Message msg = new Message();
                    msg.what = 2;
                    handler.sendMessage(msg);
                }
            }
        });
        dialog.show();
    }

    private void run_alert(String info) {
        AlertDialog.Builder dialog = new AlertDialog.Builder(CreateUpdateCustomerInfo.this);
        dialog.setTitle("警告");
        dialog.setMessage(info);
        dialog.setCancelable(false);
        dialog.setPositiveButton("知道了", new DialogInterface.OnClickListener() {
            @Override
            public void onClick(DialogInterface dialog, int which) {

            }
        });
        dialog.show();
    }

    private void get_kehu_data() {

        new HttpUtil().getCustomerDetail(kehuId, new Callback() {
            @Override
            public void onFailure(Call call, IOException e) {
                e.printStackTrace();
                Message msg = new Message();
                msg.what = 4;
                handler.sendMessage(msg);
            }

            @Override
            public void onResponse(Call call, Response response) throws IOException {
                String responseData = response.body().string();
                Message msg = new Message();
                msg.what = 3;
                msg.obj = responseData;
                handler.sendMessage(msg);
            }
        });
    }

    private void set_kehu_data(String response) {
        try {
            JSONObject jsonObject = new JSONObject(response);
            if (jsonObject.getInt("error") == 1) {
                Gson gson = new Gson();
                CustomerBean customerBean = gson.fromJson(jsonObject.getString("data"), CustomerBean.class);
                kehuMingcheng.setText(customerBean.getKehumingcheng());
                kehuBeizhu.setText(customerBean.getBeizhu());
                kehuHangye.setText(customerBean.getKehuhangye());
                kehuDizhi.setText(customerBean.getKehudizhi());
                kehuWechat.setText(customerBean.getLianxirenwechat());
                kehuQq.setText(customerBean.getLianxirenqq());
                kehuLianxiren.setText(customerBean.getKehulianxiren());
                kehuDianhua.setText(customerBean.getLianxirendianhua());
                kehuEmail.setText(customerBean.getLianxirenemail());
            }
        } catch (Exception e) {
            e.printStackTrace();
        }
    }

    private boolean isValid() {
        if (kehuMingcheng.length() == 0) {
            run_alert("客户名称不能为空");
            return false;
        }
        if (kehuLianxiren.length() == 0) {
            run_alert("联系人姓名不能为空");
            return false;
        }
        if (kehuDianhua.length() == 0) {
            run_alert("联系人电话不能为空");
            return false;
        }
        if (kehuDizhi.length() == 0) {
            run_alert("客户地址不能为空");
            return false;
        }
        return true;
    }

    @OnClick({R.id.kehu_tijiao, R.id.kehu_quxiao})
    public void onViewClicked(View view) {
        switch (view.getId()) {
            case R.id.kehu_tijiao:
                post_new_kehu();
                break;
            case R.id.kehu_quxiao:
                this.finish();
                break;
        }
    }
}
