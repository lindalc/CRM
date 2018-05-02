package com.example.os.crm.Charge.Activity;


import android.app.ProgressDialog;
import android.content.Context;
import android.content.Intent;
import android.os.Bundle;
import android.os.Handler;
import android.os.Message;
import android.support.v7.app.AppCompatActivity;
import android.util.Log;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.RadioButton;
import android.widget.RadioGroup;
import android.widget.TextView;
import android.widget.Toast;

import com.bigkoo.pickerview.TimePickerView;
import com.example.os.crm.Common.GetDataDoneListener;
import com.example.os.crm.Common.GlideEngine;
import com.example.os.crm.Common.TimeUtil;
import com.example.os.crm.Common.UserInfo;
import com.example.os.crm.Contact.Activity.ContactForOne;
import com.example.os.crm.Contact.Model.ContactBean;
import com.example.os.crm.Dingdan.Model.DkDjDetailInfoBean;
import com.example.os.crm.Dingdan.Model.OrderInfoList;
import com.example.os.crm.HttpUtil.HttpUtil;
import com.example.os.crm.ui.widget.ChooseContact;
import com.example.os.crm.ui.widget.ChoosePic;
import com.example.os.crm.R;
import com.huantansheng.easyphotos.EasyPhotos;
import com.huantansheng.easyphotos.models.album.entity.Photo;

import org.json.JSONObject;

import java.io.File;
import java.io.IOException;
import java.util.ArrayList;
import java.util.List;

import butterknife.BindView;
import butterknife.ButterKnife;
import butterknife.OnClick;
import okhttp3.Call;
import okhttp3.Callback;
import okhttp3.MediaType;
import okhttp3.MultipartBody;
import okhttp3.RequestBody;
import okhttp3.Response;

/**
 * Created by OS on 2018/3/5.
 */

public class NewChargeActivity extends AppCompatActivity {


    @BindView(R.id.shenqingshijian)
    EditText shenqingshijian;
    @BindView(R.id.shenqingliyou)
    EditText shenqingliyou;
    @BindView(R.id.btYes)
    RadioButton btYes;
    @BindView(R.id.btNo)
    RadioButton btNo;
    @BindView(R.id.SEX1)
    RadioGroup SEX1;
    @BindView(R.id.dingdanxinxi)
    EditText dingdanxinxi;
    @BindView(R.id.addpic)
    ChoosePic addpic;
    @BindView(R.id.addspr)
    ChooseContact addspr;
    @BindView(R.id.tijiaoshenqing)
    Button tijiaoshenqing;
    @BindView(R.id.jine)
    EditText jine;
    @BindView(R.id.shangchuanpingzheng)
    TextView shangchuanpingzheng;
    @BindView(R.id.title_back)
    Button titleBack;
    @BindView(R.id.title_text)
    TextView titleText;
    @BindView(R.id.title_edit)
    Button titleEdit;
    private TimePickerView timePickerView;
    private ProgressDialog progressDialog;
    private Handler handler;
    private int type;
    private List<DkDjDetailInfoBean> dkDjDetailInfoBeanList;
    private OrderInfoList orderInfoList;

