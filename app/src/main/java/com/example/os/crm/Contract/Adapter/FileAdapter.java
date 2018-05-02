package com.example.os.crm.Contract.Adapter;

import android.support.v7.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;
import android.widget.TextView;

import com.example.os.crm.Contract.Model.DocFile;
import com.example.os.crm.R;

import java.util.List;

/**
 * Created by OS on 2018/1/27.
 */

public class FileAdapter extends RecyclerView.Adapter<FileAdapter.ViewHolder> {

    private List<DocFile> docFileList;

    static class ViewHolder extends RecyclerView.ViewHolder {
        ImageView imageView;
        ImageView imageView_del;
        TextView textView;

        public ViewHolder(View view) {
            super(view);
            imageView_del = (ImageView) view.findViewById(R.id.filedelete);
            imageView = (ImageView) view.findViewById(R.id.file_icon);
            textView = (TextView) view.findViewById(R.id.file_name);
        }
    }

    public FileAdapter(List<DocFile> FileList){
        docFileList = FileList;
    }

    @Override
    public ViewHolder onCreateViewHolder(ViewGroup parent, int viewType){
        View view = LayoutInflater.from(parent.getContext())
                .inflate(R.layout.itemhetong, parent, false);
        final ViewHolder holder = new ViewHolder(view);
        holder.imageView_del.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                int position = holder.getAdapterPosition();
                docFileList.remove(position);
                notifyItemRemoved(position);
            }
        });
        return holder;
    }

    @Override
    public void onBindViewHolder(ViewHolder holder, int position){
        DocFile docFile = docFileList.get(position);
        holder.imageView.setImageResource(docFile.getImageId());
        holder.textView.setText(docFile.getName());
    }
    @Override
    public int getItemCount(){
        return docFileList.size();
    }
}
