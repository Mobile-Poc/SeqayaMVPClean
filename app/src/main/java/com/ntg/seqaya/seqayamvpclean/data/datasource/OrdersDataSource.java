package com.ntg.seqaya.seqayamvpclean.data.datasource;

import com.ntg.seqaya.seqayamvpclean.domain.entity.Order;
import com.ntg.seqaya.seqayamvpclean.domain.repository.ErrorCallback;
import com.ntg.seqaya.seqayamvpclean.domain.repository.SuccessCallback;

import java.util.List;

public interface OrdersDataSource {

    void addNewOrder(Order order,
                     SuccessCallback<Order> successCallback,
                     ErrorCallback errorCallback);

    void getOrderHistory(SuccessCallback<List<Order>> successCallback,
                         ErrorCallback errorCallback);
}
