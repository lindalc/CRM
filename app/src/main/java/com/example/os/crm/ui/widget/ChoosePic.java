package com.example.os.crm.ui.widget;

import android.content.Context;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.util.AttributeSet;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;
import android.widget.LinearLayout;
import android.widget.TextView;

import com.chad.library.adapter.base.BaseQuickAdapter;
import com.example.os.crm.Common.ImagePreview;
import com.example.os.crm.R;
import com.huantansheng.easyphotos.models.album.entity.Photo;

import java.util.ArrayList;
import java.util.List;

import butterknife.BindView;
import butterknife.ButterKnife;

/**
 * Created by OS on 2018/3/5.
 */

public class ChoosePic extends LinearLayout {


    @BindView(R.id.title)
    TextView title;
    @BindView(R.id.add_image)
    ImageView addImage;
    @BindView(R.id.recyclerview)
    RecyclerView recyclerview;
    private List<Photo> tImageList = new ArrayList<>();
    private ChoosePicAdapter choosePicAdapter = new ChoosePicAdapter(R.layout.choose_pic_item, tImageList);
    private View footer;


    public ChoosePic(Context context, AttributeSet attrs) {
        super(context, attrs);
        LayoutInflater.from(context).inflate(R.layout.choose_pic, this);
        ButterKnife.bind(this);
        LinearLayoutManager linearLayoutManager = new LinearLayoutManager(getContext());
        linearLayoutManager.setOrientation(HORIZONTAL);
        recyclerview.setLayoutManager(linearLayoutManager);
        addFooter();
        initRecyler();
    }

    public void setAddImageClick(OnClickListener onClickListener) {
        addImage.setOnClickListener(onClickListener);
        footer.setOnClickListener(onClickListener);
    }

    public void setTitle(String temp) {
        title.setText(temp);
    }

    public void addData(Photo tImage) {
        tImageList.add(tImage);
        choosePicAdapter.notifyDataSetChanged();
    }

    public void addData(List<Photo> tImages) {
        tImageList.addAll(tImages);
        choosePicAdapter.notifyDataSetChanged();
    }

    private void initRecyler() {

        recyclerview.setAdapter(choosePicAdapter);
        choosePicAdapter.setOnItemClickListener(new BaseQuickAdapter.OnItemClickListener() {
            @Override
            public void onItemClick(BaseQuickAdapter adapter, View view, int position) {
                Photo tImage = tImageList.get(position);
                new ImagePreview().startActivity(getContext(),tImage.path);
            }
        });
        choosePicAdapter.setOnItemLongClickListener(new BaseQuickAdapter.OnItemLongClickListener() {
            @Override
            public boolean onItemLongClick(BaseQuickAdapter adapter, View view, int position) {
                tImageList.remove(position);
                choosePicAdapter.notifyDataSetChanged();
                return false;
            }
        });
    }

    private void addFooter() {
        footer = LayoutInflater.from(getContext()).inflate(R.layout.add_new_footer,
                (ViewGroup) recyclerview.getParent(),
                false);
        choosePicAdapter.addFooterView(footer);
        choosePicAdapter.notifyDataSetChanged();
    }

    public int getCount(){
        return tImageList.size();
    }

    public List<Photo> getAllItem(){
        return tImageList;
    }

}
