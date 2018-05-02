package com.example.os.crm.model;

import com.example.os.crm.Common.GetDataDoneListener;
import com.example.os.crm.HttpUtil.HttpUtil;
import com.google.gson.Gson;

import org.json.JSONArray;
import org.json.JSONObject;

import java.io.IOException;
import java.util.ArrayList;
import java.util.List;

import okhttp3.Call;
import okhttp3.Callback;
import okhttp3.Response;

/**
 * Created by OS on 2018/3/16.
 */

public class ModelHvkrDkdjBean {

    private GetDataDoneListener listener;
    private List<HvKrDkDjBean> hvKrDkDjBeanList = new ArrayList<>();
    private List<HvKrDkDjBean> hvKrDkDjBeansResult = new ArrayList<>();

    private static final ModelHvkrDkdjBean ourInstance = new ModelHvkrDkdjBean();

    public static ModelHvkrDkdjBean getInstance() {
        return ourInstance;
    }

    private ModelHvkrDkdjBean() {
        initData();
    }

    public void setListener(GetDataDoneListener listener){
        this.listener = listener;
    }

    private void initData(){
        new HttpUtil().GetAllHvkr(new Callback() {
            @Override
            public void onFailure(Call call, IOException e) {
                e.printStackTrace();
            }

            @Override
            public void onResponse(Call call, Response response) throws IOException {
                hvKrDkDjBeanList.clear();
                String data = response.body().string();
                try {
                    JSONObject jsonObject = new JSONObject(data);
                    if (jsonObject.getInt("error") == 1) {
                        JSONArray jsonArray = jsonObject.getJSONArray("data");
                        Gson gson = new Gson();
                        for (int i = 0; i < jsonArray.length(); i++) {
                            JSONObject jsonObject1 = jsonArray.getJSONObject(i);
                            HvKrDkDjBean hvKrDkDjBean = gson.fromJson(jsonObject1.toString(),
                                    HvKrDkDjBean.class);
                            hvKrDkDjBeanList.add(hvKrDkDjBean);
                        }
                    }
                    listener.onFinish();
                }catch (Exception e){
                    e.printStackTrace();
                }
            }
        });
    }

    public List<HvKrDkDjBean> getHvKrDkDjBeanList(){
        return hvKrDkDjBeanList;
    }

    public List<HvKrDkDjBean> getHvKrDkDjBeansResult() {
        return hvKrDkDjBeansResult;
    }

    public List<HvKrDkDjBean> getWithCustomerId(String customerid, int[] vltd){
        hvKrDkDjBeansResult.clear();
        for (HvKrDkDjBean hvKrDkDjBean : hvKrDkDjBeanList){
            if (hvKrDkDjBean.getDkdj().getKehuziln().getKehuid().equals(customerid)){
                for (int i: vltd){
                    if (i == hvKrDkDjBean.getDkdj().getVltd().getWjig()){
                        hvKrDkDjBeansResult.add(hvKrDkDjBean);
                    }
                }

            }
        }
        return hvKrDkDjBeansResult;
    }

    public void refresh(){
        initData();
    }
}
