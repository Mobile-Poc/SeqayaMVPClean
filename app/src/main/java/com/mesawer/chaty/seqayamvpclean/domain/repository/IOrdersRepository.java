package com.mesawer.chaty.seqayamvpclean.domain.repository;

import com.mesawer.chaty.seqayamvpclean.domain.entity.Order;

import java.util.List;

public interface IOrdersRepository {

    void addNewOrder(Order order,
                     SuccessCallback<Order> successCallback,
                     ErrorCallback errorCallback);

    void getOrderHistory(SuccessCallback<List<Order>> successCallback,
                         ErrorCallback errorCallback);
}
