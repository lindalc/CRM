package com.example.os.crm.model;

import com.example.os.crm.Common.TimeUtil;
import java.util.List;

/**
 * Created by OS on 2018/3/15.
 * 业务员回款
 */

public class FundRestream {

    private int type;
    private String dingdanbianhao;
    private String userId;
    private String timestamp;
    private String kehumingcheng;
    private String yewuxingming;
    private float dingdanzonge;
    private List<UfpiBean> ufpi;

    public void setUfpi(List<UfpiBean> ufpi) {
        this.ufpi = ufpi;
    }

    public List<UfpiBean> getUfpi() {
        return ufpi;
    }

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

    public static class UfpiBean{
        /**
         * vltd : 0
         * xkmk : 徐美静
         * uijm :
         * ufpirf : yrxmj
         * bwvu :
         */

        private int vltd;
        private String xkmk;
        private String uijm;
        private String ufpirf;
        private String bwvu;

        public int getVltd() {
            return vltd;
        }

        public void setVltd(int vltd) {
            this.vltd = vltd;
        }

        public String getXkmk() {
            return xkmk;
        }

        public void setXkmk(String xkmk) {
            this.xkmk = xkmk;
        }

        public String getUijm() {
            return uijm;
        }

        public void setUijm(String uijm) {
            this.uijm = uijm;
        }

        public String getUfpirf() {
            return ufpirf;
        }

        public void setUfpirf(String ufpirf) {
            this.ufpirf = ufpirf;
        }

        public String getBwvu() {
            return bwvu;
        }

        public void setBwvu(String bwvu) {
            this.bwvu = bwvu;
        }

    }
}
