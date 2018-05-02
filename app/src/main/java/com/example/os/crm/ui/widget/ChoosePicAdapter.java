package com.example.os.crm.ui.widget;

import android.widget.ImageView;

import com.bumptech.glide.Glide;
import com.chad.library.adapter.base.BaseQuickAdapter;
import com.chad.library.adapter.base.BaseViewHolder;
import com.example.os.crm.R;
import com.huantansheng.easyphotos.models.album.entity.Photo;

import java.io.File;
import java.util.List;

/**
 * Created by OS on 2018/3/5.
 */

public class ChoosePicAdapter extends BaseQuickAdapter<Photo, BaseViewHolder> {

    public ChoosePicAdapter(int layoutResId, List<Photo> data){
        super(layoutResId, data);
    }

    @Override
    protected void convert(BaseViewHolder holder, Photo tImage) {
        Glide.with(mContext).load(new File(tImage.path)).into((ImageView) holder.getView(R.id.imageview));
    }
}
