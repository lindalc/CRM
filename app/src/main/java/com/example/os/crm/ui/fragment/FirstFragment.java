package com.example.os.crm.ui.fragment;

import android.os.Bundle;
import android.os.Handler;
import android.os.Message;
import android.support.design.widget.AppBarLayout;
import android.support.v4.app.Fragment;
import android.support.v7.widget.CardView;
import android.support.v7.widget.Toolbar;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.LinearLayout;
import android.widget.TextView;

import com.example.os.crm.Contact.Activity.CustomerList;
import com.example.os.crm.Dingdan.Activity.History;
import com.example.os.crm.HttpUtil.HttpUtil;
import com.example.os.crm.R;
import com.example.os.crm.ui.activity.Approval_WaitActivity;

import org.json.JSONObject;

import java.io.IOException;

import butterknife.BindView;
import butterknife.ButterKnife;
import butterknife.OnClick;
import butterknife.Unbinder;
import okhttp3.Call;
import okhttp3.Callback;
import okhttp3.Response;


public class FirstFragment extends Fragment {

    private static final int TabPosition = 0;
    @BindView(R.id.toolbar)
    Toolbar toolbar;
    @BindView(R.id.appbar)
    AppBarLayout appbar;
    @BindView(R.id.zsee)
    TextView zsee;
    @BindView(R.id.wwuz)
    TextView wwuz;
    @BindView(R.id.tiig)
    TextView tiig;
    @BindView(R.id.dkdjzsuu)
    TextView dkdjzsuu;
    @BindView(R.id.dcqi)
    TextView dcqi;
    @BindView(R.id.dkdjtsjifjww)
    TextView dkdjtsjifjww;
    @BindView(R.id.dkdjtsji)
    CardView dkdjtsji;
    @BindView(R.id.zoribdfh)
    TextView zoribdfh;
    @BindView(R.id.zoribdfh_linear)
    LinearLayout zoribdfhLinear;
    @BindView(R.id.kehuzsji)
    TextView kehuzsji;
    @BindView(R.id.kehuzsji_linear)
    LinearLayout kehuzsjiLinear;
    @BindView(R.id.zorixbzg)
    TextView zorixbzg;
    @BindView(R.id.zorixbzg_linear)
    LinearLayout zorixbzgLinear;
    @BindView(R.id.tsjifjww)
    TextView tsjifjww;
    @BindView(R.id.cardview)
    CardView cardview;
    @BindView(R.id.wodeufqk)
    TextView wodeufqk;
    @BindView(R.id.wodeufqk_linear)
    LinearLayout wodeufqkLinear;
    @BindView(R.id.ddwoufpi)
    TextView ddwoufpi;
    @BindView(R.id.ddwoufpi_linear)
    LinearLayout ddwoufpiLinear;
    @BindView(R.id.ufpitsji)
    CardView ufpitsji;
    Unbinder unbinder;
    private String TAG = "MainFragment";

    public int getTabPosition() {
        return TabPosition;
    }
    Handler handler;


    @Override
    public void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
    }

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        // Inflate the layout for this fragment
        View view = inflater.inflate(R.layout.fragment_uzye, container, false);
        unbinder = ButterKnife.bind(this, view);



        return view;
    }

    @Override
    public void onDetach() {
        super.onDetach();
    }

    @Override
    public void onStop() {
        super.onStop();
        Log.d(TAG, "onStop: ");
    }

    @Override
    public void onStart(){
        super.onStart();
        initHandler();
        initData();
    }

    private void initHandler() {
        handler = new Handler(){
            public void handleMessage(Message msg){
                switch (msg.what){
                    case 0:
                        setUi(msg.obj.toString());
                        break;
                    default:
                        break;
                }
            }
        };
    }

    private void initData() {
        new HttpUtil().getTsJi(new Callback() {
            @Override
            public void onFailure(Call call, IOException e) {

            }

            @Override
            public void onResponse(Call call, Response response) throws IOException {
                String data = response.body().string();
                Message msg = new Message();
                msg.what = 0;
                msg.obj = data;
                handler.sendMessage(msg);
            }
        });
    }

    private void setUi(String response){
        try{
            JSONObject jsonObject = new JSONObject(response);
            if (jsonObject.getInt("error") == 1){
                zsee.setText(jsonObject.getString("dkdjzsee"));
                wwuz.setText(jsonObject.getString("wwuz"));
                tiig.setText(jsonObject.getString("tiig"));
                dcqi.setText(jsonObject.getString("vibcjbdcqi"));
                dkdjzsuu.setText(jsonObject.getString("dkdjzsuu"));
                kehuzsji.setText(jsonObject.getString("kehuzsuu"));
                zorixbzg.setText(jsonObject.getString("zorixbzg"));
                wodeufqk.setText(jsonObject.getString("ufqk"));
                ddwoufpi.setText(jsonObject.getString("ufpi"));
                zoribdfh.setText("?");
            }
        }catch (Exception e){

        }
    }

    @Override
    public void onDestroyView() {
        super.onDestroyView();
        unbinder.unbind();
    }

    @OnClick({R.id.dkdjtsji, R.id.zoribdfh_linear, R.id.kehuzsji_linear, R.id.zorixbzg_linear, R.id.wodeufqk_linear, R.id.ddwoufpi_linear})
    public void onViewClicked(View view) {
        switch (view.getId()) {
            case R.id.dkdjtsji:
                new History().startActivity(getContext());
                break;
            case R.id.zoribdfh_linear:
                break;
            case R.id.kehuzsji_linear:
                new CustomerList().startActivity(getContext(), 0);
                break;
            case R.id.zorixbzg_linear:
                new CustomerList().startActivity(getContext(), 1);
                break;
            case R.id.wodeufqk_linear:
                new Approval_WaitActivity().startActivity(getContext(), 1);
                break;
            case R.id.ddwoufpi_linear:
                new Approval_WaitActivity().startActivity(getContext());
                break;
        }
    }
}
