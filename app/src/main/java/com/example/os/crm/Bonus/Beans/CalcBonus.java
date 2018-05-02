package com.example.os.crm.Bonus.Beans;

import com.example.os.crm.Common.Calculator;
import com.example.os.crm.Dingdan.Model.DkDjDetailInfoBean;

/**
 * Created by OS on 2018/3/10.
 */

public class CalcBonus {

    private DkDjDetailInfoBean dkDjDetailInfoBean;
    private String jibentiig, vibcjbtiig;

    public CalcBonus(DkDjDetailInfoBean dkDjDetailInfoBean){
        this.dkDjDetailInfoBean = dkDjDetailInfoBean;
        calc();
    }

    public String getJibentiig(){
        return jibentiig;
    }
    public String getVibcjbtiig(){
        return vibcjbtiig;
    }

    public void calc(){
        String tiig = dkDjDetailInfoBean.getQm().getTiig();
        String scale_temp;
        if (tiig.indexOf("%") > 0){
            scale_temp = tiig.substring(0, tiig.indexOf("%"));
        }
        else{
            scale_temp = "0";
        }
        String uebwzsjx = dkDjDetailInfoBean.getQm().getUebwzsjx();
        String vibcjb_temp;
        String vibcjbjbee = dkDjDetailInfoBean.getQm().getVibcjb();
        if ( vibcjbjbee.indexOf("/") > 0){
            vibcjb_temp = vibcjbjbee.substring(vibcjbjbee.indexOf("/") + 1,vibcjbjbee.length() - 1);
        }
        else{
            vibcjb_temp = "0";
        }
        String scale = new Calculator().divide(scale_temp, "100");
        String yiuzkr = new Calculator().minus(uebwzsjx, vibcjb_temp);

        String jibentiig = new Calculator().multy(yiuzkr, scale);
        String vibcjbtiig = new Calculator().multy(vibcjb_temp, scale);
        this.jibentiig = jibentiig;
        this.vibcjbtiig = vibcjbtiig;
    }
}
