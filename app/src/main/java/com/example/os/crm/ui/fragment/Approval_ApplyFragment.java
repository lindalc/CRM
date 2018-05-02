package com.example.os.crm.ui.fragment;

import android.os.Bundle;
import android.os.Handler;
import android.os.Message;
import android.support.v4.app.Fragment;
import android.support.v4.widget.SwipeRefreshLayout;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Button;
import android.widget.Toast;

import com.example.os.crm.Common.GetDataDoneListener;
import com.example.os.crm.R;
import com.example.os.crm.ui.adapter.ApprovalFragmentAdapter;
import com.example.os.crm.model.GetApplyData;
import com.example.os.crm.model.ApprovalMultyItem;

import java.util.List;

import butterknife.BindView;
import butterknife.ButterKnife;
import butterknife.OnClick;
import butterknife.Unbinder;

public class Approval_ApplyFragment extends Fragment {

    Handler handler;
    @BindView(R.id.list_view)
    RecyclerView listView;
    @BindView(R.id.udxr)
    Button udxr;
    @BindView(R.id.swipe_refresh)
    SwipeRefreshLayout swipeRefresh;
    Unbinder unbinder;


    private List<ApprovalMultyItem> approvalMultyItemList;
    private GetApplyData getApplyData;
    private ApprovalFragmentAdapter approvalFragmentAdapter;

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        View view = inflater.inflate(R.layout.dai_shen_pi, null);
        unbinder = ButterKnife.bind(this, view);
        return view;
    }

    @Override
    public void onViewCreated(View view, Bundle savedInstanceState) {
        super.onViewCreated(view, savedInstanceState);
        handler = new Handler() {
            public void handleMessage(Message msg) {
                switch (msg.what) {
                    case 0:
                        swipeRefresh.setRefreshing(false);
                        approvalFragmentAdapter.notifyDataSetChanged();
                        break;
                    case 2:
                        break;
                    case 4:
                        getData();
                        break;
                    default:
                        break;
                }
            }
        };
        getData();
    }

    @Override
    public void onResume() {
        super.onResume();
        getData();
    }

    private void getData() {
        LinearLayoutManager linearLayoutManager = new LinearLayoutManager(getContext());
        listView.setLayoutManager(linearLayoutManager);
        getApplyData = GetApplyData.getInstance();
        approvalMultyItemList = getApplyData.getApprovalMultyItemList();
        approvalFragmentAdapter = new ApprovalFragmentAdapter(getContext(), approvalMultyItemList);
        getApplyData.setListener(new GetDataDoneListener() {
            @Override
            public void onFinish() {

                Message msg = new Message();
                msg.what = 0;
                handler.sendMessage(msg);
            }
        });
        listView.setAdapter(approvalFragmentAdapter);
        swipeRefresh.setOnRefreshListener(new SwipeRefreshLayout.OnRefreshListener() {
            @Override
            public void onRefresh() {
                getApplyData.refresh();
            }
        });
    }


    @Override
    public void onDestroyView() {
        super.onDestroyView();
        unbinder.unbind();
    }

    @OnClick(R.id.udxr)
    public void onViewClicked() {
        //TODO 添加我的申请的筛选功能
        Toast.makeText(getContext(), "改功能未添加", Toast.LENGTH_SHORT).show();
    }
}
