package com.example.os.crm.ui.adapter;

import android.content.Context;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ArrayAdapter;
import android.widget.TextView;

import com.example.os.crm.R;
import com.example.os.crm.model.UserDetail;

import java.util.List;

/**
 * Created by OS on 2018/1/11.
 */

public class UserAdapter extends ArrayAdapter<UserDetail> {
    private int resourceId;

    public UserAdapter(Context context, int textViewResourceId, List<UserDetail> userDetailList){
        super(context, textViewResourceId, userDetailList);
        resourceId = textViewResourceId;
    }

    @Override
    public View getView(int position, View convertView, ViewGroup parent){
        UserDetail userDetail = getItem(position);
        View view = LayoutInflater.from(getContext()).inflate(resourceId,parent,false);
        TextView textView_yewuxingming = (TextView) view.findViewById(R.id.admin_yewuxingming);
        TextView textView_dingdanzonge = (TextView) view.findViewById(R.id.admin_yewuzonge);
        TextView textView_weishoukuan = (TextView) view.findViewById(R.id.admin_yewuweishou);

        textView_yewuxingming.setText(userDetail.getXingming());
        textView_dingdanzonge.setText(userDetail.getDingdanzonge());
        textView_weishoukuan.setText(userDetail.getWeishouhuokuan());
        return view;
    }
}
