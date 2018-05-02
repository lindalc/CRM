package com.example.os.crm.Bonus.Beans;

import com.example.os.crm.Common.TimeUtil;

import java.util.List;


public class BonusInfoBean {

    private String fuzeyewu;
    private String jbee;
    private int timestamp;
    private int fafh;
    private List<String> dkdjlist;
    private List<UfpiBean> ufpi;
    private String xkmk;

    public String getXkmk() {
        return xkmk;
    }

    public void setXkmk(String xkmk) {
        this.xkmk = xkmk;
    }

    public void setFafh(int fafh) {
        this.fafh = fafh;
    }

    public int getFafh() {
        return fafh;
    }

    public String getFuzeyewu() {
        return fuzeyewu;
    }

    public void setFuzeyewu(String fuzeyewu) {
        this.fuzeyewu = fuzeyewu;
    }

    public String getJbee() {
        return jbee;
    }

    public void setJbee(String jbee) {
        this.jbee = jbee;
    }

    public int getTimestamp() {
        return timestamp;
    }

    public void setTimestamp(int timestamp) {
        this.timestamp = timestamp;
    }

    public String getTijnuijm(){
        return new TimeUtil().getDate(timestamp * 1000);
    }

    public List<String> getDkdjlist() {
        return dkdjlist;
    }

    public void setDkdjlist(List<String> dkdjlist) {
        this.dkdjlist = dkdjlist;
    }

    public List<UfpiBean> getUfpi() {
        return ufpi;
    }

    public void setUfpi(List<UfpiBean> ufpi) {
        this.ufpi = ufpi;
    }

    public static class UfpiBean {

        private String bwvu;
        private int vltd;
        private String uijm;
        private String xkmk;
        private String ufpirf;

        public String getBwvu() {
            return bwvu;
        }

        public void setBwvu(String bwvu) {
            this.bwvu = bwvu;
        }

        public int getVltd() {
            return vltd;
        }

        public void setVltd(int vltd) {
            this.vltd = vltd;
        }

        public String getUijm() {
            return uijm;
        }

        public void setUijm(String uijm) {
            this.uijm = uijm;
        }

        public String getXkmk() {
            return xkmk;
        }

        public void setXkmk(String xkmk) {
            this.xkmk = xkmk;
        }

        public String getUfpirf() {
            return ufpirf;
        }

        public void setUfpirf(String ufpirf) {
            this.ufpirf = ufpirf;
        }
    }
}
