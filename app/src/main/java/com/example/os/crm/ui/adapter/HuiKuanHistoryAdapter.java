package com.example.os.crm.ui.adapter;

import android.content.Context;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ArrayAdapter;
import android.widget.TextView;

import com.example.os.crm.model.HvKrDkDjBean;
import com.example.os.crm.R;

import java.util.List;

/**
 * Created by OS on 2018/1/11.
 */

public class HuiKuanHistoryAdapter extends ArrayAdapter<HvKrDkDjBean>{

    private int resourceId;

    public HuiKuanHistoryAdapter(Context context, int textViewResourceId, List<HvKrDkDjBean> hvKrDkDjBeans){
        super(context, textViewResourceId, hvKrDkDjBeans);
        resourceId = textViewResourceId;
    }

    @Override
    public View getView(int position, View convertView, ViewGroup parent){

        HvKrDkDjBean hvKrDkDjBean = getItem(position);
        View view = LayoutInflater.from(getContext()).inflate(resourceId,parent,false);
        TextView tv_kehumingcheng = (TextView) view.findViewById(R.id.hkh_kehumingcheng);
        tv_kehumingcheng.setText(hvKrDkDjBean.getDkdj().getKehuziln().getKehumkig());
        TextView tv_dingdanbianhao = (TextView) view.findViewById(R.id.hkh_dingdanbianhao);
        tv_dingdanbianhao.setText(hvKrDkDjBean.getDkdj().getDkdjxbxi().getDkdjbmhc());
        TextView tv_zhibaojin = (TextView) view.findViewById(R.id.hkh_zhibaojin);
        tv_zhibaojin.setText("(含质保金"+ hvKrDkDjBean.getDkdj().getQm().getVibcjb() + ")");
        if(hvKrDkDjBean.getHvkr().getWwuz() == 0){
            tv_zhibaojin.setVisibility(View.INVISIBLE);
        }
        TextView tv_yingshou = (TextView) view.findViewById(R.id.hkh_yingshou);
        tv_yingshou.setText(hvKrDkDjBean.getDkdj().getQm().getUebwzsjx());
        TextView tv_weishou = (TextView) view.findViewById(R.id.hkh_weishou);
        tv_weishou.setText(hvKrDkDjBean.getHvkr().getWwuz() + "");
        return view;
    }

}
