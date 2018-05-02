package com.example.os.crm.Device.Activity;

import android.content.Intent;
import android.os.Bundle;
import android.support.design.widget.TextInputLayout;
import android.support.v7.app.AppCompatActivity;
import android.text.Editable;
import android.text.TextWatcher;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.RadioButton;
import android.widget.RadioGroup;

import com.bigkoo.pickerview.TimePickerView;
import com.example.os.crm.Common.TimeUtil;
import com.example.os.crm.Device.Model.DeviceInfoBean;
import com.example.os.crm.R;

import butterknife.BindView;
import butterknife.ButterKnife;
import butterknife.OnClick;

public class NewDevice extends AppCompatActivity {

    @BindView(R.id.uebwxkhc)
    EditText uebwxkhc;
    @BindView(R.id.uebwxkhc_l)
    TextInputLayout uebwxkhcL;
    @BindView(R.id.jnhoriqi)
    EditText jnhoriqi;
    @BindView(R.id.jnhoriqi_l)
    TextInputLayout jnhoriqiL;
    @BindView(R.id.uebwdjjx)
    EditText uebwdjjx;
    @BindView(R.id.uebwdjjx_l)
    TextInputLayout uebwdjjxL;
    @BindView(R.id.uebwuull)
    EditText uebwuull;
    @BindView(R.id.uebwuull_l)
    TextInputLayout uebwuullL;
    @BindView(R.id.uebwzsjx)
    EditText uebwzsjx;
    @BindView(R.id.uebwzsjx_l)
    TextInputLayout uebwzsjxL;
    @BindView(R.id.cczofhui)
    EditText cczofhui;
    @BindView(R.id.cczofhui_l)
    TextInputLayout cczofhuiL;
    @BindView(R.id.uhxxfuyhfhui)
    EditText uhxxfuyhfhui;
    @BindView(R.id.uhxxfuyhfhui_l)
    TextInputLayout uhxxfuyhfhuiL;
    @BindView(R.id.uhxxqiui)
    EditText uhxxqiui;
    @BindView(R.id.uhxxqiui_l)
    TextInputLayout uhxxqiuiL;
    @BindView(R.id.uhxxvsvi)
    EditText uhxxvsvi;
    @BindView(R.id.uhxxvsvi_l)
    TextInputLayout uhxxvsviL;
    @BindView(R.id.zoyzxrvrfhui)
    EditText zoyzxrvrfhui;
    @BindView(R.id.zoyzxrvrfhui_l)
    TextInputLayout zoyzxrvrfhuiL;
    @BindView(R.id.zoyzqiui)
    EditText zoyzqiui;
    @BindView(R.id.zoyzqiui_l)
    TextInputLayout zoyzqiuiL;
    @BindView(R.id.zoyzvsvi)
    EditText zoyzvsvi;
    @BindView(R.id.zoyzvsvi_l)
    TextInputLayout zoyzvsviL;
    @BindView(R.id.yeyaghuull)
    EditText yeyaghuull;
    @BindView(R.id.yeyaghuull_l)
    TextInputLayout yeyaghuullL;
    @BindView(R.id.uvxlrsji)
    EditText uvxlrsji;
    @BindView(R.id.uvxlrsji_l)
    TextInputLayout uvxlrsjiL;
    @BindView(R.id.uvbgxkhc)
    EditText uvbgxkhc;
    @BindView(R.id.uvbgxkhc_l)
    TextInputLayout uvbgxkhcL;
    @BindView(R.id.fadmjizu)
    EditText fadmjizu;
    @BindView(R.id.fadmjizu_l)
    TextInputLayout fadmjizuL;
    @BindView(R.id.dmqijmpbpd)
    EditText dmqijmpbpd;
    @BindView(R.id.dmqijmpbpd_l)
    TextInputLayout dmqijmpbpdL;
    @BindView(R.id.pftuyjse)
    EditText pftuyjse;
    @BindView(R.id.pftuyjse_l)
    TextInputLayout pftuyjseL;
    @BindView(R.id.anvlfhui)
    EditText anvlfhui;
    @BindView(R.id.anvlfhui_l)
    TextInputLayout anvlfhuiL;
    @BindView(R.id.wdxkiicuycqq)
    EditText wdxkiicuycqq;
    @BindView(R.id.wdxkiicuycqq_l)
    TextInputLayout wdxkiicuycqqL;
    @BindView(R.id.qita)
    EditText qita;
    @BindView(R.id.qita_l)
    TextInputLayout qitaL;
    @BindView(R.id.submit)
    Button submit;

