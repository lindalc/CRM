package com.example.os.crm.Dingdan.Model;

import android.os.Parcel;
import android.os.Parcelable;

import com.example.os.crm.Common.TimeUtil;
import com.example.os.crm.Device.Model.DeviceInfoBean;

import java.util.ArrayList;
import java.util.List;

public class DkDjDetailInfoBean implements Parcelable {

    private DkdjxbxiBean dkdjxbxi;
    private QmBean qm;
    private KehuzilnBean kehuziln;
    private VltdBean vltd;
    private List<DeviceInfoBean> uebwxbxi;
    private List<UfpiBean> ufpi;
    private List<String> files;

    public void setFiles(List<String> files) {
        this.files = files;
    }

    public List<String> getFiles() {
        return files;
    }

    public DkdjxbxiBean getDkdjxbxi() {
        return dkdjxbxi;
    }

    public void setDkdjxbxi(DkdjxbxiBean dkdjxbxi) {
        this.dkdjxbxi = dkdjxbxi;
    }

    public QmBean getQm() {
        return qm;
    }

    public void setQm(QmBean qm) {
        this.qm = qm;
    }

    public KehuzilnBean getKehuziln() {
        return kehuziln;
    }

    public void setKehuziln(KehuzilnBean kehuziln) {
        this.kehuziln = kehuziln;
    }

    public VltdBean getVltd() {
        return vltd;
    }

    public void setVltd(VltdBean vltd) {
        this.vltd = vltd;
    }

    public List<DeviceInfoBean> getUebwxbxi() {
        return uebwxbxi;
    }

    public void setUebwxbxi(List<DeviceInfoBean> uebwxbxi) {
        this.uebwxbxi = uebwxbxi;
    }

    public List<UfpiBean> getUfpi() {
        return ufpi;
    }

    public void setUfpi(List<UfpiBean> ufpi) {
        this.ufpi = ufpi;
    }

    public static class DkdjxbxiBean implements Parcelable {
        /**
         * fuzeyewu : yryjl
         * dkdjbmhc : YR1802241310
         * hetsbmhc : gvbnbnhh
         * yewuyr : 已经 GG
         * bwvu : 尽快发货
         * uijmio : 1519449248
         * tijnuijm : 2018-02-24
         */

        private String fuzeyewu;
        private String dkdjbmhc;
        private String hetsbmhc;
        private String yewuyr;
        private String bwvu;
        private long uijmio;
        private String tijnuijm;

        public String getFuzeyewu() {
            return fuzeyewu;
        }

        public void setFuzeyewu(String fuzeyewu) {
            this.fuzeyewu = fuzeyewu;
        }

        public String getDkdjbmhc() {
            return dkdjbmhc;
        }

        public void setDkdjbmhc(String dkdjbmhc) {
            this.dkdjbmhc = dkdjbmhc;
        }

        public String getHetsbmhc() {
            return hetsbmhc;
        }

        public void setHetsbmhc(String hetsbmhc) {
            this.hetsbmhc = hetsbmhc;
        }

        public String getYewuyr() {
            return yewuyr;
        }

        public void setYewuyr(String yewuyr) {
            this.yewuyr = yewuyr;
        }

        public String getBwvu() {
            return bwvu;
        }

        public void setBwvu(String bwvu) {
            this.bwvu = bwvu;
        }

        public long getUijmio() {
            return uijmio;
        }

        public void setUijmio(long uijmio) {
            this.uijmio = uijmio;
        }

        public String getTijnuijm() {
            return tijnuijm;
        }

        public void setTijnuijm(String tijnuijm) {
            this.tijnuijm = tijnuijm;
        }

        @Override
        public int describeContents() {
            return 0;
        }

        @Override
        public void writeToParcel(Parcel dest, int flags) {
            dest.writeString(this.fuzeyewu);
            dest.writeString(this.dkdjbmhc);
            dest.writeString(this.hetsbmhc);
            dest.writeString(this.yewuyr);
            dest.writeString(this.bwvu);
            dest.writeLong(this.uijmio);
            dest.writeString(this.tijnuijm);
        }

        public DkdjxbxiBean() {
        }

        protected DkdjxbxiBean(Parcel in) {
            this.fuzeyewu = in.readString();
            this.dkdjbmhc = in.readString();
            this.hetsbmhc = in.readString();
            this.yewuyr = in.readString();
            this.bwvu = in.readString();
            this.uijmio = in.readLong();
            this.tijnuijm = in.readString();
        }

        public static final Creator<DkdjxbxiBean> CREATOR = new Creator<DkdjxbxiBean>() {
            @Override
            public DkdjxbxiBean createFromParcel(Parcel source) {
                return new DkdjxbxiBean(source);
            }

            @Override
            public DkdjxbxiBean[] newArray(int size) {
                return new DkdjxbxiBean[size];
            }
        };
    }

