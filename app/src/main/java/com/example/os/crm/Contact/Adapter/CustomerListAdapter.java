package com.example.os.crm.Contact.Adapter;

import com.chad.library.adapter.base.BaseQuickAdapter;
import com.chad.library.adapter.base.BaseViewHolder;
import com.example.os.crm.Contact.Model.CustomerBean;
import com.example.os.crm.R;

import java.util.List;

/**
 * Created by OS on 2018/3/3.
 */

public class CustomerListAdapter extends BaseQuickAdapter<CustomerBean, BaseViewHolder>{
    public CustomerListAdapter(int layoutResId, List<CustomerBean> data){
        super(layoutResId, data);
    }

    @Override
    protected void convert(BaseViewHolder holder, CustomerBean customerBean) {
        holder.setText(R.id.name, customerBean.getKehulianxiren())
                .setText(R.id.company_name, customerBean.getKehumingcheng())
                .addOnClickListener(R.id.new_msg)
                .addOnClickListener(R.id.new_call);
    }
}
