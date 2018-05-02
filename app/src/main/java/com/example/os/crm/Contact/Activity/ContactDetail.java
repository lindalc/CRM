package com.example.os.crm.Contact.Activity;

import android.content.Context;
import android.content.Intent;
import android.os.Bundle;
import android.os.Parcelable;
import android.support.design.widget.AppBarLayout;
import android.support.design.widget.CollapsingToolbarLayout;
import android.support.v7.app.AppCompatActivity;
import android.util.Log;
import android.view.View;
import android.widget.ImageView;
import android.widget.LinearLayout;
import android.widget.TextView;

import com.example.os.crm.Contact.Model.ContactBean;
import com.example.os.crm.R;

import butterknife.BindView;
import butterknife.ButterKnife;
import butterknife.OnClick;
import de.hdodenhof.circleimageview.CircleImageView;

public class ContactDetail extends AppCompatActivity {


    @BindView(R.id.contact_img)
    CircleImageView contactImg;
    @BindView(R.id.name)
    TextView name;
    @BindView(R.id.bumf)
    TextView bumf;
    @BindView(R.id.viwu)
    TextView viwu;
    @BindView(R.id.contact_linear)
    LinearLayout contactLinear;
    @BindView(R.id.collapsing_toolbar)
    CollapsingToolbarLayout collapsingToolbar;
    @BindView(R.id.appbar)
    AppBarLayout appbar;
    @BindView(R.id.tel)
    TextView tel;
    @BindView(R.id.new_msg)
    ImageView newMsg;
    @BindView(R.id.new_call)
    ImageView newCall;
    @BindView(R.id.zoji)
    TextView zoji;
    @BindView(R.id.new_call_zoji)
    ImageView newCallZoji;
    @BindView(R.id.email)
    TextView email;
    @BindView(R.id.new_email)
    ImageView newEmail;
    @BindView(R.id.qq_num)
    TextView qqNum;
    @BindView(R.id.ll_qq)
    LinearLayout llQq;
    @BindView(R.id.wechat_num)
    TextView wechatNum;
    @BindView(R.id.ll_wechat)
    LinearLayout llWechat;
    @BindView(R.id.department)
    TextView department;
    @BindView(R.id.ll_department)
    LinearLayout llDepartment;
    @BindView(R.id.company_name)
    TextView companyName;
    private ContactBean contactBean;
    private String TAG = "ContactDetail";

    public void startActivity(Context context, Parcelable data) {
        Intent intent = new Intent(context, ContactDetail.class);
        intent.putExtra("data", data);
        context.startActivity(intent);
    }

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_contact_detail);
        ButterKnife.bind(this);
        Intent intent = getIntent();
        contactBean = intent.getParcelableExtra("data");
        Log.d(TAG, "onCreate:");
        name.setText(contactBean.getJibf().getXkmk());
        department.setText(contactBean.getJibf().getBumf());
        bumf.setText(contactBean.getJibf().getBumf());
        viwu.setText(contactBean.getJibf().getViwu());
        tel.setText(contactBean.getLmxi().getUzji());
        zoji.setText(contactBean.getLmxi().getZoji());
        email.setText(contactBean.getLmxi().getEmail());
        qqNum.setText(contactBean.getLmxi().getQQ());
        wechatNum.setText(contactBean.getLmxi().getWechat());
        companyName.setText(contactBean.getJibf().getGssi());
    }

    @OnClick({R.id.ll_qq, R.id.ll_wechat, R.id.ll_department})
    public void onViewClicked(View view) {
        switch (view.getId()) {
            case R.id.ll_qq:
                break;
            case R.id.ll_wechat:
                break;
            case R.id.ll_department:
                break;
        }
    }
}
