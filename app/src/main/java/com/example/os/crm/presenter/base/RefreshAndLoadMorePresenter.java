package com.example.os.crm.presenter.base;




import android.text.TextUtils;

import com.example.os.crm.model.Res;
import com.example.os.crm.ui.view.base.LoadMoreView;

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
public abstract class RefreshAndLoadMorePresenter<V extends LoadMoreView> extends BasePresenterImp<V> {

    /**
     * 获取数据
     *
     * @param page
     * @param count
     */
    public abstract void getData(String rdm,String sign,String userid,int page, int count);


    /**
     * 获取数据
     *
     * @param page
     * @param count
     */
    public abstract void getData(int page, int count);

    /**
     * 设置数据状态
     *
     * @param page
     * @param count
     * @param res
     */
    public void setDataStatus(int page, int count, Res res) {
        if (page * count >= res.recordCount ) {
            //没有更多了
            view.noMore();
        } else {
            //还有更多
            view.hasMore();
        }

    }


    public void setDataStatus(List data) {
        if(data.size()<10){
            view.noMore();
        }else{
            view.hasMore();
        }


    }
}
