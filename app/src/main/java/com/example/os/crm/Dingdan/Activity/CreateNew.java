package com.example.os.crm.Dingdan.Activity;

import android.content.Context;
import android.content.DialogInterface;
import android.content.Intent;
import android.os.Bundle;
import android.os.Handler;
import android.os.Message;
import android.support.annotation.NonNull;
import android.support.design.widget.FloatingActionButton;
import android.support.design.widget.TextInputLayout;
import android.support.v7.app.AlertDialog;
import android.support.v7.app.AppCompatActivity;
import android.text.Editable;
import android.text.TextWatcher;
import android.util.Log;
import android.view.View;
import android.view.ViewGroup;
import android.view.inputmethod.InputMethodManager;
import android.widget.AdapterView;
import android.widget.AutoCompleteTextView;
import android.widget.Button;
import android.widget.EditText;
import android.widget.LinearLayout;
import android.widget.ListAdapter;
import android.widget.ListView;
import android.widget.RadioButton;
import android.widget.RadioGroup;
import android.widget.RelativeLayout;
import android.widget.Toast;

import com.bigkoo.pickerview.TimePickerView;
import com.example.os.crm.Common.GlideEngine;
import com.example.os.crm.Common.TimeUtil;
import com.example.os.crm.Common.UserInfo;
import com.example.os.crm.Contact.Activity.CreateUpdateCustomerInfo;
import com.example.os.crm.Contact.Model.ContactBean;
import com.example.os.crm.Contact.Activity.ContactForOne;
import com.example.os.crm.Contact.Model.Contact;
import com.example.os.crm.Device.Model.DeviceInfoBean;
import com.example.os.crm.Device.Adapter.DeviceinfolistAdapter;
import com.example.os.crm.Device.Activity.NewDevice;
import com.example.os.crm.Dingdan.Model.KeHuAutoDetail;
import com.example.os.crm.Dingdan.Adapter.KehuautodetailAdapter;
import com.example.os.crm.Contract.Activity.AddContract;
import com.example.os.crm.HttpUtil.HttpGet;
import com.example.os.crm.HttpUtil.HttpPost;
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
import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.Date;
import java.util.List;
import java.util.Locale;

import butterknife.BindView;
import butterknife.ButterKnife;
import butterknife.OnClick;
import okhttp3.Call;
import okhttp3.Callback;
import okhttp3.MediaType;
import okhttp3.MultipartBody;
import okhttp3.RequestBody;
import okhttp3.Response;

public class CreateNew extends AppCompatActivity {

