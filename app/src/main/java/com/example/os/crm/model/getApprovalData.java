package com.example.os.crm.model;

import android.util.Log;

import com.example.os.crm.Common.GetDataDoneListener;
import com.example.os.crm.Dingdan.Model.DkDjDetailInfoBean;
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
 * Created by OS on 2018/3/15.
 * 获取审核数据
 */

public class getApprovalData {

    private String TAG = "getApprovalData";

    private static final getApprovalData ourInstance = new getApprovalData();

    private GetDataDoneListener listener;
    private List<ApprovalMultyItem> approvalMultyItemList = new ArrayList<>();

    public static getApprovalData getInstance() {
        return ourInstance;
    }

    private getApprovalData() {
        initData();
    }

    public void setListener(GetDataDoneListener listener) {
        this.listener = listener;
    }

    private void initData(){
        approvalMultyItemList.clear();
        new HttpUtil().GetAllDdufpi(new Callback() {
            @Override
            public void onFailure(Call call, IOException e) {
                e.printStackTrace();
            }

            @Override
            public void onResponse(Call call, Response response) throws IOException {
                String data = response.body().string();
                Log.d(TAG, "onResponse: " + data);
                try{
                    JSONObject jsonObject = new JSONObject(data);
                    if (jsonObject.getInt("error") == 1){
                        JSONArray jsonArray = jsonObject.getJSONArray("data");
                        Gson gson = new Gson();

                        for (int i = 0; i< jsonArray.length(); i++){
                            JSONObject jsonObject1 = jsonArray.getJSONObject(i);
                            int type = jsonObject1.getInt("type");
                            jsonObject1.remove("type");
                            switch (type){
                                case 1:
                                    DkDjDetailInfoBean dkDjDetailInfoBean = gson.fromJson(jsonObject1.toString(), DkDjDetailInfoBean.class);
                                    approvalMultyItemList.add(new ApprovalMultyItem(type, dkDjDetailInfoBean));
                                    break;
                                case 2:
                                    FundRestream fundRestream = gson.fromJson(jsonObject1.toString(), FundRestream.class);
                                    approvalMultyItemList.add(new ApprovalMultyItem(type, fundRestream));
                                    break;
                                case 3:
                                    PaymentInfoBean paymentInfoBean = gson.fromJson(jsonObject1.toString(), PaymentInfoBean.class);
                                    approvalMultyItemList.add(new ApprovalMultyItem(type, paymentInfoBean));
                                    break;
                            }
                        }
                        Collections.sort(approvalMultyItemList, new Comparator<ApprovalMultyItem>() {
                            @Override
                            public int compare(ApprovalMultyItem o1, ApprovalMultyItem o2) {
                                return getOTimestamp(o2) - getOTimestamp(o1);
                            }
                        });
                    }
                    listener.onFinish();
                }catch (Exception e){
                    e.printStackTrace();
                }
            }
        });
    }

    public List<ApprovalMultyItem> getApprovalMultyItemList(){
        return approvalMultyItemList;
    }

    public void refresh(){
        initData();
    }

    private int getOTimestamp(ApprovalMultyItem o){
        switch (o.getItemType()){
            case ApprovalMultyItem.CDWUHVKR:
                return Integer.parseInt(o.getPaymentInfoBean().getUijm());
            case ApprovalMultyItem.DKDJ:
                return (int)o.getDkdjBean().getDkdjxbxi().getUijmio();
            case ApprovalMultyItem.HVKR:
                return Integer.parseInt(o.getFundRestream().getTimestamp());
        }
        return 0;
    }
}
