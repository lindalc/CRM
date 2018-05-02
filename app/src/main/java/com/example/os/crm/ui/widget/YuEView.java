package com.example.os.crm.ui.widget;

import android.content.Context;
import android.support.v7.widget.AppCompatTextView;
import android.util.AttributeSet;
import android.widget.TextView;

import java.math.BigDecimal;
import java.text.DecimalFormat;

/**
 * Created by Administrator on 2017/8/29.
 */

public class YuEView extends AppCompatTextView{
    private static final int COUNT = 100;
    private int mTextColor = 0xF3E5C9;
    private float mValue = 0.0f;
    private float mAverage = 0.0f;
    private int mCount = 0;
    public YuEView(Context context) {
        this(context, null);
    }
    public YuEView(Context context, AttributeSet attrs) {
        super(context, attrs);
        mAverage = mValue / COUNT;
        setTextColor(mTextColor);
        setText(String.valueOf(0.0f));

    }
    public double getValue() {
        return mValue;
    }
    public void setValue(float mValue) {
        mAverage = mValue / COUNT;
        new Thread(runnable).start();
    }
    public int getTextColor() {
        return mTextColor;
    }
    public void setTextColor(int mTextColor) {
        this.mTextColor = mTextColor;
    }

    Runnable runnable = new Runnable() {
        @Override
        public void run() {
            while (mCount < COUNT) {
                mValue += mAverage;
                try {
                    Thread.sleep(10);
                } catch (InterruptedException e) {
                    e.printStackTrace();
                }
                post(new Runnable() {
                    @Override
                    public void run() {
                        BigDecimal bg = new BigDecimal(mValue);
                        double f1 = bg.setScale(2, BigDecimal.ROUND_HALF_UP).doubleValue();
                        setText(f1+"");
                    }
                });
                mCount++;
            }
        }
    };
}