    @BindView(R.id.uhxxfhui_ziso)
    RadioButton uhxxfhuiZiso;
    @BindView(R.id.uhxxfhui_dmds)
    RadioButton uhxxfhuiDmds;
    @BindView(R.id.uhxxfhui)
    RadioGroup uhxxfhui;
    @BindView(R.id.zoyzfhui_zids)
    RadioButton zoyzfhuiZids;
    @BindView(R.id.zoyzfhui_dmds)
    RadioButton zoyzfhuiDmds;
    @BindView(R.id.zoyzfhui)
    RadioGroup zoyzfhui;

    private TimePickerView pvTime;
    private DeviceInfoBean deviceInfoBean;
    private DeviceInfoBean deviceInfoBean2;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_new_device);
        ButterKnife.bind(this);
        Intent intent = getIntent();
        int t = intent.getIntExtra("t", 0);
        TimepickInit();
        setupUi();
        if (t == 1) {
            deviceInfoBean2 = (DeviceInfoBean) intent.getParcelableExtra("deviceInfo");
            setUiData();
        }
    }

    @OnClick({R.id.submit, R.id.jnhoriqi})
    public void onViewClicked(View view) {
        switch (view.getId()) {
            case R.id.submit:
                if (!isValid()) {
                    return;
                }
                setData();
                Intent intent = getIntent();
                intent.putExtra("deviceinfo", deviceInfoBean);
                setResult(RESULT_OK, intent);
                finish();
                break;
            case R.id.jnhoriqi:
                pvTime.show(view);
                break;
        }
    }

    private void setUiData() {
        uebwxkhc.setText(deviceInfoBean2.getUebwxkhc());
        uebwuull.setText(deviceInfoBean2.getUebwuull());
        uebwdjjx.setText(deviceInfoBean2.getUebwdjjx());
        uebwzsjx.setText(deviceInfoBean2.getUebwzsjx());
        jnhoriqi.setText(deviceInfoBean2.getJnhoriqi());
        fadmjizu.setText(deviceInfoBean2.getFadmjizu());
        cczofhui.setText(deviceInfoBean2.getCczofhui());
        uvxlrsji.setText(deviceInfoBean2.getUvxlrsji());
        pftuyjse.setText(deviceInfoBean2.getPftuyjse());
        anvlfhui.setText(deviceInfoBean2.getAnvlfhui());
        uvbgxkhc.setText(deviceInfoBean2.getUvbgxkhc());
        uhxxqiui.setText(deviceInfoBean2.getUhxxqiui());
        uhxxvsvi.setText(deviceInfoBean2.getUhxxvsvi());
        zoyzqiui.setText(deviceInfoBean2.getZoyzqiui());
        zoyzvsvi.setText(deviceInfoBean2.getZoyzvsvi());
        yeyaghuull.setText(deviceInfoBean2.getYeyaghuull());
        dmqijmpbpd.setText(deviceInfoBean2.getDmqijmpbpd());
        uhxxfuyhfhui.setText(deviceInfoBean2.getUhxxfuyhfhui());
        zoyzxrvrfhui.setText(deviceInfoBean2.getZoyzxrvrfhui());
        wdxkiicuycqq.setText(deviceInfoBean2.getWdxkiicuycqq());
        qita.setText(deviceInfoBean2.getBwvu());
        if (deviceInfoBean2.getUhxxfhui() == 1){
            uhxxfhuiZiso.setChecked(true);
            uhxxfhuiDmds.setChecked(false);
            uhxxfhuiDmds.setClickable(false);
        }
        else{
            uhxxfhuiDmds.setChecked(true);
            uhxxfhuiZiso.setChecked(false);
            uhxxfhuiZiso.setClickable(false);
        }
        if (deviceInfoBean2.getZoyzfhui() == 1){
            zoyzfhuiZids.setChecked(true);
            zoyzfhuiDmds.setChecked(false);
            zoyzfhuiDmds.setClickable(false);
        }
        else{
            zoyzfhuiDmds.setChecked(true);
            zoyzfhuiZids.setChecked(false);
            zoyzfhuiZids.setClickable(false);
        }
    }

    private void setupUi() {
        jnhoriqi.setFocusable(false);
        jnhoriqi.setFocusableInTouchMode(false);
        uebwzsjx.setFocusable(false);
        uebwzsjx.setFocusableInTouchMode(false);

        uebwdjjx.addTextChangedListener(new TextWatcher() {
            @Override
            public void beforeTextChanged(CharSequence s, int start, int count, int after) {
            }

            @Override
            public void onTextChanged(CharSequence s, int start, int before, int count) {
            }

            @Override
            public void afterTextChanged(Editable s) {
                System.out.println("afterTextChanged:" + s);
                setUebwzsjx();
            }
        });
        uebwuull.addTextChangedListener(new TextWatcher() {
            @Override
            public void beforeTextChanged(CharSequence s, int start, int count, int after) {

            }

            @Override
            public void onTextChanged(CharSequence s, int start, int before, int count) {

            }

            @Override
            public void afterTextChanged(Editable s) {
                setUebwzsjx();
            }
        });

    }

    private void setUebwzsjx() {
        float danjia, shuliang, zongjia;
        if (uebwdjjx.length() > 0) {
            danjia = Float.parseFloat(uebwdjjx.getText().toString());
        } else {
            return;
        }
        if (uebwuull.length() > 0) {
            shuliang = Float.parseFloat(uebwuull.getText().toString());
        } else {
            return;
        }
        zongjia = danjia * shuliang;
        uebwzsjx.setText(String.valueOf(zongjia));
    }

    private boolean isValid() {
        boolean flag = true;
        if (!check_anvlfhui()) {
            flag = false;
        }
        if (!check_cczofhui()) {
            flag = false;
        }
        if (!check_jnhoriqi()) {
            flag = false;
        }
        if (!check_pftuyjse()) {
            flag = false;
        }
        if (!check_uebwdjjx()) {
            flag = false;
        }
        if (!check_uebwuull()) {
            flag = false;
        }
        if (!check_uebwxkhc()) {
            flag = false;
        }
        if (!check_uhxxfuyhfhui()) {
            flag = false;
        }
        if (!check_uhxxvsvi()) {
            flag = false;
        }
        if (!check_zoyzqiui()) {
            flag = false;
        }
        if (!check_zoyzxrvrfhui()) {
            flag = false;
        }
        if (!check_uhxxqiui()) {
            flag = false;
        }
        if (!check_zoyzvsvi()) {
            flag = false;
        }
        return flag;
    }
    private boolean check_uebwxkhc() {
        if (uebwxkhc.getText().toString().trim().isEmpty()) {
            setError(uebwxkhcL, "请输入设备型号");
            return false;
        } else {
            uebwxkhcL.setErrorEnabled(false);
            return true;
        }
    }
    private boolean check_uebwuull() {
        if (uebwuull.getText().toString().trim().isEmpty()) {
            setError(uebwuullL, "请输入设备数量");
            return false;
        } else {
            uebwuullL.setErrorEnabled(false);
            return true;
        }
    }
    private boolean check_uebwdjjx() {
        if (uebwdjjx.getText().toString().trim().isEmpty()) {
            setError(uebwdjjxL, "起输入设备单价");
            return false;
        } else {
            uebwdjjxL.setErrorEnabled(false);
            return true;
        }
    }
    private boolean check_jnhoriqi() {
        if (jnhoriqi.getText().toString().trim().isEmpty()) {
            setError(jnhoriqiL, "请输入交货日期");
            return false;
        } else {
            jnhoriqiL.setErrorEnabled(false);
            return true;
        }
    }
    private boolean check_cczofhui() {
        if (cczofhui.getText().toString().trim().isEmpty()) {
            setError(cczofhuiL, "请输入操作方式");
            return false;
        } else {
            cczofhuiL.setErrorEnabled(false);
            return true;
        }
    }
    private boolean check_pftuyjse() {
        if (pftuyjse.getText().toString().trim().isEmpty()) {
            setError(pftuyjseL, "请输入喷涂颜色");
            return false;
        } else {
            pftuyjseL.setErrorEnabled(false);
            return true;
        }
    }
    private boolean check_anvlfhui() {
        if (anvlfhui.getText().toString().trim().isEmpty()) {
            setError(anvlfhuiL, "请输入安装方式");
            return false;
        } else {
            anvlfhuiL.setErrorEnabled(false);
            return true;
        }
    }
    private boolean check_uhxxqiui() {
        if (uhxxqiui.getText().toString().trim().isEmpty()) {
            setError(uhxxqiuiL, "起始角度");
            return false;
        } else {
            uhxxqiuiL.setErrorEnabled(false);
            return true;
        }
    }
    private boolean check_uhxxvsvi() {
        if (uhxxvsvi.getText().toString().trim().isEmpty()) {
            setError(uhxxvsviL, "终止角度");
            return false;
        } else {
            uhxxvsviL.setErrorEnabled(false);
            return true;
        }
    }
    private boolean check_zoyzqiui() {
        if (zoyzqiui.getText().toString().trim().isEmpty()) {
            setError(zoyzqiuiL, "起始角度");
            return false;
        } else {
            zoyzqiuiL.setErrorEnabled(false);
            return true;
        }
    }
    private boolean check_zoyzvsvi() {
        if (zoyzvsvi.getText().toString().trim().isEmpty()) {
            setError(zoyzvsviL, "终止角度");
            return false;
        } else {
            zoyzvsviL.setErrorEnabled(false);
            return true;
        }
    }
    private boolean check_uhxxfuyhfhui() {
        if (uhxxfuyhfhui.getText().toString().trim().isEmpty()) {
            setError(uhxxfuyhfhuiL, "请输入上下俯仰方式");
            return false;
        } else {
            uhxxfuyhfhuiL.setErrorEnabled(false);
            return true;
        }
    }
    private boolean check_zoyzxrvrfhui() {
        if (zoyzxrvrfhui.getText().toString().trim().isEmpty()) {
            setError(zoyzxrvrfhuiL, "请输入左右旋转方式");
            return false;
        } else {
            zoyzxrvrfhuiL.setErrorEnabled(false);
            return true;
        }
    }
    private static void setError(TextInputLayout textInputLayout, String info) {
        textInputLayout.setError(info);
        textInputLayout.setErrorEnabled(true);
    }

    private void setData() {
        deviceInfoBean = new DeviceInfoBean();
        deviceInfoBean.setUebwxkhc(uebwxkhc.getText().toString());
        deviceInfoBean.setUebwuull(uebwuull.getText().toString());
        deviceInfoBean.setUebwdjjx(uebwdjjx.getText().toString());
        deviceInfoBean.setUebwzsjx(uebwzsjx.getText().toString());
        deviceInfoBean.setJnhoriqi(jnhoriqi.getText().toString());
        deviceInfoBean.setFadmjizu(fadmjizu.getText().toString());
        deviceInfoBean.setCczofhui(cczofhui.getText().toString());
        deviceInfoBean.setUvxlrsji(uvxlrsji.getText().toString());
        deviceInfoBean.setPftuyjse(pftuyjse.getText().toString());
        deviceInfoBean.setAnvlfhui(anvlfhui.getText().toString());
        deviceInfoBean.setUvbgxkhc(uvbgxkhc.getText().toString());
        deviceInfoBean.setUhxxqiui(uhxxqiui.getText().toString());
        deviceInfoBean.setUhxxvsvi(uhxxvsvi.getText().toString());
        deviceInfoBean.setZoyzqiui(zoyzqiui.getText().toString());
        deviceInfoBean.setZoyzvsvi(zoyzvsvi.getText().toString());
        deviceInfoBean.setYeyaghuull(yeyaghuull.getText().toString());
        deviceInfoBean.setDmqijmpbpd(dmqijmpbpd.getText().toString());
        deviceInfoBean.setUhxxfuyhfhui(uhxxfuyhfhui.getText().toString());
        deviceInfoBean.setZoyzxrvrfhui(zoyzxrvrfhui.getText().toString());
        deviceInfoBean.setWdxkiicuycqq(wdxkiicuycqq.getText().toString());
        deviceInfoBean.setBwvu(qita.getText().toString());
        if (uhxxfhuiDmds.isChecked()){
            deviceInfoBean.setUhxxfhui(0);
        }
        if (uhxxfhuiZiso.isChecked()){
            deviceInfoBean.setUhxxfhui(1);
        }
        if (zoyzfhuiDmds.isChecked()){
            deviceInfoBean.setZoyzfhui(0);
        }
        if (zoyzfhuiZids.isChecked()){
            deviceInfoBean.setZoyzfhui(1);
        }
    }

    private void TimepickInit() {
        pvTime = new TimeUtil(this).getTimePicker();
    }
}
