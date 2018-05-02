package com.example.os.crm.ui.widget;

import android.content.Context;
import android.util.AttributeSet;
import android.widget.GridView;

public class QuanBuFenLei_MyGridView extends GridView{

	public QuanBuFenLei_MyGridView(Context context, AttributeSet attrs) {
		super(context, attrs);
	}

	public QuanBuFenLei_MyGridView(Context context) {
		super(context);
	}

	public QuanBuFenLei_MyGridView(Context context, AttributeSet attrs, int defStyle) {
		super(context, attrs, defStyle);
	}

	@Override
	public void onMeasure(int widthMeasureSpec, int heightMeasureSpec) {
		int expandSpec = MeasureSpec.makeMeasureSpec(Integer.MAX_VALUE >> 2,
				MeasureSpec.AT_MOST);
		super.onMeasure(widthMeasureSpec, expandSpec);
	}
}
