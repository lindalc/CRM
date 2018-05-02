package com.example.os.crm.ui.widget;

import android.content.Context;
import android.support.v7.widget.RecyclerView;
import android.util.AttributeSet;
import android.widget.ListView;

/**解决ListView在ScrollView中只显示一条问题*/
public class RecyclerForScroll extends RecyclerView {

	public RecyclerForScroll(Context context) {
		super(context);
	}

	public RecyclerForScroll(Context context, AttributeSet attrs) {
		super(context, attrs);
	}

	public RecyclerForScroll(Context context, AttributeSet attrs, int defStyle) {
		super(context, attrs, defStyle);
	}

	@Override
	protected void onMeasure(int widthMeasureSpec, int heightMeasureSpec) {
		int expandSpec = MeasureSpec.makeMeasureSpec(Integer.MAX_VALUE >> 2,
				MeasureSpec.AT_MOST);
		super.onMeasure(widthMeasureSpec, expandSpec);
	}

}
