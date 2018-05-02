package com.example.os.crm.utils;

import android.content.Context;
import android.util.AttributeSet;
import android.widget.ListView;

/**解决ListView在ScrollView中只显示一条问题*/
public class ListViewForScroll extends ListView {

	public ListViewForScroll(Context context) {
		super(context);
	}

	public ListViewForScroll(Context context, AttributeSet attrs) {
		super(context, attrs);
	}

	public ListViewForScroll(Context context, AttributeSet attrs, int defStyle) {
		super(context, attrs, defStyle);
	}

	@Override
	protected void onMeasure(int widthMeasureSpec, int heightMeasureSpec) {
		int expandSpec = MeasureSpec.makeMeasureSpec(Integer.MAX_VALUE >> 2,
				MeasureSpec.AT_MOST);
		super.onMeasure(widthMeasureSpec, expandSpec);
	}

}
