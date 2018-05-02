package com.example.os.crm.ui.activity.base;

import android.content.Context;
import android.graphics.Bitmap;

import com.example.os.crm.utils.SharedPreferenceUtils;


/**
 * @author csh
 * @date 2016年5月14日
 * 登录用户信息
 */
public class AppModel {
	
    /**
     * 当前帐号是否已经登录的标识
     */
	private boolean login = false;
    private int userid;//用户Id
    private String username;//登录账号
    private String password;//登录密码
    private String avatar;//头像
    private String mobile;//手机
    private String name;//手机
    private String lat;//
    private String lng;//
	private String Token;
	private Bitmap code;
    private boolean IsSetPayPwd = false;//是否设置支付密码

	public Bitmap getCode() {
		return code;
	}

	public void setCode(Bitmap code) {
		this.code = code;
	}

	private static SharedPreferenceUtils utils;
    
    public static AppModel init(Context context){
        AppModel model =new AppModel();
        utils = SharedPreferenceUtils.init(context);

        model.login= utils.isLogin();
        model.IsSetPayPwd= utils.isIsSetPayPwd();
        model.userid= utils.getUserid();

        if(utils.getUsername()!=null){
            model.username = utils.getUsername();
        }

        if(utils.getMobile() != null){
            model.mobile= utils.getMobile();
        }

        if(utils.getPassword() != null){
            model.password= utils.getPassword();
        }

        if(utils.getAvatar() != null){
            model.avatar= utils.getAvatar();
        }

        if(utils.getName() != null){
            model.name= utils.getName();
        }

        if(utils.getLat() != null){
            model.lat= utils.getLat();
        }

        if(utils.getLng() != null){
            model.lng= utils.getLng();
        }

        return model;
    }


	public boolean isLogin() {
		return login;
	}
	public boolean isIsSetPayPwd() {
		return IsSetPayPwd;
	}
	
	public void setLogin(boolean login) {
		this.login = login;
        utils.setLogin(login);
	}
	public void setIsSetPayPwd(boolean IsSetPayPwd) {
		this.IsSetPayPwd = IsSetPayPwd;
        utils.setIsSetPayPwd(IsSetPayPwd);
	}
	public int getUserid() {
		return userid;
	}
	public void setUserid(int userid) {
		this.userid = userid;
        utils.setUserid(userid);
	}
	public String getUsername() {
		return username;
	}
	public void setUsername(String username) {
		this.username = username;
        utils.setUsername(username);
	}
	public String getMobile() {
		return mobile;
	}
	public void setMobile(String mobile) {
		this.mobile = mobile;
        utils.setMobile(mobile);
	}
	public String getPassword() {
		return password;
	}
	public void setPassword(String password) {
		this.password = password;
        utils.setPassword(password);
	}
	public String getAvatar() {
		return avatar;
	}
	public void setAvatar(String avatar) {
		this.avatar = avatar;
        utils.setAvatar(avatar);
	}
	public String getName() {
		return name;
	}
	public void setName(String name) {
		this.name = name;
        utils.setName(name);
	}
	public String getLat() {
		return lat;
	}
	public void setLat(String lat) {
		this.lat = lat;
        utils.setLat(lat);
	}
	public String getLng() {
		return lng;
	}
	public void setLng(String lng) {
		this.lng = lng;
		utils.setLng(lng);
	}

	public String getToken() {
		return Token;
	}

	public void setToken(String token) {
		Token = token;
	}

	public void clear(){
		this.setLogin(false);
		this.setIsSetPayPwd(false);
		this.setUserid(0);
		this.setUsername("");
		this.setMobile("");
		this.setPassword("");
		this.setAvatar("");
		this.setName("");
		
		utils.clear();
	}
}