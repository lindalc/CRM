package com.example.os.crm.Contract.Model;

/**
 * Created by OS on 2018/1/29.
 */

public class ContractDetailBean {

    /**
     * dingdanbianhao : 订单编号
     * hetongbianhao : 合同编号
     * qiandingriqi : 合同签订日期
     * kehumingcheng : 客户名称
     * dingdanzonge : 订单总额
     */

    private String dingdanbianhao;
    private String hetongbianhao;
    private String qiandingriqi;
    private String kehumingcheng;
    private String dingdanzonge;

    public String getDingdanbianhao() {
        return dingdanbianhao;
    }

    public void setDingdanbianhao(String dingdanbianhao) {
        this.dingdanbianhao = dingdanbianhao;
    }

    public String getHetongbianhao() {
        return hetongbianhao;
    }

    public void setHetongbianhao(String hetongbianhao) {
        this.hetongbianhao = hetongbianhao;
    }

    public String getQiandingriqi() {
        return qiandingriqi;
    }

    public void setQiandingriqi(String qiandingriqi) {
        this.qiandingriqi = qiandingriqi;
    }

    public String getKehumingcheng() {
        return kehumingcheng;
    }

    public void setKehumingcheng(String kehumingcheng) {
        this.kehumingcheng = kehumingcheng;
    }

    public String getDingdanzonge() {
        return dingdanzonge;
    }

    public void setDingdanzonge(String dingdanzonge) {
        this.dingdanzonge = dingdanzonge;
    }
}
