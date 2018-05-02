package com.example.os.crm.Dingdan.Adapter;

import android.content.Context;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.BaseAdapter;
import android.widget.TextView;

import com.bumptech.glide.Glide;
import com.example.os.crm.Common.UserInfo;
import com.example.os.crm.Contact.Model.Contact;
import com.example.os.crm.Contact.Model.ContactBean;
import com.example.os.crm.Dingdan.Model.DkDjDetailInfoBean;
import com.example.os.crm.R;
import com.xyz.step.FlowViewHorizontal;

import java.util.ArrayList;
import java.util.List;

import butterknife.BindView;
import butterknife.ButterKnife;


public class DkDjListAdapter extends BaseAdapter {

    private List<DkDjDetailInfoBean> objects = new ArrayList<DkDjDetailInfoBean>();

    private Context context;
    private LayoutInflater layoutInflater;

    public DkDjListAdapter(Context context, List<DkDjDetailInfoBean> itemDetailList) {
        this.context = context;
        this.layoutInflater = LayoutInflater.from(context);
        this.objects = itemDetailList;
    }

    @Override
    public int getCount() {
        return objects.size();
    }

    @Override
    public DkDjDetailInfoBean getItem(int position) {
        return objects.get(position);
    }

    @Override
    public long getItemId(int position) {
        return position;
    }

    @Override
    public View getView(int position, View convertView, ViewGroup parent) {
        if (convertView == null) {
            convertView = layoutInflater.inflate(R.layout.itemdetail, null);
            convertView.setTag(new ViewHolder(convertView));
        }
        initializeViews((DkDjDetailInfoBean) getItem(position), (ViewHolder) convertView.getTag());
        return convertView;
    }

    private void initializeViews(DkDjDetailInfoBean object, ViewHolder holder) {



        holder.hisDiandanbianhao.setText(object.getDkdjxbxi().getDkdjbmhc());
        holder.hisDiandanzonge.setText(object.getQm().getUebwzsjx());
        holder.hisKehumingcheng.setText(object.getKehuziln().getKehumkig());
        holder.yewuyuan.setText(object.getDkdjxbxi().getYewuyr());

        switch (object.getVltd().getUfpi()) {
            case 0:
                holder.ufpi.setText(R.string.ddufpi);
                holder.ufpi.setTextColor(context.getResources().getColor(R.color.grey));
                break;
            case 1:
                holder.ufpi.setText(R.string.yitsyi);
                holder.ufpi.setTextColor(context.getResources().getColor(R.color.green));
                break;
            case 2:
                holder.ufpi.setText(R.string.yibohv);
                holder.ufpi.setTextColor(context.getResources().getColor(R.color.red));
                break;
        }

        switch (object.getVltd().getWjig()) {
            case 0:
                holder.wjig.setText(R.string.wwwjig);
                holder.wjig.setTextColor(context.getResources().getColor(R.color.grey));
                break;
            case 1:
                holder.wjig.setText(R.string.wjig);
                holder.wjig.setTextColor(context.getResources().getColor(R.color.green));
                break;
            case 2:
                holder.wjig.setText(R.string.jibfwjig);
                holder.wjig.setTextColor(context.getResources().getColor(R.color.colorPrimaryDark));
                break;
            case 3:
                holder.wjig.setText(R.string.jibfwjig);
                holder.wjig.setTextColor(context.getResources().getColor(R.color.red));
                break;
        }
        switch (object.getVltd().getWjvg()) {
            case 0:
                holder.wjvg.setText(R.string.buwjvg);
                holder.wjvg.setTextColor(context.getResources().getColor(R.color.red));
                break;
            case 1:
                holder.wjvg.setText(R.string.wjvg);
                holder.wjvg.setTextColor(context.getResources().getColor(R.color.green));
                break;
        }
        int size = object.getUfpi().size();
        String[] name = new String[size + 1];
        String[] time = new String[size + 1];
        int progress = 1;
        int maxstep;
        name[0] = getXkmk(object.getDkdjxbxi().getFuzeyewu());
        time[0] = object.getDkdjxbxi().getTijnuijm();
        for (int i = 0; i< size; i++){
            DkDjDetailInfoBean.UfpiBean ufpiBean = object.getUfpi().get(i);
            name[i+1] = ufpiBean.getXkmk();
            time[i+1] = ufpiBean.getUijm();
            if (ufpiBean.getVltd() != 0){
                progress = i + 2;
            }
        }
        maxstep = size + 1;
        holder.stepView.setProgress(progress, maxstep, name, time);

    }

    static class ViewHolder {
        @BindView(R.id.his_diandanbianhao)
        TextView hisDiandanbianhao;
        @BindView(R.id.ufpi)
        TextView ufpi;
        @BindView(R.id.wjvg)
        TextView wjvg;
        @BindView(R.id.wjig)
        TextView wjig;
        @BindView(R.id.his_diandanzonge)
        TextView hisDiandanzonge;
        @BindView(R.id.his_kehumingcheng)
        TextView hisKehumingcheng;
        @BindView(R.id.yewuyuan)
        TextView yewuyuan;
        @BindView(R.id.stepview)
        FlowViewHorizontal stepView;

        ViewHolder(View view) {
            ButterKnife.bind(this, view);
        }
    }

    private String getXkmk(String userid){
        Contact contact = Contact.getInstance();
        ContactBean contactBean = contact.getContact(userid);
        return contactBean.getJibf().getXkmk();
    }
}
