package com.example.os.crm.Contract.Activity;


import android.app.Activity;
import android.app.ProgressDialog;
import android.content.Context;
import android.content.Intent;
import android.os.Bundle;
import android.os.Handler;
import android.os.Message;
import android.support.v4.widget.SwipeRefreshLayout;
import android.support.v7.app.AppCompatActivity;
import android.util.Log;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
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

public class AddContract extends AppCompatActivity {

    @BindView(R.id.qiandingriqi)
    EditText qiandingriqi;
    @BindView(R.id.hetongbianhao)
    EditText hetongbianhao;
    @BindView(R.id.choose_file)
    Button chooseFile;
    @BindView(R.id.upload)
    Button upload;
    @BindView(R.id.choosepic)
    ChoosePic choosepic;
    @BindView(R.id.dkdjbmhc)
    EditText dkdjbmhc;
    @BindView(R.id.swipe_refresh)
    SwipeRefreshLayout swipeRefresh;
    @BindView(R.id.choose_contact)
    ChooseContact chooseContact;

    private TimePickerView timePickerView;
    private ProgressDialog progressDialog;
    private Handler handler;
    private int type;
    private List<DkDjDetailInfoBean> dkDjDetailInfoBeanList;
    private OrderInfoList orderInfoList;

    public void startActivity(Context context) {
        Intent intent = new Intent(context, AddContract.class);
        context.startActivity(intent);
    }

