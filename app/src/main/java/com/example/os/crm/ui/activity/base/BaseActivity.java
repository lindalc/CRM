package com.example.os.crm.ui.activity.base;

import android.app.Activity;
import android.content.Context;
import android.content.Intent;
import android.content.SharedPreferences;
import android.content.res.AssetManager;
import android.graphics.Color;
import android.graphics.Typeface;
import android.os.Build;
import android.os.Bundle;
import android.os.Handler;
import android.support.design.widget.Snackbar;
import android.support.v4.view.LayoutInflaterCompat;
import android.support.v4.view.LayoutInflaterFactory;
import android.support.v7.app.AppCompatDelegate;
import android.support.v7.widget.AppCompatTextView;
import android.util.AttributeSet;
import android.view.LayoutInflater;
import android.view.View;
import android.view.Window;
import android.view.inputmethod.InputMethodManager;
import android.widget.EditText;
import android.widget.TextView;
import android.widget.Toast;
import android.widget.ToggleButton;

import com.bumptech.glide.Glide;
import com.example.os.crm.presenter.base.BasePresenterImp;
import com.example.os.crm.R;
import com.zhy.autolayout.AutoLayoutActivity;
import com.example.os.crm.ui.view.base.BaseView;
import com.example.os.crm.ui.widget.AppValue;

import java.lang.reflect.Field;
import java.lang.reflect.Method;

import butterknife.ButterKnife;

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
public abstract class BaseActivity<P extends BasePresenterImp> extends AutoLayoutActivity implements BaseView {

    protected Intent intent;

    public P presenter;

    private SharedPreferences defalut_sp;
    private SharedPreferences appoint_sp;
    /**
     * @return 提供LayoutId
     */
    abstract protected int provideContentViewId();


    /**
     * 初始化事件监听者
     */
    public abstract void initListeners();

    /**
     * @param savedInstanceState 缓存数据
     *                           <p>
     *                           初始化一些事情
     */
    protected abstract void initThings(Bundle savedInstanceState);

    /**
     * 绑定Presenter
     */
    public abstract P createPresenter();

    /**
     * 解绑Presenter
     */
    public void killPresenter() {
        if (presenter != null) {
            presenter.detachView();
        }
    }

    public static Typeface typeface;


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        if (savedInstanceState != null) {
            savedInstanceState.putParcelable("android:support:fragments", null);
        }

        super.onCreate(savedInstanceState);
        beforeSetCView(savedInstanceState);
        setContentView(provideContentViewId());
        ButterKnife.bind(this);
        defalut_sp = getSharedPreferences(AppValue.config, Context.MODE_PRIVATE);


//        if (Build.VERSION.SDK_INT >= Build.VERSION_CODES.M) {
//            getWindow().getDecorView().setSystemUiVisibility( View.SYSTEM_UI_FLAG_LAYOUT_FULLSCREEN|View.SYSTEM_UI_FLAG_LIGHT_STATUS_BAR);
//        }
//
//        MIUISetStatusBarLightMode(this,true);

        this.presenter = createPresenter();
        if (presenter != null) {
            presenter.attachView(this);
        }

