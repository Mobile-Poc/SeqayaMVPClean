package com.ntg.seqaya.seqayamvpclean.data.repository;

import com.ntg.seqaya.seqayamvpclean.data.datasource.OrdersDataSource;
import com.ntg.seqaya.seqayamvpclean.data.datasource.ProductsDataSource;
import com.ntg.seqaya.seqayamvpclean.domain.entity.Order;
import com.ntg.seqaya.seqayamvpclean.domain.entity.Product;
import com.ntg.seqaya.seqayamvpclean.domain.repository.ErrorCallback;
import com.ntg.seqaya.seqayamvpclean.domain.repository.IOrdersRepository;
import com.ntg.seqaya.seqayamvpclean.domain.repository.IProductsRepository;
import com.ntg.seqaya.seqayamvpclean.domain.repository.SuccessCallback;

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
