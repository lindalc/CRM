package com.example.os.crm.Charge.Model;

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

public class OrderInfoChargeList {
    private static final OrderInfoChargeList ourInstance = new OrderInfoChargeList();
    private List<NewChargeBean> fwysDetailInfoBeanList = new ArrayList<>();
    private boolean ready = false;
    private GetDataDoneListener listener;

    public static OrderInfoChargeList getInstance() {
        return ourInstance;
    }

    private OrderInfoChargeList() {
        initData();
    }

    public void setListener(GetDataDoneListener listener){
        this.listener = listener;
    }

    public List<NewChargeBean> getFwysDetailInfoBean(){
        return fwysDetailInfoBeanList;
    }

    private void initData(){
        fwysDetailInfoBeanList.clear();
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
                            fwysDetailInfoBeanList.add(gson.fromJson(jsonObject1.toString(), NewChargeBean.class));
                        }
                        ready  =true;
                      //  Collections.sort(fwysDetailInfoBeanList, new Comparator<NewChargeBean>() {
                      //      @Override
                     //       public int compare(NewChargeBean o1, NewChargeBean o2) {
                       //         Long i =  o2.getFwysxbxi().getUijmio() - o1.getFwysxbxi().getUijmio();
                        //        return i.intValue();
                     //       }
                     //   });
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



    public List<NewChargeBean> getFwysDkdj(String userid){
        List<NewChargeBean> list = new ArrayList<>();
        for (NewChargeBean NewChargeBean : fwysDetailInfoBeanList){
            int vltd = NewChargeBean.getUfpi().size();
            if (NewChargeBean.getShenqingshijian().getBytes().equals(userid)){
                if (vltd == 0 || vltd == 1 || vltd == 2){
                    list.add(NewChargeBean);
                }
            }
        }
        return list;
    }

    public NewChargeBean getWjig(){
        for (NewChargeBean NewChargeBean : fwysDetailInfoBeanList){
            if (NewChargeBean.getShenqingshijian().getBytes().equals(fwysDetailInfoBeanList)){
                return NewChargeBean;
            }
        }
        return null;
    }



}
