package com.example.os.crm.HttpUtil;

import com.example.os.crm.Common.UserInfo;

import java.io.IOException;

import okhttp3.Call;
import okhttp3.Callback;
import okhttp3.FormBody;
import okhttp3.MultipartBody;
import okhttp3.Response;

/**
 * Created by OS on 2018/3/2.
 */

public class HttpUtil {

    //获取公司所有联系人url
    private static final String getContactListUrl = UserInfo.host + "/crm/getcontactlist";
    //获取客户联系人url
    private static final String getCustomerListUrl = UserInfo.host + "/crm/getkehu";
    //获取昨日新增联系人
    private static final String getCustomerXbZgListUrl = UserInfo.host + "/crm/getkehuxbzg";
    //创建新客户资料
    private static final String createNewCustomerUrl = UserInfo.host + "/crm/newkehu";
    //获取客户详细资料
    private static final String getCustomerDetailUrl = UserInfo.host + "/crm/getkehudetail/";
    //获取首页统计信息
    private static final String getTsJiUrl = UserInfo.host + "/crm/gettsji/" + UserInfo.userid;
    //上传合同文件
    private static final String uploadContractUrl = UserInfo.host + "/crm/uploadfile";
    //财务提交回款信息
    private static final String newPaymentUrl = UserInfo.host + "/crm/newpayment";
    //获取客户回款列表
    private static final String getPaymentHistoryUrl = UserInfo.host + "/crm/getpaymenthistory/" + UserInfo.userid;
    //提交基于客户的回款审批
    private static final String postPaymentUfPiUrl = UserInfo.host + "/crm/paymentufpi";
    //获取所有状态为基本完成和已完成的订单信息，计算提成
    private static final String getTiigDkdjListUrl = UserInfo.host + "/crm/tiigdkdj";
    //提交提成发放申请，由业务员自己提交
    private static final String postTiigFafhUfqkUrl = UserInfo.host + "/crm/bonus";
    //获取所有提交的未完成的提成发放申请
    private static final String getTiigFafhUfqkUrl = UserInfo.host + "/crm/bonus";
    //获取所有订单列表
    private static final String getOrdersListUrl = UserInfo.host + "/crm/getallitem/" + UserInfo.userid;
    //获取所有待审批项目
    private static final String getAllDdufpiUrl = UserInfo.host + "/crm/allweishenpi/" + UserInfo.userid;
    //获取所有已申请项目
    private static final String getAllUfqk = UserInfo.host + "/crm/ufqk/" + UserInfo.userid;
    //获取所有回款信息
    private static final String getAllHvkrUrl = UserInfo.host + "/crm/huikuanhistory/" + UserInfo.userid;
    //上传业务回款信息，多个订单同时上传
    private static final String postMultyHuiKuanUrl = UserInfo.host + "/crm/multyhvkr";
    //删除合同
    private static final String delContractUrl = UserInfo.host + "/crm/delhetong/";
    // 添加费用申请
    private static final String tianjiashenqingUrl =  UserInfo.host + "/crm/newcharge";
    //获取费用申请记录信息
    private static final String getFwysListUrl = UserInfo.host + "/crm/returncharge/" + UserInfo.userid;



    public void getContactList(okhttp3.Callback callback) {
        new HttpGet().getData(getContactListUrl, callback);
    }

    public void getCustomerList(okhttp3.Callback callback){
        if (UserInfo.userid.equals("yrzzq") || UserInfo.userid.equals("yrzlm")){
            new HttpGet().getData(getCustomerListUrl, callback);
        }else {
            String url = getCustomerListUrl + "/" + UserInfo.userid;
            new HttpGet().getData(url, callback);
        }
    }

    public void getCustomerXbZgList(okhttp3.Callback callback){
        if (UserInfo.userid.equals("yrzzq")){
            new HttpGet().getData(getCustomerXbZgListUrl, callback);
        }else {
            String url = getCustomerXbZgListUrl + "/" + UserInfo.userid;
            new HttpGet().getData(url, callback);
        }
    }
    public void getFwysList(okhttp3.Callback callback){
        if (UserInfo.userid.equals("yrzzq") || UserInfo.userid.equals("yrzlm")){
            new HttpGet().getData(getFwysListUrl, callback);
        }else {
            String url = getFwysListUrl + "/" + UserInfo.userid;
            new HttpGet().getData(url, callback);
        }
    }

    public void createNewCustomer(FormBody.Builder builder, okhttp3.Callback callback){
        new HttpPost().postData(createNewCustomerUrl, builder, callback);
    }

    public void getCustomerDetail(String customerId, okhttp3.Callback callback){
        String url = getCustomerDetailUrl + customerId;
        new HttpGet().getData(url, callback);
    }

    public void getTsJi(okhttp3.Callback callback){
        new HttpGet().getData(getTsJiUrl, callback);
    }

    public void uploadContract(MultipartBody.Builder builder, okhttp3.Callback callback){
        new HttpPost().postData(uploadContractUrl, builder, callback);
    }
    public void tijiaoshenqing(MultipartBody.Builder builder, okhttp3.Callback callback){
        new HttpPost().postData(tianjiashenqingUrl, builder, callback);
    }
    public void CreateNewPayment(MultipartBody.Builder builder, okhttp3.Callback callback){
        if (UserInfo.userid.equals("yrzlm")){
            new HttpPost().postData(newPaymentUrl, builder, callback);
        }
    }

    public void GetPaymentHistory(okhttp3.Callback callback){
            new HttpGet().getData(getPaymentHistoryUrl, callback);
    }

    public void PostPaymentUfPi(FormBody.Builder builder, okhttp3.Callback callback){
        new HttpPost().postData(postPaymentUfPiUrl, builder, callback);
    }

    public void GetTiigDkdjList(okhttp3.Callback callback){
        String url = getTiigDkdjListUrl + "/" + UserInfo.userid;
        new HttpGet().getData(url, callback);
    }

    public void PostTiigFaFhUfqk(FormBody.Builder builder, okhttp3.Callback callback){
        new HttpPost().postData(postTiigFafhUfqkUrl, builder, callback);
    }

    public void GetTiigFafhUfqk(okhttp3.Callback callback){
        String url = getTiigFafhUfqkUrl + "/" + UserInfo.userid;
        new HttpGet().getData(url, callback);
    }

    public void GetOrdersList(okhttp3.Callback callback){
        new HttpGet().getData(getOrdersListUrl, callback);
    }

    public void GetAllDdufpi(okhttp3.Callback callback){
        new HttpGet().getData(getAllDdufpiUrl, callback);
    }

    public void GetAllHvkr(okhttp3.Callback callback){
        new HttpGet().getData(getAllHvkrUrl, callback);
    }

    public void PostMultyHuiKuan(FormBody.Builder builder, okhttp3.Callback callback){
        new HttpPost().postData( postMultyHuiKuanUrl, builder, callback);
    }

    public void DelContract(String dkdjbmhc, okhttp3.Callback callback){
        String url = delContractUrl + dkdjbmhc;
        new HttpGet().getData(url, callback);
    }

    public void GetAllUfqk(okhttp3.Callback callback){
        new HttpGet().getData(getAllUfqk, callback);
    }
}
