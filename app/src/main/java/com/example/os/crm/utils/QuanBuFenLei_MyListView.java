package com.example.os.crm.utils;

import android.content.Context;
import android.util.AttributeSet;
import android.widget.ListView;

public class QuanBuFenLei_MyListView extends ListView {

	public QuanBuFenLei_MyListView(Context context) {
		super(context);
	}

	public QuanBuFenLei_MyListView(Context context, AttributeSet attrs) {

		super(context, attrs);
	}

	public QuanBuFenLei_MyListView(Context context, AttributeSet attrs, int defStyle) {

		super(context, attrs, defStyle);
	}

	public void onMeasure(int widthMeasureSpec, int heightMeasureSpec) {

		int mExpandSpec = MeasureSpec.makeMeasureSpec(Integer.MAX_VALUE >> 2,
				MeasureSpec.AT_MOST);

		super.onMeasure(widthMeasureSpec, mExpandSpec);

	}

}
