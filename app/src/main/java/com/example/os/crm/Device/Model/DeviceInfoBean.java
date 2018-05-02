package com.example.os.crm.Device.Model;

import android.os.Parcel;
import android.os.Parcelable;

/**
 * Created by OS on 2018/2/7.
 */

public class DeviceInfoBean implements Parcelable {

    private String uebwxkhc;
    private String uebwuull;
    private String uebwdjjx;
    private String uebwzsjx;
    private String jnhoriqi;
    private String fadmjizu;
    private String cczofhui;
    private String uvxlrsji;
    private String pftuyjse;
    private String anvlfhui;
    private String uvbgxkhc;
    private String uhxxqiui;
    private String uhxxvsvi;
    private String zoyzqiui;
    private String zoyzvsvi;
    private String yeyaghuull;
    private String dmqijmpbpd;
    private String uhxxfuyhfhui;
    private String zoyzxrvrfhui;
    private String wdxkiicuycqq;
    private int uhxxfhui;
    private int zoyzfhui;
    private String bwvu;

    public String getBwvu(){return bwvu;}
    public void setBwvu(String temp) {this.bwvu = temp;}

    public String getUebwxkhc() {
        return uebwxkhc;
    }
    public void setUebwxkhc(String uebwxkhc) {
        this.uebwxkhc = uebwxkhc;
    }
    public String getUebwuull() {
        return uebwuull;
    }
    public void setUebwuull(String uebwuull) {
        this.uebwuull = uebwuull;
    }
    public String getUebwdjjx() {
        return uebwdjjx;
    }
    public void setUebwdjjx(String uebwdjjx) {
        this.uebwdjjx = uebwdjjx;
    }
    public String getUebwzsjx() {
        return uebwzsjx;
    }
    public void setUebwzsjx(String uebwzsjx) {
        this.uebwzsjx = uebwzsjx;
    }
    public String getJnhoriqi() {
        return jnhoriqi;
    }
    public void setJnhoriqi(String jnhoriqi) {
        this.jnhoriqi = jnhoriqi;
    }
    public String getFadmjizu() {
        return fadmjizu;
    }
    public void setFadmjizu(String fadmjizu) {
        this.fadmjizu = fadmjizu;
    }
    public String getCczofhui() {
        return cczofhui;
    }
    public void setCczofhui(String cczofhui) {
        this.cczofhui = cczofhui;
    }
    public String getUvxlrsji() {
        return uvxlrsji;
    }
    public void setUvxlrsji(String uvxlrsji) {
        this.uvxlrsji = uvxlrsji;
    }
    public String getPftuyjse() {
        return pftuyjse;
    }
    public void setPftuyjse(String pftuyjse) {
        this.pftuyjse = pftuyjse;
    }
    public String getAnvlfhui() {
        return anvlfhui;
    }
    public void setAnvlfhui(String anvlfhui) {
        this.anvlfhui = anvlfhui;
    }
    public String getUvbgxkhc() {
        return uvbgxkhc;
    }
    public void setUvbgxkhc(String uvbgxkhc) {
        this.uvbgxkhc = uvbgxkhc;
    }
    public String getUhxxqiui() {
        return uhxxqiui;
    }
    public void setUhxxqiui(String uhxxqiui) {
        this.uhxxqiui = uhxxqiui;
    }
    public String getUhxxvsvi() {
        return uhxxvsvi;
    }
    public void setUhxxvsvi(String uhxxvsvi) {
        this.uhxxvsvi = uhxxvsvi;
    }
    public String getZoyzqiui() {
        return zoyzqiui;
    }
    public void setZoyzqiui(String zoyzqiui) {
        this.zoyzqiui = zoyzqiui;
    }
    public String getZoyzvsvi() {
        return zoyzvsvi;
    }
    public void setZoyzvsvi(String zoyzvsvi) {
        this.zoyzvsvi = zoyzvsvi;
    }
    public String getYeyaghuull() {
        return yeyaghuull;
    }
    public void setYeyaghuull(String yeyaghuull) {
        this.yeyaghuull = yeyaghuull;
    }
    public String getDmqijmpbpd() {
        return dmqijmpbpd;
    }
    public void setDmqijmpbpd(String dmqijmpbpd) {
        this.dmqijmpbpd = dmqijmpbpd;
    }
    public String getUhxxfuyhfhui() {
        return uhxxfuyhfhui;
    }
    public void setUhxxfuyhfhui(String uhxxfuyhfhui) {
        this.uhxxfuyhfhui = uhxxfuyhfhui;
    }
    public String getZoyzxrvrfhui() {
        return zoyzxrvrfhui;
    }
    public void setZoyzxrvrfhui(String zoyzxrvrfhui) {
        this.zoyzxrvrfhui = zoyzxrvrfhui;
    }
    public String getWdxkiicuycqq() {
        return wdxkiicuycqq;
    }
    public void setWdxkiicuycqq(String wdxkiicuycqq) {
        this.wdxkiicuycqq = wdxkiicuycqq;
    }
    public void setUhxxfhui(int temp){this.uhxxfhui = temp;}
    public int getUhxxfhui(){return uhxxfhui;}
    public void setZoyzfhui(int temp){this.zoyzfhui=temp;}
    public int getZoyzfhui(){return zoyzfhui;}

