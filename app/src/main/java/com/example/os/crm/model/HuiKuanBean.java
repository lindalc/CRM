package com.example.os.crm.model;

import android.os.Parcel;
import android.os.Parcelable;

import java.util.ArrayList;
import java.util.List;

/**
 * Created by OS on 2018/1/10.
 */

public class HuiKuanBean implements Parcelable {

    private int wwuz;
    private String vibcjb;
    private String zsjx;
    private String dkdjbmhc;
    private List<HvkrliuiBean> hvkrliui;

    public int getWwuz() {
        return wwuz;
    }

    public void setWwuz(int wwuz) {
        this.wwuz = wwuz;
    }

    public String getVibcjb() {
        return vibcjb;
    }

    public void setVibcjb(String vibcjb) {
        this.vibcjb = vibcjb;
    }

    public String getZsjx() {
        return zsjx;
    }

    public void setZsjx(String zsjx) {
        this.zsjx = zsjx;
    }

    public String getDkdjbmhc() {
        return dkdjbmhc;
    }

    public void setDkdjbmhc(String dkdjbmhc) {
        this.dkdjbmhc = dkdjbmhc;
    }

    public List<HvkrliuiBean> getHvkrliui() {
        return hvkrliui;
    }

    public void setHvkrliui(List<HvkrliuiBean> hvkrliui) {
        this.hvkrliui = hvkrliui;
    }

    public static class HvkrliuiBean implements Parcelable {
        /**
         * dkdjbmhc : YR1803140653
         * ufpi : [{"vltd":0,"xkmk":"徐美静","uijm":"","ufpirf":"yrxmj","bwvu":""},{"vltd":0,"xkmk":"张泽启","uijm":"","ufpirf":"yrzzq","bwvu":""}]
         * riqi : 2018年03月15日
         * fuzeyewu : yryjl
         * uijmio : 1521103402
         * jbee : 81
         */

        private String dkdjbmhc;
        private String riqi;
        private String fuzeyewu;
        private int uijmio;
        private String jbee;
        private List<UfpiBean> ufpi;
        private int vltd;

        public int getVltd() {
            return vltd;
        }

        public void setVltd(int vltd) {
            this.vltd = vltd;
        }

        public String getDkdjbmhc() {
            return dkdjbmhc;
        }

        public void setDkdjbmhc(String dkdjbmhc) {
            this.dkdjbmhc = dkdjbmhc;
        }

        public String getRiqi() {
            return riqi;
        }

        public void setRiqi(String riqi) {
            this.riqi = riqi;
        }

        public String getFuzeyewu() {
            return fuzeyewu;
        }

        public void setFuzeyewu(String fuzeyewu) {
            this.fuzeyewu = fuzeyewu;
        }

        public int getUijmio() {
            return uijmio;
        }

        public void setUijmio(int uijmio) {
            this.uijmio = uijmio;
        }

        public String getJbee() {
            return jbee;
        }

        public void setJbee(String jbee) {
            this.jbee = jbee;
        }

        public List<UfpiBean> getUfpi() {
            return ufpi;
        }

        public void setUfpi(List<UfpiBean> ufpi) {
            this.ufpi = ufpi;
        }

        public static class UfpiBean implements Parcelable {
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

            @Override
            public int describeContents() {
                return 0;
            }

            @Override
            public void writeToParcel(Parcel dest, int flags) {
                dest.writeInt(this.vltd);
                dest.writeString(this.xkmk);
                dest.writeString(this.uijm);
                dest.writeString(this.ufpirf);
                dest.writeString(this.bwvu);
            }

            public UfpiBean() {
            }

            protected UfpiBean(Parcel in) {
                this.vltd = in.readInt();
                this.xkmk = in.readString();
                this.uijm = in.readString();
                this.ufpirf = in.readString();
                this.bwvu = in.readString();
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
            dest.writeString(this.dkdjbmhc);
            dest.writeString(this.riqi);
            dest.writeString(this.fuzeyewu);
            dest.writeInt(this.uijmio);
            dest.writeString(this.jbee);
            dest.writeList(this.ufpi);
        }

        public HvkrliuiBean() {
        }

        protected HvkrliuiBean(Parcel in) {
            this.dkdjbmhc = in.readString();
            this.riqi = in.readString();
            this.fuzeyewu = in.readString();
            this.uijmio = in.readInt();
            this.jbee = in.readString();
            this.ufpi = new ArrayList<UfpiBean>();
            in.readList(this.ufpi, UfpiBean.class.getClassLoader());
        }

        public static final Creator<HvkrliuiBean> CREATOR = new Creator<HvkrliuiBean>() {
            @Override
            public HvkrliuiBean createFromParcel(Parcel source) {
                return new HvkrliuiBean(source);
            }

            @Override
            public HvkrliuiBean[] newArray(int size) {
                return new HvkrliuiBean[size];
            }
        };
    }

    @Override
    public int describeContents() {
        return 0;
    }

    @Override
    public void writeToParcel(Parcel dest, int flags) {
        dest.writeInt(this.wwuz);
        dest.writeString(this.vibcjb);
        dest.writeString(this.zsjx);
        dest.writeString(this.dkdjbmhc);
        dest.writeList(this.hvkrliui);
    }

    public HuiKuanBean() {
    }

    protected HuiKuanBean(Parcel in) {
        this.wwuz = in.readInt();
        this.vibcjb = in.readString();
        this.zsjx = in.readString();
        this.dkdjbmhc = in.readString();
        this.hvkrliui = new ArrayList<HvkrliuiBean>();
        in.readList(this.hvkrliui, HvkrliuiBean.class.getClassLoader());
    }

    public static final Parcelable.Creator<HuiKuanBean> CREATOR = new Parcelable.Creator<HuiKuanBean>() {
        @Override
        public HuiKuanBean createFromParcel(Parcel source) {
            return new HuiKuanBean(source);
        }

        @Override
        public HuiKuanBean[] newArray(int size) {
            return new HuiKuanBean[size];
        }
    };
}
