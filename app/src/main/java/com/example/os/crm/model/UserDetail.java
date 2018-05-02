package com.example.os.crm.model;

/**
 * Created by OS on 2018/1/11.
 * 用户实体类
 */

public class UserDetail {

    private int id;
    private String xingming;
    private String dingdanzonge;
    private String dingdanbianhao;
    private String weishouhuokuan;
    private String yewubianhao;

    public int getId() {
        return id;
    }

    public void setId(int id) {
        this.id = id;
    }

    public void setXingming(String temp){this.xingming = temp;}
    public String getXingming(){return xingming;}

    public void setDingdanzonge(String temp){this.dingdanzonge = temp;}
    public String getDingdanzonge(){return dingdanzonge;}

    public void setDingdanbianhao(String temp){this.dingdanbianhao = temp;}
    public String getDingdanbianhao(){return dingdanbianhao;}

    public void setWeishouhuokuan(String temp){this.weishouhuokuan = temp;}
    public String getWeishouhuokuan(){return weishouhuokuan;}

    public void setYewubianhao(String temp){this.yewubianhao = temp;}
    public String getYewubianhao(){return yewubianhao;}

}
