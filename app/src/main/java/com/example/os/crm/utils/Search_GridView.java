package com.example.os.crm.utils;

import android.content.Context;
import android.util.AttributeSet;
import android.widget.GridView;


public class Search_GridView extends GridView {
    public Search_GridView(Context context) {
        super(context);
    }

    public Search_GridView(Context context, AttributeSet attrs) {
        super(context, attrs);
    }

    @Override
    protected void onMeasure(int widthMeasureSpec, int heightMeasureSpec) {
        int expandSpec = MeasureSpec.makeMeasureSpec(Integer.MAX_VALUE >> 2,
                MeasureSpec.AT_MOST);
        super.onMeasure(widthMeasureSpec, expandSpec);
    }
}
