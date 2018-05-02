package com.example.os.crm.ui.activity;

import android.content.Context;
import android.content.Intent;
import android.os.Bundle;
import android.os.Handler;
import android.os.Message;
import android.support.design.widget.TextInputLayout;
import android.support.v7.app.AppCompatActivity;
import android.util.Log;
import android.view.View;
import android.widget.AdapterView;
import android.widget.AutoCompleteTextView;
import android.widget.Button;
import android.widget.EditText;
import android.widget.Toast;

import com.bigkoo.pickerview.TimePickerView;
import com.example.os.crm.Common.GlideEngine;
import com.example.os.crm.Common.TimeUtil;
import com.example.os.crm.Contact.Model.ContactBean;
import com.example.os.crm.Contact.Activity.ContactForOne;
import com.example.os.crm.Dingdan.Model.KeHuAutoDetail;
import com.example.os.crm.Dingdan.Adapter.KehuautodetailAdapter;
import com.example.os.crm.HttpUtil.HttpUtil;
import com.example.os.crm.ui.widget.ChooseContact;
import com.example.os.crm.ui.widget.ChoosePic;
import com.example.os.crm.R;
import com.google.gson.Gson;
import com.google.gson.reflect.TypeToken;
import com.huantansheng.easyphotos.EasyPhotos;
import com.huantansheng.easyphotos.models.album.entity.Photo;

import org.json.JSONException;
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
 * 添加回款，原NewPayment
 */
public class AddFundActivity extends AppCompatActivity {

    @BindView(R.id.hvkrjbee)
    EditText hvkrjbee;
    @BindView(R.id.hvkrjbee_l)
    TextInputLayout hvkrjbeeL;
    @BindView(R.id.kehumkig)
    AutoCompleteTextView kehumkig;
    @BindView(R.id.kehumkig_l)
    TextInputLayout kehumkigL;
    @BindView(R.id.hvkrriqi)
    EditText hvkrriqi;
    @BindView(R.id.hvkrriqi_l)
    TextInputLayout hvkrriqiL;
    @BindView(R.id.choose_pic)
    ChoosePic choosePic;
    @BindView(R.id.choose_contact)
    ChooseContact chooseContact;
    @BindView(R.id.tijn)
    Button tijn;

    private TimePickerView timePickerView;
    private String TAG = "AddFundActivity";
    private Handler handler;
    private List<KeHuAutoDetail> keHuAutoDetailList = new ArrayList<>();
    private KeHuAutoDetail keHuAutoDetail;

