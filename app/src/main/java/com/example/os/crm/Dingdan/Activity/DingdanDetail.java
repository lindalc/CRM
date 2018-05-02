package com.example.os.crm.Dingdan.Activity;

import android.content.Context;
import android.content.Intent;
import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;
import android.view.View;
import android.view.ViewGroup;
import android.widget.AdapterView;
import android.widget.LinearLayout;
import android.widget.ListAdapter;
import android.widget.ListView;
import android.widget.TextView;
import android.widget.Toast;

import com.example.os.crm.Contact.Activity.CustomerDetail;
import com.example.os.crm.Device.Activity.DeviceInfoDetail;
import com.example.os.crm.Device.Adapter.DeviceinfolistAdapter;
import com.example.os.crm.Device.Model.DeviceInfoBean;
import com.example.os.crm.Dingdan.Model.DkDjDetailInfoBean;
import com.example.os.crm.Contract.Activity.HeTongFilesList;
import com.example.os.crm.ui.activity.HuiKuanDetailActivity;
import com.example.os.crm.R;

import java.util.List;

import butterknife.BindView;
import butterknife.ButterKnife;
import butterknife.OnClick;

public class DingdanDetail extends AppCompatActivity {

    @BindView(R.id.dkdjbmhc)
    TextView dkdjbmhc;
    @BindView(R.id.hetsbmhc)
    TextView hetsbmhc;
    @BindView(R.id.yewuyr)
    TextView yewuyr;
    @BindView(R.id.kehumkig)
    TextView kehumkig;
    @BindView(R.id.kehumkig_l)
    LinearLayout kehumkigL;
    @BindView(R.id.kehudivi)
    TextView kehudivi;
    @BindView(R.id.lmxirf)
    TextView lmxirf;
    @BindView(R.id.lmxirfdmhx)
    TextView lmxirfdmhx;
    @BindView(R.id.uebwzsjx)
    TextView uebwzsjx;
    @BindView(R.id.uebwzsjx_l)
    LinearLayout uebwzsjxL;
    @BindView(R.id.vekz)
    TextView vekz;
    @BindView(R.id.tiig)
    TextView tiig;
    @BindView(R.id.vibcjb)
    TextView vibcjb;
    @BindView(R.id.dcqiuijm)
    TextView dcqiuijm;
    @BindView(R.id.listview)
    ListView listview;
    @BindView(R.id.hetsbmhc_l)
    LinearLayout hetsbmhcL;

    private String TAG = "订单详情";
    private DkDjDetailInfoBean dkDjDetailInfoBean;
    private List<DeviceInfoBean> deviceInfoBeanList;


    public void startActivity(Context context, DkDjDetailInfoBean dkDjDetailInfoBean){
        Intent intent = new Intent(context, DingdanDetail.class);
        intent.putExtra("data", dkDjDetailInfoBean);
        context.startActivity(intent);
    }


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_dingdan_detail);
        ButterKnife.bind(this);
        initParam();
        if (dkDjDetailInfoBean!=null){
            initUiData();
        }

    }
    private void initParam() {
        Intent intent = getIntent();
        dkDjDetailInfoBean = intent.getParcelableExtra("data");
    }
     private void initUiData() {
        dkdjbmhc.setText(dkDjDetailInfoBean.getDkdjxbxi().getDkdjbmhc());
        hetsbmhc.setText(dkDjDetailInfoBean.getDkdjxbxi().getHetsbmhc());
        yewuyr.setText(dkDjDetailInfoBean.getDkdjxbxi().getYewuyr());
        kehumkig.setText(dkDjDetailInfoBean.getKehuziln().getKehumkig());
        kehudivi.setText(dkDjDetailInfoBean.getKehuziln().getKehudivi());
        lmxirf.setText(dkDjDetailInfoBean.getKehuziln().getLmxirf());
        lmxirfdmhx.setText(dkDjDetailInfoBean.getKehuziln().getLmxirfdmhx());
        tiig.setText(dkDjDetailInfoBean.getQm().getTiig());
        setVibcjb();
        setUebwzsjx();
        setDeviceInfo();
    }

    private void setVibcjb() {
        if (dkDjDetailInfoBean.getQm().getVibcjb().length() < 1) {
            vibcjb.setText("0元");
            dcqiuijm.setVisibility(View.GONE);
        } else {
            vibcjb.setText(dkDjDetailInfoBean.getQm().getVibcjb());
            dcqiuijm.setText(dkDjDetailInfoBean.getQm().getVibcjbdcqi());
        }
    }

    private void setUebwzsjx() {
        int uvfz = Integer.parseInt(dkDjDetailInfoBean.getQm().getUvfz());
        String temp;
        if (uvfz == 1) {
            temp = "(含税合计)";
        } else {
            temp = "(不开发票)";
        }
        temp += dkDjDetailInfoBean.getQm().getUebwzsjx() + "元";
        uebwzsjx.setText(temp);
    }

    private void setDeviceInfo() {
        deviceInfoBeanList = dkDjDetailInfoBean.getUebwxbxi();
        DeviceinfolistAdapter deviceinfolistAdapter = new DeviceinfolistAdapter(this, deviceInfoBeanList);
        listview.setAdapter(deviceinfolistAdapter);
        listview.setOnItemClickListener(new AdapterView.OnItemClickListener() {
            @Override
            public void onItemClick(AdapterView<?> parent, View view, int position, long id) {
                DeviceInfoBean deviceInfoBean = deviceInfoBeanList.get(position);
                new DeviceInfoDetail().startActivity(DingdanDetail.this, deviceInfoBean);
            }
        });
        setListViewHeightBasedOnChildren(listview);
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

    @OnClick({R.id.kehumkig_l, R.id.uebwzsjx_l, R.id.hetsbmhc_l})
    public void onViewClicked(View view) {
        switch (view.getId()) {
            case R.id.kehumkig_l:
                new CustomerDetail().startActivity(this, dkDjDetailInfoBean.getKehuziln().getKehuid());
                break;
            case R.id.uebwzsjx_l:
                if (dkDjDetailInfoBean.getVltd().getUfpi() == 2){
                    Toast.makeText(DingdanDetail.this, "订单已驳回，没有回款信息", Toast.LENGTH_SHORT).show();
                    return;
                }
                Intent intent = new Intent(DingdanDetail.this, HuiKuanDetailActivity.class);
                intent.putExtra("dkdjbmhc", dkDjDetailInfoBean.getDkdjxbxi().getDkdjbmhc());
                startActivity(intent);
                break;
            case R.id.hetsbmhc_l:
                new HeTongFilesList().actionStart(DingdanDetail.this, dkDjDetailInfoBean.getDkdjxbxi().getDkdjbmhc());
                break;
        }
    }
}