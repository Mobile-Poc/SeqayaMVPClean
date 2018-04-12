package com.mesawer.chaty.seqayamvpclean.data.datasource.remote;

import com.mesawer.chaty.seqayamvpclean.data.datasource.OrdersDataSource;
import com.mesawer.chaty.seqayamvpclean.data.datasource.remote.network.ApiClient;
import com.mesawer.chaty.seqayamvpclean.data.datasource.remote.network.ProductService;
import com.mesawer.chaty.seqayamvpclean.domain.entity.Order;
import com.mesawer.chaty.seqayamvpclean.domain.repository.ErrorCallback;
import com.mesawer.chaty.seqayamvpclean.domain.repository.SuccessCallback;

import java.util.List;

import retrofit2.Call;
import retrofit2.Callback;
import retrofit2.Response;

public class OrdersRemoteDataSource implements OrdersDataSource {

    private static OrdersRemoteDataSource INSTANCE;

    private OrdersRemoteDataSource() {
    }

    public static OrdersRemoteDataSource getInstance() {
        if (INSTANCE == null) INSTANCE = new OrdersRemoteDataSource();
        return INSTANCE;
    }

    @Override
    public void addNewOrder(Order order,
                            SuccessCallback<Order> successCallback,
                            ErrorCallback errorCallback) {
        ProductService productService = ApiClient.getClient().create(ProductService.class);
        Call<Order> call = productService.addNewOrder(order);
        call.enqueue(new Callback<Order>() {
            @Override
            public void onResponse(Call<Order> call, Response<Order> response) {
                if (response.isSuccessful()) {
                    Order responseOrder = response.body();
                    successCallback.onSuccess(responseOrder);
                }

            }

            @Override
            public void onFailure(Call<Order> call, Throwable t) {
                errorCallback.onError("تأكد من اتصال الانترنت");
            }
        });

    }

    @Override
    public void getOrderHistory(SuccessCallback<List<Order>> successCallback,
                                ErrorCallback errorCallback) {

    }
}
