package com.example.os.crm.ui.activity.base;

import android.os.Bundle;
import android.support.annotation.Nullable;
import android.support.v4.view.ViewCompat;
import android.support.v7.widget.DefaultItemAnimator;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;

import com.example.os.crm.R;
import com.example.os.crm.presenter.base.RefreshAndLoadMorePresenter;
import com.example.os.crm.ui.adapter.base.BaseAdapter;
import com.example.os.crm.ui.view.base.LoadMoreView;
import com.example.os.crm.ui.view.base.OnItemClickListener;
import com.example.os.crm.ui.widget.DividerItemDecoration;
import com.example.os.crm.utils.UserUtil;

import java.util.ArrayList;
import java.util.LinkedList;
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
public abstract class RecycleViewActivity<P extends RefreshAndLoadMorePresenter, A extends BaseAdapter, M> extends ToolBarActivity<P> implements OnItemClickListener<M>,LoadMoreView {

//    @Nullable
//    @Bind(R.id.recycler_view)
    public RecyclerView recyclerView;
    public A adapter;
    public RecyclerView.LayoutManager layoutManager;

    public int page = 1;
    public int count = 10;

    @Override
    protected void initThings(Bundle savedInstanceState) {
        super.initThings(savedInstanceState);

        this.layoutManager = new LinearLayoutManager(this);
        this.layoutManager = provideLayoutManager();

        recyclerView = (RecyclerView) findViewById(R.id.recycler_view);

        recyclerView.setLayoutManager(new LinearLayoutManager(getContext(),LinearLayoutManager.VERTICAL,false));

//        recyclerView.addItemDecoration(new DividerItemDecoration(this,
//                DividerItemDecoration.VERTICAL_LIST));

        this.adapter = provideAdapter();
        recyclerView.setAdapter(adapter);

        adapter.setOnItemClickListener(this);
        if (layoutManager instanceof LinearLayoutManager) {
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
                        loadMore();
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
    public void bd(List<M> data) {
        if (page == 1) {
            adapter.addDatas(data);
        } else {
            adapter.addMore(data);
        }
    }



    @Override
    public void loadMore() {
        if (adapter.getStatus() == BaseAdapter.STATUS_HASMORE) {
            page++;
            presenter.getData(page, count);
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
            page++;
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
