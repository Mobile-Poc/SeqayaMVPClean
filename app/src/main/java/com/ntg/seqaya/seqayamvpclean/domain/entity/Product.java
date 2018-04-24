package com.ntg.seqaya.seqayamvpclean.domain.entity;

import java.io.Serializable;

/**
 * Created by islam on 3/26/2018.
 */

public class Product implements Serializable {

    private int id;
    private String name;
    private String manufacturerName;
    private String photoUrl;
    private float bottleSize;
    private int no_bpp;
    private int price;
    private boolean isLiked;

    public boolean isLiked() {
        return isLiked;
    }

    public void setLiked(boolean liked) {
        isLiked = liked;
    }

    public Product() {

    }

    public int getId() {
        return id;
    }

    public String getName() {
        return name;
    }

    public String getManufacturerName() {
        return manufacturerName;
    }

    public String getPhotoUrl() {
        return photoUrl;
    }


    public int getNo_bpp() {
        return no_bpp;
    }

    public int getPrice() {
        return price;
    }

    public float getBottleSize() {
        return bottleSize;
    }

    public void setBottleSize(float bottleSize) {
        this.bottleSize = bottleSize;
    }
}
