package com.example.os.crm.Dingdan.Model;

import com.example.os.crm.Common.GetDataDoneListener;
import com.example.os.crm.HttpUtil.HttpUtil;
import com.google.gson.Gson;

import org.json.JSONArray;
import org.json.JSONObject;

import java.io.IOException;
import java.util.ArrayList;
import java.util.Collections;
import java.util.Comparator;
import java.util.List;

import okhttp3.Call;
import okhttp3.Callback;
import okhttp3.Response;

/**
 * Created by OS on 2018/3/14.
 */

public class OrderInfoList {

    private static final OrderInfoList ourInstance = new OrderInfoList();
    private List<DkDjDetailInfoBean> dkDjDetailInfoBeanList = new ArrayList<>();
    private boolean ready = false;
    private GetDataDoneListener listener;

    public static OrderInfoList getInstance() {
        return ourInstance;
    }

    private OrderInfoList() {
        initData();
    }

    public void setListener(GetDataDoneListener listener){
        this.listener = listener;
    }

    public List<DkDjDetailInfoBean> getDkDjDetailInfoBeanList(){
        return dkDjDetailInfoBeanList;
    }

    private void initData(){
        dkDjDetailInfoBeanList.clear();
        new HttpUtil().GetOrdersList(new Callback() {
            @Override
            public void onFailure(Call call, IOException e) {
                e.printStackTrace();
            }

            @Override
            public void onResponse(Call call, Response response) throws IOException {
                String data = response.body().string();
                try{
                    JSONObject jsonObject = new JSONObject(data);
                    if (jsonObject.getInt("error") == 1){
                        JSONArray jsonArray = jsonObject.getJSONArray("data");
                        for(int i = 0; i< jsonArray.length(); i++){
                            JSONObject jsonObject1 = jsonArray.getJSONObject(i);
                            Gson gson = new Gson();
                            dkDjDetailInfoBeanList.add(gson.fromJson(jsonObject1.toString(), DkDjDetailInfoBean.class));
                        }
                        ready  =true;
                        Collections.sort(dkDjDetailInfoBeanList, new Comparator<DkDjDetailInfoBean>() {
                            @Override
                            public int compare(DkDjDetailInfoBean o1, DkDjDetailInfoBean o2) {
                                Long i =  o2.getDkdjxbxi().getUijmio() - o1.getDkdjxbxi().getUijmio();
                                return i.intValue();
                            }
                        });
                    }
                    listener.onFinish();
                }
                catch (Exception e) {
                    e.printStackTrace();
                }
            }
        });
    }

    public void refresh(){
        initData();
    }

    public boolean isReady(){
        return ready;
    }

    public List<DkDjDetailInfoBean> getWwwjig(){
        List<DkDjDetailInfoBean> list = new ArrayList<>();
        for (DkDjDetailInfoBean dkDjDetailInfoBean : dkDjDetailInfoBeanList){
            int vltd = dkDjDetailInfoBean.getVltd().getWjig();
            if (vltd == 0 || vltd == 1 || vltd == 2){
                list.add(dkDjDetailInfoBean);
            }
        }
        return list;
    }

    public List<DkDjDetailInfoBean> getKehuDkdj(String kehuid){
        List<DkDjDetailInfoBean> list = new ArrayList<>();
        for (DkDjDetailInfoBean dkDjDetailInfoBean : dkDjDetailInfoBeanList){
            int vltd = dkDjDetailInfoBean.getVltd().getWjig();
            if (dkDjDetailInfoBean.getKehuziln().getKehuid().equals(kehuid)){
                if (vltd == 0 || vltd == 1 || vltd == 2){
                    list.add(dkDjDetailInfoBean);
                }
            }
        }
        return list;
    }

    public DkDjDetailInfoBean getDkdj(String dkdjbmhc){
        for (DkDjDetailInfoBean dkDjDetailInfoBean : dkDjDetailInfoBeanList){
            if (dkDjDetailInfoBean.getDkdjxbxi().getDkdjbmhc().equals(dkdjbmhc)){
                return dkDjDetailInfoBean;
            }
        }
        return null;
    }
}
