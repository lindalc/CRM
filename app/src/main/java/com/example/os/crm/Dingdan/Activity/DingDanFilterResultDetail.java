package com.example.os.crm.Dingdan.Activity;

/**
 * Created by OS on 2018/2/5.
 */

public class DingDanFilterResultDetail {

    /**
     * zongjia : 20000.0
     * kehumingcheng : 发发发
     * shenpibeizhu : testtest
     * tijiaoshijian : 2018-01-24 08:53
     * hetongbianhao : ttt
     * dingdanbianhao : YR1801301355
     * shenpizhuangtai : 1
     * yewuyuan : 反反复复，反反复复
     */

    private String zongjia;
    private String kehumingcheng;
    private String tijiaoshijian;
    private String hetongbianhao;
    private String dingdanbianhao;
    private int shenpizhuangtai;
    private String yewuyuan;
    private int wjvg;
    private int wjig;


    public String getZongjia() {
        return zongjia;
    }

    public void setZongjia(String zongjia) {
        this.zongjia = zongjia;
    }

    public String getKehumingcheng() {
        return kehumingcheng;
    }

    public void setKehumingcheng(String kehumingcheng) {
        this.kehumingcheng = kehumingcheng;
    }


    public String getTijiaoshijian() {
        return tijiaoshijian;
    }

    public void setTijiaoshijian(String tijiaoshijian) {
        this.tijiaoshijian = tijiaoshijian;
    }

    public String getHetongbianhao() {
        return hetongbianhao;
    }

    public void setHetongbianhao(String hetongbianhao) {
        this.hetongbianhao = hetongbianhao;
    }

    public String getDingdanbianhao() {
        return dingdanbianhao;
    }

    public void setDingdanbianhao(String dingdanbianhao) {
        this.dingdanbianhao = dingdanbianhao;
    }

    public String getShenpizhuangtai() {
        String temp = "";
        switch(shenpizhuangtai){
            case 0:
                temp = "未审批";
                break;
            case 1:
                temp = "已同意";
                break;
            case 2:
                temp = "已驳回";
                break;
        }

        return temp;
    }

    public int getufpi(){
        return shenpizhuangtai;
    }

    public void setShenpizhuangtai(int shenpizhuangtai) {
        this.shenpizhuangtai = shenpizhuangtai;
    }

    public void setWjvg(int temp){this.wjvg = temp;}
    public int getWjvg(){
        return wjvg;
    }

    public void setWjig(int temp){this.wjig = temp;}
    public int getWjig(){
        return wjig;
    }

    public String getYewuyuan() {
        return yewuyuan;
    }

    public void setYewuyuan(String yewuyuan) {
        this.yewuyuan = yewuyuan;
    }
}
