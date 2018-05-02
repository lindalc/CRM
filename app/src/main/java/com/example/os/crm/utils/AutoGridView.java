package com.example.os.crm.utils;

import android.content.Context;
import android.util.AttributeSet;
import android.widget.GridView;


/**
 * @作者 lss
 * 
 * @时间 2017年1月16日下午9:26:34
 * 
 * @说明 
 */
public class AutoGridView extends GridView{  
    public AutoGridView(Context context, AttributeSet attrs) {   
        super(context, attrs);   
    }   
   
    public AutoGridView(Context context) {   
        super(context);   
    }   
   
    public AutoGridView(Context context, AttributeSet attrs, int defStyle) {   
        super(context, attrs, defStyle);   
    }   
   
    @Override   
    public void onMeasure(int widthMeasureSpec, int heightMeasureSpec) {   
   
        int expandSpec = MeasureSpec.makeMeasureSpec(Integer.MAX_VALUE >> 2,   
                MeasureSpec.AT_MOST);   
        super.onMeasure(widthMeasureSpec, expandSpec);   
    }   
}  