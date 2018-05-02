package com.example.os.crm.Contract.Model;

import com.example.os.crm.Common.UserInfo;
import com.example.os.crm.R;

/**
 * Created by OS on 2018/1/27.
 */

public class DocFile {
    private String name;
    private int imageId;
    private String path;
    private String ext;



    public DocFile(String path){
        this.path = path;
        String filename;
        int namelen = 0;
        filename = path.substring(path.lastIndexOf("/")+1, path.length());
        namelen = filename.indexOf(".");
        this.ext = filename.substring(namelen+1, filename.length());
        setImageId(ext);
        setName(filename);
    }
    public String getExt(){
        return ext;
    }
    public String getName(){
        return name;
    }
    public int getImageId(){
        return imageId;
    }
    public String getPath(){
        return path;
    }
    public String getUrl(){
        String url;
        url = UserInfo.filehost + path.substring(5, path.length());
        return url;
    }
    private void setImageId(String ext){
        if (ext.equals("jpg") || ext.equals("JPG") || ext.equals("JPEG") || ext.equals("jpeg")){
            this.imageId = R.mipmap.tupian;
        }
        else if(ext.equals("mp4")){
            this.imageId = R.mipmap.movie;
        }
        else if(ext.equals("doc") || ext.equals("docx")){
            this.imageId = R.mipmap.word;
        }
        else if (ext.equals("xls") || ext.equals("xlsx")){
            this.imageId = R.mipmap.excel;
        }
        else{
            this.imageId = R.mipmap.file;
        }
    }
    private void setName(String name){
        this.name = name;
    }
}
