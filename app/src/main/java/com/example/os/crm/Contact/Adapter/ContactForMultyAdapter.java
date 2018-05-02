package com.example.os.crm.Contact.Adapter;

import com.chad.library.adapter.base.BaseQuickAdapter;
import com.chad.library.adapter.base.BaseViewHolder;
import com.example.os.crm.Contact.Model.ContactBean;
import com.example.os.crm.R;

import java.util.List;

/**
 * Created by OS on 2018/3/2.
 */

public class ContactForMultyAdapter extends BaseQuickAdapter<ContactBean, BaseViewHolder> {

    public ContactForMultyAdapter(int layoutResId, List<ContactBean> data){
            super(layoutResId, data);
    }

    @Override
    protected void convert(BaseViewHolder holder, ContactBean contactBean) {
            holder.setText(R.id.name, contactBean.getJibf().getXkmk())
            .setText(R.id.department, contactBean.getJibf().getBumf())
            .setText(R.id.viwu, contactBean.getJibf().getViwu())
            .setChecked(R.id.checkbox, false)
            .setVisible(R.id.new_call, false)
            .setVisible(R.id.new_msg, false)
            .addOnClickListener(R.id.radiobutton);
        }
}