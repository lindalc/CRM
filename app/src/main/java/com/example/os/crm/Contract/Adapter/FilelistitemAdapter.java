package com.example.os.crm.Contract.Adapter;

import java.util.ArrayList;
import java.util.List;

import android.content.Context;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.BaseAdapter;
import android.widget.ImageView;
import android.widget.TextView;

import com.bumptech.glide.Glide;
import com.example.os.crm.Contract.Model.DocFile;
import com.example.os.crm.R;

public class FilelistitemAdapter extends BaseAdapter {

    private List<DocFile> objects = new ArrayList<DocFile>();

    private Context context;
    private LayoutInflater layoutInflater;

    public FilelistitemAdapter(Context context, List<DocFile> objectlist) {
        this.context = context;
        this.layoutInflater = LayoutInflater.from(context);
        this.objects = objectlist;
    }

    @Override
    public int getCount() {
        return objects.size();
    }

    @Override
    public DocFile getItem(int position) {
        return objects.get(position);
    }

    @Override
    public long getItemId(int position) {
        return position;
    }

    @Override
    public View getView(int position, View convertView, ViewGroup parent) {
        if (convertView == null) {
            convertView = layoutInflater.inflate(R.layout.filelistitem, null);
            convertView.setTag(new ViewHolder(convertView));
        }
        initializeViews((DocFile)getItem(position), (ViewHolder) convertView.getTag());
        return convertView;
    }

    private void initializeViews(DocFile object, ViewHolder holder) {
//        holder.fileIcon.setImageResource(object.getImageId());
        Log.i("FilelistitemAdapter", "initializeViews: "+ object.getUrl());
        String ext;
        ext = object.getExt();
        if (ext.equals("jpg") || ext.equals("JPG") || ext.equals("JPEG") || ext.equals("jpeg")){
            Glide.with(context)
                    .load(object.getUrl())
                    .into(holder.fileIcon);
        }
        else if(ext.equals("mp4")){
            holder.fileIcon.setImageResource(R.mipmap.movie);
        }
        else if(ext.equals("doc") || ext.equals("docx")){
            holder.fileIcon.setImageResource(R.mipmap.word);
        }
        else if (ext.equals("xls") || ext.equals("xlsx")){
            holder.fileIcon.setImageResource(R.mipmap.excel);
        }
        else{
            holder.fileIcon.setImageResource(R.mipmap.file);
        }

        holder.fileName.setText(object.getName());
    }

    protected class ViewHolder {
        private ImageView fileIcon;
        private TextView fileName;

        public ViewHolder(View view) {
            fileIcon = (ImageView) view.findViewById(R.id.file_icon);
            fileName = (TextView) view.findViewById(R.id.file_name);
        }
    }
}