    public void startActivity(Context context) {
        Intent intent = new Intent(context, AddFundActivity.class);
        context.startActivity(intent);
    }

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_new_pay_ment);
        ButterKnife.bind(this);
        initUi();
        initHandler();
        createCustomerList();
    }

    private void initHandler() {
        handler = new Handler(){
            public void handleMessage(Message msg){
                switch (msg.what){
                    case 0:
                        try{
                            JSONObject jsonObject = new JSONObject(msg.obj.toString());
                            setAutoComplete(jsonObject);
                        }catch (Exception e){
                            e.printStackTrace();
                        }
                        break;
                    default:
                        break;
                }
            }
        };
    }

    private void initUi() {
        hvkrriqi.setFocusable(false);
        hvkrriqi.setFocusableInTouchMode(false);
        timePickerView = new TimeUtil(this).getTimePicker();

        View.OnClickListener onClickListener = new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                EasyPhotos.createAlbum(AddFundActivity.this, true, GlideEngine.getInstance())
                        .setFileProviderAuthority("com.huantansheng.easyphotos.sample.fileprovider")
                        .setCount(9)
                        .start(101);
            }
        };
        choosePic.setAddImageClick(onClickListener);
        chooseContact.setFootClick(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                new ContactForOne().startActivity(AddFundActivity.this, 1);
            }
        });
        kehumkig.setOnItemClickListener(new AdapterView.OnItemClickListener() {
            @Override
            public void onItemClick(AdapterView<?> parent, View view, int position, long id) {
                keHuAutoDetail = (KeHuAutoDetail) kehumkig.getAdapter().getItem(position);
                kehumkig.setText(keHuAutoDetail.getKehumingcheng());
            }
        });
    }

    @Override
    protected void onActivityResult(int requestCode, int resultCode, Intent data) {
        if (resultCode == RESULT_OK){
            switch (requestCode) {
                case 1:
                    ContactBean contactBean = (ContactBean) data.getParcelableExtra("data");
                    chooseContact.addData(contactBean);
                    break;
                case 101:
                    ArrayList<Photo> resultPhotos = data.getParcelableArrayListExtra(EasyPhotos.RESULT_PHOTOS);
                    choosePic.addData(resultPhotos);
                default:
                    break;
            }
        }
    }

    @OnClick({R.id.hvkrriqi, R.id.tijn})
    public void onViewClicked(View view) {
        switch (view.getId()) {
            case R.id.hvkrriqi:
                timePickerView.show(view);
                break;
            case R.id.tijn:
                postData();
                break;
        }
    }

    private void createCustomerList(){
        new HttpUtil().getCustomerList(new Callback() {
            @Override
            public void onFailure(Call call, IOException e) {

            }

            @Override
            public void onResponse(Call call, Response response) throws IOException {
                Message msg = new Message();
                msg.what = 0;
                msg.obj = response.body().string();
                handler.sendMessage(msg);
            }
        });
    }

    private void setAutoComplete(JSONObject jsonObject) throws JSONException {
        Gson gson = new Gson();
        keHuAutoDetailList = gson.fromJson(jsonObject.getString("data"), new TypeToken<List<KeHuAutoDetail>>() {
        }.getType());
        KehuautodetailAdapter kehuautodetailAdapter = new KehuautodetailAdapter(this, keHuAutoDetailList);
        kehumkig.setAdapter(kehuautodetailAdapter);
        kehumkig.setThreshold(1);
    }

    private void postData(){
        if (isValid()){
            return;
        }
        MultipartBody.Builder builder = new MultipartBody.Builder().setType(MultipartBody.FORM);
        builder.addFormDataPart("customerid", getCustomerId())
                .addFormDataPart("customername", getCustomerName())
                .addFormDataPart("payment", getMoney())
                .addFormDataPart("date", getDate())
                .addFormDataPart("ufpi", getUfPi());
        List<Photo> tImageList = getPhotoList();
        for (Photo tImage: tImageList){
            String filepath = tImage.path;
            File file = new File(filepath);
            RequestBody filebody = RequestBody.create(MediaType.parse("application/octet-stream"), file);
            String filename = filepath.substring(filepath.lastIndexOf("/")+1, filepath.length());
            builder.addFormDataPart("files", filename, filebody);
        }
        new HttpUtil().CreateNewPayment(builder, new Callback() {
            @Override
            public void onFailure(Call call, IOException e) {
                Log.d(TAG, "onFailure: ");
                e.printStackTrace();
            }

            @Override
            public void onResponse(Call call, Response response) throws IOException {
                Log.d(TAG, "onResponse: " + response.body().string());
                AddFundActivity.this.finish();
            }
        });
    }

    private List<Photo> getPhotoList(){
        return choosePic.getAllItem();
    }
    private String getCustomerId(){
        return keHuAutoDetail.getKehuid();
    }
    private String getCustomerName(){return keHuAutoDetail.getKehumingcheng();}
    private String getMoney(){
        return hvkrjbee.getText().toString();
    }
    private String getDate(){
        return hvkrriqi.getText().toString();
    }
    private String getUfPi(){
        return chooseContact.getUfPiRf();
    }
    private boolean isValid(){
        if (kehumkig.length() < 1){
            Toast.makeText(this, "客户名称不能为空", Toast.LENGTH_SHORT).show();
            return true;
        }
        if (hvkrriqi.length() < 1){
            Toast.makeText(this, "回款日期不能为空", Toast.LENGTH_SHORT).show();
            return true;
        }
        if (hvkrjbee.length() < 1){
            Toast.makeText(this, "回款金额不能为空", Toast.LENGTH_SHORT).show();
            return true;
        }
        if (choosePic.getCount() < 1){
            Toast.makeText(this, "回款不能没有凭证", Toast.LENGTH_SHORT).show();
            return true;
        }
        if (chooseContact.getCount() < 1){
            Toast.makeText(this, "不能没有审批人", Toast.LENGTH_SHORT).show();
            return true;
        }
        return false;
    }
}
