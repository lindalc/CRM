package com.example.os.crm.Device.Activity;

import android.content.Context;
import android.content.Intent;
import android.databinding.DataBindingUtil;
import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;
import android.view.View;
import android.widget.TextView;

import com.example.os.crm.Device.Model.DeviceInfoBean;
import com.example.os.crm.R;
import com.example.os.crm.databinding.ActivityDeviceInfoDetailBinding;

import butterknife.BindView;
import butterknife.ButterKnife;

public class DeviceInfoDetail extends AppCompatActivity {

    @BindView(R.id.fuyhjndu)
    TextView fuyhjndu;
    @BindView(R.id.xrvrjndu)
    TextView xrvrjndu;

    public void startActivity(Context context, DeviceInfoBean deviceInfoBean){
        Intent intent = new Intent(context, DeviceInfoDetail.class);
        intent.putExtra("data", deviceInfoBean);
        context.startActivity(intent);
    }

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        ActivityDeviceInfoDetailBinding binding =
                DataBindingUtil.setContentView(this, R.layout.activity_device_info_detail);
        ButterKnife.bind(this);
        Intent intent = getIntent();
        DeviceInfoBean deviceInfoBean = intent.getParcelableExtra("data");
        binding.setDeviceinfo(deviceInfoBean);
        fuyhjndu.setText(deviceInfoBean.getUhxxqiui() + "--" + deviceInfoBean.getUhxxvsvi());
        xrvrjndu.setText(deviceInfoBean.getZoyzqiui() + "--" + deviceInfoBean.getZoyzvsvi());
    }

}
