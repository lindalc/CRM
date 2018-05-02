/*
 * Copyright (C) 2015 Drakeet <drakeet.me@gmail.com>
 *
 * This file is part of Meizhi
 *
 * Meizhi is free software: you can redistribute it and/or modify
 * it under the terms of the GNU General Public License as published by
 * the Free Software Foundation, either version 3 of the License, or
 * (at your option) any later version.
 *
 * Meizhi is distributed in the hope that it will be useful,
 * but WITHOUT ANY WARRANTY; without even the implied warranty of
 * MERCHANTABILITY or FITNESS FOR A PARTICULAR PURPOSE.  See the
 * GNU General Public License for more details.
 *
 * You should have received a copy of the GNU General Public License
 * along with Meizhi.  If not, see <http://www.gnu.org/licenses/>.
 */

package com.example.os.crm.ui.activity.base;

import android.support.v4.widget.SwipeRefreshLayout;
import android.view.View;

import com.example.os.crm.R;
import com.example.os.crm.presenter.base.BasePresenterImp;
import com.example.os.crm.ui.activity.base.BaseFragment;
import com.example.os.crm.ui.view.base.SwipeRefreshView;
import com.example.os.crm.ui.widget.MultiSwipeRefreshLayout;
import com.example.os.crm.utils.WindowUtil;


/**
 * Created by drakeet on 8/11/15.SwipeRefreshFragment
 */
public abstract class SwipeRefreshFragmentNoRefresh<P extends BasePresenterImp> extends BaseFragment<P>{

    public MultiSwipeRefreshLayout mSwipeRefreshLayout;


    @Override
    protected void initThings(View view) {
        trySetupSwipeRefresh(view);
    }

    void trySetupSwipeRefresh(View root) {
        mSwipeRefreshLayout = (MultiSwipeRefreshLayout) root.findViewById(
                R.id.swipe_refresh_layout);
//        if (mSwipeRefreshLayout != null) {
//            mSwipeRefreshLayout.setColorSchemeResources(R.color.refresh_progress_3,
//                    R.color.refresh_progress_2, R.color.refresh_progress_1);
//            mSwipeRefreshLayout.setOnRefreshListener(new SwipeRefreshLayout.OnRefreshListener() {
//                @Override
//                public void onRefresh() {
//                    requestDataRefresh();
//                }
//            });
//        }
//        setProgressViewOffset(false, 0, WindowUtil.dip2px(getContext(), 24));
    }


    /**
     * 从数据源获取数据
     */
    public void requestDataRefresh() {


    }



    public void setProgressViewOffset(boolean scale, int start, int end) {
        mSwipeRefreshLayout.setProgressViewOffset(scale, start, end);
    }

    public void setSwipeableChildren(MultiSwipeRefreshLayout.CanChildScrollUpCallback canChildScrollUpCallback) {
        mSwipeRefreshLayout.setCanChildScrollUpCallback(canChildScrollUpCallback);
    }

}
