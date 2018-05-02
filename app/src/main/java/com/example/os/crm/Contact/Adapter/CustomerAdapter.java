package com.example.os.crm.Contact.Adapter;

import android.content.Context;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.BaseAdapter;
import android.widget.TextView;

import com.example.os.crm.Contact.Model.CustomerBean;
import com.example.os.crm.R;

import java.util.ArrayList;
import java.util.List;

import butterknife.BindView;
import butterknife.ButterKnife;

/**
 * Created by OS on 2018/1/16.
 */

public class CustomerAdapter extends BaseAdapter {

    private List<CustomerBean> objects = new ArrayList<CustomerBean>();

    private Context context;
    private LayoutInflater layoutInflater;

    public CustomerAdapter(Context context, List<CustomerBean> customerBeanList) {
        this.objects = customerBeanList;
        this.context = context;
        this.layoutInflater = LayoutInflater.from(context);
    }

    @Override
    public int getCount() {
        return objects.size();
    }

    @Override
    public CustomerBean getItem(int position) {
        return objects.get(position);
    }

    @Override
    public long getItemId(int position) {
        return position;
    }

    @Override
    public View getView(int position, View convertView, ViewGroup parent) {
        if (convertView == null) {
            convertView = layoutInflater.inflate(R.layout.kehudetail, null);
            convertView.setTag(new ViewHolder(convertView));
        }
        initializeViews((CustomerBean) getItem(position), (ViewHolder) convertView.getTag());
        return convertView;
    }

    private void initializeViews(CustomerBean object, ViewHolder holder) {
        holder.divi.setText(object.getKehudizhi());
        holder.kehumkig.setText(object.getKehumingcheng());
        holder.lmxirf.setText(object.getKehulianxiren());
        holder.dmhx.setText(object.getLianxirendianhua());
        holder.iljmuijm.setText(object.getDateTimestamp());
    }

    static class ViewHolder {
        @BindView(R.id.kehumkig)
        TextView kehumkig;
        @BindView(R.id.lmxirf)
        TextView lmxirf;
        @BindView(R.id.dmhx)
        TextView dmhx;
        @BindView(R.id.divi)
        TextView divi;
        @BindView((R.id.ilkmuijm))
        TextView iljmuijm;

        ViewHolder(View view) {
            ButterKnife.bind(this, view);
        }
    }
}