    @BindView(R.id.dingdanbianhao)
    EditText dingdanbianhao;
    @BindView(R.id.dingdanbianhao_layout)
    TextInputLayout dingdanbianhaoLayout;
    @BindView(R.id.hetongbianhao)
    EditText hetongbianhao;
    @BindView(R.id.hetongbianhao_l)
    TextInputLayout hetongbianhaoL;
    @BindView(R.id.yewuyuan)
    EditText yewuyuan;
    @BindView(R.id.yewuyuan_l)
    TextInputLayout yewuyuanL;
    @BindView(R.id.kehumingcheng)
    AutoCompleteTextView kehumingcheng;
    @BindView(R.id.kehumingcheng_l)
    TextInputLayout kehumingchengL;
    @BindView(R.id.kehudizhi)
    AutoCompleteTextView kehudizhi;
    @BindView(R.id.kehudizhi_l)
    TextInputLayout kehudizhiL;
    @BindView(R.id.lianxiren)
    AutoCompleteTextView lianxiren;
    @BindView(R.id.lianxiren_l)
    TextInputLayout lianxirenL;
    @BindView(R.id.lianxirendianhua)
    AutoCompleteTextView lianxirendianhua;
    @BindView(R.id.lianxirendianhua_l)
    TextInputLayout lianxirendianhuaL;
    @BindView(R.id.shebeizongjia)
    EditText shebeizongjia;
    @BindView(R.id.shebeizongjia_l)
    TextInputLayout shebeizongjiaL;
    @BindView(R.id.zhekou)
    EditText zhekou;
    @BindView(R.id.zhekou_l)
    TextInputLayout zhekouL;
    @BindView(R.id.zhibaojin)
    EditText zhibaojin;
    @BindView(R.id.zhibaojin_l)
    TextInputLayout zhibaojinL;
    @BindView(R.id.ticheng)
    EditText ticheng;
    @BindView(R.id.tichengjine_l)
    TextInputLayout tichengjineL;
    @BindView(R.id.shuifoushi)
    RadioButton shuifoushi;
    @BindView(R.id.shuifoufou)
    RadioButton shuifoufou;
    @BindView(R.id.shuifou)
    RadioGroup shuifou;
    @BindView(R.id.qita)
    EditText qita;
    @BindView(R.id.qita_l)
    TextInputLayout qitaL;
    @BindView(R.id.tijiaodingdan)
    FloatingActionButton tijiaodingdan;
    @BindView(R.id.quxiaotijiao)
    FloatingActionButton quxiaotijiao;
    @BindView(R.id.deviceinfo)
    ListView deviceinfo;
    @BindView(R.id.zhibaojindaoqi)
    EditText zhibaojindaoqi;
    @BindView(R.id.zhibaojindaoqi_l)
    TextInputLayout zhibaojindaoqiL;
    @BindView(R.id.rl)
    RelativeLayout rl;
    @BindView(R.id.fab)
    FloatingActionButton fab;
    @BindView(R.id.addnewkehu)
    FloatingActionButton addnewkehu;
    @BindView(R.id.addnewdevice)
    FloatingActionButton addnewdevice;
    @BindView(R.id.addnewhetong)
    FloatingActionButton addnewhetong;
    @BindView(R.id.tijndkdj)
    Button tijndkdj;
    @BindView(R.id.addshenpi)
    ChooseContact addshenpi;
    @BindView(R.id.ll01)
    LinearLayout ll01;
    @BindView(R.id.ll02)
    LinearLayout ll02;
    @BindView(R.id.ll03)
    LinearLayout ll03;
    @BindView(R.id.ll04)
    LinearLayout ll04;
    @BindView(R.id.ll0)
    LinearLayout ll0;
    @BindView(R.id.choosepic)
    ChoosePic choosepic;

    private String TAG = "Create New";
    private List<DeviceInfoBean> deviceInfoBeanList = new ArrayList<>();
    private List<KeHuAutoDetail> keHuAutoDetailList = new ArrayList<>();
    DeviceinfolistAdapter deviceinfolistAdapter;
    private TimePickerView pvTime;
    Handler handler;
    private String kehuId;
    private boolean flag = false;

