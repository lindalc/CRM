package com.example.os.crm.model;

import com.chad.library.adapter.base.entity.MultiItemEntity;
import com.example.os.crm.Dingdan.Model.DkDjDetailInfoBean;

/**
 * Created by OS on 2018/3/15.
 * 没看懂，是原来的ShenPi下的MultyItemBean
 */

public class ApprovalMultyItem implements MultiItemEntity {
    public static final int DKDJ = 1;
    public static final int HVKR = 2;
    public static final int CDWUHVKR = 3;

    private DkDjDetailInfoBean dkdjBean;
    private FundRestream fundRestream;
    private PaymentInfoBean paymentInfoBean;

    private int itemType;

    public ApprovalMultyItem(int type, DkDjDetailInfoBean dkdjBean){
        this.itemType = type;
        this.dkdjBean = dkdjBean;
    }

    public ApprovalMultyItem(int type, FundRestream fundRestream){
        this.itemType = type;
        this.fundRestream = fundRestream;
    }

    public ApprovalMultyItem(int type, PaymentInfoBean paymentInfoBean){
        this.itemType = type;
        this.paymentInfoBean = paymentInfoBean;
    }

    @Override
    public int getItemType() {
        return itemType;
    }

    public PaymentInfoBean getPaymentInfoBean() {
        return paymentInfoBean;
    }

    public FundRestream getFundRestream() {
        return fundRestream;
    }

    public DkDjDetailInfoBean getDkdjBean() {
        return dkdjBean;
    }
}