    public void startForResultActivity(Activity activity, int type, String dkdjbmhc, int reqCode) {
        Intent intent = new Intent(activity, AddContract.class);
        intent.putExtra("type", type);
        intent.putExtra("dkdjbmhc", dkdjbmhc);
        activity.startActivityForResult(intent, reqCode);
    }

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_add_contract);
        ButterKnife.bind(this);
        Intent intent = getIntent();
        this.type = intent.getIntExtra("type", 0);
        if (type == 1) {
            dkdjbmhc.setText(intent.getStringExtra("dkdjbmhc"));
            dkdjbmhc.setFocusable(false);
            dkdjbmhc.setFocusableInTouchMode(false);
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
                swipeRefresh.setRefreshing(false);
            }
        });
    }

    private void initUi() {
        qiandingriqi.setFocusable(false);
        qiandingriqi.setFocusableInTouchMode(false);
        timePickerView = new TimeUtil(this).getTimePicker();
        swipeRefresh.setOnRefreshListener(new SwipeRefreshLayout.OnRefreshListener() {
            @Override
            public void onRefresh() {
                orderInfoList.refresh();
            }
        });
        chooseContact.setFootClick(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                new ContactForOne().startActivity(AddContract.this, 1);
            }
        });
    }

    private void initViewClick() {
        View.OnClickListener onClickListener = new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                EasyPhotos.createAlbum(AddContract.this, true, GlideEngine.getInstance())
                        .setFileProviderAuthority("com.huantansheng.easyphotos.sample.fileprovider")
                        .setCount(9)
                        .start(101);
            }
        };
        choosepic.setAddImageClick(onClickListener);
        chooseFile.setOnClickListener(onClickListener);
    }


    @OnClick({R.id.qiandingriqi, R.id.choose_file, R.id.upload})
    public void onViewClicked(View view) {
        switch (view.getId()) {
            case R.id.qiandingriqi:
                timePickerView.show(view);
                break;
            case R.id.choose_file:
                break;
            case R.id.upload:
                uploadFiles();
                break;
        }
    }

    private void initHandler() {
        handler = new Handler() {
            public void handleMessage(Message msg) {
                switch (msg.what) {
                    case 0:
                        Toast.makeText(AddContract.this, "该订单已有合同文件，上传失败！", Toast.LENGTH_SHORT).show();
                        break;
                    case 1:
                        Toast.makeText(AddContract.this, "上传成功", Toast.LENGTH_SHORT).show();
                        if (type == 1) {
                            Intent intent = getIntent();
                            intent.putExtra("hetongbianhao", hetongbianhao.getText().toString());
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
            switch (requestCode){
                case 101:
                    //返回对象集合：如果你需要了解图片的宽、高、大小、用户是否选中原图选项等信息，可以用这个
                    ArrayList<Photo> resultPhotos = data.getParcelableArrayListExtra(EasyPhotos.RESULT_PHOTOS);
                    choosepic.addData(resultPhotos);
                    break;
                case 1:
                    ContactBean contactBean = data.getParcelableExtra("data");
                    chooseContact.addData(contactBean);
                    break;
            }
        }
    }

    private void uploadFiles() {
        if (isValid()) return;
        showProgressDialog();
        MultipartBody.Builder builder = new MultipartBody.Builder().setType(MultipartBody.FORM);
        builder.addFormDataPart("dingdanbianhao", dkdjbmhc.getText().toString())
                .addFormDataPart("hetongbianhao", hetongbianhao.getText().toString())
                .addFormDataPart("userid", UserInfo.userid)
                .addFormDataPart("ufpi", chooseContact.getUfPiRf())
                .addFormDataPart("qiandingriqi", qiandingriqi.getText().toString());
        List<Photo> tImageList = choosepic.getAllItem();
        for (Photo tImage : tImageList) {
            String filepath = tImage.path;
            File file = new File(filepath);
            RequestBody filebody = RequestBody.create(MediaType.parse("application/octet-stream"), file);
            String filename = filepath.substring(filepath.lastIndexOf("/") + 1, filepath.length());
            builder.addFormDataPart("files", filename, filebody);
        }
        new HttpUtil().uploadContract(builder, new Callback() {
            @Override
            public void onFailure(Call call, IOException e) {
                e.printStackTrace();
                progressDialog.dismiss();
            }

            @Override
            public void onResponse(Call call, Response response) throws IOException {
                String res = response.body().string();
                progressDialog.dismiss();
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
                Log.i("AddContract", "uploadMultiFile() response=" + res);
            }
        });
    }

    private boolean isValid() {
        if (choosepic.getCount() == 0) {
            Toast.makeText(this, "没有选择文件", Toast.LENGTH_SHORT).show();
            return true;
        }
        if (dkdjbmhc.length() == 0) {
            Toast.makeText(this, "订单编号不能为空", Toast.LENGTH_SHORT).show();
            return true;
        } else {
            int i = 0;
            for (; i < dkDjDetailInfoBeanList.size(); i++) {
                DkDjDetailInfoBean dkDjDetailInfoBean = dkDjDetailInfoBeanList.get(i);
                if (dkDjDetailInfoBean.getDkdjxbxi().getDkdjbmhc()
                        .equals(dkdjbmhc.getText().toString())) {
                    if (dkDjDetailInfoBean.getDkdjxbxi().getHetsbmhc().length() > 1) {
                        Toast.makeText(this, "该订单已有合同文件，不能再次添加",
                                Toast.LENGTH_LONG).show();
                        return true;
                    }
                }
            }
            if (i >= dkDjDetailInfoBeanList.size() && type != 1) {
                Toast.makeText(this,
                        "该订单编号不存在，请确认填写是否正确或尝试下拉刷新以更新订单信息",
                        Toast.LENGTH_LONG).show();
                return true;
            }
        }
        if (hetongbianhao.length() == 0) {
            Toast.makeText(this, "合同编号不能为空", Toast.LENGTH_SHORT).show();
            return true;
        }
        if (chooseContact.getCount() == 0){
            Toast.makeText(this, "不能没有审批人", Toast.LENGTH_LONG).show();
        }
        return false;
    }

    private void showProgressDialog() {
        progressDialog = new ProgressDialog(AddContract.this);
        progressDialog.setTitle("上传中");
        progressDialog.setMessage("。。。。。。。。。。。。。");
        progressDialog.setCancelable(false);
        progressDialog.show();
    }
}
