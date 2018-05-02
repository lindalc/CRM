package com.example.os.crm.ui.widget;

import java.util.Stack;

import android.app.Activity;
import android.app.Application;
import android.util.Log;

import com.example.os.crm.ui.activity.base.AppModel;
import com.example.os.crm.utils.ToolsUtil;


//import com.iflytek.cloud.SpeechUtility;
//import com.nostra13.universalimageloader.cache.disc.naming.Md5FileNameGenerator;
//import com.nostra13.universalimageloader.core.ImageLoader;
//import com.nostra13.universalimageloader.core.ImageLoaderConfiguration;
//import com.nostra13.universalimageloader.core.assist.QueueProcessingType;

public class BaseApp extends Application {
	private static Stack<Activity> activityStack;
	public static BaseApp singleton;
	private static AppModel model;
	
	

	public void onCreate() {
		super.onCreate();

		singleton = this;
//		SpeechUtility.createUtility(BaseApp.this, SpeechConstant.APPID +"=58ac085a");
		initImageLoader();
		initModel();
	}

	public static BaseApp getInstance() {
		return singleton;
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
	 * 
	 */
	public void addActivity(Activity activity) {
		if (activityStack == null) {
			activityStack = new Stack<Activity>();
		}
		activityStack.add(activity);

		for (Activity temp : activityStack) {
			ToolsUtil.print("----","类名:" + temp.toString() + "地址：" + temp);
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

		ToolsUtil.print("----","最后一个参数:" + activityStack.lastElement());
	}

	/** 初始化ImageLoader的数据 */
	public void initImageLoader() {
//		ImageLoaderConfiguration config = new ImageLoaderConfiguration.Builder(
//				getApplicationContext())
//				.threadPriority(Thread.NORM_PRIORITY - 2)
//				.denyCacheImageMultipleSizesInMemory()
//				.diskCacheFileNameGenerator(new Md5FileNameGenerator())
//				.diskCacheSize(50 * 1024 * 1024)
//				// 50 Mb
//				.tasksProcessingOrder(QueueProcessingType.LIFO)
//				.writeDebugLogs() // Remove for release app
//				.build();
//
//		ImageLoader.getInstance().init(config);
	}
}
