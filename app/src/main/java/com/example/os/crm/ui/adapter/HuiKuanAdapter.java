package com.example.os.crm.ui.adapter;

import android.content.Context;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ArrayAdapter;
import android.widget.TextView;


import com.example.os.crm.model.HuiKuanBean;
import com.example.os.crm.R;

import java.util.List;

/**
 * Created by OS on 2018/1/10.
 */


public class HuiKuanAdapter extends ArrayAdapter<HuiKuanBean.HvkrliuiBean> {

    private int resourceId;

    public HuiKuanAdapter(Context context, int textViewResourceId, List<HuiKuanBean.HvkrliuiBean> huiKuanBean){
        super(context, textViewResourceId, huiKuanBean);
        resourceId = textViewResourceId;
    }

    @Override
    public View getView(int position, View convertView, ViewGroup parent){

        HuiKuanBean.HvkrliuiBean huiKuanBean = getItem(position);
        View view = LayoutInflater.from(getContext()).inflate(resourceId,parent,false);
        TextView textView_huikuanriqi = (TextView) view.findViewById(R.id.tv_huikuanriqi);
        TextView textView_huikuanjine = (TextView) view.findViewById(R.id.tv_huikuanjine);
        TextView textView_huikuanshenpi = (TextView) view.findViewById(R.id.tv_huikuanshenpi);
        textView_huikuanriqi.setText(huiKuanBean.getRiqi());
        textView_huikuanjine.setText(huiKuanBean.getJbee());
        textView_huikuanshenpi.setText("");
        List<HuiKuanBean.HvkrliuiBean.UfpiBean> ufpiBeanList = huiKuanBean.getUfpi();
        switch (ufpiBeanList.get(ufpiBeanList.size()-1).getVltd()){
            case 0:
                textView_huikuanshenpi.setText("未审批");
                textView_huikuanshenpi.setTextColor(getContext().getResources().getColor(R.color.grey));
                break;
            case 1:
                textView_huikuanshenpi.setText("已同意");
                textView_huikuanshenpi.setTextColor(getContext().getResources().getColor(R.color.green));
                break;
            case 2:
                textView_huikuanshenpi.setText("已驳回");
                textView_huikuanshenpi.setTextColor(getContext().getResources().getColor(R.color.red));
                break;
        }
        return view;
    }
}
