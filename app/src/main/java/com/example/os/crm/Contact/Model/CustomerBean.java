package com.example.os.crm.Contact.Model;

import com.example.os.crm.Common.TimeUtil;

/**
 * Created by OS on 2018/1/16.
 */

public class CustomerBean {

    private String lianxirenqq;
    private String lianxirenemail;
    private String lianxirenwechat;
    private String kehuid;
    private String lianxirendianhua;
    private String kehudizhi;
    private String kehumingcheng;
    private String kehulianxiren;
    private String userid;
    private String kehuhangye;
    private String beizhu;
    private String timestamp;


    public String getTimestamp(){
        return timestamp;
    }

    public String getDateTimestamp(){
        Long t = Long.parseLong(timestamp) * 1000;
        return new TimeUtil().getDate(t);
    }

    public void setTimestamp(String temp){
        this.timestamp = temp;
    }

    public String getLianxirenqq() {
        return lianxirenqq;
    }

    public void setLianxirenqq(String lianxirenqq) {
        this.lianxirenqq = lianxirenqq;
    }

    public String getLianxirenemail() {
        return lianxirenemail;
    }

    public void setLianxirenemail(String lianxirenemail) {
        this.lianxirenemail = lianxirenemail;
    }

    public String getLianxirenwechat() {
        return lianxirenwechat;
    }

    public void setLianxirenwechat(String lianxirenwechat) {
        this.lianxirenwechat = lianxirenwechat;
    }

    public String getKehuid() {
        return kehuid;
    }

    public void setKehuid(String kehuid) {
        this.kehuid = kehuid;
    }

    public String getLianxirendianhua() {
        return lianxirendianhua;
    }

    public void setLianxirendianhua(String lianxirendianhua) {
        this.lianxirendianhua = lianxirendianhua;
    }

    public String getKehudizhi() {
        return kehudizhi;
    }

    public void setKehudizhi(String kehudizhi) {
        this.kehudizhi = kehudizhi;
    }

    public String getKehumingcheng() {
        return kehumingcheng;
    }

    public void setKehumingcheng(String kehumingcheng) {
        this.kehumingcheng = kehumingcheng;
    }

    public String getKehulianxiren() {
        return kehulianxiren;
    }

    public void setKehulianxiren(String kehulianxiren) {
        this.kehulianxiren = kehulianxiren;
    }

    public String getUserid() {
        return userid;
    }

    public void setUserid(String userid) {
        this.userid = userid;
    }

    public String getKehuhangye() {
        return kehuhangye;
    }

    public void setKehuhangye(String kehuhangye) {
        this.kehuhangye = kehuhangye;
    }

    public String getBeizhu() {
        return beizhu;
    }

    public void setBeizhu(String beizhu) {
        this.beizhu = beizhu;
    }
}
