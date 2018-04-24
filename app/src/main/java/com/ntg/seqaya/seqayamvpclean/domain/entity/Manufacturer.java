package com.ntg.seqaya.seqayamvpclean.domain.entity;

import java.io.Serializable;

public class Manufacturer implements Serializable {

    private int id;
    private String name;
    private String photoUrl;

    public Manufacturer() {
    }

    public Manufacturer(int id, String name, String photoUrl) {
        this.id = id;
        this.name = name;
        this.photoUrl = photoUrl;
    }

    public int getId() {
        return id;
    }

    public void setId(int id) {
        this.id = id;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public String getPhotoUrl() {
        return photoUrl;
    }

    public void setPhotoUrl(String photoUrl) {
        this.photoUrl = photoUrl;
    }
}