    public void startActivity(Context context) {
        Intent intent = new Intent(context, CreateNew.class);
        context.startActivity(intent);
    }

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_create_new);
        ButterKnife.bind(this);
        initHandler();
        initDeviceList();
        initUi();
        TimepickInit();
        initAddufpi();
    }

    private void initAddufpi() {

        Contact contact = Contact.getInstance();
        addshenpi.setFootClick(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                new ContactForOne().startActivity(CreateNew.this, 3);
            }
        });
        String[] userid = {"yrzlm", "yrzzq"};
        List<String> userids = Arrays.asList(userid);
        addshenpi.addData(contact.getContact(userids));
        choosepic.setAddImageClick(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                EasyPhotos.createAlbum(CreateNew.this, true, GlideEngine.getInstance())
                        .setFileProviderAuthority("com.huantansheng.easyphotos.sample.fileprovider")
                        .setCount(9)
                        .start(101);
            }
        });
    }

    @Override
    public void onBackPressed() {
        if (!flag && hetongbianhao.length() > 1) {
            String dkdjbmhc = getDingdanbianhao();
            new HttpUtil().DelContract(dkdjbmhc, new Callback() {
                @Override
                public void onFailure(Call call, IOException e) {
                    e.printStackTrace();
                }

                @Override
                public void onResponse(Call call, Response response) throws IOException {
                    Log.d(TAG, "onResponse: " + response.body().string());
                }
            });
        }
        finish();
    }

    @Override
    protected void onDestroy() {
        super.onDestroy();
    }

    private void initHandler() {
        handler = new Handler() {
            public void handleMessage(Message msg) {
                switch (msg.what) {
                    case 0:
                        try {
                            JSONObject jsonObject = new JSONObject(msg.obj.toString());
                            setAutoComplete(jsonObject);
                        } catch (Exception e) {
                            e.printStackTrace();
                        }
                        break;
                    default:
                        break;
                }
            }
        };
    }

    private void setAutoComplete(JSONObject jsonObject) throws JSONException {
        Gson gson = new Gson();
        keHuAutoDetailList = gson.fromJson(jsonObject.getString("data"), new TypeToken<List<KeHuAutoDetail>>() {
        }.getType());
        KehuautodetailAdapter kehuautodetailAdapter = new KehuautodetailAdapter(this, keHuAutoDetailList);
        kehumingcheng.setAdapter(kehuautodetailAdapter);
        kehumingcheng.setThreshold(1);
    }

    private void initUi() {
        setDkdjbmhc();
        setReadOnly(shebeizongjia);
        setReadOnly(hetongbianhao);
        setReadOnly(zhibaojindaoqi);
        zhibaojindaoqi.setText(new TimeUtil(this).getYearAfterDate());
        zhekou.addTextChangedListener(new TextWatcher() {
            @Override
            public void beforeTextChanged(CharSequence s, int start, int count, int after) {

            }

            @Override
            public void onTextChanged(CharSequence s, int start, int before, int count) {

            }

            @Override
            public void afterTextChanged(Editable s) {
                setDkdjzsjxoff();
            }
        });
        zhibaojin.addTextChangedListener(new TextWatcher() {
            @Override
            public void beforeTextChanged(CharSequence s, int start, int count, int after) {

            }

            @Override
            public void onTextChanged(CharSequence s, int start, int before, int count) {

            }

            @Override
            public void afterTextChanged(Editable s) {
                setZhibaojin();
            }
        });
        ticheng.addTextChangedListener(new TextWatcher() {
            @Override
            public void beforeTextChanged(CharSequence s, int start, int count, int after) {

            }

            @Override
            public void onTextChanged(CharSequence s, int start, int before, int count) {

            }

            @Override
            public void afterTextChanged(Editable s) {
                setTicheng();
            }
        });
        initKehuList();
        kehumingcheng.setOnItemClickListener(new AdapterView.OnItemClickListener() {
            @Override
            public void onItemClick(AdapterView<?> parent, View view, int position, long id) {
                KeHuAutoDetail keHuAutoDetail = (KeHuAutoDetail) kehumingcheng.getAdapter().getItem(position);
                kehuId = keHuAutoDetail.getKehuid();
                kehumingcheng.setText(keHuAutoDetail.getKehumingcheng());
                kehudizhi.setText(keHuAutoDetail.getKehudizhi());
                lianxiren.setText(keHuAutoDetail.getKehulianxiren());
                lianxirendianhua.setText(keHuAutoDetail.getLianxirendianhua());
            }
        });
    }

    private void initKehuList() {

        String url = UserInfo.host + "/crm/getkehu/" + UserInfo.userid;
        new HttpGet().getData(url, new Callback() {
            @Override
            public void onFailure(Call call, IOException e) {
                Log.d(TAG, "onFailure: 获取客户列表失败");
                e.printStackTrace();
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

    private void initDeviceList() {
        deviceinfolistAdapter = new DeviceinfolistAdapter(this, deviceInfoBeanList);
        deviceinfo.setAdapter(deviceinfolistAdapter);
        deviceinfo.setOnItemClickListener(new AdapterView.OnItemClickListener() {
            @Override
            public void onItemClick(AdapterView<?> parent, View view, int position, long id) {
                DeviceInfoBean deviceInfoBean = deviceInfoBeanList.get(position);
                deviceInfoBeanList.remove(position);
                deviceinfolistAdapter.notifyDataSetChanged();
                Intent intent = new Intent(CreateNew.this, NewDevice.class);
                intent.putExtra("t", 1);
                intent.putExtra("deviceInfo", deviceInfoBean);
                startActivityForResult(intent, 1);
            }
        });
        deviceinfo.setOnItemLongClickListener(new AdapterView.OnItemLongClickListener() {
            @Override
            public boolean onItemLongClick(AdapterView<?> parent, View view, int position, long id) {
                deviceInfoBeanList.remove(position);
                deviceinfolistAdapter.notifyDataSetChanged();
                setListViewHeightBasedOnChildren(deviceinfo);
                if (deviceinfolistAdapter.getCount() == 0) {
                    shebeizongjia.setText("0");
                } else {
                    setDkdjzsjx();
                }
                return false;
            }
        });
    }

    @OnClick({R.id.addnewhetong,
            R.id.addnewkehu,
            R.id.addnewdevice,
            R.id.tijiaodingdan,
            R.id.quxiaotijiao,
            R.id.dingdanbianhao,
            R.id.shebeizongjia, R.id.zhibaojindaoqi, R.id.hetongbianhao, R.id.fab, R.id.rl})
    public void onViewClicked(View view) {
        Intent intent;
        switch (view.getId()) {
            case R.id.rl:
                rl.setVisibility(View.GONE);
                fab.setImageResource(R.drawable.ic_add_white);
                break;
            case R.id.addnewhetong:
                rl.setVisibility(View.GONE);
                fab.setImageResource(R.drawable.ic_add_white);
                new AddContract().startForResultActivity(this, 1, getDingdanbianhao(), 2);
                break;
            case R.id.addnewkehu:
                rl.setVisibility(View.GONE);
                fab.setImageResource(R.drawable.ic_add_white);
                new CreateUpdateCustomerInfo().startActivity(this, "");
                break;
            case R.id.addnewdevice:
                rl.setVisibility(View.GONE);
                fab.setImageResource(R.drawable.ic_add_white);
                intent = new Intent(CreateNew.this, NewDevice.class);
                intent.putExtra("t", 0);
                startActivityForResult(intent, 1);
                break;
            case R.id.tijiaodingdan:
                rl.setVisibility(View.GONE);
                fab.setImageResource(R.drawable.ic_add_white);
                if (!isValid()) {
                    return;
                }
                if (deviceinfolistAdapter.getCount() == 0) {
                    Toast.makeText(this, "请添加订单信息", Toast.LENGTH_SHORT).show();
                    return;
                }
                postData();
                break;
            case R.id.quxiaotijiao:
                rl.setVisibility(View.GONE);
                fab.setImageResource(R.drawable.ic_add_white);
                finish();
                break;
            case R.id.dingdanbianhao:
                Toast.makeText(this, "订单编号不许修改！", Toast.LENGTH_SHORT).show();
                break;
            case R.id.shebeizongjia:
                Toast.makeText(this, "添加设备后自动计算", Toast.LENGTH_SHORT).show();
                break;
            case R.id.zhibaojindaoqi:
                pvTime.show(view);
                break;
            case R.id.hetongbianhao:
                Toast.makeText(this, "请点击右侧添加合同", Toast.LENGTH_SHORT).show();
                break;
            case R.id.fab:
                CloseSoftKeyBoard();
                if (rl.getVisibility() == View.VISIBLE) {
                    rl.setVisibility(View.GONE);
                    fab.setImageResource(R.drawable.ic_add_white);
                } else {
                    rl.setVisibility(View.VISIBLE);
                    fab.setImageResource(R.drawable.ic_cancel_white_36pt_3x);
                }
                break;
        }
    }

    private void CloseSoftKeyBoard() {
        InputMethodManager imm = (InputMethodManager) getSystemService(Context.INPUT_METHOD_SERVICE);
        if (imm.isActive() && getCurrentFocus() != null) {
            if (getCurrentFocus().getWindowToken() != null) {
                imm.hideSoftInputFromWindow(getCurrentFocus().getWindowToken(), InputMethodManager.HIDE_NOT_ALWAYS);
            }
        }
    }

    @Override
    protected void onActivityResult(int requestCode, int resultCode, Intent data) {
        if (resultCode == RESULT_OK){
            switch (requestCode) {
                case 1:
                    DeviceInfoBean deviceInfoBean = (DeviceInfoBean) data.getParcelableExtra("deviceinfo");
                    Log.d(TAG, "onActivityResult: " + deviceInfoBean.getUebwxkhc());
                    deviceInfoBeanList.add(deviceInfoBean);
                    deviceinfolistAdapter.notifyDataSetChanged();
                    setListViewHeightBasedOnChildren(deviceinfo);
                    setDkdjzsjx();
                    break;
                case 2:
                    String hetsbmhc = data.getStringExtra("hetongbianhao");
                    hetongbianhao.setText(hetsbmhc);
                    break;
                case 3:
                    ContactBean contactBean = (ContactBean) data.getParcelableExtra("data");
                    addshenpi.addData(contactBean);
                    break;
                case 101:
                    ArrayList<Photo> resultPhotos = data.getParcelableArrayListExtra(EasyPhotos.RESULT_PHOTOS);
                    choosepic.addData(resultPhotos);
                    break;
                default:
                    break;
            }
        }

    }

    private void setDkdjzsjx() {
        float zsjx = 0;
        for (DeviceInfoBean deviceInfoBean : deviceInfoBeanList) {
            float temp = Float.parseFloat(deviceInfoBean.getUebwzsjx());
            zsjx += temp;
        }
        shebeizongjia.setText(String.valueOf(zsjx));
    }

    private void setDkdjzsjxoff() {
        setDkdjzsjx();
        float vekz = 0;
        if (zhekou.length() > 0) {
            vekz = Float.parseFloat(zhekou.getText().toString());
        }
        if (shebeizongjia.length() > 0) {
            float uebwzsjx = Float.parseFloat(shebeizongjia.getText().toString());
            if (uebwzsjx > vekz) {
                uebwzsjx -= vekz;
                shebeizongjia.setText(String.valueOf(uebwzsjx));
            }
        }
    }

    private void setZhibaojin() {
        if (zhibaojin.getText().toString().equals(".")) {
            zhibaojin.setText("");
            return;
        }
        if (zhibaojin.length() > 0) {
            calcScale(zhibaojin);
        }
    }

    private void setTicheng() {
        if (ticheng.getText().toString().equals(".")) {
            ticheng.setText("");
            return;
        }
        if (ticheng.length() > 0) {
            calcScale(ticheng);
        }
    }

    private void calcScale(EditText editText) {
        if (shebeizongjia.length() == 0) {
            editText.setText("0");
            return;
        }
        float uebwzsjx = Float.parseFloat(shebeizongjia.getText().toString());
        String temp = editText.getText().toString();
        int split = temp.indexOf("%");
        String scale;
        if (split == 0) {
            editText.setText("");
            return;
        } else if (split > 0) {
            scale = temp.substring(0, split);
        } else {
            scale = temp;
        }
        float float_scale = Float.parseFloat(scale);
        float float_jine = uebwzsjx * float_scale / 100;
        String display_jine = scale + "%/" + String.valueOf(float_jine) + "元";
        split = display_jine.indexOf("%");
        if (!display_jine.equals(temp)) {
            editText.setText(display_jine);
            editText.setSelection(split);
        }
    }

    private void setDkdjbmhc() {
        Date date = new Date();
        SimpleDateFormat simpleDateFormat = new SimpleDateFormat("yyMMddHHmm", Locale.CHINA);
        String current_date = simpleDateFormat.format(date);
        dingdanbianhao.setText("YR" + current_date);
        setReadOnly(dingdanbianhao);
    }

    private static void setReadOnly(EditText hetongbianhao) {
        hetongbianhao.setFocusable(false);
        hetongbianhao.setFocusableInTouchMode(false);
    }

    private void setListViewHeightBasedOnChildren(ListView listView) {
        // 获取ListView对应的Adapter
        ListAdapter listAdapter = listView.getAdapter();
        if (listAdapter == null) {
            return;
        }
        int totalHeight = 0;
        for (int i = 0; i < listAdapter.getCount(); i++) { // listAdapter.getCount()返回数据项的数目
            View listItem = listAdapter.getView(i, null, listView);
            listItem.measure(0, 0); // 计算子项View 的宽高
            totalHeight += listItem.getMeasuredHeight(); // 统计所有子项的总高度
        }
        ViewGroup.LayoutParams params = listView.getLayoutParams();
        params.height = totalHeight
                + (listView.getDividerHeight() * (listAdapter.getCount() - 1));
        // listView.getDividerHeight()获取子项间分隔符占用的高度
        // params.height最后得到整个ListView完整显示需要的高度
        listView.setLayoutParams(params);
    }

    private boolean isValid() {
        boolean flag = true;
        if (!checkKehumkig()) {
            flag = false;
        }
        if (!checkYewuyr()) {
            flag = false;
        }
        return flag;
    }

    private boolean checkKehumkig() {
        if (kehumingcheng.length() < 1) {
            setError(kehumingchengL, "客户信息不能为空");
            return false;
        } else {
            kehumingchengL.setErrorEnabled(false);
            return true;
        }
    }

    private boolean checkYewuyr() {
        if (yewuyuan.length() < 1) {
            setError(yewuyuanL, "业务员不能没有");
            return false;
        } else {
            yewuyuanL.setErrorEnabled(false);
            return true;
        }
    }

    private static void setError(TextInputLayout textInputLayout, String info) {
        textInputLayout.setError(info);
        textInputLayout.setErrorEnabled(true);
    }

    private void TimepickInit() {
        pvTime = new TimeUtil(this).getTimePicker();
    }

    private void postData() {
        String url = UserInfo.host + "/crm/createnew";

        MultipartBody.Builder builder = new MultipartBody.Builder().setType(MultipartBody.FORM);
        builder.addFormDataPart("fuzeyewu", UserInfo.userid)
                .addFormDataPart("kehuid", kehuId)
                .addFormDataPart("dkdjbmhc", getDingdanbianhao())
                .addFormDataPart("hetsbmhc", getHetongbianhao())
                .addFormDataPart("yewuyr", getYewuyuan())
                .addFormDataPart("kehumkig", getKehumingcheng())
                .addFormDataPart("kehudivi", getKehudizhi())
                .addFormDataPart("lmxirf", getLianxiren())
                .addFormDataPart("lmxirfdmhx", getLianxirendianhua())
                .addFormDataPart("uebwzsjx", getShebeizongjia())
                .addFormDataPart("vekz", getZhekou())
                .addFormDataPart("vibcjb", getZhibaojin())
                .addFormDataPart("tiig", getTicheng())
                .addFormDataPart("uvfz", String.valueOf(getShuifou()))
                .addFormDataPart("bwvu", getQita())
                .addFormDataPart("vibcjbdcqi", getZhibaojindaoqi())
                .addFormDataPart("uebwxbxi", getDeviceinfo())
                .addFormDataPart("wjvg", getWjVg())
                .addFormDataPart("ufpi", getUfPi());
        List<Photo> tImageList = getPhotoList();
        for (Photo tImage: tImageList){
            String filepath = tImage.path;
            File file = new File(filepath);
            RequestBody filebody = RequestBody.create(MediaType.parse("application/octet-stream"), file);
            String filename = filepath.substring(filepath.lastIndexOf("/")+1, filepath.length());
            builder.addFormDataPart("files", filename, filebody);
        }
        new HttpPost().postData(url, builder, new Callback() {
            @Override
            public void onFailure(Call call, IOException e) {
                Toast.makeText(CreateNew.this, "上传失败，请检查网络", Toast.LENGTH_SHORT).show();
                Log.d(TAG, "onFailure: 上传失败");
                e.printStackTrace();
            }

            @Override
            public void onResponse(Call call, Response response) throws IOException {
                String data = response.body().string();
                Log.d(TAG, "onResponse: " + data);
                try {
                    JSONObject jsonObject = new JSONObject(data);
                    if (jsonObject.getInt("error") == 1) {
                        Log.d(TAG, "onResponse: 上传成功");
                        flag = true;
                        CreateNew.this.finish();
                    } else {
                        Log.d(TAG, "onResponse: 上传失败");
                    }
                } catch (Exception e) {
                    e.printStackTrace();
                }


            }
        });
    }

    private List<Photo> getPhotoList(){
        return choosepic.getAllItem();
    }

    @NonNull
    private String getDingdanbianhao() {
        return dingdanbianhao.getText().toString();
    }

    @NonNull
    private String getHetongbianhao() {
        return hetongbianhao.getText().toString();
    }

    @NonNull
    private String getYewuyuan() {
        return yewuyuan.getText().toString();
    }

    @NonNull
    private String getKehumingcheng() {
        return kehumingcheng.getText().toString();
    }

    @NonNull
    private String getKehudizhi() {
        return kehudizhi.getText().toString();
    }

    @NonNull
    private String getLianxiren() {
        return lianxiren.getText().toString();
    }

    @NonNull
    private String getLianxirendianhua() {
        return lianxirendianhua.getText().toString();
    }

    @NonNull
    private String getShebeizongjia() {
        return shebeizongjia.getText().toString();
    }

    @NonNull
    private String getZhekou() {
        return zhekou.getText().toString();
    }

    @NonNull
    private String getZhibaojin() {
        if (zhibaojin.length() < 1) {
            return "0%/0元";
        }
        return zhibaojin.getText().toString();
    }

    @NonNull
    private String getTicheng() {
        if (ticheng.length() < 1) {
            return "0%/0元";
        }
        return ticheng.getText().toString();
    }

    private int getShuifou() {
        if (shuifoushi.isChecked()) {
            return 1;
        } else {
            return 0;
        }
    }

    @NonNull
    private String getQita() {
        return qita.getText().toString();
    }

    private String getDeviceinfo() {

        StringBuilder stringBuilder = new StringBuilder();
        String temp = "[";
        for (DeviceInfoBean deviceInfoBean : deviceInfoBeanList) {
            stringBuilder.append(deviceInfoBean.toString()).append(",");
        }
        temp += stringBuilder.toString();
        temp = temp.substring(0, temp.length() - 1);
        temp += "]";
        Log.d(TAG, "getDeviceinfo: " + temp);
        return temp;
    }

    private String getUfPi() {
        return addshenpi.getUfPiRf();
    }

    private String getWjVg() {
        return hetongbianhao.length() > 0 ? "1" : "0";
    }

    @NonNull
    private String getZhibaojindaoqi() {
        String temp = zhibaojindaoqi.getText().toString();
        return new TimeUtil().getTimeStamp("yyyy年MM月dd日", temp);
    }

    @OnClick(R.id.tijndkdj)
    public void onViewClicked() {
        AlertDialog.Builder dialog = new AlertDialog.Builder(this);
        dialog.setTitle("提交订单");
        dialog.setMessage("确认提交订单吗？");
        dialog.setPositiveButton("提交", new DialogInterface.OnClickListener() {
            @Override
            public void onClick(DialogInterface dialog, int which) {
                if (!isValid()) {
                    return;
                }
                if (deviceinfolistAdapter.getCount() == 0) {
                    Toast.makeText(CreateNew.this, "请添加订单信息", Toast.LENGTH_SHORT).show();
                    return;
                }
                postData();
            }
        });
        dialog.setNegativeButton("取消", new DialogInterface.OnClickListener() {
            @Override
            public void onClick(DialogInterface dialog, int which) {

            }
        });
        dialog.show();
    }
}
