package com.example.os.crm.Contact.Model;

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
 * Created by OS on 2018/3/12.
 */

public class Customer {
    private static List<CustomerBean> customerBeanList = new ArrayList<>();
    private String TAG = "Customer 单例类";
    private Customer(){
        initCustomerList();
    }
    public static Customer getInstance(){
        return CustomerHolder.sCustomer;
    }
    private static class CustomerHolder{
        private static final Customer sCustomer = new Customer();
    }

    private void initCustomerList(){
        customerBeanList.clear();
        new HttpUtil().getCustomerList(new Callback() {
            @Override
            public void onFailure(Call call, IOException e) {
                e.printStackTrace();
            }

            @Override
            public void onResponse(Call call, Response response) throws IOException {
                String data = response.body().string();
                try {
                    JSONObject jsonObject = new JSONObject(data);
                    if (jsonObject.getInt("error") == 1) {
                        JSONArray jsonArray = jsonObject.getJSONArray("data");
                        Gson gson = new Gson();
                        for (int i = 0; i < jsonArray.length(); i++) {
                            JSONObject jsonObject1 = jsonArray.getJSONObject(i);
                            CustomerBean customerBean = gson.fromJson(jsonObject1.toString(), CustomerBean.class);
                            customerBeanList.add(customerBean);
                        }
                    }
                } catch (Exception e) {
                    e.printStackTrace();
                }
            }
        });
    }

    public List<CustomerBean> getCustomerBeanList(){
        return customerBeanList;
    }
    public void refresh(){
        initCustomerList();
    }
}
