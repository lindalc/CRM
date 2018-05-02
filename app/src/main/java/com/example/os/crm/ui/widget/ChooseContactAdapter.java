package com.example.os.crm.ui.widget;

import com.chad.library.adapter.base.BaseItemDraggableAdapter;
import com.chad.library.adapter.base.BaseViewHolder;
import com.example.os.crm.Contact.Model.ContactBean;
import com.example.os.crm.R;

import java.util.List;

public class ChooseContactAdapter extends BaseItemDraggableAdapter<ContactBean, BaseViewHolder> {

    public ChooseContactAdapter(int layoutResId, List<ContactBean> data){
        super(layoutResId, data);
    }

    @Override
    protected void convert(BaseViewHolder holder, ContactBean contactBean) {
        holder.setText(R.id.name, contactBean.getJibf().getXkmk())
                .setImageResource(R.id.circleimageview, R.mipmap.ic_launcher)
                .addOnClickListener(R.id.circleimageview);
    }

}