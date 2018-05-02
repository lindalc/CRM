package com.example.os.crm.model;

import java.io.Serializable;

/**
 * Created by ninos on 2016/7/11.
 */
public class Image implements Serializable {

    public Image(){}
    public Image(String FilePath){
        this.FilePath = FilePath;
        this.ImagePath = FilePath;
    }
    public Image(String ImagePath, int i){
        this.ImagePath = ImagePath;
    }
    public int Id;
    public String ImagePath;
    public int ParentId;
    public String PictureUrl;
    public String FilePath;
}
