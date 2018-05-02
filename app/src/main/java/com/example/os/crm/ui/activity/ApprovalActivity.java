package com.example.os.crm.ui.activity;

import android.content.Context;
import android.content.Intent;
import android.os.Handler;
import android.os.Message;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.util.Log;
import android.view.View;
import android.widget.AdapterView;
import android.widget.Button;
import android.widget.EditText;
import android.widget.ListView;
import android.widget.Toast;

import com.afollestad.materialdialogs.MaterialDialog;
import com.example.os.crm.Common.UserInfo;
import com.example.os.crm.Dingdan.Model.OrderInfoList;
import com.example.os.crm.Dingdan.Activity.DingdanDetail;
import com.example.os.crm.HttpUtil.HttpGet;
import com.example.os.crm.HttpUtil.HttpPost;
import com.example.os.crm.R;
import com.example.os.crm.ui.adapter.Approval_WaitAdapter;
import com.example.os.crm.model.Approval;

import org.json.JSONArray;
import org.json.JSONObject;
import java.io.IOException;
import java.util.ArrayList;
import java.util.Collections;
import java.util.Comparator;
import java.util.List;
import okhttp3.Call;
import okhttp3.Callback;
import okhttp3.FormBody;

/**
 * "功能"内的审批
 */
public class ApprovalActivity extends AppCompatActivity {

    ListView listView;
    private int type;
    private String TAG = "待审批窗口";
    private Button udxr;
    Handler handler;
    private List<Approval> approvalList = new ArrayList<>();
    private Approval approval_1;

    public void startActivity(Context context,String respnse){
        Intent intent = new Intent(context, ApprovalActivity.class);
        intent.putExtra("response", respnse);
        context.startActivity(intent);
    }

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.dai_shen_pi);
        initUi();
        initHandler();
        Intent intent = getIntent();
        String response = intent.getStringExtra("response");
        CreateItems(response);
