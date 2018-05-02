package com.example.os.crm.ui.widget;

/**
 * Created by Administrator on 2017/9/15.
 */
import android.graphics.Rect;
import android.support.v7.widget.RecyclerView;

public class SpacesItemDecoration2 extends RecyclerView.ItemDecoration {

    private int space;

    public SpacesItemDecoration2(int space) {
        this.space=space;
    }

    @Override
    public void getItemOffsets(Rect outRect, int itemPosition, RecyclerView parent) {
        super.getItemOffsets(outRect, itemPosition, parent);
//        if(itemPosition%2 == 1) {
//            outRect.left = space;
//        }else{
//            outRect.right=space;
//        }
//        if(itemPosition%2 == 0||itemPosition%2 == 2){
//            outRect.right=space;
//        }else {
//            outRect.left = space;
//        }
        outRect.right=space;
        outRect.left = space;
        outRect.bottom=space;
        outRect.top=space;
    }

//    @Override
//    public void getItemOffsets(Rect outRect, View view, RecyclerView parent, RecyclerView.State state) {
//        outRect.left=space;
//        outRect.right=space;
//        outRect.bottom=space;
//        outRect.top=space;
////        if(parent.getChildAdapterPosition(view)==0){
////            outRect.top=space;
////        }
//    }
}