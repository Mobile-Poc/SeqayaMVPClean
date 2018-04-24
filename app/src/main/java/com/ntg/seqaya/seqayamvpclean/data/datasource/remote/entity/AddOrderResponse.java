package com.ntg.seqaya.seqayamvpclean.data.datasource.remote.entity;

import java.io.Serializable;

public class AddOrderResponse implements Serializable {

    private String orderNumber;
    private double total;

    public AddOrderResponse(String orderNumber, double total) {
        this.orderNumber = orderNumber;
        this.total = total;
    }

    public String getOrderNumber() {
        return orderNumber;
    }

    public void setOrderNumber(String orderNumber) {
        this.orderNumber = orderNumber;
    }

    public double getTotal() {
        return total;
    }

    public void setTotal(double total) {
        this.total = total;
    }
}