//        get_item_all();

    }

    private void initHandler() {
        handler = new Handler(){
            public void handleMessage(Message msg){
                switch (msg.what){
                    case 0:
                        CreateItems(msg.obj.toString());
                        break;
                    case 2:
                        close();
                        break;
                    case 4:
                        get_item_all();
                        break;
                    default:
                        break;
                }
            }
        };
    }

    private void initUi() {
        listView = (ListView) findViewById(R.id.alldaishenpi);
        udxr = (Button) findViewById(R.id.udxr);
        udxr.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                new Approval_FilterActivity().startActivity(ApprovalActivity.this);
            }
        });
        udxr.setVisibility(View.GONE);
    }

    @Override
    protected void onRestart(){
        super.onRestart();
//        get_item_all();
    }

    private void get_item_all(){

        String url = UserInfo.host + "/crm/admin/allweishenpi/" + UserInfo.userid;
        new HttpGet().getData(url, new Callback() {
            @Override
            public void onFailure(Call call, IOException e) {
                Message msg = new Message();
                msg.what = 1;
                handler.sendMessage(msg);
            }

            @Override
            public void onResponse(Call call, okhttp3.Response response) throws IOException {
                String responseData = response.body().string();
                Message msg = new Message();
                msg.what = 0;
                msg.obj = responseData;
                handler.sendMessage(msg);
                Log.d("serData", responseData);
            }
        });
    }

    private void CreateItems(String response){
        listView.setAdapter(null);
        approvalList.clear();
        try{
            JSONObject jsonObject = new JSONObject(response);
            if(jsonObject.getInt("error") == 1){
                JSONArray jsonArray = jsonObject.getJSONArray("data");
                if(jsonArray.length() == 0){
                    alert_no_weishenpi();
                }
                for(int i = 0; i< jsonArray.length();i ++) {
                    JSONObject jsonObject1 = jsonArray.getJSONObject(i);
                    Approval approval = new Approval();
                    approval.setDingdanzonge(jsonObject1.getString("zongjia"));
                    type = jsonObject1.getInt("type");
                    approval.setType(type);

                    approval.setDingdanbianhao(jsonObject1.getString("dingdanbianhao"));
                    approval.setTimestamp(jsonObject1.getString("uijmio"));

                    approval.setUserId(jsonObject1.getString("userid"));
                    approval.setKehumingcheng(jsonObject1.getString("kehumingcheng"));
                    approval.setYewuxingming(jsonObject1.getString("name"));
                    int ufpivltd = jsonObject1.getInt("vltd");
                    approval.setVltd(ufpivltd);
                    approvalList.add(approval);
                }

                Collections.sort(approvalList, new Comparator<Approval>() {
                    @Override
                    public int compare(Approval o1, Approval o2) {
                        Long i =  Long.parseLong(o2.getTimestamp()) - Long.parseLong(o1.getTimestamp());
                        return i.intValue();
                    }
                });

                Approval_WaitAdapter daiShenPiAdapter = new Approval_WaitAdapter(ApprovalActivity.this,R.layout.ddufpi_item_dkdj, approvalList);
                listView.setAdapter(daiShenPiAdapter);
                listView.setOnItemLongClickListener(new AdapterView.OnItemLongClickListener() {
                    @Override
                    public boolean onItemLongClick(AdapterView<?> parent, View view, int position, long id) {
                        Approval approval = approvalList.get(position);
                        String ddbianhao = approval.getDingdanbianhao();
                        switch (approval.getType()){
                            case 0:
                                OrderInfoList orderInfoList = OrderInfoList.getInstance();
                                new DingdanDetail().startActivity(ApprovalActivity.this,
                                        orderInfoList.getDkdj(ddbianhao));
                                break;
                            case 1:
                                new HuiKuanDetailActivity().startActivity(ApprovalActivity.this, ddbianhao);
                                break;
                            default:
                                break;
                        }
                        return true;
                    }

                });
                listView.setOnItemClickListener(new AdapterView.OnItemClickListener() {
                    @Override
                    public void onItemClick(AdapterView<?> parent, View view, int position, long id) {
                        approval_1 = approvalList.get(position);
                        CreateUfPiDialog();
                    }
                });
            }
        }catch (Exception e){
            e.printStackTrace();
        }
    }

    private void CreateUfPiDialog() {
        final EditText bwvu;
        Button tsyi;
        Button quxn;
        Button bohv;
        final MaterialDialog dialog =
                new MaterialDialog.Builder(ApprovalActivity.this)
                        .title("shenpi")
                        .customView(R.layout.ufpidialog, true)
                        .build();
        //noinspection ConstantConditions
        bwvu = dialog.getCustomView().findViewById(R.id.ufpibwvu);
        tsyi = dialog.getCustomView().findViewById(R.id.tsyi);
        tsyi.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Log.d(TAG, "onClick: " + bwvu.getText().toString());
                tongyi(approval_1.getType(),1, bwvu.getText().toString());
                dialog.dismiss();
            }
        });
        bohv = dialog.getCustomView().findViewById(R.id.bohv);
        bohv.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                tongyi(approval_1.getType(),2, bwvu.getText().toString());
                dialog.dismiss();
            }
        });
        quxn = dialog.getCustomView().findViewById(R.id.quxn);
        quxn.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                dialog.dismiss();
            }
        });
        switch (approval_1.getVltd()){
            case 1:
                tsyi.setClickable(false);
                break;
            case 2:
                bohv.setClickable(false);
                break;
            default:
                break;
        }
        dialog.show();
    }

    private void alert_no_weishenpi(){
        Toast.makeText(this, "似乎没有待审批的项目", Toast.LENGTH_SHORT).show();
    }

    private void close(){
        this.finish();
    }

    private void tongyi(int type, int shenpi, String bwvu){
        if (type == 0){
            tongyi_dingdan(String .valueOf(shenpi), bwvu);
        }
        else if(type == 1){
            tongyi_huikuan(String .valueOf(shenpi), bwvu);
        }
    }

    private void tongyi_dingdan(String shenpi, String bwvu){

        String url = UserInfo.host + "/crm/admin/dingdanshenpi";

        FormBody.Builder builder = new FormBody.Builder()
                .add("dingdanbianhao", approval_1.getDingdanbianhao())
                .add("shenpi", shenpi)
                .add("shenpibeizhu", bwvu)
                .add("userid", UserInfo.userid);
        new HttpPost().postData(url, builder, new Callback() {
            @Override
            public void onFailure(Call call, IOException e) {

            }

            @Override
            public void onResponse(Call call, okhttp3.Response response) throws IOException {
                String responseData = response.body().string();
                Message msg = new Message();
                msg.what = 4;
                handler.sendMessage(msg);
                Log.d("postdata", responseData);
            }
        });
    }

    private void tongyi_huikuan(String shenpi, String bwvu){

        String url = UserInfo.host + "/crm/admin/huikuanshenpi";
        FormBody.Builder builder = new FormBody.Builder()
                .add("uijmio", approval_1.getTimestamp())
                .add("shenpi", shenpi)
                .add("shenpibeizhu", bwvu)
                .add("userid", UserInfo.userid);
        new HttpPost().postData(url, builder, new Callback() {
            @Override
            public void onFailure(Call call, IOException e) {
                Log.d("获取待审批数据", "onFailure: get data failed");
            }

            @Override
            public void onResponse(Call call, okhttp3.Response response) throws IOException {
                String data = response.body().string();
                Message msg = new Message();
                msg.what = 4;
                msg.obj = data;
                handler.sendMessage(msg);
            }
        });
    }
}
