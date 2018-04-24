package com.ntg.seqaya.seqayamvpclean.data.datasource.remote;

import com.ntg.seqaya.seqayamvpclean.data.datasource.OrdersDataSource;
import com.ntg.seqaya.seqayamvpclean.domain.entity.Order;
import com.ntg.seqaya.seqayamvpclean.domain.repository.ErrorCallback;
import com.ntg.seqaya.seqayamvpclean.domain.repository.SuccessCallback;

import java.util.List;

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

    }

    @Override
    public void getOrderHistory(SuccessCallback<List<Order>> successCallback,
                                ErrorCallback errorCallback) {
//        try {
//            Response<List<Order>> response = ApiClient.getClient().create(ProductService.class)
//                    .getOrderHistory(User.getEmail()).execute();
//            if (response.isSuccessful()) {
//                List<Order> orders = response.body();
//                successCallback.onSuccess(orders);
//            } else {
//                try {
//                    errorCallback.onError(apiErrMsg(response.errorBody().string()));
//                } catch (IOException e) {
//                    e.printStackTrace();
//                }
//            }
//        } catch (IOException e) {
//            errorCallback.onError("تأكد من اتصال الانترنت");
//        }
    }
}
