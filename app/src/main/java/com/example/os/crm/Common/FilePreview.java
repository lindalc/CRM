package com.example.os.crm.Common;

import android.content.Intent;
import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;
import android.util.Log;
import android.webkit.WebChromeClient;
import android.webkit.WebView;
import android.widget.Button;

import com.example.os.crm.R;

import butterknife.BindView;
import butterknife.ButterKnife;
import butterknife.OnClick;

public class FilePreview extends AppCompatActivity {

    @BindView(R.id.filepreview)
    WebView filepreview;
    @BindView(R.id.downloadfile)
    Button downloadfile;

    private String url;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_file_preview);
        Intent intent = getIntent();
        url = intent.getStringExtra("url");
        ButterKnife.bind(this);

        initPreView();
    }

    private void initPreView() {
        filepreview.setWebChromeClient(new WebChromeClient());
        filepreview.loadUrl(url);
    }

    @OnClick(R.id.downloadfile)
    public void onViewClicked() {
        Log.d("FilePreview", "onViewClicked: test");
    }
}
