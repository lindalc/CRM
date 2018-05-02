package com.example.os.crm.model;

import com.example.os.crm.Dingdan.Model.DkDjDetailInfoBean;

/**
 * Created by OS on 2018/3/16.
 */

public class HvKrDkDjBean {
    private DkDjDetailInfoBean dkdj;
    private HuiKuanBean hvkr;

    public void setDkdj(DkDjDetailInfoBean dkDjDetailInfoBean) {
        this.dkdj = dkDjDetailInfoBean;
    }
    public void setHvkr(HuiKuanBean huiKuanBean) {
        this.hvkr = huiKuanBean;
    }
    public DkDjDetailInfoBean getDkdj() {
        return dkdj;
    }
    public HuiKuanBean getHvkr() {
        return hvkr;
    }
}
