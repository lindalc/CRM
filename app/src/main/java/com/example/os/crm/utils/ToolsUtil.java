package com.example.os.crm.utils;

import android.annotation.SuppressLint;
import android.app.Activity;
import android.app.Application;
import android.content.Context;
import android.content.pm.PackageInfo;
import android.content.pm.PackageManager;
import android.content.res.AssetManager;
import android.graphics.Bitmap;
import android.graphics.BitmapFactory;
import android.media.MediaMetadataRetriever;
import android.media.ThumbnailUtils;
import android.os.Build;
import android.os.Looper;
import android.provider.MediaStore;
import android.util.DisplayMetrics;
import android.util.Log;
import android.view.WindowManager;
import android.widget.Toast;

import com.bumptech.glide.Glide;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStream;
import java.io.InputStreamReader;
import java.net.HttpURLConnection;
import java.net.MalformedURLException;
import java.net.URL;
import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.Calendar;
import java.util.Date;
import java.util.HashMap;
import java.util.Hashtable;
import java.util.List;
import java.util.Locale;

/**
 * @author  lc 
 * @date 创建时间：2016-1-13 上午10:13:27 
 * @version 1.0 
 * @Description 打印工具类
 */
public class ToolsUtil {
	
	static private Toast mToast = null;

	public static int M_SCREEN_WIDTH;

	public static int M_SCREEN_HEIGHT;
	
	// 打印方法
	public static void print(String tag, String msg) {
		if(true){
			Log.i(tag, msg);
		}
	}
	
	/**
	 * Toast提醒
	 * @param msg
	 */
	public static void toast (Context context,String msg) {
		// 防止Toast重复显示
		if (mToast == null) {  
            mToast = Toast.makeText(context, msg, Toast.LENGTH_SHORT);  
        } else {  
            mToast.setText(msg);  
            mToast.setDuration(Toast.LENGTH_SHORT);  
        }  
        mToast.show(); 
	}

	public static String getJson(Context mContext, String fileName) {
		// TODO Auto-generated method stub
		StringBuilder sb = new StringBuilder();
		AssetManager am = mContext.getAssets();
		try {
			BufferedReader br = new BufferedReader(new InputStreamReader(
					am.open(fileName)));
			String next = "";
			while (null != (next = br.readLine())) {
				sb.append(next);
			}
		} catch (IOException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
			sb.delete(0, sb.length());
		}
		return sb.toString().trim();
	}

	/**
	 * 根据手机的分辨率从 dp 的单位 转成为 px(像素)
	 */
	public static int dip2px(Context context, float dpValue) {
		final float scale = context.getResources().getDisplayMetrics().density;
		return (int) (dpValue * scale + 0.5f);
	}

//	/**
//	 * Exception 信息提示
//	 */
//	public static void Notic(Context context, String notic,
//			OnClickListener listener) {
//		UIDialog.ForNotic(context, notic, listener);
//	}
	
	/**
	 * 获取当前应用的版本号
	 */

	public static int getAppVersion(Context context) {
		int version = 0;
		try {
			PackageInfo packinfo = context.getPackageManager().getPackageInfo(
					context.getPackageName(), 0);
			version = packinfo.versionCode;
		} catch (Exception e) {
			e.printStackTrace();
			return version;
		}

		return version;
	}
	
