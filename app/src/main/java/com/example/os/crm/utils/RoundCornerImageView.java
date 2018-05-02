package com.example.os.crm.utils;

import android.content.Context;
import android.graphics.Canvas;
import android.graphics.Path;
import android.graphics.RectF;
import android.support.v7.widget.AppCompatImageView;
import android.util.AttributeSet;
import android.widget.ImageView;

/**
 * Created by ninos on 2016/8/29.
 */
public class RoundCornerImageView extends AppCompatImageView {
    public RoundCornerImageView(Context context) {
        super(context);
    }
    public RoundCornerImageView(Context context, AttributeSet attrs) {
        super(context, attrs);
    }
    public RoundCornerImageView(Context context, AttributeSet attrs,
                                int defStyle) {
        super(context, attrs, defStyle);
    }
    @Override
    protected void onDraw(Canvas canvas) {
        Path clipPath = new Path();
        int w = this.getWidth();
        int h = this.getHeight();
        clipPath.addRoundRect(new RectF(0, 0, w, h), 0f, 0f, Path.Direction.CW);
        canvas.clipPath(clipPath);
        try {
            super.onDraw(canvas);
        } catch (Exception e) {

        }
    }
}
