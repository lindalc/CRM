package com.example.os.crm.Contract.Adapter;

import java.util.ArrayList;
import java.util.List;

import android.content.Context;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.BaseAdapter;
import android.widget.TextView;

import com.example.os.crm.Contract.Model.ContractDetailBean;
import com.example.os.crm.R;

public class HeTongAdapter extends BaseAdapter {

    private List<ContractDetailBean> objects = new ArrayList<ContractDetailBean>();

    private Context context;
    private LayoutInflater layoutInflater;

    public HeTongAdapter(Context context, List<ContractDetailBean> contractDetailBeanList) {
        this.context = context;
        this.layoutInflater = LayoutInflater.from(context);
        this.objects = contractDetailBeanList;
    }

    @Override
    public int getCount() {
        return objects.size();
    }

    @Override
    public ContractDetailBean getItem(int position) {
        return objects.get(position);
    }

    @Override
    public long getItemId(int position) {
        return position;
    }

    @Override
    public View getView(int position, View convertView, ViewGroup parent) {
        if (convertView == null) {
            convertView = layoutInflater.inflate(R.layout.hetongitem, null);
            convertView.setTag(new ViewHolder(convertView));
        }
        initializeViews((ContractDetailBean)getItem(position), (ViewHolder) convertView.getTag());
        return convertView;
    }

    private void initializeViews(ContractDetailBean object, ViewHolder holder) {
        holder.dingdanbianhao.setText(object.getDingdanbianhao());
        holder.dingdanzonge.setText(object.getDingdanzonge());
        holder.qiandingriqi.setText(object.getQiandingriqi());
        holder.kehumingcheng.setText(object.getKehumingcheng());
        holder.hetongbianhao.setText(object.getHetongbianhao());
    }

    protected class ViewHolder {
        private TextView dingdanbianhao;
        private TextView hetongbianhao;
        private TextView qiandingriqi;
        private TextView kehumingcheng;
        private TextView dingdanzonge;

        public ViewHolder(View view) {
            dingdanbianhao = (TextView) view.findViewById(R.id.dingdanbianhao);
            hetongbianhao = (TextView) view.findViewById(R.id.hetongbianhao);
            qiandingriqi = (TextView) view.findViewById(R.id.qiandingriqi);
            kehumingcheng = (TextView) view.findViewById(R.id.kehumingcheng);
            dingdanzonge = (TextView) view.findViewById(R.id.dingdanzonge);
        }
    }
}
