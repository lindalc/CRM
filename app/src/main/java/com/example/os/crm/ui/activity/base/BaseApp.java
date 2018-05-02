package com.example.os.crm.ui.activity.base;

import android.app.Activity;
import android.app.ActivityManager;
import android.content.Context;
import android.graphics.Bitmap;
import android.graphics.Typeface;
import android.os.Debug;
import android.support.multidex.MultiDexApplication;
import android.text.TextUtils;
import android.util.Log;

import com.bumptech.glide.Glide;
import com.facebook.drawee.backends.pipeline.Fresco;
import com.facebook.imagepipeline.core.ImagePipelineConfig;
import com.iflytek.cloud.SpeechConstant;
import com.iflytek.cloud.SpeechUtility;
import com.pgyersdk.crash.PgyCrashManager;
import com.example.os.crm.utils.ToolsUtil;

import java.lang.reflect.Field;
import java.util.Stack;

import cn.jpush.android.api.JPushInterface;


public class BaseApp extends MultiDexApplication {
	private static Stack<Activity> activityStack;
	public static BaseApp singleton;


	private static BaseApp context;

	private static AppModel model;

	public void onCreate() {
		super.onCreate();


		context = this;

		singleton = this;



		ImagePipelineConfig config = ImagePipelineConfig.newBuilder(this)
				.setDownsampleEnabled(true)
				.setBitmapsConfig(Bitmap.Config.RGB_565)
				.build();
		Fresco.initialize(this.getApplicationContext(),config);

//		InitializeService.start(this);//启动服务执行耗时操作
		initModel();


		SpeechUtility.createUtility(this.getApplicationContext(), SpeechConstant.APPID +"=5ae29034");

		JPushInterface.setDebugMode(true);
		JPushInterface.init(this.getApplicationContext());

		try {
			PgyCrashManager.register(this);
		}catch (Exception e){
			PgyCrashManager.unregister();
			PgyCrashManager.register(this);
		}

	}


	private void initModel() {
	    	/*初始化用户Model*/
		model=AppModel.init(this);
	}

	/**
	 * 获取用户信息
	 */
	public static AppModel getModel(){
		if(model == null){
			Log.e("application","appmodel is null");
		}
		return model;
	}

	/**
	 * 获得当前进程的名字
	 *
	 * @param context
	 * @return 进程号
	 */
	public static String getCurProcessName(Context context) {

		int pid = android.os.Process.myPid();

		ActivityManager activityManager = (ActivityManager) context
				.getSystemService(Context.ACTIVITY_SERVICE);

		for (ActivityManager.RunningAppProcessInfo appProcess : activityManager
				.getRunningAppProcesses()) {

			if (appProcess.pid == pid) {
				return appProcess.processName;
			}
		}
		return null;
	}


	public static BaseApp getInstance() {
		return singleton;
	}

	/**
	 *
	 */
	public void addActivity(Activity activity) {
		if (activityStack == null) {
			activityStack = new Stack<Activity>();
		}
		activityStack.add(activity);

		for (Activity temp : activityStack) {
		}
	}

	/**
	 * 
	 */
	public Activity currentActivity() {
		Activity activity = activityStack.lastElement();
		return activity;
	}

	/**
	 *
	 */
	public void finishActivity() {
		Activity activity = activityStack.lastElement();
		finishActivity(activity);
	}

	/**
	 * 
	 */
	public void finishActivity(Activity activity) {
		if (activity != null) {
			activityStack.remove(activity);
			activity.finish();
			activity = null;
		}
	}

	/**
	 * 
	 */
	public void finishAllActivity() {
		for (int i = 0, size = activityStack.size(); i < size; i++) {
			if (null != activityStack.get(i)) {
				activityStack.get(i).finish();
			}
		}
		activityStack.clear();
	}

	/**
	 * 当前界面恢复时的操作
	 */
	public void resumeActivity(Activity activity) {
		if (activityStack.lastElement() == activity) {
			return;
		}
		activityStack.remove(activity);
		activityStack.push(activity);

	}


}
