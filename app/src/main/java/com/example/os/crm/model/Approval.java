package com.example.os.crm.model;

import com.example.os.crm.Common.TimeUtil;

/**
 * Created by OS on 2018/1/12.
 * 申请实体类
 */

public class Approval {

//    type:
//       0: 新订单
//       1: 新回款
    private int type;
    private String dingdanbianhao;
    private String userId;
    private String timestamp;
    private String kehumingcheng;
    private String yewuxingming;
    private float dingdanzonge;
    private int vltd;

    public void setVltd(int temp){this.vltd = temp;}
    public int getVltd(){return vltd;}

    public void setType(int temp){this.type = temp;}
    public int getType(){return type;}

    public void setDingdanbianhao(String string){this.dingdanbianhao = string;}
    public String getDingdanbianhao(){return dingdanbianhao;}

    public void setUserId(String string){this.userId = string;}
    public String getUserId(){return userId;}

    public void setKehumingcheng(String string){this.kehumingcheng = string;}
    public String getKehumingcheng(){return kehumingcheng;}

    public void setYewuxingming(String string){this.yewuxingming = string;}
    public String getYewuxingming(){return yewuxingming;}

    public void setDingdanzonge(String string){this.dingdanzonge = Float.parseFloat(string);}
    public String getDingdanzonge(){return dingdanzonge + "元";}

    public void setTimestamp(String temp){this.timestamp = temp;}
    public String getTimestamp(){return timestamp;}
    public String getRiqi(){
        return new TimeUtil().getDate(Long.parseLong(timestamp) * 1000);
    }
}
