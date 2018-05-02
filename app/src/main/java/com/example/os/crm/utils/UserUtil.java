package com.example.os.crm.utils;

import android.content.Context;
import android.content.SharedPreferences;

import com.example.os.crm.model.UserDetail;
import com.google.gson.Gson;
import com.google.gson.reflect.TypeToken;

import java.util.ArrayList;
import java.util.List;

/**
 ******************************************************
 *                                                    *
 *                                                    *
 *                       _oo0oo_                      *
 *                      o8888888o                     *
 *                      88" . "88                     *
 *                      (| -_- |)                     *
 *                      0\  =  /0                     *
 *                    ___/`---'\___                   *
 *                  .' \\|     |# '.                  *
 *                 / \\|||  :  |||# \                 *
 *                / _||||| -:- |||||- \               *
 *               |   | \\\  -  #/ |   |               *
 *               | \_|  ''\---/''  |_/ |              *
 *               \  .-\__  '-'  ___/-. /              *
 *             ___'. .'  /--.--\  `. .'___            *
 *          ."" '<  `.___\_<|>_/___.' >' "".          *
 *         | | :  `- \`.;`\ _ /`;.`/ - ` : | |        *
 *         \  \ `_.   \_ __\ /__ _/   .-` /  /        *
 *     =====`-.____`.___ \_____/___.-`___.-'=====     *
 *                       `=---='                      *
 *                                                    *
 *                                                    *
 *     ~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~    *
 *                                                    *
 *               佛祖保佑         永无BUG              *
 *                                                    *
 *                                                    *
 ******************************************************
 *
 * Created by ninos on 2016/6/2.
 *
 */
public class UserUtil {

    private Context context;

    public UserUtil(Context context) {
        this.context = context;
    }

    public String getUserInfo() {
        SharedPreferences share = context.getSharedPreferences("userinfo", 0);
        String userInfo = share.getString("userInfo", "");
        return userInfo;
    }

    public String getSharedPreferences(String str) {
        SharedPreferences share = context.getSharedPreferences(str, 0);
        String userInfo = share.getString(str,"");
        return userInfo;
    }

    public void putUserInfoStr(String userInfoStr) {
        SharedPreferences share = context.getSharedPreferences("userinfo", 0);
        SharedPreferences.Editor editor = share.edit();
        editor.putString("userInfo", userInfoStr);
        editor.commit();
    }

    public void putUser(UserDetail user) {
        SharedPreferences share = context.getSharedPreferences("userinfo", 0);
        SharedPreferences.Editor editor = share.edit();
        editor.putString("userInfo", new Gson().toJson(user));
        editor.commit();
    }


    public void putSharedPreferences(String str,String str1) {
        SharedPreferences share = context.getSharedPreferences(str, 0);
        SharedPreferences.Editor editor = share.edit();
        editor.putString(str, str1);
        editor.commit();
    }


    public void removeUserInfo() {
        SharedPreferences share = context.getSharedPreferences("userinfo", 0);
        SharedPreferences.Editor editor = share.edit();
        editor.putString("userInfo", "");
        editor.commit();
    }

    public UserDetail getUser() {
        SharedPreferences share = context.getSharedPreferences("userinfo", 0);
        String userInfo = share.getString("userInfo", "");
        if ("".equals(userInfo)) {
            return null;
        } else {
            Gson gson = new Gson();
            return gson.fromJson(userInfo, UserDetail.class);
        }
    }


    public int getUserId() {
        UserDetail user = getUser();
        if (user != null) {
            return user.getId();
        }
        return 0;
    }


    /**
     * @return
     */
    public boolean isLogin() {
        return StringUtil.isEmpty(getUserInfo()) ? false : true;
    }

}