	/**
	 * 格式化时间 昨天今天
	 * @param time 
	 * @return 
	 */
	@SuppressLint("SimpleDateFormat")
	public static String formatDateTime(String time) {
		SimpleDateFormat format = new SimpleDateFormat("yyyy-MM-dd HH:mm");
		if(time==null ||"".equals(time)){
			return "";
		}
		Date date = null;
		try {
			date = format.parse(time);
		} catch (ParseException e) {
			e.printStackTrace();
		}
		
		Calendar current = Calendar.getInstance();
		
		Calendar today = Calendar.getInstance();	//今天
		
		today.set(Calendar.YEAR, current.get(Calendar.YEAR));
		today.set(Calendar.MONTH, current.get(Calendar.MONTH));
		today.set(Calendar.DAY_OF_MONTH,current.get(Calendar.DAY_OF_MONTH));
		//  Calendar.HOUR——12小时制的小时数 Calendar.HOUR_OF_DAY——24小时制的小时数
		today.set( Calendar.HOUR_OF_DAY, 0);
		today.set( Calendar.MINUTE, 0);
		today.set(Calendar.SECOND, 0);
		
		Calendar yesterday = Calendar.getInstance();	//昨天
		
		yesterday.set(Calendar.YEAR, current.get(Calendar.YEAR));
		yesterday.set(Calendar.MONTH, current.get(Calendar.MONTH));
		yesterday.set(Calendar.DAY_OF_MONTH,current.get(Calendar.DAY_OF_MONTH)-1);
		yesterday.set( Calendar.HOUR_OF_DAY, 0);
		yesterday.set( Calendar.MINUTE, 0);
		yesterday.set(Calendar.SECOND, 0);
		
		current.setTime(date);
		
		if(current.after(today)){
			return "今天 "+time.split(" ")[1];
		}else if(current.before(today) && current.after(yesterday)){
			
			return "昨天 "+time.split(" ")[1];
		}else{
			int index = time.indexOf("-")+1;
			return time.substring(index, time.length());
		}
	}

	// 清除图片磁盘缓存，调用Glide自带方法
	public static boolean clearCacheDiskSelf(final Context context) {
		try {
			if (Looper.myLooper() == Looper.getMainLooper()) {
				new Thread(new Runnable() {
					@Override
					public void run() {
						Glide.get(context).clearDiskCache();
					}
				}).start();
			} else {
				Glide.get(context).clearDiskCache();
			}
			return true;
		} catch (Exception e) {
			e.printStackTrace();
			return false;
		}
	}
	
	/**
	 * 日期，今明后天
	 * @param time
	 * @return
	 */
	@SuppressLint("SimpleDateFormat")
	public static String getDate(String time){
		SimpleDateFormat format = new SimpleDateFormat("yyyy-MM-dd HH:mm");
		if(time==null ||"".equals(time)){
			return "";
		}
		Date date = null;
		try {
			date = format.parse(time);
		} catch (ParseException e) {
			e.printStackTrace();
		}
		
        Calendar today = Calendar.getInstance();  
		Calendar old = Calendar.getInstance();  
		Calendar current = Calendar.getInstance();
		
		//此处的isEver everType startTime  createDate为pojo的属性   
		//此处好像是去除0   
		today.set(Calendar.YEAR, current.get(Calendar.YEAR));
		today.set(Calendar.MONTH, current.get(Calendar.MONTH));
		today.set(Calendar.DAY_OF_MONTH,current.get(Calendar.DAY_OF_MONTH));
		today.set(Calendar.HOUR, 0);  
		today.set(Calendar.MINUTE, 0);  
		today.set(Calendar.SECOND, 0);  
		// 明天
		old.set(Calendar.YEAR, current.get(Calendar.YEAR));
		old.set(Calendar.MONTH, current.get(Calendar.MONTH));
		old.set(Calendar.DAY_OF_MONTH,current.get(Calendar.DAY_OF_MONTH)+1);
		old.set(Calendar.HOUR, 0);  
		old.set(Calendar.MINUTE, 0);  
		old.set(Calendar.SECOND, 0);  
		
		current.setTime(date);
		        //老的时间减去今天的时间  
        long intervalMilli = current.getTimeInMillis() - today.getTimeInMillis();  
		int xcts = (int) (intervalMilli / (24 * 60 * 60 * 1000));  
		ToolsUtil.print("----", "xcts ===== "+ xcts);
		ToolsUtil.print("----", "current ===== "+ current);
		ToolsUtil.print("----", "today ===== "+ today);
		//-2:前天 -1：昨天 0：今天 1：明天 2：后天， out：显示日期
		String day = null;
		if(xcts==0){
			day = "今天";
		}else if(xcts == 1){
			day = "明天";
		}else if(xcts == 2){
			day = "后天";
		}else if(xcts>2){
			day = "第"+(xcts)+"天";
		}else{
			day="未设置";
		}
//		if (xcts >= -2 && xcts <= 2) {  
//			return String.valueOf(xcts);  
//		} else {  
//			return "out";  
//		}  
		return day;
	}

