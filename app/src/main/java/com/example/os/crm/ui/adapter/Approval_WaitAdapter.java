package com.example.os.crm.ui.adapter;

import android.content.Context;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ArrayAdapter;
import android.widget.TextView;

import com.example.os.crm.R;
import com.example.os.crm.model.Approval;

import java.util.List;

/**
 * Created by OS on 2018/1/12.
 */

public class Approval_WaitAdapter extends ArrayAdapter<Approval>{

    private int resourceId;

    public Approval_WaitAdapter(Context context, int textViewResourceId, List<Approval> approvalList){
        super(context, textViewResourceId, approvalList);
        resourceId = textViewResourceId;
    }

    @Override
    public View getView(int position, View convertView, ViewGroup parent){
        Approval approval = getItem(position);
        View view = LayoutInflater.from(getContext()).inflate(resourceId,parent,false);

        TextView textView_leixing = (TextView) view.findViewById(R.id.leixing);
        textView_leixing.setTextColor(getContext().getResources().getColor(R.color.black));
        TextView textView_jine_type = (TextView) view.findViewById(R.id.daishenpi_jine_type);
        TextView vltd = (TextView) view.findViewById(R.id.vltd);
        switch(approval.getType()){
            case 0:
                textView_leixing.setText("新订单");
                textView_jine_type.setText("订单总额");
                break;
            case 1:
                textView_leixing.setText("新回款");
                textView_jine_type.setText("回款金额");
                break;
            default:
                break;
        }
        switch (approval.getVltd()){
            case 0:
                vltd.setText("未审批");
                vltd.setTextColor(getContext().getResources().getColor(R.color.grey));
                break;
            case 1:
                vltd.setText("已同意");
                vltd.setTextColor(getContext().getResources().getColor(R.color.green));
                break;
            case 2:
                vltd.setText("已驳回");
                vltd.setTextColor(getContext().getResources().getColor(R.color.red));
                break;
            default:
                break;
        }
        TextView textView_dingdanbianhao = (TextView) view.findViewById(R.id.daishenpi_dingdanbianhao);
        textView_dingdanbianhao.setText(approval.getDingdanbianhao());
        TextView textView_yewuxingming = (TextView) view.findViewById(R.id.daishenpi_yewuxingming);
        textView_yewuxingming.setText(approval.getYewuxingming());
        TextView textView_kehumingcheng = (TextView) view.findViewById(R.id.daishenpi_kehumingcheng);
        textView_kehumingcheng.setText(approval.getKehumingcheng());
        TextView textView_daishenpijine = (TextView) view.findViewById(R.id.daishenpi_jie);
        textView_daishenpijine.setText(approval.getDingdanzonge());
        TextView textView_riqi = (TextView) view.findViewById(R.id.riqi);
        textView_riqi.setText(approval.getRiqi());
        return view;
    }
}
