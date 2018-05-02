package com.example.os.crm.ui.activity;

import android.content.Context;
import android.content.DialogInterface;
import android.content.Intent;
import android.os.Bundle;
import android.os.Handler;
import android.os.Message;
import android.support.v4.widget.SwipeRefreshLayout;
import android.support.v7.app.AlertDialog;
import android.support.v7.app.AppCompatActivity;
import android.view.View;
import android.widget.AdapterView;
import android.widget.Button;
import android.widget.ListView;

import com.example.os.crm.Common.GetDataDoneListener;
import com.example.os.crm.Dingdan.Activity.DingdanDetail;
import com.example.os.crm.Dingdan.Model.OrderInfoList;
import com.example.os.crm.ui.adapter.HuiKuanHistoryAdapter;
import com.example.os.crm.model.HuiKuanBean;
import com.example.os.crm.model.HvKrDkDjBean;
import com.example.os.crm.model.ModelHvkrDkdjBean;
import com.example.os.crm.R;

import java.util.List;

import butterknife.BindView;
import butterknife.ButterKnife;

public class HuiKuanHistoryActivity extends AppCompatActivity {


    Handler handler;

    @BindView(R.id.hkh_listview)
    ListView listView;
    @BindView(R.id.swipe_refresh)
    SwipeRefreshLayout swipeRefresh;
    @BindView(R.id.hkh_weishenpi)
    Button button_weishenpi;
    private ModelHvkrDkdjBean modelHvkrDkdjBean;
    private List<HvKrDkDjBean> hvKrDkDjBeanList;
    private HuiKuanHistoryAdapter huiKuanHistoryAdapter;

    public void startActivity(Context context) {
        Intent intent = new Intent(context, HuiKuanHistoryActivity.class);
        context.startActivity(intent);
    }

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_hui_kuan_history);
        ButterKnife.bind(this);
        handler = new Handler() {
            public void handleMessage(Message msg) {
                switch (msg.what) {
                    case 0:
                        huiKuanHistoryAdapter.notifyDataSetChanged();
                        break;
                    case 1:
                        run_alert();
                        break;
                    default:
                        break;
                }
            }
        };
        init_ui();
        init_list();
    }

    @Override
    protected void onRestart() {
        super.onRestart();
        init_list();
    }

    private void init_ui() {
        listView = (ListView) findViewById(R.id.hkh_listview);
        button_weishenpi = (Button) findViewById(R.id.hkh_weishenpi);
        button_weishenpi.setVisibility(View.GONE);
        swipeRefresh.setOnRefreshListener(new SwipeRefreshLayout.OnRefreshListener() {
            @Override
            public void onRefresh() {
                modelHvkrDkdjBean.refresh();
            }
        });
    }

    private void init_list() {
        modelHvkrDkdjBean = ModelHvkrDkdjBean.getInstance();
        modelHvkrDkdjBean.setListener(new GetDataDoneListener() {
            @Override
            public void onFinish() {
                swipeRefresh.setRefreshing(false);
                Message msg = new Message();
                msg.what = 0;
                handler.sendMessage(msg);
            }
        });
        hvKrDkDjBeanList = modelHvkrDkdjBean.getHvKrDkDjBeanList();

        huiKuanHistoryAdapter = new HuiKuanHistoryAdapter(
                this, R.layout.huikuanhistory, hvKrDkDjBeanList);
        listView.setAdapter(huiKuanHistoryAdapter);
        listView.setOnItemClickListener(new AdapterView.OnItemClickListener() {
            @Override
            public void onItemClick(AdapterView<?> parent, View view, int position, long id) {
                HvKrDkDjBean hvKrDkDjBean = hvKrDkDjBeanList.get(position);
                HuiKuanBean huiKuanBean = hvKrDkDjBean.getHvkr();
                huiKuanBean.setDkdjbmhc(hvKrDkDjBean.getDkdj().getDkdjxbxi().getDkdjbmhc());
                huiKuanBean.setVibcjb(hvKrDkDjBean.getDkdj().getQm().getVibcjb());
                huiKuanBean.setZsjx(hvKrDkDjBean.getDkdj().getQm().getUebwzsjx());
                new HuiKuanDetailActivity().startActivity(HuiKuanHistoryActivity.this, 1, huiKuanBean);
            }
        });
        listView.setOnItemLongClickListener(new AdapterView.OnItemLongClickListener() {
            @Override
            public boolean onItemLongClick(AdapterView<?> parent, View view, int position, long id) {
                HvKrDkDjBean huiKuanBean = hvKrDkDjBeanList.get(position);
                String dingdanbianhao = huiKuanBean.getDkdj().getDkdjxbxi().getDkdjbmhc();
                OrderInfoList orderInfoList = OrderInfoList.getInstance();
                new DingdanDetail().startActivity(HuiKuanHistoryActivity.this,
                        orderInfoList.getDkdj(dingdanbianhao));
                return true;
            }
        });
    }

    private void run_alert() {
        AlertDialog.Builder dialog = new AlertDialog.Builder(HuiKuanHistoryActivity.this);
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