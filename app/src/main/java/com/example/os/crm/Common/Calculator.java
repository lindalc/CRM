package com.example.os.crm.Common;

import java.math.BigDecimal;

/**
 * Created by OS on 2018/3/10.
 */

public class Calculator {

    public String add(String x1, String x2){
        BigDecimal result = new BigDecimal(x1).add(new BigDecimal(x2));
        return result.toString();
    }
    public String minus(String x1, String x2){
        BigDecimal result = new BigDecimal(x1).subtract(new BigDecimal(x2));
        return result.toString();
    }
    public String multy(String x1, String x2){
        BigDecimal result = new BigDecimal(x1).multiply(new BigDecimal(x2));
        return result.toString();
    }
    public String divide(String x1, String x2){
        BigDecimal result = new BigDecimal(x1).divide(new BigDecimal(x2));
        return result.toString();
    }
}
