package com.example.os.crm.utils;

import android.app.Dialog;
import android.content.Context;
import android.view.View;
import android.view.View.OnClickListener;
import android.view.Window;
import android.widget.TextView;

import com.example.os.crm.R;


/**
 * @author  lc 
 * @date 创建时间：2016-4-25 下午4:47:12 
 * @version 1.0 
 * @Description
 */
public class TwoButtonDialog {
    Context context;
    Dialog  ad;

    public TextView txt_queding,txt_quexiao;
    public TextView txt_content;

    public TwoButtonDialog(Context context) {
        this.context=context;
//        ad=new Dialog.Builder(context).create();
        ad = new Dialog(context);
        ad.requestWindowFeature(Window.FEATURE_NO_TITLE);
        ad.show();
        //关键在下面的两行,使用window.setContentView,替换整个对话框窗口的布局
        Window window = ad.getWindow();
        window.setContentView(R.layout.dialog_twobutton);
        window.setBackgroundDrawableResource(R.drawable.border_white_10dp);
        ad.setCancelable(false);
        
        txt_queding = (TextView) window.findViewById(R.id.dialog_notic_confirm);
        txt_quexiao = (TextView) window.findViewById(R.id.dialog_notic_cancel);
        txt_content = (TextView) window.findViewById(R.id.dialog_notic_text);
        
        txt_quexiao.setOnClickListener(new OnClickListener() {
			
			@Override
			public void onClick(View v) {	
				ad.dismiss();
			}
		});
    }

    public void setText(String str){
        txt_content.setText(str);
    }
    
    /**
     * 关闭对话框
     */
    public void dismiss() {
        ad.dismiss();
    } 
    
    
}
