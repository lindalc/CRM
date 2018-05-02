package com.example.os.crm.ui.adapter;

import com.chad.library.adapter.base.BaseQuickAdapter;
import com.chad.library.adapter.base.BaseViewHolder;
import com.example.os.crm.model.HvKrDkDjBean;
import com.example.os.crm.R;

import java.util.List;

/**
 * Created by OS on 2018/3/15.
 * 分解回款Adapter
 */

public class DecomposeAdapter extends BaseQuickAdapter<HvKrDkDjBean, BaseViewHolder>{

    public DecomposeAdapter(int layoutResId, List<HvKrDkDjBean> data){
        super(layoutResId, data);
    }

    @Override
    protected void convert(BaseViewHolder holder, HvKrDkDjBean hvKrDkDjBean) {
        holder.setText(R.id.dkdjbmhc, hvKrDkDjBean.getDkdj().getDkdjxbxi().getDkdjbmhc())
                .setText(R.id.yewuyr, hvKrDkDjBean.getDkdj().getDkdjxbxi().getYewuyr())
                .setText(R.id.wwuz, hvKrDkDjBean.getHvkr().getWwuz() + "")
                .setText(R.id.zsjx, hvKrDkDjBean.getDkdj().getQm().getUebwzsjx() + "")
                .setText(R.id.vibcjb, hvKrDkDjBean.getDkdj().getQm().getVibcjb());
    }

}
