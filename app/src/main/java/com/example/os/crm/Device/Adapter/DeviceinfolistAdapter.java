package com.example.os.crm.Device.Adapter;

import android.content.Context;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.BaseAdapter;
import android.widget.TextView;

import com.example.os.crm.Device.Model.DeviceInfoBean;
import com.example.os.crm.R;

import java.util.ArrayList;
import java.util.List;

import butterknife.BindView;
import butterknife.ButterKnife;

public class DeviceinfolistAdapter extends BaseAdapter {

    private List<DeviceInfoBean> objects = new ArrayList<DeviceInfoBean>();

    private Context context;
    private LayoutInflater layoutInflater;

    public DeviceinfolistAdapter(Context context, List<DeviceInfoBean> deviceInfoBeans) {
        this.context = context;
        this.layoutInflater = LayoutInflater.from(context);
        this.objects = deviceInfoBeans;
    }

    @Override
    public int getCount() {
        return objects.size();
    }

    @Override
    public DeviceInfoBean getItem(int position) {
        return objects.get(position);
    }

    @Override
    public long getItemId(int position) {
        return position;
    }

    @Override
    public View getView(int position, View convertView, ViewGroup parent) {
        if (convertView == null) {
            convertView = layoutInflater.inflate(R.layout.deviceinfolist, null);
            convertView.setTag(new ViewHolder(convertView));
        }
        initializeViews((DeviceInfoBean) getItem(position), (ViewHolder) convertView.getTag());
        return convertView;
    }

    private void initializeViews(DeviceInfoBean object, ViewHolder holder) {
        holder.uebwxkhc.setText(object.getUebwxkhc());
        holder.uebwzsjx.setText(object.getUebwzsjx() + "元");
        holder.djjx.setText(object.getUebwdjjx() + "元");
        holder.uull.setText(object.getUebwuull() + "台");
        holder.jnhoriqi.setText(object.getJnhoriqi());
    }

    static class ViewHolder {
        @BindView(R.id.uebwxkhc)
        TextView uebwxkhc;
        @BindView(R.id.jnhoriqi)
        TextView jnhoriqi;
        @BindView(R.id.djjx)
        TextView djjx;
        @BindView(R.id.uull)
        TextView uull;
        @BindView(R.id.uebwzsjx)
        TextView uebwzsjx;

        ViewHolder(View view) {
            ButterKnife.bind(this, view);
        }
    }
}
