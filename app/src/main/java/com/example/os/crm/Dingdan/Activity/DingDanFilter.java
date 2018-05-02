package com.example.os.crm.Dingdan.Activity;

import android.content.Intent;
import android.os.Bundle;
import android.os.Handler;
import android.os.Message;
import android.support.v7.app.AppCompatActivity;
import android.util.Log;
import android.view.View;
import android.widget.Button;
import android.widget.CheckBox;
import android.widget.CompoundButton;
import android.widget.EditText;
import android.widget.LinearLayout;
import android.widget.Toast;

import com.bigkoo.pickerview.TimePickerView;
import com.example.os.crm.Common.TimeUtil;
import com.example.os.crm.Common.UserInfo;
import com.example.os.crm.Contact.Model.ContactBean;
import com.example.os.crm.Contact.Activity.ContactForMulty;
import com.example.os.crm.HttpUtil.HttpPost;
import com.example.os.crm.ui.widget.ChooseContact;
import com.example.os.crm.R;

import java.io.IOException;
import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.Date;
import java.util.List;
import java.util.Locale;

import butterknife.BindView;
import butterknife.ButterKnife;
import butterknife.OnClick;
import okhttp3.Call;
import okhttp3.Callback;
import okhttp3.FormBody;
import okhttp3.Response;

public class DingDanFilter extends AppCompatActivity {

    @BindView(R.id.shaixuan_starttime)
    EditText shaixuanStarttime;
    @BindView(R.id.shaixuan_endtime)
    EditText shaixuanEndtime;
    @BindView(R.id.shaixuan_queding)
    Button shaixuanQueding;
    @BindView(R.id.shaixuan_quxiao)
    Button shaixuanQuxiao;
    @BindView(R.id.filter_yitongyi)
    CheckBox filterYitongyi;
    @BindView(R.id.filter_yibohui)
    CheckBox filterYibohui;
    @BindView(R.id.filter_weishenpi)
    CheckBox filterWeishenpi;
    @BindView(R.id.filter_yiwancheng)
    CheckBox filterYiwancheng;
    @BindView(R.id.filter_weiwancheng)
    CheckBox filterWeiwancheng;
    @BindView(R.id.filter_wanzheng)
    CheckBox filterWanzheng;
    @BindView(R.id.filter_buwanzheng)
    CheckBox filterBuwanzheng;
    @BindView(R.id.filter_jibenwancheng)
    CheckBox filterJibenwancheng;
    @BindView(R.id.filter_zhibaojin_daoqi)
    CheckBox filterZhibaojinDaoqi;
    @BindView(R.id.filter_zhibaojin_weidaoqi)
    CheckBox filterZhibaojinWeidaoqi;
    @BindView(R.id.zhibaojindaoqi)
    LinearLayout zhibaojindaoqi;
    @BindView(R.id.choose_contact)
    ChooseContact chooseContact;

