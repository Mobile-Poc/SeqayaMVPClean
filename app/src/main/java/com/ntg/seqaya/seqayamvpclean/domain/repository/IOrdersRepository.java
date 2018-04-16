package com.ntg.seqaya.seqayamvpclean.domain.repository;

import com.ntg.seqaya.seqayamvpclean.domain.entity.Order;

import java.util.List;

public interface IOrdersRepository {

    void addNewOrder(Order order,
                     SuccessCallback<Order> successCallback,
                     ErrorCallback errorCallback);

    void getOrderHistory(SuccessCallback<List<Order>> successCallback,
                         ErrorCallback errorCallback);
}