    public void startActivity(Context context) {
        Intent intent = new Intent(context, NewChargeActivity.class);
        context.startActivity(intent);
    }

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_new_charge);
        ButterKnife.bind(this);
        Intent intent = getIntent();
        this.type = intent.getIntExtra("type", 0);
        if (type == 1) {
            shenqingshijian.setText(intent.getStringExtra("shenqingshijian"));
            shenqingshijian.setFocusable(false);
            shenqingshijian.setFocusableInTouchMode(false);
        }

        initDkDjList();
        initUi();
        initViewClick();
        initHandler();
    }

    private void initDkDjList() {
        orderInfoList = OrderInfoList.getInstance();
        dkDjDetailInfoBeanList = orderInfoList.getDkDjDetailInfoBeanList();
        orderInfoList.setListener(new GetDataDoneListener() {
            @Override
            public void onFinish() {

            }
        });
    }

    private void initUi() {
        titleText.setText("费用申请");
        timePickerView = new TimeUtil(this).getTimePicker();
        addspr.setFootClick(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                new ContactForOne().startActivity(NewChargeActivity.this, 1);
            }
        });
    }

    private void initViewClick() {
        View.OnClickListener onClickListener = new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                EasyPhotos.createAlbum(NewChargeActivity.this, true, GlideEngine.getInstance())
                        .setFileProviderAuthority("com.huantansheng.easyphotos.sample.fileprovider")
                        .setCount(9)
                        .start(101);
            }
        };
        addpic.setAddImageClick(onClickListener);
        addspr.setOnClickListener(onClickListener);
    }


    @OnClick({R.id.shenqingliyou, R.id.jine, R.id.addpic, R.id.tijiaoshenqing, R.id.dingdanxinxi, R.id.shenqingshijian})
    public void onViewClicked(View view) {
        switch (view.getId()) {
            case R.id.shenqingliyou:
                break;
            case R.id.addpic:
                break;
            case R.id.dingdanxinxi:
                break;
            case R.id.jine:
                break;
            case R.id.shenqingshijian:
                timePickerView.show(view);
                break;
            case R.id.tijiaoshenqing:
                uploadFiles();
                break;

        }
    }

    private void initHandler() {
        handler = new Handler() {
            public void handleMessage(Message msg) {
                switch (msg.what) {
                    case 0:
                        Toast.makeText(NewChargeActivity.this, "该订单已有合同文件，上传失败！", Toast.LENGTH_SHORT).show();
                        break;
                    case 1:
                        Toast.makeText(NewChargeActivity.this, "上传成功", Toast.LENGTH_SHORT).show();
                        if (type == 1) {
                            Intent intent = getIntent();
                            intent.putExtra("shenqingliyou", shenqingliyou.getText().toString());
                            setResult(RESULT_OK, intent);
                        }
                        finish();
                        break;
                    default:
                        break;
                }
            }
        };
    }

    @Override
    protected void onActivityResult(int requestCode, int resultCode, Intent data) {
        super.onActivityResult(requestCode, resultCode, data);
        if (RESULT_OK == resultCode) {
            switch (requestCode) {
                case 101:
                    //返回对象集合：如果你需要了解图片的宽、高、大小、用户是否选中原图选项等信息，可以用这个
                    ArrayList<Photo> resultPhotos = data.getParcelableArrayListExtra(EasyPhotos.RESULT_PHOTOS);
                    addpic.addData(resultPhotos);
                    break;
                case 1:
                    ContactBean contactBean = data.getParcelableExtra("data");
                    addspr.addData(contactBean);
                    break;
            }
        }
    }

    private void uploadFiles() {
        if (isValid()) return;
        MultipartBody.Builder builder = new MultipartBody.Builder().setType(MultipartBody.FORM);
        builder.addFormDataPart("dingdanbianhao", shenqingshijian.getText().toString())
                .addFormDataPart("shenqingliyou", shenqingliyou.getText().toString())
                .addFormDataPart("jbee", jine.getText().toString())
                .addFormDataPart("userid", UserInfo.userid)
                .addFormDataPart("phvg", shangchuanpingzheng.getText().toString())
                .addFormDataPart("ufpi", addspr.getUfPiRf());
        List<Photo> tImageList = addpic.getAllItem();
        for (Photo tImage : tImageList) {
            String filepath = tImage.path;
            File file = new File(filepath);
            RequestBody filebody = RequestBody.create(MediaType.parse("application/octet-stream"), file);
            String filename = filepath.substring(filepath.lastIndexOf("/") + 1, filepath.length());
            builder.addFormDataPart("files", filename, filebody);
        }
        new HttpUtil().tijiaoshenqing(builder, new Callback() {
            @Override
            public void onFailure(Call call, IOException e) {
                e.printStackTrace();
            }

            @Override
            public void onResponse(Call call, Response response) throws IOException {
                String res = response.body().string();
                try {
                    JSONObject jsonObject = new JSONObject(res);
                    Message msg;
                    switch (jsonObject.getInt("error")) {
                        case 0:
                            msg = new Message();
                            msg.what = 0;
                            handler.sendMessage(msg);
                            break;
                        case 1:
                            msg = new Message();
                            msg.what = 1;
                            handler.sendMessage(msg);
                            break;
                        default:
                            break;
                    }
                } catch (Exception e) {
                    e.printStackTrace();
                }
                Log.i("NewCharge", "uploadMultiFile() response=" + res);
            }
        });
    }

    private boolean isValid() {
        if (addpic.getCount() == 0) {
            Toast.makeText(this, "没有选择文件", Toast.LENGTH_SHORT).show();
            return true;
        }
        if (shenqingshijian.length() == 0) {
            Toast.makeText(this, "申请时间不能为空", Toast.LENGTH_SHORT).show();
            return true;
        }
        if (shenqingliyou.length() == 0) {
            Toast.makeText(this, "申请理由不能为空", Toast.LENGTH_SHORT).show();
            return true;
        }
        if (addspr.getCount() == 0) {
            Toast.makeText(this, "不能没有审批人", Toast.LENGTH_LONG).show();
        }
        if (jine.length() == 0) {
            Toast.makeText(this, "金额不能为空", Toast.LENGTH_LONG).show();
        }
        return false;
    }

}
