package com.example.os.crm.ui.activity.base;

import android.support.v4.view.ViewCompat;
import android.support.v7.widget.DefaultItemAnimator;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.support.v7.widget.StaggeredGridLayoutManager;
import android.view.View;


import com.example.os.crm.R;
import com.example.os.crm.presenter.base.RefreshAndLoadMorePresenter;
import com.example.os.crm.ui.adapter.base.BaseAdapter;
import com.example.os.crm.ui.view.base.LoadMoreView;
import com.example.os.crm.ui.view.base.OnItemClickListener;
import com.example.os.crm.ui.widget.ExStaggeredGridLayoutManager;
import com.example.os.crm.ui.widget.RecyclerForScroll;
import com.example.os.crm.utils.UserUtil;

import java.util.ArrayList;
import java.util.List;



/**
 ******************************************************
 *                                                    *
 *                                                    *
 *                       _oo0oo_                      *
 *                      o8888888o                     *
 *                      88" . "88                     *
 *                      (| -_- |)                     *
 *                      0\  =  /0                     *
 *                    ___/`---'\___                   *
 *                  .' \\|     |# '.                  *
 *                 / \\|||  :  |||# \                 *
 *                / _||||| -:- |||||- \               *
 *               |   | \\\  -  #/ |   |               *
 *               | \_|  ''\---/''  |_/ |              *
 *               \  .-\__  '-'  ___/-. /              *
 *             ___'. .'  /--.--\  `. .'___            *
 *          ."" '<  `.___\_<|>_/___.' >' "".          *
 *         | | :  `- \`.;`\ _ /`;.`/ - ` : | |        *
 *         \  \ `_.   \_ __\ /__ _/   .-` /  /        *
 *     =====`-.____`.___ \_____/___.-`___.-'=====     *
 *                       `=---='                      *
 *                                                    *
 *                                                    *
 *     ~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~    *
 *                                                    *
 *               佛祖保佑         永无BUG              *
 *                                                    *
 *                                                    *
 ******************************************************
 *
 * Created by ninos on 2016/6/2.
 *
 */
public abstract class RecycleViewFragment<P extends RefreshAndLoadMorePresenter, A extends BaseAdapter, M> extends ToolBarFragment<P> implements OnItemClickListener<M>, LoadMoreView {

    public RecyclerView recyclerView;
    public A adapter;
    public RecyclerView.LayoutManager layoutManager;

    public int page = 0;
    public int count = 10;

    @Override
    protected void initThings(View view) {
        super.initThings(view);

        recyclerView = (RecyclerView) view.findViewById(R.id.recycler_view);

        this.layoutManager = provideLayoutManager();
        recyclerView.setLayoutManager(layoutManager);

        this.adapter = provideAdapter();
        recyclerView.setAdapter(adapter);
//        recyclerView.setItemAnimator(new DefaultItemAnimator());
        adapter.setOnItemClickListener(this);
        if (layoutManager instanceof StaggeredGridLayoutManager) {
            recyclerView.setOnScrollListener(new RecyclerView.OnScrollListener() {
                @Override
                public void onScrollStateChanged(RecyclerView recyclerView, int newState) {
                    super.onScrollStateChanged(recyclerView, newState);

                }

                @Override
                public void onScrolled(RecyclerView recyclerView, int dx, int dy) {
                    super.onScrolled(recyclerView, dx, dy);
                    onscroll(recyclerView, dx, dy);
                    if (canScrollDown(recyclerView)) {
                    } else {
//                        loadMore();
                    }
                }
            });

        }
    }


    private boolean canScrollDown(RecyclerView recyclerView) {
        return ViewCompat.canScrollVertically(recyclerView, 1);
    }

    /**
     * RecycleView监听函数
     *
     * @param recyclerView
     * @param dx
     * @param dy
     */
    public void onscroll(RecyclerView recyclerView, int dx, int dy) {


    }

    /**
     * @return 提供Adapter
     */
    protected abstract A provideAdapter();

    /**
     * @return 提供LayoutManager
     */
    protected abstract RecyclerView.LayoutManager provideLayoutManager();


    /**
     * 基本的绑定数据
     *
     * @param data
     */
    public void bd(List<M> data,int i) {
        if (page == 0) {
            if(i == 0) {
                adapter.addDatas(data);
            }else{
                adapter.addMore(data);
            }
        } else {
            adapter.addMore(data);
        }

    }

    /**
     * 基本的绑定数据
     *
     * @param data
     */
    public void bd(List<M> data) {
        if (page == 0) {
            adapter.addDatas(data);
        } else {
            adapter.addMore(data);
        }

    }

    @Override
    public void loadMore() {
        if (adapter.getStatus() == BaseAdapter.STATUS_HASMORE) {
            presenter.getData(page, count);
            page+=10;
            adapter.loading();
        }
    }


    @Override
    public void hasMore() {
        adapter.hasMore();
    }

    @Override
    public void noMore() {
        adapter.noMore();
    }

    @Override
    public void loadMore(String tip) {
        if (adapter.getStatus() == BaseAdapter.STATUS_HASMORE) {
            page+=10;
            presenter.getData(page, count);
            adapter.loading(tip);
        }
    }

    @Override
    public void hasMore(String tip) {
        adapter.hasMore(tip);
    }

    @Override
    public void noMore(String tip) {
        adapter.noMore(tip);
    }


}
