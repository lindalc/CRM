package com.example.os.crm.ui.adapter;

import java.util.ArrayList;
import java.util.List;

import android.content.Context;
import android.graphics.drawable.Drawable;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.BaseAdapter;
import android.widget.ImageView;

import com.bumptech.glide.Glide;
import com.bumptech.glide.RequestBuilder;
import com.bumptech.glide.request.RequestOptions;
import com.example.os.crm.Common.UserInfo;
import com.example.os.crm.R;

public class ImagelistitemAdapter extends BaseAdapter {

    private List<String> objects = new ArrayList<String>();

    private Context context;
    private LayoutInflater layoutInflater;

    public ImagelistitemAdapter(Context context, List<String> paymentInfoBeanList) {
        this.context = context;
        this.layoutInflater = LayoutInflater.from(context);
        this.objects = paymentInfoBeanList;
    }

    @Override
    public int getCount() {
        return objects.size();
    }

    @Override
    public String getItem(int position) {
        return objects.get(position);
    }

    @Override
    public long getItemId(int position) {
        return position;
    }

    @Override
    public View getView(int position, View convertView, ViewGroup parent) {
        if (convertView == null) {
            convertView = layoutInflater.inflate(R.layout.imagelistitem, null);
            convertView.setTag(new ViewHolder(convertView));
        }
        initializeViews((String)getItem(position), (ViewHolder) convertView.getTag());
        return convertView;
    }

    private void initializeViews(String object, ViewHolder holder) {
        RequestOptions options = new RequestOptions()
                .error(R.mipmap.loadfailed);
        Glide.with(context)
                .load(UserInfo.filehost + object.substring(5, object.length()))
                .apply(options)
                .into(holder.image);
    }

    protected class ViewHolder {
        private ImageView image;

        public ViewHolder(View view) {
            image = (ImageView) view.findViewById(R.id.image);
        }
    }
}
