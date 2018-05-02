package com.example.os.crm.ui.adapter;

import android.content.Context;

import com.chad.library.adapter.base.BaseMultiItemQuickAdapter;
import com.chad.library.adapter.base.BaseViewHolder;
import com.example.os.crm.R;
import com.example.os.crm.model.FundRestream;
import com.example.os.crm.model.ApprovalMultyItem;

import java.util.List;

/**
 * Created by OS on 2018/3/15.
 */

public class ApprovalFragmentAdapter extends BaseMultiItemQuickAdapter<ApprovalMultyItem, BaseViewHolder> {

    public ApprovalFragmentAdapter(Context context, List data) {
        super(data);
        addItemType(ApprovalMultyItem.DKDJ, R.layout.ddufpi_item_dkdj);
        addItemType(ApprovalMultyItem.HVKR, R.layout.ddufpi_item_hvkr);
        addItemType(ApprovalMultyItem.CDWUHVKR, R.layout.ddufpi_item_cdwuhvkr);
    }

    @Override
    protected void convert(BaseViewHolder holder, ApprovalMultyItem item){
        switch (holder.getItemViewType()){
            case ApprovalMultyItem.DKDJ:
                holder.setText(R.id.leixing, "新订单")
                        .setTextColor(R.id.leixing, mContext.getResources().getColor(R.color.black))
                        .setTextColor(R.id.vltd, getVltdColor(item.getDkdjBean().getVltd().getUfpi()))
                        .setText(R.id.vltd, getVltd(item.getDkdjBean().getVltd().getUfpi()))
                        .setText(R.id.daishenpi_dingdanbianhao, item.getDkdjBean().getDkdjxbxi().getDkdjbmhc())
                        .setText(R.id.daishenpi_yewuxingming, item.getDkdjBean().getDkdjxbxi().getYewuyr())
                        .setText(R.id.daishenpi_kehumingcheng, item.getDkdjBean().getKehuziln().getKehumkig())
                        .setText(R.id.daishenpi_jine_type, "设备总价")
                        .setText(R.id.daishenpi_jie, item.getDkdjBean().getQm().getUebwzsjx())
                        .setText(R.id.riqi, item.getDkdjBean().getDkdjxbxi().getTijnuijm());
                break;
            case ApprovalMultyItem.HVKR:
                List<FundRestream.UfpiBean> ufpiBeanList = item.getFundRestream().getUfpi();
                holder.setText(R.id.leixing, "业务回款")
                        .setTextColor(R.id.leixing, mContext.getResources().getColor(R.color.black))
                        .setText(R.id.vltd, getVltd(ufpiBeanList.get(ufpiBeanList.size() - 1).getVltd()))
                        .setTextColor(R.id.vltd, getVltdColor(ufpiBeanList.get(ufpiBeanList.size() - 1).getVltd()))
                        .setText(R.id.daishenpi_dingdanbianhao, item.getFundRestream().getDingdanbianhao())
                        .setText(R.id.daishenpi_yewuxingming, item.getFundRestream().getYewuxingming())
                        .setText(R.id.daishenpi_kehumingcheng, item.getFundRestream().getKehumingcheng())
                        .setText(R.id.daishenpi_jine_type, "回款金额")
                        .setText(R.id.daishenpi_jie, item.getFundRestream().getDingdanzonge())
                        .setText(R.id.riqi, item.getFundRestream().getRiqi());
                break;
            case ApprovalMultyItem.CDWUHVKR:
                holder.setText(R.id.leixing, "财务回款")
                        .setTextColor(R.id.leixing, mContext.getResources().getColor(R.color.black))
                        .setText(R.id.vltd, getVltd(item.getPaymentInfoBean().getVltd()))
                        .setTextColor(R.id.vltd, getVltdColor(item.getPaymentInfoBean().getVltd()))
                        .setText(R.id.daishenpi_jie, item.getPaymentInfoBean().getJbee())
                        .setText(R.id.daishenpi_kehumingcheng, item.getPaymentInfoBean().getCustomername())
                        .setText(R.id.daishenpi_yewuxingming, item.getPaymentInfoBean().getFuzeyewu())
                        .setText(R.id.daishenpi_jine_type, "回款金额")
                        .setText(R.id.riqi, item.getPaymentInfoBean().getDate());
                break;
        }
    }

    private String getVltd(int vltd){
        switch (vltd){
            case 0:
                return "未审批";
            case 1:
                return "已同意";
            case 2:
                return "已驳回";
            default:
                return "未知状态";
        }
    }
    private int getVltdColor(int vltd){
        switch (vltd){
            case 0:
                return mContext.getResources().getColor(R.color.grey);
            case 1:
                return mContext.getResources().getColor(R.color.green);
            case 2:
                return mContext.getResources().getColor(R.color.red);
            default:
                break;
        }
        return 1;
    }
}