	/*public static long getTime(String signTime, String payTime) {
		try {
			long start = DateUtil.stringToLong(signTime, "yyyy-MM-dd HH:mm:ss");
			long end = DateUtil.stringToLong(payTime, "yyyy-MM-dd HH:mm:ss");
			return (end-start)/60000;
		} catch (ParseException e) {
			e.printStackTrace();
			return 0;
		}
	}*/
	

	
 	public static String getNowStr(String formatType) {
 		return new SimpleDateFormat(formatType, new Locale("zh_CN")).format(new Date());
 	}

	/**
	 * 获取视频缩略图
	 * @param filePath
	 * @return
	 */
	public static Bitmap getVideoThumbnail(String filePath) {
		Bitmap bitmap = null;
		MediaMetadataRetriever retriever = new MediaMetadataRetriever();
		int kind = MediaStore.Video.Thumbnails.MINI_KIND;
		try {
//			retriever.setDataSource(filePath);
//			bitmap = retriever.getFrameAtTime();
			if (Build.VERSION.SDK_INT >= 14) {
				retriever.setDataSource(filePath, new HashMap<String, String>());
			} else {
				retriever.setDataSource(filePath);
			}
			bitmap = retriever.getFrameAtTime();
		}
		catch(IllegalArgumentException e) {
			e.printStackTrace();
		}
		catch (RuntimeException e) {
			e.printStackTrace();
		}
		finally {
			try {
				retriever.release();
			}
			catch (RuntimeException e) {
				e.printStackTrace();
			}
		}
		if (kind == MediaStore.Images.Thumbnails.MICRO_KIND && bitmap != null) {
			bitmap = ThumbnailUtils.extractThumbnail(bitmap, 50, 50,
					ThumbnailUtils.OPTIONS_RECYCLE_INPUT);
		}
		return bitmap;
	}

	// 计算两点间距离,返回单位是千米
	private static final double EARTH_RADIUS = 6378137.0;
	public static double getDistance(double longitude1, double latitude1,
									 double longitude2, double latitude2) {
		double Lat1 = rad(latitude1);
		double Lat2 = rad(latitude2);
		double a = Lat1 - Lat2;
		double b = rad(longitude1) - rad(longitude2);
		double s = 2 * Math.asin(Math.sqrt(Math.pow(Math.sin(a / 2), 2)
				+ Math.cos(Lat1) * Math.cos(Lat2)
				* Math.pow(Math.sin(b / 2), 2)));
		s = s * EARTH_RADIUS;
		s = Math.round(s * 10000) / 10000;
		return s/1000;
	}
	private static double rad(double d) {
		return d * Math.PI / 180.0;
	}


	 /* 检查手机上是否安装了指定的软件
     * @param context
     * @param packageName：应用包名
     * @return
			 */
	public static boolean isAvilible(Context context, String packageName){
		//获取packagemanager
		final PackageManager packageManager = context.getPackageManager();
		//获取所有已安装程序的包信息
		List<PackageInfo> packageInfos = packageManager.getInstalledPackages(0);
		//用于存储所有已安装程序的包名
		List<String> packageNames = new ArrayList<String>();
		//从pinfo中将包名字逐一取出，压入pName list中
		if(packageInfos != null){
			for(int i = 0; i < packageInfos.size(); i++){
				String packName = packageInfos.get(i).packageName;
				packageNames.add(packName);
			}
		}
		//判断packageNames中是否有目标程序的包名，有TRUE，没有FALSE
		return packageNames.contains(packageName);
	}


}