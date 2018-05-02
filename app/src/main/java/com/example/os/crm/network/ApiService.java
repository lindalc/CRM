package com.example.os.crm.network;



import com.example.os.crm.model.Res;
import com.example.os.crm.model.UserDetail;

import java.util.ArrayList;
import java.util.List;

import retrofit.http.Field;
import retrofit.http.FormUrlEncoded;
import retrofit.http.POST;
import rx.Observable;

/**
 ****************************************************** 
 * * * _oo0oo_ * o8888888o * 88" . "88 * (| -_- |) * 0\ = /0 * ___/`---'\___ *
 * .' \\| |# '. * / \\||| : |||# \ * / _||||| -:- |||||- \ * | | \\\ - #/ | | *
 * | \_| ''\---/'' |_/ | * \ .-\__ '-' ___/-. / * ___'. .' /--.--\ `. .'___ *
 * ."" '< `.___\_<|>_/___.' >' "". * | | : `- \`.;`\ _ /`;.`/ - ` : | | * \ \
 * `_. \_ __\ /__ _/ .-` / / * =====`-.____`.___ \_____/___.-`___.-'===== *
 * `=---=' * * * ~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~ * * 佛祖保佑 永无BUG * *
 * *
 ****************************************************** 
 * 
 * Created by ninos on 2016/6/2.
 * 
 */
public interface ApiService {
	
    //登录
	@FormUrlEncoded
	@POST(Const.LOGIN)
    Observable<Res<UserDetail>> Login(@Field("username") String phone,@Field("password") String pwd);// Field参数



}
