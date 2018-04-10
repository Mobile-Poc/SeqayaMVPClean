package com.mesawer.chaty.seqayamvpclean.data.repository;

import com.mesawer.chaty.seqayamvpclean.data.datasource.OrdersDataSource;
import com.mesawer.chaty.seqayamvpclean.data.datasource.ProductsDataSource;
import com.mesawer.chaty.seqayamvpclean.domain.entity.Order;
import com.mesawer.chaty.seqayamvpclean.domain.entity.Product;
import com.mesawer.chaty.seqayamvpclean.domain.repository.ErrorCallback;
import com.mesawer.chaty.seqayamvpclean.domain.repository.IOrdersRepository;
import com.mesawer.chaty.seqayamvpclean.domain.repository.IProductsRepository;
import com.mesawer.chaty.seqayamvpclean.domain.repository.SuccessCallback;

import java.util.List;

public class OrdersRepository implements IOrdersRepository {

    private static OrdersRepository INSTANCE;
    private OrdersDataSource ordersDataSource;

    private OrdersRepository(OrdersDataSource ordersDataSource) {
        this.ordersDataSource = ordersDataSource;
    }

    public static OrdersRepository getInstance(OrdersDataSource ordersDataSource) {
        if (INSTANCE == null) INSTANCE = new OrdersRepository(ordersDataSource);
        return INSTANCE;
    }

    @Override
    public void addNewOrder(Order order,
                            SuccessCallback<Order> successCallback,
                            ErrorCallback errorCallback) {

    }

    @Override
    public void getOrderHistory(SuccessCallback<List<Order>> successCallback,
                                ErrorCallback errorCallback) {
        ordersDataSource.getOrderHistory(successCallback, errorCallback);
    }
}