    private TimePickerView pvTime;
    private Handler handler;
    private long start_time;


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_ding_dan_filter);
        ButterKnife.bind(this);
        initUi();
        TimepickInit();
        handler = new Handler() {
            public void handleMessage(Message msg) {
                switch (msg.what) {
                    case 0:
                        new DingDanFilterResult().startActivity(DingDanFilter.this, msg.obj.toString());
                        break;
                    default:
                        break;
                }
            }
        };
    }

    private void initUi() {
        zhibaojindaoqi.setVisibility(View.GONE);
        Date time_end = new Date();
        long end_time = System.currentTimeMillis();
        if (end_time < 31536000000L) {
            end_time = 31536000000L;
        }
        start_time = end_time - 31536000000L;
        Date time_start = new Date(start_time);
        SimpleDateFormat formatter = new SimpleDateFormat("yyyy年MM月dd日", Locale.CHINA);
        String dateString_end = formatter.format(time_end);
        String dateString_start = formatter.format(time_start);
        shaixuanEndtime.setText(dateString_end);
        shaixuanStarttime.setText(dateString_start);
        shaixuanStarttime.setFocusable(false);
        shaixuanStarttime.setFocusableInTouchMode(false);
        shaixuanEndtime.setFocusableInTouchMode(false);
        shaixuanEndtime.setFocusable(false);

        if (!UserInfo.userid.equals("test1")){
            chooseContact.setVisibility(View.GONE);
        }
        else {
            chooseContact.setTitle("选择业务员");
            chooseContact.setFootClick(new View.OnClickListener() {
                @Override
                public void onClick(View v) {
                    new ContactForMulty().startActivity(DingDanFilter.this, 1);
                }
            });
        }
        filterJibenwancheng.setOnCheckedChangeListener(new CompoundButton.OnCheckedChangeListener() {
            @Override
            public void onCheckedChanged(CompoundButton buttonView, boolean isChecked) {

                if (isChecked) {
                    zhibaojindaoqi.setVisibility(View.VISIBLE);
                    filterZhibaojinWeidaoqi.setChecked(true);
                    filterZhibaojinDaoqi.setChecked(true);
                } else {
                    zhibaojindaoqi.setVisibility(View.GONE);
                    filterZhibaojinDaoqi.setChecked(false);
                    filterZhibaojinWeidaoqi.setChecked(false);
                }
            }
        });

    }

    @Override
    protected void onActivityResult(int requestCode, int resultCode, Intent data) {
        switch (requestCode){
            case 1:
                if (resultCode == RESULT_OK){
                    List<ContactBean> contactBeans = (List<ContactBean>) data.getSerializableExtra("data");
                    chooseContact.addData(contactBeans);
                }
                break;
        }
    }


    private void TimepickInit() {
        //时间选择器
        pvTime = new TimeUtil(this).getTimePicker();
    }

    @OnClick({R.id.shaixuan_starttime, R.id.shaixuan_endtime, R.id.shaixuan_queding, R.id.shaixuan_quxiao})
    public void onViewClicked(View view) {
        switch (view.getId()) {
            case R.id.shaixuan_starttime:
                showTimePicker(view);
                break;
            case R.id.shaixuan_endtime:
                showTimePicker(view);
                break;
            case R.id.shaixuan_queding:
                exe_filter();
                break;
            case R.id.shaixuan_quxiao:
                this.finish();
                break;
        }
    }

    private void showTimePicker(View view) {
        if (UserInfo.userid.equals("yrzzq")){
            pvTime.show(view);
        }
        else{
            Toast.makeText(this, "您不是管理员，只能查看近一年的订单", Toast.LENGTH_SHORT).show();
        }
    }

    private void showToast() {
        Toast.makeText(DingDanFilter.this, "非管理员只能查看自己的订单", Toast.LENGTH_SHORT).show();
    }

    private void exe_filter() {

        int[] iswanzheng = {0, 0};
        int[] isfinish = {0, 0, 0, 0};
        int[] shenpizhuangtai = {0, 0, 0};
        String[] time = {"", ""};

        if (filterBuwanzheng.isChecked()) {
            iswanzheng[0] = 1;
        }
        if (filterWanzheng.isChecked()) {
            iswanzheng[1] = 1;
        }

        /* 未完成 */
        if (filterWeiwancheng.isChecked()) {
            isfinish[0] = 1;
        }
        /* 已完成 */
        if (filterYiwancheng.isChecked()) {
            isfinish[1] = 1;
        }
        /* 基本完成 */
        if (filterJibenwancheng.isChecked()) {
            //基本完成未到期
            if (filterZhibaojinWeidaoqi.isChecked()) {
                isfinish[2] = 1;
            }
            //基本完成已到期
            if (filterZhibaojinDaoqi.isChecked()) {
                isfinish[3] = 1;
            }
        }

        /* 未审批 */
        if (filterWeishenpi.isChecked()) {
            shenpizhuangtai[0] = 1;
        }
        /* 已同意 */
        if (filterYitongyi.isChecked()) {
            shenpizhuangtai[1] = 1;
        }
        /* 已驳回 */
        if (filterYibohui.isChecked()) {
            shenpizhuangtai[2] = 1;
        }

        String starttime = shaixuanStarttime.getText().toString();
        String endtime = shaixuanEndtime.getText().toString();

        /*判断开始时间是否是一年内，如果不是一年内，且级别不是管理员级别，则重置开始时间为一年前的今天*/
        long selectStartTime = getTimeStamp(starttime);
        if (selectStartTime < start_time && UserInfo.level > 1) {
            SimpleDateFormat formatter = new SimpleDateFormat("yyyy年MM月dd日", Locale.CHINA);
            starttime = formatter.format(start_time);
        }

        time[0] = starttime.replaceAll("[年月]", "-").replace("日", "");
        time[1] = endtime.replaceAll("[年月]", "-").replace("日", "");

        postData(iswanzheng, isfinish, shenpizhuangtai, time);
    }

    public static long getTimeStamp(String timeString) {
        long l = 0;
        SimpleDateFormat sdf = new SimpleDateFormat("yyyy年MM月dd日", Locale.CHINA);
        Date d;
        try {
            d = sdf.parse(timeString);
            l = d.getTime();
        } catch (ParseException e) {
            e.printStackTrace();
        }
        return l;
    }

    private void postData(int[] iswanzheng, int[] isfinish, int[] shenpizhuangtai, String[] time) {
        String url = UserInfo.host + "/crm/dingdan/filter";
        FormBody.Builder builder = new FormBody.Builder();
        builder.add("finish", getString(isfinish));
        builder.add("wanzheng", getString(iswanzheng));
        builder.add("shenpi", getString(shenpizhuangtai));
        builder.add("time", getString(time));
        builder.add("fuzeyewu", getFuZeYeWu());
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

    public String getString(int[] data) {
        String temp = "";
        for (int i : data) {
            temp += String.valueOf(i);
            temp += ",";
        }
        temp = temp.substring(0, temp.length() - 1);
        return temp;
    }

    public String getString(String[] data) {
        String temp = "";
        for (String i : data) {
            temp += i;
            temp += ",";
        }
        temp = temp.substring(0, temp.length() - 1);
        return temp;
    }

    public String getFuZeYeWu() {
        return chooseContact.getUserId();
    }
}
