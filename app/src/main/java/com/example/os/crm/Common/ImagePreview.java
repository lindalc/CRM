package com.example.os.crm.Common;

import android.content.Context;
import android.content.Intent;
import android.net.Uri;
import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;
import android.widget.ImageView;

import com.bumptech.glide.Glide;
import com.example.os.crm.R;
import com.github.chrisbanes.photoview.PhotoView;

import butterknife.BindView;
import butterknife.ButterKnife;

public class ImagePreview extends AppCompatActivity {

    @BindView(R.id.photoview)
    PhotoView photoview;

    private Uri imageUri;
    private String imageUrl;
    private int type;
    public int URLTYPE = 1;
    public int URITYPE = 0;

    public void startActivity(Context context, String imageUri){
        Intent intent = new Intent(context, ImagePreview.class);
        intent.putExtra("imageUri", imageUri);
        context.startActivity(intent);
    }

    public void startActivity(Context context, String url, int type){
        Intent intent = new Intent(context, ImagePreview.class);
        intent.putExtra("type", type);
        intent.putExtra("imageUrl", url);
        context.startActivity(intent);
    }


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_image_preview);
        ButterKnife.bind(this);
        Intent intent = getIntent();
        this.type = intent.getIntExtra("type", 0);
        switch (type){
            case 0:
                this.imageUri = Uri.parse(intent.getStringExtra("imageUri"));
                initImage();
                break;
            case 1:
                this.imageUrl = intent.getStringExtra("imageUrl");
                break;

        }
        initImage();
    }

    private void initImage(){
        switch (type){
            case 0:
                photoview.setImageURI(imageUri);
                break;
            case 1:
                Glide.with(this).load(imageUrl).into(photoview);
                break;
        }
    }
}
