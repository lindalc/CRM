package com.example.os.crm.ui.activity;

import android.content.Context;
import android.content.DialogInterface;
import android.content.Intent;
import android.os.Handler;
import android.os.Looper;
import android.os.Message;
import android.support.v7.app.AlertDialog;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.util.Log;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.LinearLayout;
import android.widget.ListView;
import android.widget.TextView;
import android.widget.Toast;

import com.bigkoo.pickerview.TimePickerView;
import com.example.os.crm.Contact.Model.ContactBean;
import com.example.os.crm.Contact.Activity.ContactForOne;
import com.example.os.crm.Common.TimeUtil;
import com.example.os.crm.Common.UserInfo;
import com.example.os.crm.Dingdan.Activity.DingdanDetail;
import com.example.os.crm.HttpUtil.HttpGet;
import com.example.os.crm.HttpUtil.HttpPost;
import com.example.os.crm.ui.adapter.HuiKuanAdapter;
import com.example.os.crm.model.HuiKuanBean;
import com.example.os.crm.ui.widget.ChooseContact;
import com.example.os.crm.R;
import com.google.gson.Gson;

import org.json.JSONObject;

import java.io.IOException;

import okhttp3.Call;
import okhttp3.Callback;
import okhttp3.FormBody;

public class HuiKuanDetailActivity extends AppCompatActivity implements View.OnClickListener{

    EditText editText_new_riqi;
    EditText editText_new_jine;
    Button button_new_button;
    Button button_new_tijiao;
    Button button_new_cancel;
    LinearLayout linearLayout;

    TextView textView_dingdanbianhao;
    TextView textView_yingshouzhangkuan;
    TextView textView_weishouzhangkuan;
    TextView textView_zhibaojin;
    ListView listView_yishouxiangqing;
    LinearLayout linearLayout_1;
    ChooseContact chooseContact;

    String dingdanbianhao;

    Handler handler;

    private TimePickerView pvTime;
    private int type;
    HuiKuanBean huiKuanBean;
    HuiKuanAdapter huiKuanAdapter;

    public void startActivity(Context context, String dkdjbmhc){
        Intent intent = new Intent(context, HuiKuanDetailActivity.class);
        intent.putExtra("dkdjbmhc", dkdjbmhc);
        context.startActivity(intent);
    }

    public void startActivity(Context context, int type, HuiKuanBean huiKuanBean){
        Intent intent = new Intent(context, HuiKuanDetailActivity.class);
        intent.putExtra("type", type);
        intent.putExtra("data", huiKuanBean);
        context.startActivity(intent);
    }

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_hui_kuan_detail);
        Intent intent = getIntent();
        this.type = intent.getIntExtra("type", 0);
        if (type == 1){
            this.huiKuanBean = intent.getParcelableExtra("data");
        }
        else{
            dingdanbianhao = intent.getStringExtra("dkdjbmhc");
        }

        handler = new Handler(){
            public void handleMessage(Message msg){
                switch (msg.what){
                    case 0:
                        CreateList(msg.obj.toString());
                        break;
                    case 1:
                        run_alert();
                        break;
                    case 2:
                        init_list();
                        linearLayout.setVisibility(View.GONE);
                        button_new_button.setVisibility(View.VISIBLE);
                        break;
                    default:
                        break;
                }
            }
        };

        init_ui();
        TimepickInit();
        init_list();
    }

    @Override
    protected void onRestart(){
        super.onRestart();
    }

    private void init_ui(){
        chooseContact = (ChooseContact) findViewById(R.id.choose_contact);
        linearLayout = (LinearLayout) findViewById(R.id.hkd_addnew);
        editText_new_riqi = (EditText) findViewById(R.id.hkd_newriqi);
        editText_new_riqi.setOnClickListener(this);
        editText_new_jine = (EditText) findViewById(R.id.hkd_newjine);
        button_new_button = (Button) findViewById(R.id.hkd_new_button);
        button_new_button.setOnClickListener(this);
        //TODO 回款详情页是否显示提交回款功能
        button_new_button.setVisibility(View.GONE);
        button_new_tijiao = (Button) findViewById(R.id.hkd_new_tijiao);
        button_new_tijiao.setOnClickListener(this);
        button_new_cancel = (Button) findViewById(R.id.hkd_new_cancel);
        button_new_cancel.setOnClickListener(this);
        textView_dingdanbianhao = (TextView) findViewById(R.id.hkd_dingdanbianhao);
        textView_yingshouzhangkuan = (TextView) findViewById(R.id.hkd_yingshouzhangkuan);
        textView_weishouzhangkuan = (TextView) findViewById(R.id.hkd_weishouzhangkuan);
        textView_zhibaojin = (TextView) findViewById(R.id.hkd_zhibaojin);
        listView_yishouxiangqing = (ListView) findViewById(R.id.hkd_yishouxiangqing);
        linearLayout_1 = (LinearLayout) findViewById(R.id.jumptodingdandetail);
        linearLayout_1.setOnClickListener(this);

        linearLayout.setVisibility(View.GONE);

        chooseContact.setFootClick(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                new ContactForOne().startActivity(HuiKuanDetailActivity.this, 1);
            }
        });

//        button_new_button.setVisibility(View.GONE);
        //TODO 回款页面点击会跳转到审批部分，但是这部分功能似乎还没做好
