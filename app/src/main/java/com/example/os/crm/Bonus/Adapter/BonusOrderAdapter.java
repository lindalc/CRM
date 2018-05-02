package com.example.os.crm.Bonus.Adapter;

import com.chad.library.adapter.base.BaseQuickAdapter;
import com.chad.library.adapter.base.BaseViewHolder;
import com.example.os.crm.Bonus.Beans.CalcBonus;
import com.example.os.crm.Dingdan.Model.DkDjDetailInfoBean;
import com.example.os.crm.R;

import java.util.List;

/**
 * Created by OS on 2018/3/10.
 */

public class BonusOrderAdapter extends BaseQuickAdapter<DkDjDetailInfoBean, BaseViewHolder>{

    public BonusOrderAdapter(int layoutResId, List<DkDjDetailInfoBean> data){
        super(layoutResId, data);
    }

    @Override
    protected void convert(BaseViewHolder holder, DkDjDetailInfoBean dkDjDetailInfoBean) {
        holder.setText(R.id.dkdjbmhc, dkDjDetailInfoBean.getDkdjxbxi().getDkdjbmhc())
                .setText(R.id.kehumkig, dkDjDetailInfoBean.getKehuziln().getKehumkig())
                .setText(R.id.tiig, dkDjDetailInfoBean.getQm().getTiig())
                .setText(R.id.uebwzsjx, dkDjDetailInfoBean.getQm().getUebwzsjx());
        CalcBonus calcBonus = new CalcBonus(dkDjDetailInfoBean);
        switch (dkDjDetailInfoBean.getVltd().getWjig()){
            case 0:
                holder.setText(R.id.wjig, "未完成");
                holder.setTextColor(R.id.wjig, mContext.getResources().getColor(R.color.grey));
                break;
            case 1:
                holder.setText(R.id.wjig, "已完成");
                holder.setTextColor(R.id.wjig, mContext.getResources().getColor(R.color.green));
                break;
            case 2:
                holder.setText(R.id.wjig, "基本完成");
                holder.setTextColor(R.id.wjig, mContext.getResources().getColor(R.color.colorPrimaryDark));
                holder.setTextColor(R.id.vibcjbtiig, mContext.getResources().getColor(R.color.colorPrimaryDark));
                break;
            case 3:
                holder.setText(R.id.wjig, "基本完成");
                holder.setTextColor(R.id.wjig, mContext.getResources().getColor(R.color.red));
                holder.setTextColor(R.id.vibcjbtiig, mContext.getResources().getColor(R.color.red));
                break;
        }

        holder.setText(R.id.vibcjbtiig, calcBonus.getVibcjbtiig() + "");
        holder.setText(R.id.jibenticheng, calcBonus.getJibentiig() + "");
        switch (dkDjDetailInfoBean.getVltd().getFafh()){
            case 0:
                holder.setTextColor(R.id.vibcjbtiig, mContext.getResources().getColor(R.color.grey));
                holder.setTextColor(R.id.jibenticheng, mContext.getResources().getColor(R.color.grey));
                break;
            case 1:
                holder.setTextColor(R.id.vibcjbtiig, mContext.getResources().getColor(R.color.grey));
                holder.setTextColor(R.id.jibenticheng, mContext.getResources().getColor(R.color.green));
                break;
            case 2:
                holder.setTextColor(R.id.vibcjbtiig, mContext.getResources().getColor(R.color.green));
                holder.setTextColor(R.id.jibenticheng, mContext.getResources().getColor(R.color.green));
                break;
        }
    }
}
