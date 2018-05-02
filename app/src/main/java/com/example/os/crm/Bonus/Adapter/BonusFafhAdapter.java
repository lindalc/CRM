package com.example.os.crm.Bonus.Adapter;

import com.chad.library.adapter.base.BaseQuickAdapter;
import com.chad.library.adapter.base.BaseViewHolder;
import com.example.os.crm.Bonus.Beans.BonusInfoBean;
import com.example.os.crm.R;
import com.xyz.step.FlowViewHorizontal;

import java.util.List;

/**
 * Created by OS on 2018/3/12.
 */

public class BonusFafhAdapter extends BaseQuickAdapter<BonusInfoBean, BaseViewHolder> {

    public BonusFafhAdapter(int resourceId, List<BonusInfoBean> bonusInfoBeanList){
        super(resourceId, bonusInfoBeanList);
    }

    @Override
    protected void convert(BaseViewHolder holder, BonusInfoBean bonusInfoBean) {
        holder.setText(R.id.fuzeyewu, bonusInfoBean.getXkmk())
                .setText(R.id.tiig, bonusInfoBean.getJbee());
//        FlowViewHorizontal stepView = holder.getView(R.id.stepview);
//
//        int size = bonusInfoBean.getUfpi().size();
//        String[] name = new String[size + 1];
//        String[] time = new String[size + 1];
//        int progress = 1;
//        int maxstep;
//        name[0] = bonusInfoBean.getXkmk();
//        time[0] = bonusInfoBean.getTijnuijm();
//        for (int i = 0; i< size; i++){
//            BonusInfoBean.UfpiBean ufpiBean = bonusInfoBean.getUfpi().get(i);
//            name[i+1] = ufpiBean.getXkmk();
//            time[i+1] = ufpiBean.getUijm();
//            if (ufpiBean.getVltd() != 0){
//                progress = i + 2;
//            }
//        }
//        maxstep = size + 1;
//        stepView.setProgress(progress, maxstep, name, time);
    }
}
