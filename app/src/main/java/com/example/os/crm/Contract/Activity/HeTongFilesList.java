package com.example.os.crm.Contract.Activity;

import android.app.Activity;
import android.app.AlertDialog;
import android.app.ProgressDialog;
import android.content.Context;
import android.content.DialogInterface;
import android.content.Intent;
import android.content.pm.PackageManager;
import android.os.Bundle;
import android.os.Environment;
import android.os.Handler;
import android.os.Message;
import android.support.v4.app.ActivityCompat;
import android.support.v7.app.AppCompatActivity;
import android.util.Log;
import android.view.View;
import android.widget.AdapterView;
import android.widget.ListView;

import com.arialyy.annotations.Download;
import com.arialyy.aria.core.Aria;
import com.arialyy.aria.core.download.DownloadTask;
import com.example.os.crm.Common.ImagePreview;
import com.example.os.crm.Common.OpenFileWithThird;
import com.example.os.crm.Common.UserInfo;
import com.example.os.crm.Contract.Model.DocFile;
import com.example.os.crm.Contract.Adapter.FilelistitemAdapter;
import com.example.os.crm.HttpUtil.HttpGet;
import com.example.os.crm.R;

import org.json.JSONArray;
import org.json.JSONObject;

import java.io.IOException;
import java.util.ArrayList;
import java.util.List;

import butterknife.BindView;
import butterknife.ButterKnife;
import okhttp3.Call;
import okhttp3.Callback;
import okhttp3.Response;

public class HeTongFilesList extends AppCompatActivity {

    @BindView(R.id.hetongfiles)
    ListView hetongfiles;
    Handler handler;
    String dingdanbianhao;
    List<DocFile> docFileList = new ArrayList<>();
    FilelistitemAdapter filelistitemAdapter;
    ProgressDialog progressDialog;
    String url;
    private String TAG = "合同文件列表！";
    private static final int REQUEST_EXTERNAL_STORAGE = 1;
    private static String[] PERMISSIONS_STORAGE = {
            "android.permission.READ_EXTERNAL_STORAGE",
            "android.permission.WRITE_EXTERNAL_STORAGE"
    };

    public void actionStart(Context context, String dingdanbianhao){
        Intent intent = new Intent(context, HeTongFilesList.class);
        intent.putExtra("dingdanbianhao", dingdanbianhao);
        context.startActivity(intent);
    }

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_he_tong_files_list);
        ButterKnife.bind(this);
        verifyStoragePermissions(this);
        initParams();
        Aria.download(this).register();
        initHandler();
        getData();
    }

    private void initParams() {
        final Intent intent = getIntent();
        dingdanbianhao = intent.getStringExtra("dingdanbianhao");
        filelistitemAdapter = new FilelistitemAdapter(HeTongFilesList.this, docFileList);
        hetongfiles.setOnItemClickListener(new AdapterView.OnItemClickListener() {
            @Override
            public void onItemClick(AdapterView<?> parent, View view, int position, long id) {
                DocFile docFile = filelistitemAdapter.getItem(position);
                String url_temp = docFile.getPath();
                url_temp = url_temp.substring(5, url_temp.length());
                url = UserInfo.filehost + url_temp;
                new ImagePreview().startActivity(HeTongFilesList.this, url, 1);
            }
        });
        hetongfiles.setOnItemLongClickListener(new AdapterView.OnItemLongClickListener() {
            @Override
            public boolean onItemLongClick(AdapterView<?> parent, View view, int position, long id) {
                String DOWNLOAD_PATH = Environment.getExternalStorageDirectory().getAbsolutePath() + "/com.example.os.crm/files/temp/download";
                DocFile docFile = filelistitemAdapter.getItem(position);
                String url_temp = docFile.getPath();
                url_temp = url_temp.substring(5, url_temp.length());
                url = UserInfo.filehost + url_temp;
                DOWNLOAD_PATH += url_temp;
                startDownload(DOWNLOAD_PATH);
                return false;
            }
        });
    }
    private void initHandler() {
        handler = new Handler(){
                    public void handleMessage(Message msg){
                        switch (msg.what){
                            case 0:
                                break;
                            case 1:
                                setData(msg.obj.toString());
                                break;
                            default:
                                break;
                        }
                    }
                };
    }
    @Override protected void onDestroy() {
        super.onDestroy();
        Aria.download(this).unRegister();
    }
    private void getData(){
        String url = UserInfo.host + "/crm/gethetongfiles/" + dingdanbianhao;
        new HttpGet().getData(url, new Callback() {
            @Override
            public void onFailure(Call call, IOException e) {
                Message msg = new Message();
                msg.what = 0;
                handler.sendMessage(msg);
            }

            @Override
            public void onResponse(Call call, Response response) throws IOException {
                Message msg = new Message();
                msg.what = 1;
                msg.obj = response.body().string();
                handler.sendMessage(msg);
            }
        });
    }
    private void setData(String response){
        docFileList.clear();
        try{
            JSONObject jsonObject = new JSONObject(response);
            JSONArray jsonArray = jsonObject.getJSONArray("data");
            for(int i = 0; i< jsonArray.length();i++){
                DocFile docFile = new DocFile(jsonArray.getString(i));
                docFileList.add(docFile);
            }
            hetongfiles.setAdapter(filelistitemAdapter);

        }catch (Exception e){

        }
    }
    @Download.onTaskComplete void onTaskComplete(DownloadTask task) {
        if(task.getKey().equals(url)){
            Log.d(TAG, "onTaskComplete: ");
            progressDialog.dismiss();
            if (task.getDownloadPath() != null)
            {
                String path = task.getDownloadPath();
                Intent intent = new OpenFileWithThird(this).openFile(path);
                startActivity(intent);
            }
        }
    }
    @Download.onTaskFail void onTeskFail(DownloadTask task){
        if (task.getKey().equals(url)){
            showDialog("下载失败!!");
        }
    }
    private void showProgressDialog() {
        progressDialog = new ProgressDialog(HeTongFilesList.this);
        progressDialog.setTitle("下载中");
        progressDialog.setMessage("。。。。。。。。。。。。。");
        progressDialog.show();
    }
    private void startDownload(String DOWNLOAD_PATH){
        showProgressDialog();
        Aria.download(this)
                .load(url)     //读取下载地址
                .setDownloadPath(DOWNLOAD_PATH)    //设置文件保存的完整路径
                .start();   //启动下载
    }
    private void showDialog(String info){
        AlertDialog.Builder dialog = new AlertDialog.Builder(this);
        dialog.setMessage(info);
        dialog.setTitle("");
        dialog.setPositiveButton("知道了", new DialogInterface.OnClickListener() {
            @Override
            public void onClick(DialogInterface dialog, int which) {

            }
        });
        dialog.show();
    }
    public static void verifyStoragePermissions(Activity activity) {

        try {
            //检测是否有写的权限
            int permission = ActivityCompat.checkSelfPermission(activity,
                    "android.permission.WRITE_EXTERNAL_STORAGE");
            if (permission != PackageManager.PERMISSION_GRANTED) {
                // 没有写的权限，去申请写的权限，会弹出对话框
                ActivityCompat.requestPermissions(activity, PERMISSIONS_STORAGE, REQUEST_EXTERNAL_STORAGE);
            }
        } catch (Exception e) {
            e.printStackTrace();
        }
    }
}
