package com.example.os.crm.model;

import android.os.Parcel;
import android.os.Parcelable;

import com.example.os.crm.Common.TimeUtil;

import java.util.ArrayList;
import java.util.List;

/**
 * Created by OS on 2018/3/9.
 */

public class PaymentInfoBean implements Parcelable {


    private String customername;
    private String fuzeyewu;
    private String fuzeid;
    private String date;
    private String customerId;
    private String jbee;
    private List<String> path;
    private List<UfpiBean> ufpi;
    private String uijm;
    private int vltd;

    public void setVltd(int vltd) {
        this.vltd = vltd;
    }

    public int getVltd() {
        return vltd;
    }

    public void setFuzeid(String temp){
        this.fuzeid = temp;
    }

    public String getFuzeid(){
        return fuzeid;
    }

    public void setUijm(String temp){
        this.uijm = temp;
    }
    public String getUijm(){
        return uijm;
    }

    public void setCustomername(String temp){this.customername = temp;}

    public String getCustomername(){return customername;}

    public String getFuzeyewu() {
        return fuzeyewu;
    }

    public void setFuzeyewu(String fuzeyewu) {
        this.fuzeyewu = fuzeyewu;
    }

    public String getDate() {
        return date;
    }

    public void setDate(String date) {
        this.date = date;
    }

    public String getCustomerId() {
        return customerId;
    }

    public void setCustomerId(String customerId) {
        this.customerId = customerId;
    }

    public String getJbee() {
        return jbee;
    }

    public void setJbee(String jbee) {
        this.jbee = jbee;
    }

    public List<String> getPath() {
        return path;
    }

    public void setPath(List<String> path) {
        this.path = path;
    }

    public List<UfpiBean> getUfpi() {
        return ufpi;
    }

    public void setUfpi(List<UfpiBean> ufpi) {
        this.ufpi = ufpi;
    }

    public static class UfpiBean implements Parcelable {


        private String xkmk;
        private String bwvu;
        private String ufpirf;
        private int vltd;
        private String uijm;

        public void setUijm(String temp){this.uijm = temp;}
        public String getUijm(){
            if (uijm.length() > 1){
                return new TimeUtil().getDate(Long.parseLong(uijm)*1000);
            }
            else{
                return "";
            }
        }

        public String getXkmk() {
            return xkmk;
        }

        public void setXkmk(String xkmk) {
            this.xkmk = xkmk;
        }

        public String getBwvu() {
            return bwvu;
        }

        public void setBwvu(String bwvu) {
            this.bwvu = bwvu;
        }

        public String getUfpirf() {
            return ufpirf;
        }

        public void setUfpirf(String ufpirf) {
            this.ufpirf = ufpirf;
        }

        public int getVltd() {
            return vltd;
        }

        public void setVltd(int vltd) {
            this.vltd = vltd;
        }

        @Override
        public int describeContents() {
            return 0;
        }

        @Override
        public void writeToParcel(Parcel dest, int flags) {
            dest.writeString(this.xkmk);
            dest.writeString(this.bwvu);
            dest.writeString(this.ufpirf);
            dest.writeInt(this.vltd);
            dest.writeString(this.uijm);
        }

        public UfpiBean() {
        }

        protected UfpiBean(Parcel in) {
            this.xkmk = in.readString();
            this.bwvu = in.readString();
            this.ufpirf = in.readString();
            this.vltd = in.readInt();
            this.uijm = in.readString();
        }

        public static final Creator<UfpiBean> CREATOR = new Creator<UfpiBean>() {
            @Override
            public UfpiBean createFromParcel(Parcel source) {
                return new UfpiBean(source);
            }

            @Override
            public UfpiBean[] newArray(int size) {
                return new UfpiBean[size];
            }
        };
    }

    @Override
    public int describeContents() {
        return 0;
    }

    @Override
    public void writeToParcel(Parcel dest, int flags) {
        dest.writeString(this.customername);
        dest.writeString(this.fuzeyewu);
        dest.writeString(this.date);
        dest.writeString(this.customerId);
        dest.writeString(this.jbee);
        dest.writeStringList(this.path);
        dest.writeList(this.ufpi);
        dest.writeString(this.uijm);
        dest.writeInt(this.vltd);
    }

    public PaymentInfoBean() {
    }

    protected PaymentInfoBean(Parcel in) {
        this.customername = in.readString();
        this.fuzeyewu = in.readString();
        this.date = in.readString();
        this.customerId = in.readString();
        this.jbee = in.readString();
        this.path = in.createStringArrayList();
        this.ufpi = new ArrayList<UfpiBean>();
        in.readList(this.ufpi, UfpiBean.class.getClassLoader());
        this.uijm = in.readString();
        this.vltd = in.readInt();
    }

    public static final Parcelable.Creator<PaymentInfoBean> CREATOR = new Parcelable.Creator<PaymentInfoBean>() {
        @Override
        public PaymentInfoBean createFromParcel(Parcel source) {
            return new PaymentInfoBean(source);
        }

        @Override
        public PaymentInfoBean[] newArray(int size) {
            return new PaymentInfoBean[size];
        }
    };
}
