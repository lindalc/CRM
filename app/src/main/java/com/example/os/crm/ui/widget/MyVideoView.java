package com.example.os.crm.ui.widget;

import java.io.IOException;
import java.util.Map;

import android.annotation.SuppressLint;
import android.app.AlertDialog;
import android.content.Context;
import android.content.DialogInterface;
import android.content.Intent;
import android.content.res.Resources;
import android.media.AudioManager;
import android.media.MediaPlayer;
import android.media.MediaPlayer.OnCompletionListener;
import android.media.MediaPlayer.OnErrorListener;
import android.net.Uri;
import android.util.AttributeSet;
import android.util.Log;
import android.view.KeyEvent;
import android.view.MotionEvent;
import android.view.SurfaceHolder;
import android.view.View;
import android.widget.MediaController;
import android.widget.MediaController.MediaPlayerControl;
import android.widget.VideoView;

/**
 * @author  lc 
 * @date 创建时间：2016-8-16 上午9:17:29 
 * @version 1.0 
 * @Description
 */
public class MyVideoView extends VideoView implements MediaPlayerControl {

	public MyVideoView(Context context) {
		super(context);
		}

		public MyVideoView(Context context, AttributeSet attrs) {
		super(context, attrs);
		}

		public MyVideoView(Context context, AttributeSet attrs, int defStyle) {
		super(context, attrs, defStyle);
		}
		@Override
		protected void onMeasure(int widthMeasureSpec, int heightMeasureSpec) {
		//super.onMeasure(widthMeasureSpec, heightMeasureSpec);
		int width = getDefaultSize(getWidth(), widthMeasureSpec);
		int height = getDefaultSize(getHeight(), heightMeasureSpec);
		setMeasuredDimension(width, height);   
		}
}
