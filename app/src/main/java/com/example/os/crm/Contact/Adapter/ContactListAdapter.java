package com.example.os.crm.Contact.Adapter;

import com.chad.library.adapter.base.BaseQuickAdapter;
import com.chad.library.adapter.base.BaseViewHolder;
import com.example.os.crm.Contact.Model.ContactBean;
import com.example.os.crm.R;

import java.util.List;


public class ContactListAdapter extends BaseQuickAdapter<ContactBean, BaseViewHolder> {

    public ContactListAdapter(int layoutResId, List<ContactBean> data){
        super(layoutResId, data);
    }

    @Override
    protected void convert(BaseViewHolder holder, ContactBean contactBean) {
        holder.setText(R.id.name, contactBean.getJibf().getXkmk())
                .setText(R.id.department, contactBean.getJibf().getBumf())
                .setText(R.id.viwu, contactBean.getJibf().getViwu())
                .addOnClickListener(R.id.new_msg)
                .addOnClickListener(R.id.new_call);
    }

}