    @Override
    public int describeContents() {
        return 0;
    }
    @Override
    public void writeToParcel(Parcel dest, int flags) {
        dest.writeString(this.uebwxkhc);
        dest.writeString(this.uebwuull);
        dest.writeString(this.uebwdjjx);
        dest.writeString(this.uebwzsjx);
        dest.writeString(this.jnhoriqi);
        dest.writeString(this.fadmjizu);
        dest.writeString(this.cczofhui);
        dest.writeString(this.uvxlrsji);
        dest.writeString(this.pftuyjse);
        dest.writeString(this.anvlfhui);
        dest.writeString(this.uvbgxkhc);
        dest.writeString(this.uhxxqiui);
        dest.writeString(this.uhxxvsvi);
        dest.writeString(this.zoyzqiui);
        dest.writeString(this.zoyzvsvi);
        dest.writeString(this.yeyaghuull);
        dest.writeString(this.dmqijmpbpd);
        dest.writeString(this.uhxxfuyhfhui);
        dest.writeString(this.zoyzxrvrfhui);
        dest.writeString(this.wdxkiicuycqq);
        dest.writeInt(this.uhxxfhui);
        dest.writeInt(this.zoyzfhui);
        dest.writeString(this.bwvu);
    }
    public DeviceInfoBean() {
    }
    protected DeviceInfoBean(Parcel in) {
        this.uebwxkhc = in.readString();
        this.uebwuull = in.readString();
        this.uebwdjjx = in.readString();
        this.uebwzsjx = in.readString();
        this.jnhoriqi = in.readString();
        this.fadmjizu = in.readString();
        this.cczofhui = in.readString();
        this.uvxlrsji = in.readString();
        this.pftuyjse = in.readString();
        this.anvlfhui = in.readString();
        this.uvbgxkhc = in.readString();
        this.uhxxqiui = in.readString();
        this.uhxxvsvi = in.readString();
        this.zoyzqiui = in.readString();
        this.zoyzvsvi = in.readString();
        this.yeyaghuull = in.readString();
        this.dmqijmpbpd = in.readString();
        this.uhxxfuyhfhui = in.readString();
        this.zoyzxrvrfhui = in.readString();
        this.wdxkiicuycqq = in.readString();
        this.uhxxfhui = in.readInt();
        this.zoyzfhui = in.readInt();
        this.bwvu = in.readString();
    }
    public static final Creator<DeviceInfoBean> CREATOR = new Creator<DeviceInfoBean>() {
        @Override
        public DeviceInfoBean createFromParcel(Parcel source) {
            return new DeviceInfoBean(source);
        }

        @Override
        public DeviceInfoBean[] newArray(int size) {
            return new DeviceInfoBean[size];
        }
    };

    @Override
    public String toString() {
        return "{" +
                "\'uebwxkhc\':" + "\'" + uebwxkhc + "\'," +
                "\'uebwuull\':" + "\'" + uebwuull + "\'," +
                "\'uebwdjjx\':" + "\'" + uebwdjjx + "\'," +
                "\'uebwzsjx\':" + "\'" + uebwzsjx + "\'," +
                "\'jnhoriqi\':" + "\'" + jnhoriqi + "\'," +
                "\'fadmjizu\':" + "\'" + fadmjizu + "\'," +
                "\'cczofhui\':" + "\'" + cczofhui + "\'," +
                "\'uvxlrsji\':" + "\'" + uvxlrsji + "\'," +
                "\'pftuyjse\':" + "\'" + pftuyjse + "\'," +
                "\'anvlfhui\':" + "\'" + anvlfhui + "\'," +
                "\'uvbgxkhc\':" + "\'" + uvbgxkhc + "\'," +
                "\'uhxxqiui\':" + "\'" + uhxxqiui + "\'," +
                "\'uhxxvsvi\':" + "\'" + uhxxvsvi + "\'," +
                "\'zoyzqiui\':" + "\'" + zoyzqiui + "\'," +
                "\'zoyzvsvi\':" + "\'" + zoyzvsvi + "\'," +
                "\'yeyaghuull\':" + "\'" + yeyaghuull + "\'," +
                "\'dmqijmpbpd\':" + "\'" + dmqijmpbpd + "\'," +
                "\'uhxxfuyhfhui\':" + "\'" + uhxxfuyhfhui + "\'," +
                "\'zoyzxrvrfhui\':" + "\'" + zoyzxrvrfhui + "\'," +
                "\'wdxkiicuycqq\':" + "\'" + wdxkiicuycqq + "\'," +
                "\'uhxxfhui\':" + uhxxfhui + "," +
                "\'zoyzfhui\':" + zoyzfhui + "," +
                "\'bwvu\':" + "\'" + bwvu + "\'," +
                "}";
    }
}