    public static class QmBean implements Parcelable {
        /**
         * uebwzsjx : 400000.0
         * vekz :
         * vibcjb : 10%/40000.0元
         * tiig : 3%/12000.0元
         * uvfz : 1
         * vibcjbdcqi : 2019年02月24日
         */

        private String uebwzsjx;
        private String vekz;
        private String vibcjb;
        private String tiig;
        private String uvfz;
        private long vibcjbdcqi;

        public String getUebwzsjx() {
            return uebwzsjx;
        }

        public void setUebwzsjx(String uebwzsjx) {
            this.uebwzsjx = uebwzsjx;
        }

        public String getVekz() {
            return vekz;
        }

        public void setVekz(String vekz) {
            this.vekz = vekz;
        }

        public String getVibcjb() {
            return vibcjb;
        }

        public void setVibcjb(String vibcjb) {
            this.vibcjb = vibcjb;
        }

        public String getTiig() {
            return tiig;
        }

        public void setTiig(String tiig) {
            this.tiig = tiig;
        }

        public String getUvfz() {
            return uvfz;
        }

        public void setUvfz(String uvfz) {
            this.uvfz = uvfz;
        }

        public String getVibcjbdcqi() {
            return new TimeUtil().getDate(vibcjbdcqi * 1000);
        }

        public void setVibcjbdcqi(long vibcjbdcqi) {
            this.vibcjbdcqi = vibcjbdcqi;
        }

        @Override
        public int describeContents() {
            return 0;
        }

        @Override
        public void writeToParcel(Parcel dest, int flags) {
            dest.writeString(this.uebwzsjx);
            dest.writeString(this.vekz);
            dest.writeString(this.vibcjb);
            dest.writeString(this.tiig);
            dest.writeString(this.uvfz);
            dest.writeLong(this.vibcjbdcqi);
        }

        public QmBean() {
        }

        protected QmBean(Parcel in) {
            this.uebwzsjx = in.readString();
            this.vekz = in.readString();
            this.vibcjb = in.readString();
            this.tiig = in.readString();
            this.uvfz = in.readString();
            this.vibcjbdcqi = in.readLong();
        }

        public static final Creator<QmBean> CREATOR = new Creator<QmBean>() {
            @Override
            public QmBean createFromParcel(Parcel source) {
                return new QmBean(source);
            }

            @Override
            public QmBean[] newArray(int size) {
                return new QmBean[size];
            }
        };
    }

    public static class KehuzilnBean implements Parcelable {
        /**
         * kehuid : 1519437622285
         * kehumkig : 山东雨润环保
         * kehudivi : 等你等你
         * lmxirf : 一直
         * lmxirfdmhx : 4667989
         */

        private String kehuid;
        private String kehumkig;
        private String kehudivi;
        private String lmxirf;
        private String lmxirfdmhx;

        public String getKehuid() {
            return kehuid;
        }

        public void setKehuid(String kehuid) {
            this.kehuid = kehuid;
        }

        public String getKehumkig() {
            return kehumkig;
        }

        public void setKehumkig(String kehumkig) {
            this.kehumkig = kehumkig;
        }

        public String getKehudivi() {
            return kehudivi;
        }

        public void setKehudivi(String kehudivi) {
            this.kehudivi = kehudivi;
        }

        public String getLmxirf() {
            return lmxirf;
        }

        public void setLmxirf(String lmxirf) {
            this.lmxirf = lmxirf;
        }

        public String getLmxirfdmhx() {
            return lmxirfdmhx;
        }

        public void setLmxirfdmhx(String lmxirfdmhx) {
            this.lmxirfdmhx = lmxirfdmhx;
        }

        @Override
        public int describeContents() {
            return 0;
        }

        @Override
        public void writeToParcel(Parcel dest, int flags) {
            dest.writeString(this.kehuid);
            dest.writeString(this.kehumkig);
            dest.writeString(this.kehudivi);
            dest.writeString(this.lmxirf);
            dest.writeString(this.lmxirfdmhx);
        }

        public KehuzilnBean() {
        }

        protected KehuzilnBean(Parcel in) {
            this.kehuid = in.readString();
            this.kehumkig = in.readString();
            this.kehudivi = in.readString();
            this.lmxirf = in.readString();
            this.lmxirfdmhx = in.readString();
        }

        public static final Creator<KehuzilnBean> CREATOR = new Creator<KehuzilnBean>() {
            @Override
            public KehuzilnBean createFromParcel(Parcel source) {
                return new KehuzilnBean(source);
            }

            @Override
            public KehuzilnBean[] newArray(int size) {
                return new KehuzilnBean[size];
            }
        };
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

