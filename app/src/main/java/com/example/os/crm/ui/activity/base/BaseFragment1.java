package com.example.os.crm.ui.activity.base;

import android.app.Activity;
import android.content.Context;
import android.content.Intent;
import android.graphics.Color;
import android.os.Bundle;
import android.support.annotation.Nullable;
import android.support.design.widget.Snackbar;
import android.support.v4.app.Fragment;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.view.inputmethod.InputMethodManager;
import android.widget.Toast;

import com.example.os.crm.R;
import com.example.os.crm.presenter.base.BasePresenterImp;
import com.example.os.crm.ui.view.base.BaseView;

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
public abstract class BaseFragment1<P extends BasePresenterImp> extends Fragment implements BaseView {

    public P presenter;

    /**
     * 初始化事件监听者
     */
    abstract public void initListeners();

    /**
     * 初始化一些事情
     */
    protected abstract void initThings(View view);

    /**
     * @return 提供LayoutId
     */
    abstract public int provideLayoutId();

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

    public void toast(String text) {
        Toast.makeText(getActivity(), text, Toast.LENGTH_SHORT).show();
    }

    public void snb(String text, View view) {
        Snackbar.make(view, text, Snackbar.LENGTH_SHORT).show();
    }

    public void snb(String text, View view, String actionText, View.OnClickListener action) {
        Snackbar.make(view, text, Snackbar.LENGTH_SHORT).setAction(actionText, action).show();
    }

    View view;

    @Nullable
    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container, Bundle savedInstanceState) {
        view = inflater.inflate(provideLayoutId(), container, false);
        ButterKnife.bind(this, view);
        this.presenter = createPresenter();
        if (presenter != null) {
            presenter.attachView(this);
        }

        initThings(view);
        initListeners();

        return view;
    }

    @Override
    public void onResume() {
        super.onResume();


    }



    @Override
    public void startActivity(Class clazz) {
        startActivity(new Intent(getContext(), clazz));
    }

    @Override
    public void startActivityForResult(Class clazz, int requestCode) {
        Intent intent = new Intent(getContext(), clazz);
        startActivityForResult(intent, requestCode);
    }

    @Override
    public void startActivity(Class clazz, Bundle bundle) {
        startActivity(new Intent(getContext(), clazz).putExtra("data", bundle));
    }


    @Override
    public void showSoftInput(View v) {
        InputMethodManager inputMethodManager = (InputMethodManager) getContext().getSystemService(Activity.INPUT_METHOD_SERVICE);
        inputMethodManager.showSoftInput(v, InputMethodManager.SHOW_FORCED);
    }


    @Override
    public void hideSoftMethod(View v) {
        InputMethodManager inputMethodManager = (InputMethodManager) getContext().getSystemService(Activity.INPUT_METHOD_SERVICE);
        inputMethodManager.hideSoftInputFromWindow(v.getWindowToken(), InputMethodManager.HIDE_NOT_ALWAYS);

    }



    @Override
    public void startActivityForResult(Class clazz, Bundle bundle, int requestCode) {
        Intent intent = new Intent(getContext(), clazz);
        intent.putExtra("data", bundle);
        startActivityForResult(intent, requestCode);

    }


    @Override
    public void onDestroy() {
        super.onDestroy();
        killPresenter();
    }

    @Override
    public void finishActivity() {
        getActivity().finish();
    }


    @Override
    public Context getContext() {
        return getActivity();
    }
}
