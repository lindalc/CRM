package com.example.os.crm.ui.fragment;

import android.os.Bundle;
import android.support.design.widget.AppBarLayout;
import android.support.v4.app.Fragment;
import android.support.v7.widget.Toolbar;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.LinearLayout;

import com.example.os.crm.Bonus.Activity.BonusFaFh;
import com.example.os.crm.Bonus.Activity.BonusOrder;
import com.example.os.crm.Charge.Activity.NewChargeActivity;
import com.example.os.crm.Charge.Activity.ReturnCharge;
import com.example.os.crm.Contact.Activity.CreateUpdateCustomerInfo;
import com.example.os.crm.Contact.Activity.CustomerList;
import com.example.os.crm.Contract.Activity.AddContract;
import com.example.os.crm.Contract.Activity.HeTongList;
import com.example.os.crm.Dingdan.Activity.CreateNew;
import com.example.os.crm.Dingdan.Activity.History;
import com.example.os.crm.ui.activity.AddFundActivity;
import com.example.os.crm.ui.activity.FundListActivity;
import com.example.os.crm.ui.activity.HuiKuanHistoryActivity;
import com.example.os.crm.R;
import com.example.os.crm.ui.activity.Approval_WaitActivity;

import butterknife.BindView;
import butterknife.ButterKnife;
import butterknife.OnClick;
import butterknife.Unbinder;


public class FunctionFragment extends Fragment {


    @BindView(R.id.toolbar)
    Toolbar toolbar;
    @BindView(R.id.appbar)
    AppBarLayout appbar;
    @BindView(R.id.dkdjjilu)
    LinearLayout dkdjjilu;
    @BindView(R.id.xbdkdj)
    LinearLayout xbdkdj;
    @BindView(R.id.hetsjilu)
    LinearLayout hetsjilu;
    @BindView(R.id.xbhets)
    LinearLayout xbhets;
    @BindView(R.id.hvkrjilu)
    LinearLayout hvkrjilu;
    @BindView(R.id.xbhvkr)
    LinearLayout xbhvkr;
    Unbinder unbinder;
    @BindView(R.id.jisrtiig)
    LinearLayout jisrtiig;
    @BindView(R.id.jpsrtiig)
    LinearLayout jpsrtiig;
    @BindView(R.id.ufpi)
    LinearLayout ufpi;
    @BindView(R.id.kehugrli)
    LinearLayout kehugrli;
    @BindView(R.id.xbzgkehu)
    LinearLayout xbzgkehu;
    @BindView(R.id.fwysufqk)
    LinearLayout fwysufqk;
    @BindView(R.id.dkdjhvkr)
    LinearLayout dkdjhvkr;
    @BindView(R.id.fwysjilv)
    LinearLayout fwysjilv;


    private String TAG = "FunctionFragment";
    private static final int TabPosition = 2;

    public int getTabPosition() {
        return TabPosition;
    }

    @Override
    public void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
    }

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        // Inflate the layout for this fragment
        View view = inflater.inflate(R.layout.fragment_gsng, container, false);
        unbinder = ButterKnife.bind(this, view);
        return view;
    }

    @Override
    public void onDetach() {
        super.onDetach();
    }

    @Override
    public void onStop() {
        super.onStop();
        Log.d(TAG, "onStop: FunctionFragment");
    }

    public void initData() {

    }

    @Override
    public void onDestroyView() {
        super.onDestroyView();
        unbinder.unbind();
    }


    @OnClick({R.id.dkdjjilu, R.id.xbdkdj, R.id.hetsjilu,
            R.id.xbhets, R.id.hvkrjilu, R.id.xbhvkr,
            R.id.jisrtiig, R.id.jpsrtiig, R.id.ufpi,
            R.id.xbzgkehu, R.id.kehugrli, R.id.fwysufqk,
            R.id.dkdjhvkr, R.id.fwysjilv})
    public void onViewClicked(View view) {
        switch (view.getId()) {
            case R.id.dkdjjilu:
                new History().startActivity(getContext());
                break;
            case R.id.xbdkdj:
                new CreateNew().startActivity(getContext());
                break;
            case R.id.hetsjilu:
                new HeTongList().startActivity(getContext());
                break;
            case R.id.xbhets:
                new AddContract().startActivity(getContext());
                break;
            case R.id.hvkrjilu:
                new FundListActivity().startActivity(getContext());
                break;
            case R.id.xbhvkr:
                new AddFundActivity().startActivity(getContext());
                break;
            case R.id.jisrtiig:
                new BonusOrder().startActivity(getContext());
                break;
            case R.id.jpsrtiig:
                new BonusFaFh().startActivity(getContext());
                break;
            case R.id.ufpi:
                new Approval_WaitActivity().startActivity(getContext());
                break;
            case R.id.xbzgkehu:
                new CreateUpdateCustomerInfo().startActivity(getContext(), "");
                break;
            case R.id.kehugrli:
                new CustomerList().startActivity(getContext());
                break;
            case R.id.fwysufqk:
                new NewChargeActivity().startActivity(getContext());
                break;
            case R.id.dkdjhvkr:
                new HuiKuanHistoryActivity().startActivity(getContext());
                break;
            case R.id.fwysjilv:
                new ReturnCharge().startActivity(getContext());
                break;
        }
    }
}
