package com.mesawer.chaty.seqayamvpclean.data.datasource;

import com.mesawer.chaty.seqayamvpclean.domain.entity.Order;
import com.mesawer.chaty.seqayamvpclean.domain.repository.ErrorCallback;
import com.mesawer.chaty.seqayamvpclean.domain.repository.SuccessCallback;

import java.util.List;

public interface OrdersDataSource {

    void addNewOrder(Order order,
                     SuccessCallback<Order> successCallback,
                     ErrorCallback errorCallback);

    void getOrderHistory(SuccessCallback<List<Order>> successCallback,
                         ErrorCallback errorCallback);
}
