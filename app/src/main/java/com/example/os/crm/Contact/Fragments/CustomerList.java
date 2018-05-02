package com.example.os.crm.Contact.Fragments;


import android.content.Intent;
import android.net.Uri;
import android.os.Bundle;
import android.support.v4.app.Fragment;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.EditText;
import com.chad.library.adapter.base.BaseQuickAdapter;
import com.example.os.crm.Contact.Adapter.CustomerListAdapter;
import com.example.os.crm.Contact.Model.Customer;
import com.example.os.crm.Contact.Model.CustomerBean;
import com.example.os.crm.Contact.Activity.CustomerDetail;
import com.example.os.crm.R;
import java.util.List;

import butterknife.BindView;
import butterknife.ButterKnife;
import butterknife.Unbinder;

/**
 * A simple {@link Fragment} subclass.
 */
public class CustomerList extends Fragment {

    @BindView(R.id.search)
    EditText search;
    @BindView(R.id.customer_list)
    RecyclerView customerList;
    Unbinder unbinder;

    private static final int tabPosition = 1;
    private boolean isfirst = true;
    private String TAG = "CustomerList";

    Customer customer = Customer.getInstance();
    private List<CustomerBean> customerBeanList = customer.getCustomerBeanList();
    private CustomerListAdapter customerListAdapter = new CustomerListAdapter(R.layout.customer_item, customerBeanList);

    public int getTabPosition() {
        return tabPosition;
    }


    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        // Inflate the layout for this fragment
        View view = inflater.inflate(R.layout.fragment_customer_list, container, false);
        unbinder = ButterKnife.bind(this, view);
        if (isfirst){
            getData();
            customerList.setLayoutManager(new LinearLayoutManager(getActivity()));
            customerList.setAdapter(customerListAdapter);
            isfirst = false;
        }
        return view;
    }

    public void initData() {
        getData();
    }

    @Override
    public void onDestroyView() {
        super.onDestroyView();
        unbinder.unbind();
    }

    public void getData(){
        customerBeanList = customer.getCustomerBeanList();
        customerListAdapter.notifyDataSetChanged();
        initClick();
    }

    private void initClick() {
        customerListAdapter.setOnItemClickListener(new BaseQuickAdapter.OnItemClickListener() {
            @Override
            public void onItemClick(BaseQuickAdapter adapter, View view, int position) {
                Log.d(TAG, "onItemClick: " + position);
                CustomerBean customerBean = customerBeanList.get(position);
                new CustomerDetail().startActivity(getActivity(), customerBean.getKehuid());
            }
        });
        customerListAdapter.setOnItemChildClickListener(new BaseQuickAdapter.OnItemChildClickListener() {
            @Override
            public void onItemChildClick(BaseQuickAdapter adapter, View view, int position) {
                Log.d(TAG, "onItemChildClick: " + view.getId());
                Log.d(TAG, "onItemChildClick: " + position);
                CustomerBean customerBean = customerBeanList.get(position);
                switch (view.getId()) {
                    case R.id.new_call:
                        String tel = "tel:" + customerBean.getLianxirendianhua();
                        Intent intent = new Intent(Intent.ACTION_DIAL);
                        intent.setData(Uri.parse(tel));
                        startActivity(intent);
                }
            }
        });
    }

    private void initSearch(){
        //TODO:添加通讯录搜索功能
    }
}
