package com.example.os.crm.utils;

import android.content.Context;
import android.util.AttributeSet;
import android.util.Log;
import android.widget.ListView;

/**
 * 解决getView()多次重复调用问题_还需在adapter的getView()中添加一段代码
 */
public class MyListView extends ListView {
	public boolean isOnMeasure;

	public MyListView(Context context) {
		super(context);
	}

	public MyListView(Context context, AttributeSet attrs) {
		super(context, attrs);
	}

	public MyListView(Context context, AttributeSet attrs, int defStyle) {
		super(context, attrs, defStyle);
	}

	@Override
	protected void onMeasure(int widthMeasureSpec, int heightMeasureSpec) {
		Log.d("onMeasure", "onMeasure");
		isOnMeasure = true;
		super.onMeasure(widthMeasureSpec, heightMeasureSpec);
	}

	@Override
	protected void onLayout(boolean changed, int l, int t, int r, int b) {
		Log.d("onLayout", "onLayout");
		isOnMeasure = false;
		super.onLayout(changed, l, t, r, b);
	}

	public boolean isOnMeasure() {

		return isOnMeasure;
	}
}