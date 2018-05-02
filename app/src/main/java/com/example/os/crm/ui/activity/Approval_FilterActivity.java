package com.example.os.crm.ui.activity;

import android.content.Context;
import android.content.Intent;
import android.os.Bundle;
import android.os.Handler;
import android.os.Message;
import android.support.v7.app.AppCompatActivity;
import android.util.Log;
import android.view.View;
import android.widget.Button;
import android.widget.CheckBox;
import android.widget.EditText;
import com.bigkoo.pickerview.TimePickerView;
import com.example.os.crm.Common.TimeUtil;
import com.example.os.crm.Common.UserInfo;
import com.example.os.crm.Dingdan.Activity.DingDanFilter;
import com.example.os.crm.HttpUtil.HttpPost;
import com.example.os.crm.R;

import java.io.IOException;

import butterknife.BindView;
import butterknife.ButterKnife;
import butterknife.OnClick;
import okhttp3.Call;
import okhttp3.Callback;
import okhttp3.FormBody;
import okhttp3.Response;

/**
 * 审批筛选
 */
public class Approval_FilterActivity extends AppCompatActivity {

    @BindView(R.id.dkdj)
    CheckBox dkdj;
    @BindView(R.id.hvkr)
    CheckBox hvkr;
    @BindView(R.id.tsyi)
    CheckBox tsyi;
    @BindView(R.id.bohv)
    CheckBox bohv;
    @BindView(R.id.wwufpi)
    CheckBox wwufpi;
    @BindView(R.id.shaixuan_starttime)
    EditText shaixuanStarttime;
    @BindView(R.id.shaixuan_endtime)
    EditText shaixuanEndtime;
    @BindView(R.id.qtdk)
    Button qtdk;

    private TimePickerView pvTime;
    Handler handler;

    public void startActivity(Context context) {
        Intent intent = new Intent(context, Approval_FilterActivity.class);
        context.startActivity(intent);
    }

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_uf_pi_ud_xr);
        ButterKnife.bind(this);
        pvTime = new TimeUtil(this).getTimePicker();
        initUi();
        handler = new Handler(){
            public void handleMessage(Message msg){
                switch (msg.what){
                    case 0:
                        new ApprovalActivity().startActivity(Approval_FilterActivity.this, msg.obj.toString());
                        break;
                    default:
                        break;
                }
            }
        };
    }

    private void initUi() {
        shaixuanStarttime.setFocusable(false);
        shaixuanStarttime.setFocusableInTouchMode(false);
        shaixuanEndtime.setFocusableInTouchMode(false);
        shaixuanEndtime.setFocusable(false);
        shaixuanStarttime.setText(new TimeUtil().get7Daybefore());
        shaixuanEndtime.setText(new TimeUtil().getCurrentDate());
    }

    @OnClick({R.id.shaixuan_starttime, R.id.shaixuan_endtime, R.id.qtdk})
    public void onViewClicked(View view) {
        switch (view.getId()) {
            case R.id.shaixuan_starttime:
                pvTime.show(view);
                break;
            case R.id.shaixuan_endtime:
                pvTime.show(view);
                break;
            case R.id.qtdk:
                udxr();
                break;
        }
    }

    private void udxr(){
        //{未审批，已同意，已驳回}
        int[] vltd = {0,0,0};
        //{dkdj，回款}
        int[] type = {0,0};
        //{开始时间，结束时间}
        String time[] = {"",""};
        if (dkdj.isChecked()){
            type[0] = 1;
        }
        if (hvkr.isChecked()){
            type[1] = 1;
        }
        if (wwufpi.isChecked()){
            vltd[0] = 1;
        }
        if (tsyi.isChecked()){
            vltd[1] = 1;
        }
        if (bohv.isChecked()){
            vltd[2] = 1;
        }
        if (shaixuanStarttime.length()> 0 && shaixuanEndtime.length() > 0){
            time[0] = shaixuanStarttime.getText().toString();
            time[1] = shaixuanEndtime.getText().toString();
            time[0] = time[0].replaceAll("[年月]", "-").replace("日", "");
            time[1] = time[1].replaceAll("[年月]", "-").replace("日", "");
        }
        postData(type, vltd, time);
    }

    private void postData(int[] type, int[] vltd, String[] time){
        String url = UserInfo.host + "/crm/ufpi/filter";
        FormBody.Builder builder = new FormBody.Builder();
        builder.add("type", new DingDanFilter().getString(type));
        builder.add("vltd", new DingDanFilter().getString(vltd));
        builder.add("time", new DingDanFilter().getString(time));
        builder.add("userid", UserInfo.userid);

        new HttpPost().postData(url, builder, new Callback() {
            @Override
            public void onFailure(Call call, IOException e) {
                e.printStackTrace();
            }

            @Override
            public void onResponse(Call call, Response response) throws IOException {
                String data = response.body().string();
                Log.d("onResponse", "onResponse: " + data);
                Message msg = new Message();
                msg.what = 0;
                msg.obj = data;
                handler.sendMessage(msg);
            }
        });
    }
}
