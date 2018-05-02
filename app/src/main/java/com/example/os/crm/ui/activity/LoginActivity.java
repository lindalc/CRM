package com.example.os.crm.ui.activity;

import android.app.AlarmManager;
import android.app.PendingIntent;
import android.content.Context;
import android.content.Intent;
import android.content.SharedPreferences;
import android.os.Build;
import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;

import com.example.os.crm.Common.UserInfo;
import com.example.os.crm.R;
import com.example.os.crm.presenter.LoginPresenter;
import com.example.os.crm.ui.activity.base.BaseActivity;
import com.example.os.crm.ui.view.LoginView;

import org.json.JSONException;
import org.json.JSONObject;

public class LoginActivity extends BaseActivity<LoginPresenter> implements LoginView,View.OnClickListener{
    EditText editText_username;
    EditText editText_password;
    Button button_login_submit;

    AlarmManager alarmManager;
    PendingIntent pi;

    private String userName,passWord;

    @Override
    protected int provideContentViewId() {
        return R.layout.activity_main;
    }

    @Override
    public void initListeners() {

    }

    @Override
    protected void initThings(Bundle savedInstanceState) {

        init_ui();
        try{
            UserInfo.userid = getCurrentUser();
        }
        catch (Exception e){
            saveUserInfo("","");
            UserInfo.userid = getCurrentUser();
        }
        if (UserInfo.userid.length() != 0){
            String pwd = getCurrentPwd();
            Login(UserInfo.userid, pwd);
        }

        //创建Intent对象，action为ELITOR_CLOCK，附加信息为字符串“你该打酱油了”
//        Intent intent = new Intent("ELITOR_CLOCK");
//        intent.putExtra("msg","你该打酱油了");
//        pi = PendingIntent.getBroadcast(this,0,intent,0);
//
//        alarmManager = (AlarmManager) getSystemService(ALARM_SERVICE);
//        alarmManager.setRepeating(AlarmManager.RTC_WAKEUP,System.currentTimeMillis(),1*1000,pi);

    }

    @Override
    public LoginPresenter createPresenter() {
        return new LoginPresenter();
    }


    @Override
    protected void onDestroy() {
        super.onDestroy();
        if(pi!=null&&alarmManager!=null)
            alarmManager.cancel(pi);
    }

    @Override
    protected void onRestart(){
        super.onRestart();
        finish();
    }

    @Override
    public void onClick(View v){
        switch (v.getId()){
            case R.id.login_submit:
                userName = editText_username.getText().toString();
                passWord = editText_password.getText().toString();

                Login(userName,passWord);
                break;
            default:
                break;
        }
    }

    void init_ui(){
        editText_username = (EditText) findViewById(R.id.login_username);
        editText_password = (EditText) findViewById(R.id.login_password);
        button_login_submit = (Button) findViewById(R.id.login_submit);
        button_login_submit.setOnClickListener(this);
    }

    private void saveUserInfo(String username, String password){
        SharedPreferences.Editor editor = getSharedPreferences("userinfo", MODE_PRIVATE).edit();
        editor.putString("username", username);
        editor.putString("pwd", password);
        editor.apply();
    }

    private String getCurrentUser() {
        SharedPreferences pref = getSharedPreferences("userinfo", MODE_PRIVATE);
        return pref.getString("username", "");
    }

    private String getCurrentPwd(){
        SharedPreferences pref = getSharedPreferences("userinfo", MODE_PRIVATE);
        return pref.getString("pwd", "");
    }


    private void Login(String username, String password){
        goto_admin_activity();

        presenter.Login(username,password);
//        String url = UserInfo.host + "/crm/login";

//        FormBody.Builder builder = new FormBody.Builder()
//                .add("username",username)
//                .add("password",password);
//        new HttpPost().postData(url, builder, new Callback() {
//            @Override
//            public void onFailure(Call call, IOException e) {
//                Log.d("登陆", "onFailure: failed");
//            }
//
//            @Override
//            public void onResponse(Call call, okhttp3.Response response) throws IOException {
//                try{
//                    JSONObject jsonObject = new JSONObject(response.body().string());
//                    if(jsonObject.getInt("error") == 1){
//                        initUserinfo(jsonObject);
//                        goto_admin_activity();
//                    }
//                }catch (Exception e){
//                    e.printStackTrace();
//                }
//            }
//        });
    }

    private void isLoginSuccess(String response){
        try{
            JSONObject jsonObject = new JSONObject(response);
            if(jsonObject.getInt("error") == 1){
                saveUserInfo(editText_username.getText().toString(), editText_password.getText().toString());
                initUserinfo(jsonObject);
                goto_admin_activity();
            }
        }catch (Exception e){
            e.printStackTrace();
        }
    }

    private void initUserinfo(JSONObject jsonObject) throws JSONException {
        UserInfo.userid = getCurrentUser();
        JSONObject jsonObject1 = jsonObject.getJSONObject("jibf");
        UserInfo.level = jsonObject1.getInt("dgji");
        UserInfo.bumen = jsonObject1.getString("bumf");
        UserInfo.xkmk = jsonObject1.getString("xkmk");
        UserInfo.tel = jsonObject.getJSONObject("lmxi").getString("uzji");
    }

    private void goto_admin_activity(){
        Intent intent = new Intent(LoginActivity.this, MainActivity.class);
        startActivity(intent);
    }
}
