package com.example.os.crm.ui.widget;

import android.content.Context;
import android.os.Bundle;
import android.view.Window;
import android.view.WindowManager;

/**
 * Created by win7 on 2016/1/25.
 */
public class BaseBanParentDialog extends BaseDialog {

    private Context context;

    public BaseBanParentDialog(Context context) {
        super(context);
        this.context = context;
    }

    public BaseBanParentDialog(Context context, int themeResId) {
        super(context, themeResId);
        this.context = context;
    }

    protected BaseBanParentDialog(Context context, boolean cancelable, OnCancelListener cancelListener) {
        super(context, cancelable, cancelListener);
        this.context = context;
    }

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setCanceledOnTouchOutside(false);

    }

    @Override
    public void show() {
        super.show();
        WindowManager windowManager = (WindowManager) context.getSystemService(Context.WINDOW_SERVICE);
        Window window = getWindow();
        // 获取对话框当前的参数值
        WindowManager.LayoutParams params = window.getAttributes();
        //高度
        params.height = WindowManager.LayoutParams.WRAP_CONTENT;
        //宽度设置为屏幕的0.9
        params.width = WindowManager.LayoutParams.MATCH_PARENT;
        window.setAttributes(params);
    }

    /**
     * 自定义Dialog监听器
     */
    public interface  PriorityListenerBan{
        /**
         * 回调函数，用于在Dialog的监听事件触发后刷新Activity的UI显示
         * @param obj
         */
        public void refreshPriorityUI(Object obj);
    }
}