        initThings(savedInstanceState);
        initListeners();
    }

    /**
     * 需要MIUIV6以上
     * @param activity
     * @param dark 是否把状态栏字体及图标颜色设置为深色
     * @return  boolean 成功执行返回true
     *
     */
    public static boolean MIUISetStatusBarLightMode(Activity activity, boolean dark) {
        boolean result = false;
        Window window=activity.getWindow();
        if (window != null) {
            Class clazz = window.getClass();
            try {
                int darkModeFlag = 0;
                Class layoutParams = Class.forName("android.view.MiuiWindowManager$LayoutParams");
                Field field = layoutParams.getField("EXTRA_FLAG_STATUS_BAR_DARK_MODE");
                darkModeFlag = field.getInt(layoutParams);
                Method extraFlagField = clazz.getMethod("setExtraFlags", int.class, int.class);
                if(dark){
                    extraFlagField.invoke(window,darkModeFlag,darkModeFlag);//状态栏透明且黑色字体
                }else{
                    extraFlagField.invoke(window, 0, darkModeFlag);//清除黑色字体
                }
                result=true;

                if (Build.VERSION.SDK_INT >= Build.VERSION_CODES.M) {
                    //开发版 7.7.13 及以后版本采用了系统API，旧方法无效但不会报错，所以两个方式都要加上
                    if(dark){
                        activity.getWindow().getDecorView().setSystemUiVisibility( View.SYSTEM_UI_FLAG_LAYOUT_FULLSCREEN| View.SYSTEM_UI_FLAG_LIGHT_STATUS_BAR);
                    }else {
                        activity.getWindow().getDecorView().setSystemUiVisibility(View.SYSTEM_UI_FLAG_VISIBLE);
                    }
                }
            }catch (Exception e){

            }
        }
        return result;
    }

    public String getSharedPreferenceValue(String key) {
        String value = defalut_sp.getString(key, "");
        return value;
    }

    public void putSharedPreferenceValue(String key, String value) {
        SharedPreferences.Editor editor;
        editor = defalut_sp.edit();
        editor.putString(key, value);
        editor.commit();
    }

    public String getSharedPreferenceValue(String tag, String key) {
        appoint_sp = getSharedPreferences(tag, Context.MODE_PRIVATE);
        String value = appoint_sp.getString(key, "");
        return value;
    }

    public void putSharedPreferenceValue(String tag, String key, String value) {
        appoint_sp = getSharedPreferences(tag, 0);
        SharedPreferences.Editor editor;
        editor = appoint_sp.edit();
        editor.putString(key, value);
        editor.commit();
    }

    public void beforeSetCView(Bundle savedInstanceState) {


    }

    @Override
    public Context getContext() {
        return this;
    }

    public void toast(String text) {
        Toast.makeText(this, text, Toast.LENGTH_SHORT).show();
    }

    public void snb(String text, View view) {
        Snackbar.make(view, text, Snackbar.LENGTH_SHORT).show();
    }

    public void snb(String text, View view, String actionText, View.OnClickListener action) {
        Snackbar.make(view, text, Snackbar.LENGTH_SHORT).setAction(actionText, action).show();
    }


    @Override
    public void showSoftInput(View v) {
        InputMethodManager inputMethodManager = (InputMethodManager) getSystemService(INPUT_METHOD_SERVICE);
        inputMethodManager.showSoftInput(v, InputMethodManager.SHOW_FORCED);
    }


    @Override
    public void hideSoftMethod(View v) {
        InputMethodManager inputMethodManager = (InputMethodManager) getSystemService(INPUT_METHOD_SERVICE);
        inputMethodManager.hideSoftInputFromWindow(v.getWindowToken(), InputMethodManager.RESULT_UNCHANGED_SHOWN);

    }

    @Override
    protected void onDestroy() {
        super.onDestroy();
        killPresenter();
        Glide.get(getContext()).clearMemory();
        System.gc();

    }



    @Override
    public void startActivity(Class clazz) {
        startActivity(new Intent(getContext(), clazz));
    }

    @Override
    public void startActivity(Class clazz, Bundle bundle) {
        startActivity(new Intent(getContext(), clazz).putExtra("data", bundle));
    }

    @Override
    public void startActivityForResult(Class clazz, int requestCode) {
        startActivityForResult(new Intent(getContext(), clazz), requestCode);
    }

    @Override
    public void startActivityForResult(Class clazz, Bundle bundle, int requestCode) {
        Intent intent = new Intent(getContext(), clazz);
        intent.putExtra("data", bundle);
        startActivityForResult(intent, requestCode);
    }


    public Bundle getIntentData() {
        Bundle data = getIntent().getBundleExtra("data");
        return data == null ? new Bundle() : data;
    }

    @Override
    public void finishActivity() {
        this.finish();
    }

    @Override
    protected void onResume() {
        super.onResume();

    }

    @Override
    protected void onPause() {
        super.onPause();
    }

}
