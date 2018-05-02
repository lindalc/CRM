package com.example.os.crm.utils;

import android.content.Context;
import android.content.SharedPreferences;

/**
 * @author csh
 * @date 2016年5月14日
 * 保存用户信息搭配本地
 */
public class SharedPreferenceUtils {

    private static SharedPreferenceUtils mUtil;
    private static final String PREFERENCE_NAME="_ZYKJMJ";
    private static SharedPreferences mSharedPreference;
    private SharedPreferences.Editor mEditor;

    private static final String LOGIN="login";
    private static final String ISSetPayPwd="IsSetPayPwd";
    private static final String USERID="userid";
    private static final String USERNAME="username";
    private static final String MOBILE="mobile";
    private static final String NAME="name";
    private static final String PASSWORD="password";
    private static final String AVATAR="avatar";
    private static final String LNG="lng";
    private static final String LAT="lat";
//	public String setNicname;// 昵称
//	public String Address;// 详细地址（卖家版专用）
//	public String AdvanceDays;// 预订单可提前天数（卖家版专用）
//	public String AreaId;// 所在区域ID
//	public String DistributionFee;// 配送费（卖家版专用）
//	public String DistributionRange;// 配送范围（卖家版专用）
//	public String FreezeMoney;// 冻结资金
//	public String IsReceiveBill;// 非营业状态是否接受预订单（卖家版专用）
//	public String Lat;// 经度
//	public String Lng;// 纬度
//	public String MinPrice;// 起送价格
//	public String NumberOfId;// 证件号码
//	public String Phone;// 手机号
//	public String PhoneBind;// 绑定手机
//	public String PhotoPath;// 头像
//	public String PrintWidth;// 打印宽度（卖家版专用）
//	public String RegTime;// 注册时间
//	public String ShopAnnouncement;// 店铺公告（卖家版专用）
//	public String Token;// 第三方登录唯一标识
//	public String TotalMoney;// 余额
//	public String Type;// 用户类型 0：普通用户 1：商户 2：店铺审核中 3：骑手 4:待定
//	public String TypeOfId;// 证件类型 0：身份证 1：驾驶证 2：待定
	
    private SharedPreferenceUtils(Context context){
        mSharedPreference=context.getSharedPreferences(PREFERENCE_NAME, Context.MODE_PRIVATE);
        mEditor=mSharedPreference.edit();
    }
    
    public static synchronized SharedPreferenceUtils init(Context context){
        if(mUtil==null){
            mUtil=new SharedPreferenceUtils(context);
        }
        return mUtil;
    }

	public boolean isLogin() {
		return mSharedPreference.getBoolean(LOGIN, false);
	}
	public boolean isIsSetPayPwd() {
		return mSharedPreference.getBoolean(ISSetPayPwd, false);
	}

	public int getUserid() {
		return mSharedPreference.getInt(USERID, 0);
	}

	public String getUsername() {
		return mSharedPreference.getString(USERNAME, null);
	}

	public String getPassword() {
		return mSharedPreference.getString(PASSWORD, null);
	}

	public String getAvatar() {
		return mSharedPreference.getString(AVATAR, null);
	}

	public String getMobile() {
		return mSharedPreference.getString(MOBILE, null);
	}

	public String getName() {
		return mSharedPreference.getString(NAME, null);
	}

	public String getLng() {
		return mSharedPreference.getString(LNG, null);
	}

	public String getLat() {
		return mSharedPreference.getString(LAT, null);
	}
	
//	public String getMobile() {
//		return mSharedPreference.getString(MOBILE, null);
//	}
//
//	public String getMoney() {
//		return mSharedPreference.getString(MONEY, null);
//	}
//
//	public String getIntegral() {
//		return mSharedPreference.getString(INTEGRAL, null);
//	}
//
//	public String getLatitude() {
//		return mSharedPreference.getString(LATITUDE, null);
//	}
//
//	public String getLongitude() {
//		return mSharedPreference.getString(LONGITUDE, null);
//	}
//
//	public String getSign() {
//		return mSharedPreference.getString(SIGN, null);
//	}

    public void setLogin(boolean login){
        mEditor.putBoolean(LOGIN, login);
        mEditor.commit();
    }
    public void setIsSetPayPwd(boolean IsSetPayPwd){
    	mEditor.putBoolean(ISSetPayPwd, IsSetPayPwd);
    	mEditor.commit();
    }

    public void setUserid(int userid){
        mEditor.putInt(USERID, userid);
        mEditor.commit();
    }
    
    public void setUsername(String username){
        mEditor.putString(USERNAME, username);
        mEditor.commit();
    }
    
    public void setMobile(String mobile){
        mEditor.putString(MOBILE, mobile);
        mEditor.commit();
    }
    
    public void setPassword(String password){
        mEditor.putString(PASSWORD, password);
        mEditor.commit();
    }

    public void setAvatar(String avatar){
        mEditor.putString(AVATAR,avatar);
        mEditor.commit();
    }

    public void setName(String name){
        mEditor.putString(NAME,name);
        mEditor.commit();
    }

    public void setLat(String lat){
        mEditor.putString(LAT,lat);
        mEditor.commit();
    }

    public void setLng(String lng){
        mEditor.putString(LNG,lng);
        mEditor.commit();
    }
  /*  
    public String getSetNicname() {
		return setNicname;
	}

	public void setSetNicname(String setNicname) {
		this.setNicname = setNicname;
	}*/

	public void clear(){
        mEditor.clear();
    }
}