package com.example.os.crm.Contact.Model;

import android.os.Parcel;
import android.os.Parcelable;

import com.github.stuxuhai.jpinyin.PinyinHelper;

import java.util.List;


public class ContactBean implements Parcelable {

    private JibfBean jibf;
    private LmxiBean lmxi;
    private String userid;

    public JibfBean getJibf() {
        return jibf;
    }

    public void setJibf(JibfBean jibf) {
        this.jibf = jibf;
    }

    public LmxiBean getLmxi() {
        return lmxi;
    }

    public void setLmxi(LmxiBean lmxi) {
        this.lmxi = lmxi;
    }

    public String getUserid() {
        return userid;
    }

    public void setUserid(String userid) {
        this.userid = userid;
    }

    public static class JibfBean implements Parcelable {

        private int dgji;
        private String xkmk;
        private String viwu;
        private String bumf;
        private String gssi;
        private List<String> qrxm;

        public int getFirstChar(){
            char pinyin = 0;
            try{
                pinyin = PinyinHelper.getShortPinyin(xkmk).charAt(0);
            }catch (Exception e){
                e.printStackTrace();
            }
            return pinyin;
        }

        public String getPinYin(){
            String pinyin = "";
            try{
                pinyin = PinyinHelper.getShortPinyin(xkmk);
            }catch (Exception e){
                e.printStackTrace();
            }
            return pinyin;
        }

        public String getGssi(){return gssi;}
        public void setGssi(String temp){this.gssi = temp;}

        public int getDgji() {
            return dgji;
        }

        public void setDgji(int dgji) {
            this.dgji = dgji;
        }

        public String getXkmk() {
            return xkmk;
        }

        public void setXkmk(String xkmk) {
            this.xkmk = xkmk;
        }

        public String getViwu() {
            return viwu;
        }

        public void setViwu(String viwu) {
            this.viwu = viwu;
        }

        public String getBumf() {
            return bumf;
        }

        public void setBumf(String bumf) {
            this.bumf = bumf;
        }

        public List<String> getQrxm() {
            return qrxm;
        }

        public void setQrxm(List<String> qrxm) {
            this.qrxm = qrxm;
        }

        @Override
        public int describeContents() {
            return 0;
        }

        @Override
        public void writeToParcel(Parcel dest, int flags) {
            dest.writeInt(this.dgji);
            dest.writeString(this.xkmk);
            dest.writeString(this.viwu);
            dest.writeString(this.bumf);
            dest.writeString(this.gssi);
            dest.writeList(this.qrxm);
        }

        public JibfBean() {
        }

        protected JibfBean(Parcel in) {
            this.dgji = in.readInt();
            this.xkmk = in.readString();
            this.viwu = in.readString();
            this.bumf = in.readString();
            this.gssi = in.readString();
//            this.qrxm = new ArrayList<>();
            in.readList(this.qrxm, String.class.getClassLoader());
        }

        public static final Creator<JibfBean> CREATOR = new Creator<JibfBean>() {
            @Override
            public JibfBean createFromParcel(Parcel source) {
                return new JibfBean(source);
            }

            @Override
            public JibfBean[] newArray(int size) {
                return new JibfBean[size];
            }
        };
    }

    public static class LmxiBean implements Parcelable {
        /**
         * wechat : lingdukedao
         * uzji : 151213515395
         * QQ : 156435
         * zoji : 0539-222222222
         * email : vvaa00@126.com
         */

        private String wechat;
        private String uzji;
        private String QQ;
        private String zoji;
        private String email;

        public String getWechat() {
            return wechat;
        }

        public void setWechat(String wechat) {
            this.wechat = wechat;
        }

        public String getUzji() {
            return uzji;
        }

        public void setUzji(String uzji) {
            this.uzji = uzji;
        }

        public String getQQ() {
            return QQ;
        }

        public void setQQ(String QQ) {
            this.QQ = QQ;
        }

        public String getZoji() {
            return zoji;
        }

        public void setZoji(String zoji) {
            this.zoji = zoji;
        }

        public String getEmail() {
            return email;
        }

        public void setEmail(String email) {
            this.email = email;
        }

        @Override
        public int describeContents() {
            return 0;
        }

        @Override
        public void writeToParcel(Parcel dest, int flags) {
            dest.writeString(this.wechat);
            dest.writeString(this.uzji);
            dest.writeString(this.QQ);
            dest.writeString(this.zoji);
            dest.writeString(this.email);
        }

        public LmxiBean() {
        }

        protected LmxiBean(Parcel in) {
            this.wechat = in.readString();
            this.uzji = in.readString();
            this.QQ = in.readString();
            this.zoji = in.readString();
            this.email = in.readString();
        }

        public static final Creator<LmxiBean> CREATOR = new Creator<LmxiBean>() {
            @Override
            public LmxiBean createFromParcel(Parcel source) {
                return new LmxiBean(source);
            }

            @Override
            public LmxiBean[] newArray(int size) {
                return new LmxiBean[size];
            }
        };
    }

    @Override
    public int describeContents() {
        return 0;
    }

    @Override
    public void writeToParcel(Parcel dest, int flags) {
        dest.writeParcelable(this.jibf, flags);
        dest.writeParcelable(this.lmxi, flags);
        dest.writeString(this.userid);
    }

    public ContactBean() {
    }

    protected ContactBean(Parcel in) {
        this.jibf = in.readParcelable(JibfBean.class.getClassLoader());
        this.lmxi = in.readParcelable(LmxiBean.class.getClassLoader());
        this.userid = in.readString();
    }

    public static final Parcelable.Creator<ContactBean> CREATOR = new Parcelable.Creator<ContactBean>() {
        @Override
        public ContactBean createFromParcel(Parcel source) {
            return new ContactBean(source);
        }

        @Override
        public ContactBean[] newArray(int size) {
            return new ContactBean[size];
        }
    };
}
