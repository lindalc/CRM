package com.example.os.crm.Dingdan.Activity;

import android.content.Context;
import android.content.DialogInterface;
import android.content.Intent;
import android.os.Bundle;
import android.os.Handler;
import android.os.Message;
import android.support.v4.widget.SwipeRefreshLayout;
import android.support.v7.app.AlertDialog;
import android.support.v7.app.AppCompatActivity;
import android.support.v7.widget.DividerItemDecoration;
import android.support.v7.widget.GridLayoutManager;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.support.v7.widget.StaggeredGridLayoutManager;
import android.view.View;
import android.widget.AdapterView;
import android.widget.Button;
import android.widget.ListView;
import android.widget.Toast;

import com.example.os.crm.Common.GetDataDoneListener;
import com.example.os.crm.Common.UserInfo;
import com.example.os.crm.Dingdan.Adapter.DkDjListAdapter;
import com.example.os.crm.Dingdan.Model.DkDjDetailInfoBean;
import com.example.os.crm.Dingdan.Model.OrderInfoList;
import com.example.os.crm.R;
import com.squareup.okhttp.OkHttpClient;
import com.squareup.okhttp.Request;
import com.squareup.okhttp.Response;

import org.json.JSONObject;

import java.io.IOException;
import java.util.ArrayList;
import java.util.List;
import java.util.concurrent.TimeUnit;

import butterknife.BindView;
import butterknife.ButterKnife;


public class History extends AppCompatActivity {

    @BindView(R.id.history_list)
    ListView listView;
    @BindView(R.id.swipe_refresh)
    SwipeRefreshLayout swipeRefresh;
    @BindView(R.id.shaixuan)
    Button button_shaixuan;

    String userId;
    Handler handler;
    private List<DkDjDetailInfoBean> dkDjDetailInfoBeanList;
    DkDjListAdapter dkDjListAdapter;
    private String daishanchudingdanbianhao;
    private OrderInfoList orderInfoList;

    public void startActivity(Context context) {
        Intent intent = new Intent(context, History.class);
        context.startActivity(intent);
    }

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_history);
        ButterKnife.bind(this);

        listView.setOnItemClickListener(new AdapterView.OnItemClickListener() {
            @Override
            public void onItemClick(AdapterView<?> parent, View view, int position, long id) {

            }
        });

        userId = UserInfo.userid;
        initData();
        initUi();
        initHandler();
        initListView();

    }

    private void initUi() {
        listView = (ListView) findViewById(R.id.history_list);
        button_shaixuan = (Button) findViewById(R.id.shaixuan);
        button_shaixuan.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent intent1 = new Intent(History.this, DingDanFilter.class);
                intent1.putExtra("userid", userId);
                startActivity(intent1);
            }
        });
    }

    private void initHandler() {
        handler = new Handler() {
            public void handleMessage(Message msg) {
                switch (msg.what) {
                    case 0:
                        swipeRefresh.setRefreshing(false);
                        dkDjListAdapter.notifyDataSetChanged();
                        break;
                    case 2:
                        shanchudingdan();
                        break;
                    case 3:
                        parseResponse(msg.obj.toString());
                        initData();
                        break;
                    default:
                        break;
                }
            }
        };
    }

    private void initListView() {
        listView.setAdapter(dkDjListAdapter);
        listView.setOnItemClickListener(new AdapterView.OnItemClickListener() {
            @Override
            public void onItemClick(AdapterView<?> parent, View view, int position, long id) {
                DkDjDetailInfoBean itemDetail = dkDjDetailInfoBeanList.get(position);
                new DingdanDetail().startActivity(History.this, itemDetail);
            }
        });
        listView.setOnItemLongClickListener(new AdapterView.OnItemLongClickListener() {
            @Override
            public boolean onItemLongClick(AdapterView<?> parent, View view, int position, long id) {
                DkDjDetailInfoBean itemDetail = dkDjDetailInfoBeanList.get(position);
                daishanchudingdanbianhao = itemDetail.getDkdjxbxi().getDkdjbmhc();
                int shenpi = itemDetail.getVltd().getUfpi();
                AlertDialog.Builder dialog = new AlertDialog.Builder(History.this);
                dialog.setTitle("");
                dialog.setCancelable(false);
                if (shenpi == 0) {
                    dialog.setMessage("确认删除吗?");
                    dialog.setPositiveButton("删除", new DialogInterface.OnClickListener() {
                        @Override
                        public void onClick(DialogInterface dialog, int which) {
                            Message msg = new Message();
                            msg.what = 2;
                            handler.sendMessage(msg);
                        }
                    });
                } else {
                    dialog.setMessage("已审批订单不能删除");
                }
                dialog.setNegativeButton("取消", new DialogInterface.OnClickListener() {
                    @Override
                    public void onClick(DialogInterface dialog, int which) {

                    }
                });
                dialog.show();
                return true;
            }
        });
        swipeRefresh.setOnRefreshListener(new SwipeRefreshLayout.OnRefreshListener() {
            @Override
            public void onRefresh() {
                orderInfoList.refresh();
            }
        });
    }

    private void initData() {
        orderInfoList = OrderInfoList.getInstance();
        dkDjDetailInfoBeanList = orderInfoList.getDkDjDetailInfoBeanList();
        dkDjListAdapter = new DkDjListAdapter(History.this, dkDjDetailInfoBeanList);
        GetDataDoneListener getDataDoneListener = new GetDataDoneListener() {
            @Override
            public void onFinish() {

                Message msg = new Message();
                msg.what = 0;
                handler.sendMessage(msg);
            }
        };
        orderInfoList.setListener(getDataDoneListener);
    }

    private void shanchudingdan() {
        new Thread() {
            @Override
            public void run() {
                super.run();
                String url = UserInfo.host + "/crm/delete/" + daishanchudingdanbianhao;
                OkHttpClient client = new OkHttpClient();
                client.setConnectTimeout(10, TimeUnit.SECONDS);
                Request request = new Request.Builder().url(url).build();
                Response response;
                String responseData;
                try {
                    response = client.newCall(request).execute();
                    responseData = response.body().string();
                    Message msg = new Message();
                    msg.what = 3;
                    msg.obj = responseData;
                    handler.sendMessage(msg);
                } catch (IOException e) {
                    e.printStackTrace();
                }
            }
        }.start();
    }

    private void parseResponse(String response) {
        try {
            JSONObject jsonObject = new JSONObject(response);
            if (jsonObject.getInt("error") == 1) {
                Toast.makeText(History.this, "删除成功", Toast.LENGTH_SHORT).show();
            } else {
                Toast.makeText(History.this, "删除失败", Toast.LENGTH_SHORT).show();
            }
        } catch (Exception e) {
            e.printStackTrace();
        }
    }
}
