package com.ntg.seqaya.seqayamvpclean.domain.entity;

import com.google.gson.annotations.SerializedName;

import java.io.Serializable;

/**
 * Created by ilias on 28/03/2018.
 */

public class Location implements Serializable {

    private String id;
    private String address;
    @SerializedName("lat")
    private double latitude;
    @SerializedName("long")
    private double longitude;

    public Location(String address, double latitude, double longitude) {
        this.address = address;
        this.latitude = latitude;
        this.longitude = longitude;
    }

    public String getId() {
        return id;
    }

    public void setId(String id) {
        this.id = id;
    }

    public String getAddress() {
        return address;
    }

    public void setAddress(String address) {
        this.address = address;
    }

    public double getLatitude() {
        return latitude;
    }

    public void setLatitude(double latitude) {
        this.latitude = latitude;
    }

    public double getLongitude() {
        return longitude;
    }

    public void setLongitude(double longitude) {
        this.longitude = longitude;
    }
}
