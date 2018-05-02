package com.example.os.crm.ui.adapter;

import com.chad.library.adapter.base.BaseQuickAdapter;
import com.chad.library.adapter.base.BaseViewHolder;
import com.example.os.crm.model.PaymentInfoBean;
import com.example.os.crm.R;
import com.xyz.step.FlowViewHorizontal;

import java.util.List;

/**
 * Created by OS on 2018/3/9.
 */

public class FundListAdapter extends BaseQuickAdapter<PaymentInfoBean, BaseViewHolder> {

    public FundListAdapter(int layoutResId, List<PaymentInfoBean> data){

        super(layoutResId, data);
    }

    @Override
    protected void convert(BaseViewHolder holder, PaymentInfoBean paymentInfoBean) {
        holder.setText(R.id.kehumkig, paymentInfoBean.getCustomername())
                .setText(R.id.ffjpvltd, getVltd(paymentInfoBean.getVltd()))
                .setText(R.id.hvkrjbee, paymentInfoBean.getJbee())
                .setText(R.id.hvkrriqi, paymentInfoBean.getDate());

        FlowViewHorizontal stepView = (FlowViewHorizontal) holder.getView(R.id.ufpilqig);

        int size = paymentInfoBean.getUfpi().size();
        String[] name = new String[size + 1];
        String[] time = new String[size + 1];
        int progress = 1;
        int maxstep;
        name[0] = "赵黎敏";
        time[0] = paymentInfoBean.getDate();
        for (int i = 0; i< size; i++){
            PaymentInfoBean.UfpiBean ufpiBean = paymentInfoBean.getUfpi().get(i);
            name[i+1] = ufpiBean.getXkmk();
            time[i+1] = ufpiBean.getUijm();
            if (ufpiBean.getVltd() != 0){
                progress = i + 2;
            }
        }
        maxstep = size + 1;
        stepView.setProgress(progress, maxstep, name, time);
    }

    private String getVltd(int vltd){
        switch (vltd){
            case 0:
                return "未分解";
            case 1:
                return "已分解";
        }
        return "";
    }
}
