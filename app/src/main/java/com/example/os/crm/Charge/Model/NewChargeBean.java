package com.example.os.crm.Charge.Model;

import android.os.Parcel;
import android.os.Parcelable;

import com.google.gson.annotations.SerializedName;

import java.util.List;

public class NewChargeBean {

    /**
     * userid : test
     * jbee : 100
     * vlxl : 0
     * dkdlbmhc : test
     * kehuid : test
     * pkvg : 0
     * files : ["test","test"]
     * ufpi : [{"ufpirf":"test","xkmk":"test","vltd":0,"uijm":"test","bwvu":"test"}]
     */

    private String userid;
    private String shenqingshijian;
    private String shenqingliyou;
    private String shenqingzhuangtai;
    private int jbee;
    private int vlxl;
    private List<String> files;
    private List<UfpiBean> ufpi;

    public NewChargeBean() {
    }

    public String getUserid() {
        return userid;
    }

    public void setUserid(String userid) {
        this.userid = userid;
    }
    public String getShenqingshijian(){
        return shenqingshijian;
    }
    public void setShenqingshijian(String shenqingshijian) {
        this.shenqingshijian = shenqingshijian;
    }

    public String getShenqingliyou(){
        return shenqingliyou;
    }

    public void setShenqingliyou(String shenqingliyou) {
        this.shenqingliyou = shenqingliyou;
    }

    public String getShenqingzhuangtai() {
        return shenqingzhuangtai;
    }
    public void setShenqingzhuangtai(String shenqingzhuangtai){
        this.shenqingzhuangtai = shenqingzhuangtai;
    }

    public int getJbee() {
        return jbee;
    }

    public void setJbee(int jbee) {
        this.jbee = jbee;
    }

    public int getVlxl() {
        return vlxl;
    }

    public void setVlxl(int vlxl) {
        this.vlxl = vlxl;
    }

    public List<String> getFiles() {
        return files;
    }

    public void setFiles(List<String> files) {
        this.files = files;
    }

    public List<UfpiBean> getUfpi() {
        return ufpi;
    }

    public void setUfpi(List<UfpiBean> ufpi) {
        this.ufpi = ufpi;
    }
    public static class VltdBean implements Parcelable {
        /**
         * wjvg : 1
         * wjig : 2
         * ufpi : 1
         * ccgc : 0
         * fafh : 0
         */

        private int wjvg;
        private int wjig;
        private int ufpi;
        private int ccgc;
        private int fafh;

        public void setFafh(int fafh) {
            this.fafh = fafh;
        }

        public int getFafh() {
            return fafh;
        }

        public int getWjvg() {
            return wjvg;
        }

        public void setWjvg(int wjvg) {
            this.wjvg = wjvg;
        }

        public int getWjig() {
            return wjig;
        }

        public void setWjig(int wjig) {
            this.wjig = wjig;
        }

        public int getUfpi() {
            return ufpi;
        }

        public void setUfpi(int ufpi) {
            this.ufpi = ufpi;
        }

        public int getCcgc() {
            return ccgc;
        }

        public void setCcgc(int ccgc) {
            this.ccgc = ccgc;
        }

        @Override
        public int describeContents() {
            return 0;
        }

        @Override
        public void writeToParcel(Parcel dest, int flags) {
            dest.writeInt(this.wjvg);
            dest.writeInt(this.wjig);
            dest.writeInt(this.ufpi);
            dest.writeInt(this.ccgc);
            dest.writeInt(this.fafh);
        }

        public VltdBean() {
        }

        protected VltdBean(Parcel in) {
            this.wjvg = in.readInt();
            this.wjig = in.readInt();
            this.ufpi = in.readInt();
            this.ccgc = in.readInt();
            this.fafh = in.readInt();
        }

        public static final Creator<NewChargeBean.VltdBean> CREATOR = new Creator<NewChargeBean.VltdBean>() {
            @Override
            public NewChargeBean.VltdBean createFromParcel(Parcel source) {
                return new NewChargeBean.VltdBean(source);
            }

            @Override
            public NewChargeBean.VltdBean[] newArray(int size) {
                return new NewChargeBean.VltdBean[size];
            }
        };
    }
    public static class UfpiBean {
        /**
         * ufpirf : test
         * xkmk : test
         * vltd : 0
         * uijm : test
         * bwvu : test
         */

        private String ufpirf;
        private String xkmk;
        private int vltd;
        private String uijm;
        private String bwvu;
        /**
         * vltd : {"ufpi":0}
         */

        @SerializedName("vltd")
        private VltdBean vltdX;

        public String getUfpirf() {
            return ufpirf;
        }

        public void setUfpirf(String ufpirf) {
            this.ufpirf = ufpirf;
        }

        public String getXkmk() {
            return xkmk;
        }

        public void setXkmk(String xkmk) {
            this.xkmk = xkmk;
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

        public String getBwvu() {
            return bwvu;
        }

        public void setBwvu(String bwvu) {
            this.bwvu = bwvu;
        }

        public VltdBean getVltdX() {
            return vltdX;
        }

        public void setVltdX(VltdBean vltdX) {
            this.vltdX = vltdX;
        }

        public static class VltdBean {
            /**
             * ufpi : 0
             */

            private int ufpi;

            public int getUfpi() {
                return ufpi;
            }

            public void setUfpi(int ufpi) {
                this.ufpi = ufpi;
            }
        }
    }
}
