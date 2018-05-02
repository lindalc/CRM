package com.example.os.crm.Charge.Activity;

import android.content.Context;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.BaseAdapter;
import android.widget.TextView;

import com.example.os.crm.Charge.Model.NewChargeBean;
import com.example.os.crm.R;

import java.util.ArrayList;
import java.util.List;

import butterknife.BindView;
import butterknife.ButterKnife;

class FwysAdapter extends BaseAdapter {
    private List<NewChargeBean> objects = new ArrayList<NewChargeBean>();

    private Context context;
    private LayoutInflater layoutInflater;

    public FwysAdapter(Context context, List<NewChargeBean> newchargeBeanList) {
        this.objects = newchargeBeanList;
        this.context = context;
        this.layoutInflater = LayoutInflater.from(context);
    }

    @Override
    public int getCount() {
        return objects.size();
    }

    @Override
    public NewChargeBean getItem(int position) {
        return objects.get(position);
    }

    @Override
    public long getItemId(int position) {
        return position;
    }

    @Override
    public View getView(int position, View convertView, ViewGroup parent) {
        if (convertView == null) {
            convertView = layoutInflater.inflate(R.layout.feiyongdetail, null);
            convertView.setTag(new ViewHolder(convertView));
        }
        initializeViews((NewChargeBean) getItem(position), (ViewHolder) convertView.getTag());
        return convertView;
    }

    private void initializeViews(NewChargeBean object, ViewHolder holder) {
        holder.shenqingren.setText(object.getUserid());
        holder.jine.setText(object.getJbee());
        holder.shenqingliyou.setText(object.getShenqingliyou());
        holder.shenqingshijian.setText(object.getShenqingshijian());
        holder.shenqingzhuangtai.setText(object.getShenqingzhuangtai());
        switch (object.getUfpi().get(object.getUfpi().size() - 1).getVltd()) {
            case 0:
                holder.shenqingzhuangtai.setText(R.string.ddufpi);
                holder.shenqingzhuangtai.setTextColor(context.getResources().getColor(R.color.grey));
                break;
            case 1:
                holder.shenqingzhuangtai.setText(R.string.yitsyi);
                holder.shenqingzhuangtai.setTextColor(context.getResources().getColor(R.color.green));
                break;
            case 2:
                holder.shenqingzhuangtai.setText(R.string.yibohv);
                holder.shenqingzhuangtai.setTextColor(context.getResources().getColor(R.color.red));
                break;
        }
    }

    static class ViewHolder {
        @BindView(R.id.shenqingzhuangtai)
        TextView shenqingzhuangtai;
        @BindView(R.id.shenqingren)
        TextView shenqingren;
        @BindView(R.id.jine)
        TextView jine;
        @BindView(R.id.shenqingshijian)
        TextView shenqingshijian;
        @BindView(R.id.shenqingliyou)
        TextView shenqingliyou;

        ViewHolder(View view) {
            ButterKnife.bind(this, view);
        }
    }
}