        public static final Creator<VltdBean> CREATOR = new Creator<VltdBean>() {
            @Override
            public VltdBean createFromParcel(Parcel source) {
                return new VltdBean(source);
            }

            @Override
            public VltdBean[] newArray(int size) {
                return new VltdBean[size];
            }
        };
    }

    public static class UebwxbxiBean implements Parcelable {
        /**
         * uebwxkhc : yr80
         * uebwuull : 5
         * uebwdjjx : 80000
         * uebwzsjx : 400000.0
         * jnhoriqi : 2018年02月28日
         * fadmjizu : 无
         * cczofhui : 遥控
         * uvxlrsji : 无
         * pftuyjse : 白
         * anvlfhui : 固定
         * uvbgxkhc : 离心泵
         * uhxxqiui : -10
         * uhxxvsvi : 45
         * zoyzqiui : 0
         * zoyzvsvi : 320
         * yeyaghuull : 标准
         * dmqijmpbpd : 德力西
         * uhxxfuyhfhui : 液压
         * zoyzxrvrfhui : 液压
         * wdxkiicuycqq : 标准
         * uhxxfhui : 1
         * zoyzfhui : 0
         * bwvu : 尽快
         */

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

        public int getUhxxfhui() {
            return uhxxfhui;
        }

        public void setUhxxfhui(int uhxxfhui) {
            this.uhxxfhui = uhxxfhui;
        }

        public int getZoyzfhui() {
            return zoyzfhui;
        }

        public void setZoyzfhui(int zoyzfhui) {
            this.zoyzfhui = zoyzfhui;
        }

        public String getBwvu() {
            return bwvu;
        }

        public void setBwvu(String bwvu) {
            this.bwvu = bwvu;
        }

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

        public UebwxbxiBean() {
        }

        protected UebwxbxiBean(Parcel in) {
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

        public static final Creator<UebwxbxiBean> CREATOR = new Creator<UebwxbxiBean>() {
            @Override
            public UebwxbxiBean createFromParcel(Parcel source) {
                return new UebwxbxiBean(source);
            }

            @Override
            public UebwxbxiBean[] newArray(int size) {
                return new UebwxbxiBean[size];
            }
        };
    }

    public static class UfpiBean implements Parcelable {
        /**
         * ufpirf : yrzlm
         * vltd : 0
         * uijm :
         * bwvu :
         */

        private String ufpirf;
        private int vltd;
        private String uijm;
        private String bwvu;
        private String xkmk;

        public String getXkmk(){
            return xkmk;
        }
        public void setXkmk(String temp){
            this.xkmk = temp;
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

        @Override
        public int describeContents() {
            return 0;
        }

        @Override
        public void writeToParcel(Parcel dest, int flags) {
            dest.writeString(this.ufpirf);
            dest.writeInt(this.vltd);
            dest.writeString(this.uijm);
            dest.writeString(this.bwvu);
            dest.writeString(this.xkmk);
        }

        public UfpiBean() {
        }

        protected UfpiBean(Parcel in) {
            this.ufpirf = in.readString();
            this.vltd = in.readInt();
            this.uijm = in.readString();
            this.bwvu = in.readString();
            this.xkmk = in.readString();
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
        dest.writeParcelable(this.dkdjxbxi, flags);
        dest.writeParcelable(this.qm, flags);
        dest.writeParcelable(this.kehuziln, flags);
        dest.writeParcelable(this.vltd, flags);
        dest.writeTypedList(this.uebwxbxi);
        dest.writeList(this.ufpi);
        dest.writeStringList(this.files);
    }

    public DkDjDetailInfoBean() {
    }

    protected DkDjDetailInfoBean(Parcel in) {
        this.dkdjxbxi = in.readParcelable(DkdjxbxiBean.class.getClassLoader());
        this.qm = in.readParcelable(QmBean.class.getClassLoader());
        this.kehuziln = in.readParcelable(KehuzilnBean.class.getClassLoader());
        this.vltd = in.readParcelable(VltdBean.class.getClassLoader());
        this.uebwxbxi = in.createTypedArrayList(DeviceInfoBean.CREATOR);
        this.ufpi = new ArrayList<UfpiBean>();
        in.readList(this.ufpi, UfpiBean.class.getClassLoader());
        this.files = in.createStringArrayList();
    }

    public static final Parcelable.Creator<DkDjDetailInfoBean> CREATOR = new Parcelable.Creator<DkDjDetailInfoBean>() {
        @Override
        public DkDjDetailInfoBean createFromParcel(Parcel source) {
            return new DkDjDetailInfoBean(source);
        }

        @Override
        public DkDjDetailInfoBean[] newArray(int size) {
            return new DkDjDetailInfoBean[size];
        }
    };
}