//        listView_yishouxiangqing.setOnItemClickListener(new AdapterView.OnItemClickListener(){
//            @Override
//            public void onItemClick(AdapterView<?> parent, View view, int position, long id){
//                HuiKuanBean huiKuanDetail = huiKuanBeanList.get(position);
//                Intent intent = new Intent(HuiKuanDetailActivity.this, HuiKuanWeiShenPiActivity.class);
//                intent.putExtra("dingdanbianhao", dingdanbianhao);
//                intent.putExtra("timestamp", huiKuanDetail.getTimestamp());
//                startActivity(intent);
//            }
//        });
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

    @Override
    public void onClick(View v){
        switch (v.getId()){
            case R.id.hkd_new_button:
                Log.d("HuiKuanDetailActivity", "onClick: 添加新回款信息");
                button_new_button.setVisibility(View.GONE);
                linearLayout.setVisibility(View.VISIBLE);
                break;
            case R.id.hkd_new_tijiao:
                Log.d("HuiKuanDetailActivity", "onClick: 提交新回款");
                post_new_huikuan();
                break;
            case R.id.hkd_new_cancel:
                Log.d("HuiKuanDetailActivity", "onClick: 取消添加新回款信息");
                linearLayout.setVisibility(View.GONE);
                button_new_button.setVisibility(View.VISIBLE);
                break;
            case R.id.hkd_newriqi:
                pvTime.show(v);
                break;
            case R.id.jumptodingdandetail:
                Intent intent = new Intent(HuiKuanDetailActivity.this, DingdanDetail.class);
                intent.putExtra("dkdjbmhc", dingdanbianhao);
                startActivity(intent);
                break;
            default:
                break;
        }
    }

    private void TimepickInit(){
        pvTime = new TimeUtil(this).getTimePicker();
    }

    private void init_list(){
        if (type == 1){
            CreateList();
        }
        else{
            String url = UserInfo.host + "/crm/huikuandetail/" + dingdanbianhao;
            new HttpGet().getData(url, new Callback() {
                @Override
                public void onFailure(Call call, IOException e) {
                    e.printStackTrace();
                }

                @Override
                public void onResponse(Call call, okhttp3.Response response) throws IOException {
                    Message msg = new Message();
                    msg.what = 0;
                    msg.obj = response.body().string();
                    handler.sendMessage(msg);
                }
            });
        }
    }

    private void CreateList(String response){
        try{
            JSONObject jsonObject = new JSONObject(response);
            if(jsonObject.getInt("error") == 1){
                JSONObject jsonObject1 = jsonObject.getJSONObject("data");
                Gson gson = new Gson();
                huiKuanBean = gson.fromJson(jsonObject1.toString(), HuiKuanBean.class);
                huiKuanAdapter = new HuiKuanAdapter(this,R.layout.huikuandetail,
                        huiKuanBean.getHvkrliui());
                listView_yishouxiangqing.setAdapter(huiKuanAdapter);
                textView_dingdanbianhao.setText(huiKuanBean.getDkdjbmhc());
                textView_weishouzhangkuan.setText(huiKuanBean.getWwuz() + "");
                textView_yingshouzhangkuan.setText(huiKuanBean.getZsjx());
            }
        }catch (Exception e){
            e.printStackTrace();
        }
    }

    private void CreateList(){
        huiKuanAdapter = new HuiKuanAdapter(this,R.layout.huikuandetail,
                huiKuanBean.getHvkrliui());
        listView_yishouxiangqing.setAdapter(huiKuanAdapter);
        textView_dingdanbianhao.setText(huiKuanBean.getDkdjbmhc());
        textView_weishouzhangkuan.setText(huiKuanBean.getWwuz() + "");
        textView_yingshouzhangkuan.setText(huiKuanBean.getZsjx());
    }

    private void post_new_huikuan(){
        if (editText_new_riqi.length()<1){
            Toast.makeText(this, "回款日期不能为空",Toast.LENGTH_SHORT).show();
            return;
        }
        if(editText_new_jine.length()<1){
            Toast.makeText(this, "回款金额不能为空",Toast.LENGTH_SHORT).show();
            return;
        }
        String url = UserInfo.host + "/crm/huikuandetail";
        FormBody.Builder builder = new FormBody.Builder()
                .add("userid", UserInfo.userid)
                .add("dkdjbmhc", dingdanbianhao)
                .add("hvkrjbee", editText_new_jine.getText().toString())
                .add("hvkrriqi", editText_new_riqi.getText().toString())
                .add("ufpi", chooseContact.getUfPiRf());
        new HttpPost().postData(url, builder, new Callback() {
            @Override
            public void onFailure(Call call, IOException e) {
                e.printStackTrace();
                createToast("回款信息添加失败");
            }

            @Override
            public void onResponse(Call call, okhttp3.Response response) throws IOException {
                createToast("回款信息添加成功");
            }
        });
    }

    private void createToast(String info){
        Looper.prepare();
        Toast.makeText(HuiKuanDetailActivity.this, info, Toast.LENGTH_SHORT).show();
        Message msg = new Message();
        msg.what = 2;
        handler.sendMessage(msg);
        Looper.loop();
    }

    private void run_alert(){
        AlertDialog.Builder dialog = new AlertDialog.Builder(HuiKuanDetailActivity.this);
        dialog.setTitle("警告");
        dialog.setMessage("网络连接失败，请重试，如果多次重试仍不能解决，请联系服务器管理员");
        dialog.setCancelable(false);
        dialog.setPositiveButton("知道了", new DialogInterface.OnClickListener() {
            @Override
            public void onClick(DialogInterface dialog, int which) {

            }
        });
        dialog.show();
    }
}
