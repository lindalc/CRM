package com.example.os.crm.Contact.Model;

import android.util.Log;

import com.example.os.crm.Contact.Listener.ContactListener;
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
 * Created by OS on 2018/3/12.
 */

public class Contact {

    private static List<ContactBean> contactBeanList = new ArrayList<>();
    private String TAG = "Contact 单例类";
    private ContactListener contactListener;
    private static boolean ready = false;

    private Contact(){
        initContactList();
    }
    public static Contact getInstance(){
        return ContactHolder.sContact;
    }

    private static class ContactHolder{
        private static final Contact sContact = new Contact();
    }

    public void setFinishListener(ContactListener contactListener){
        this.contactListener = contactListener;
    }

    private void initContactList(){
        contactBeanList.clear();
        new HttpUtil().getContactList(new Callback() {
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
                        for (int i = 0; i < jsonArray.length(); i++){
                            JSONObject jsonObject1 = jsonArray.getJSONObject(i);
                            ContactBean contactBean = gson.fromJson(jsonObject1.toString(), ContactBean.class);
                            contactBeanList.add(contactBean);
                        }
                        Collections.sort(contactBeanList, new Comparator<ContactBean>() {
                            @Override
                            public int compare(ContactBean o1, ContactBean o2) {
                                return o1.getJibf().getFirstChar() - o2.getJibf().getFirstChar();
                            }
                        });
                        ready = true;
                        if (contactListener != null){
                            contactListener.onFinish();
                        }
                    }
                }catch (Exception e){
                    e.printStackTrace();
                }
            }
        });
    }

    public List<ContactBean> getContactBeanList(){
        return contactBeanList;
    }

    public void refresh(){
        initContactList();
    }

    public boolean isReady(){
        return ready;
    }

    public ContactBean getContact(String userid){
        for (ContactBean contactBean : contactBeanList){
            if (contactBean.getUserid().equals(userid)){
                return contactBean;
            }
        }
        return null;
    }

    public List<ContactBean> getContact(List<String> userids){
        List<ContactBean> contactBeans = new ArrayList<>();
        for (ContactBean contactBean : contactBeanList){
            if (userids.contains(contactBean.getUserid())){
                contactBeans.add(contactBean);
            }
        }
        return contactBeans;
    }
}
