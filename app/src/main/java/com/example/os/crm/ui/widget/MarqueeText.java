package com.example.os.crm.ui.widget;

/**
 * Created by Administrator on 2017/10/13.
 */

import android.app.Activity;
import android.content.Context;
import android.graphics.Canvas;
import android.graphics.Color;
import android.graphics.Paint;
import android.os.Handler;
import android.os.Message;
import android.support.v7.widget.AppCompatTextView;
import android.text.TextUtils;
import android.util.AttributeSet;
import android.util.Log;
import android.view.View;
import android.view.WindowManager;
import android.widget.TextView;

public class MarqueeText extends AppCompatTextView {

    @Override
    public boolean isFocused() {
        return true;
    }


    public MarqueeText(Context context) {
        this(context,null);
    }
    public MarqueeText(Context context, AttributeSet attrs) {
        this(context, attrs,0);
    }
    public MarqueeText(Context context, AttributeSet attrs, int defStyleAttr) {
        super(context, attrs, defStyleAttr);
    }

}
