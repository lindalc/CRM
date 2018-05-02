package com.example.os.crm.ui.activity;

import android.content.Context;
import android.content.Intent;
import android.os.Bundle;
import android.support.design.widget.AppBarLayout;
import android.support.v7.app.AppCompatActivity;
import android.support.v7.widget.Toolbar;
import android.view.View;
import android.widget.AdapterView;
import android.widget.Button;
import android.widget.ListView;
import android.widget.TextView;
import android.widget.Toast;

import com.example.os.crm.Common.ImagePreview;
import com.example.os.crm.Common.UserInfo;
import com.example.os.crm.ui.adapter.ImagelistitemAdapter;
import com.example.os.crm.model.PaymentInfoBean;
import com.example.os.crm.R;

import java.util.List;

import butterknife.BindView;
import butterknife.ButterKnife;
import butterknife.OnClick;

/**
 * 回款记录详情，原PaymentDetail
 */
public class FundDetailActivity extends AppCompatActivity {

    @BindView(R.id.toolbar)
    Toolbar toolbar;
    @BindView(R.id.appbar)
    AppBarLayout appbar;
    @BindView(R.id.kehumkig)
    TextView kehumkig;
    @BindView(R.id.hvkrjbee)
    TextView hvkrjbee;
    @BindView(R.id.hvkrriqi)
    TextView hvkrriqi;
    @BindView(R.id.fuzeyewu)
    TextView fuzeyewu;
    @BindView(R.id.imagelist)
    ListView imagelist;
    @BindView(R.id.ffjphvkr)
    Button ffjphvkr;

    private PaymentInfoBean paymentInfoBean;

    public void startActivity(Context context, PaymentInfoBean paymentInfoBean) {
        Intent intent = new Intent(context, FundDetailActivity.class);
        intent.putExtra("data", paymentInfoBean);
        context.startActivity(intent);
    }

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_payment_detail);
        ButterKnife.bind(this);
        initParam();
        initList();
    }

    private void initParam() {
        Intent intent = getIntent();
        paymentInfoBean = intent.getParcelableExtra("data");
        kehumkig.setText(paymentInfoBean.getCustomername());
        hvkrjbee.setText(paymentInfoBean.getJbee() + "元");
        hvkrriqi.setText(paymentInfoBean.getDate());
        fuzeyewu.setText(paymentInfoBean.getFuzeyewu());
    }

    private void initList() {
        final List<String> urls = paymentInfoBean.getPath();
        ImagelistitemAdapter imagelistitemAdapter = new ImagelistitemAdapter(this, urls);
        imagelist.setAdapter(imagelistitemAdapter);
        imagelist.setOnItemClickListener(new AdapterView.OnItemClickListener() {
            @Override
            public void onItemClick(AdapterView<?> parent, View view, int position, long id) {
                String path = urls.get(position);
                String url = UserInfo.filehost + path.substring(5, path.length());
                new ImagePreview().startActivity(FundDetailActivity.this, url, 1);
            }
        });
    }

    @OnClick(R.id.ffjphvkr)
    public void onViewClicked() {
        if (paymentInfoBean.getVltd() == 0){
            new DecomposeFundActivity().startActivity(FundDetailActivity.this, paymentInfoBean);
        }
        else{
            Toast.makeText(this, "该财务回款已经分解过，不可以再次分解", Toast.LENGTH_SHORT).show();
        }

    }
}
