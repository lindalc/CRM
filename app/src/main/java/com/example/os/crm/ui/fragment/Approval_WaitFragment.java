package com.example.os.crm.ui.fragment;

import android.os.Bundle;
import android.os.Handler;
import android.os.Message;
import android.support.v4.app.Fragment;
import android.support.v4.widget.SwipeRefreshLayout;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Button;
import android.widget.EditText;

import com.afollestad.materialdialogs.MaterialDialog;
import com.chad.library.adapter.base.BaseQuickAdapter;
import com.example.os.crm.Common.GetDataDoneListener;
import com.example.os.crm.Common.UserInfo;
import com.example.os.crm.Dingdan.Model.DkDjDetailInfoBean;
import com.example.os.crm.Dingdan.Activity.DingdanDetail;
import com.example.os.crm.HttpUtil.HttpPost;
import com.example.os.crm.ui.activity.HuiKuanDetailActivity;
import com.example.os.crm.ui.activity.FundDetailActivity;
import com.example.os.crm.model.PaymentInfoBean;
import com.example.os.crm.R;
import com.example.os.crm.ui.activity.Approval_FilterActivity;
import com.example.os.crm.ui.adapter.ApprovalFragmentAdapter;
import com.example.os.crm.model.getApprovalData;
import com.example.os.crm.model.FundRestream;
import com.example.os.crm.model.ApprovalMultyItem;

import java.io.IOException;
import java.util.List;

import butterknife.BindView;
import butterknife.ButterKnife;
import butterknife.OnClick;
import butterknife.Unbinder;
import okhttp3.Call;
import okhttp3.Callback;
import okhttp3.FormBody;
import okhttp3.Response;

public class Approval_WaitFragment extends Fragment {

    Handler handler;
    @BindView(R.id.udxr)
    Button udxr;
    @BindView(R.id.list_view)
    RecyclerView listView;
    @BindView(R.id.swipe_refresh)
    SwipeRefreshLayout swipeRefresh;
    Unbinder unbinder;

    private List<ApprovalMultyItem> approvalMultyItemList;
    private ApprovalFragmentAdapter approvalFragmentAdapter;
    private int ItemType;
    private DkDjDetailInfoBean dkDjDetailInfoBean;
    private FundRestream fundRestream;
    private PaymentInfoBean paymentInfoBean;
    private getApprovalData getUfpiData;

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
                        break;
                    default:
                        break;
                }
            }
        };
        initData();
    }

    private void initData() {
        LinearLayoutManager linearLayoutManager = new LinearLayoutManager(getContext());
        listView.setLayoutManager(linearLayoutManager);
        getUfpiData = getApprovalData.getInstance();
        approvalMultyItemList = getUfpiData.getApprovalMultyItemList();
        approvalFragmentAdapter = new ApprovalFragmentAdapter(getContext(), approvalMultyItemList);
        listView.setAdapter(approvalFragmentAdapter);
        getUfpiData.setListener(new GetDataDoneListener() {
            @Override
            public void onFinish() {

                Message msg = new Message();
                msg.what = 0;
                handler.sendMessage(msg);
            }
        });
        approvalFragmentAdapter.setOnItemClickListener(new BaseQuickAdapter.OnItemClickListener() {
            @Override
            public void onItemClick(BaseQuickAdapter adapter, View view, int position) {
                ApprovalMultyItem approvalMultyItem = approvalMultyItemList.get(position);
                ItemType = approvalMultyItem.getItemType();
                switch (ItemType) {
                    case 1:
                        dkDjDetailInfoBean = approvalMultyItem.getDkdjBean();
                        break;
                    case 2:
                        fundRestream = approvalMultyItem.getFundRestream();
                        break;
                    case 3:
                        paymentInfoBean = approvalMultyItem.getPaymentInfoBean();
                        if (paymentInfoBean.getFuzeid().equals(UserInfo.userid)){
                            new FundDetailActivity().startActivity(getContext(), paymentInfoBean);
                            return;
                        }
                        break;
                }
                CreateUfPiDialog();
            }
        });

        approvalFragmentAdapter.setOnItemLongClickListener(new BaseQuickAdapter.OnItemLongClickListener() {
            @Override
            public boolean onItemLongClick(BaseQuickAdapter adapter, View view, int position) {
                ApprovalMultyItem approvalMultyItem = approvalMultyItemList.get(position);
                ItemType = approvalMultyItem.getItemType();
                switch (ItemType) {
                    case 1:
                        dkDjDetailInfoBean = approvalMultyItem.getDkdjBean();
                        new DingdanDetail().startActivity(getContext(), dkDjDetailInfoBean);
                        break;
                    case 2:
                        fundRestream = approvalMultyItem.getFundRestream();
                        new HuiKuanDetailActivity().startActivity(getContext(), fundRestream.getDingdanbianhao());
                        break;
                    case 3:
                        paymentInfoBean = approvalMultyItem.getPaymentInfoBean();
                        new FundDetailActivity().startActivity(getContext(), paymentInfoBean);
                        break;
                }
                return false;
            }
        });

        swipeRefresh.setOnRefreshListener(new SwipeRefreshLayout.OnRefreshListener() {
            @Override
            public void onRefresh() {
                getUfpiData.refresh();
            }
        });
    }

    private void CreateUfPiDialog() {
        final EditText bwvu;
        Button tsyi;
        Button quxn;
        Button bohv;
        final MaterialDialog dialog =
                new MaterialDialog.Builder(getActivity())
                        .title("审批")
                        .customView(R.layout.ufpidialog, true)
                        .build();
        //noinspection ConstantConditions
        bwvu = dialog.getCustomView().findViewById(R.id.ufpibwvu);
        tsyi = dialog.getCustomView().findViewById(R.id.tsyi);
        tsyi.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                tongyi(1, bwvu.getText().toString());
                getUfpiData.refresh();
                dialog.dismiss();

            }
        });
        bohv = dialog.getCustomView().findViewById(R.id.bohv);
        bohv.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                tongyi(2, bwvu.getText().toString());
                getUfpiData.refresh();
                dialog.dismiss();
            }
        });
        quxn = dialog.getCustomView().findViewById(R.id.quxn);
        quxn.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                dialog.dismiss();
            }
        });
        dialog.show();
    }

    private void tongyi(int shenpi, String bwvu) {
        switch (ItemType) {
            case 1:
                tongyi_dingdan(String.valueOf(shenpi), bwvu);
                break;
            case 2:
                tongyi_huikuan(String.valueOf(shenpi), bwvu);
                break;
            case 3:
                tongyi_cdwuhvkr(String.valueOf(shenpi), bwvu);
                break;
        }
    }

    private void tongyi_dingdan(String shenpi, String bwvu) {

        String url = UserInfo.host + "/crm/admin/dingdanshenpi";

        FormBody.Builder builder = new FormBody.Builder()
                .add("dingdanbianhao", dkDjDetailInfoBean.getDkdjxbxi().getDkdjbmhc())
                .add("shenpi", shenpi)
                .add("shenpibeizhu", bwvu)
                .add("userid", UserInfo.userid);
        new HttpPost().postData(url, builder, new Callback() {
            @Override
            public void onFailure(Call call, IOException e) {
                e.printStackTrace();
            }

            @Override
            public void onResponse(Call call, Response response) throws IOException {
                String responseData = response.body().string();
                Message msg = new Message();
                msg.what = 4;
                handler.sendMessage(msg);
                Log.d("postdata", responseData);
            }
        });
    }

    private void tongyi_huikuan(String shenpi, String bwvu) {

        String url = UserInfo.host + "/crm/admin/huikuanshenpi";
        FormBody.Builder builder = new FormBody.Builder()
                .add("uijmio", fundRestream.getTimestamp())
                .add("shenpi", shenpi)
                .add("shenpibeizhu", bwvu)
                .add("userid", UserInfo.userid);
        new HttpPost().postData(url, builder, new Callback() {
            @Override
            public void onFailure(Call call, IOException e) {
                e.printStackTrace();
                Log.d("获取待审批数据", "onFailure: get data failed");
            }

            @Override
            public void onResponse(Call call, Response response) throws IOException {
                String data = response.body().string();
                Message msg = new Message();
                msg.what = 4;
                msg.obj = data;
                handler.sendMessage(msg);
            }
        });
    }

    private void tongyi_cdwuhvkr(String shenpi, String bwvu) {

        String url = UserInfo.host + "/crm/paymentufpi";
        FormBody.Builder builder = new FormBody.Builder()
                .add("uijmio", String.valueOf(paymentInfoBean.getUijm()))
                .add("shenpi", shenpi)
                .add("shenpibeizhu", bwvu)
                .add("userid", UserInfo.userid);
        new HttpPost().postData(url, builder, new Callback() {
            @Override
            public void onFailure(Call call, IOException e) {
                Log.d("获取待审批数据", "onFailure: get data failed");
            }

            @Override
            public void onResponse(Call call, Response response) throws IOException {
                String data = response.body().string();
                Message msg = new Message();
                msg.what = 4;
                msg.obj = data;
                handler.sendMessage(msg);
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
        new Approval_FilterActivity().startActivity(getContext());
    }
}
