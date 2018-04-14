package com.mesawer.chaty.seqayamvpclean.data.datasource.remote;

import android.support.annotation.NonNull;

import com.mesawer.chaty.seqayamvpclean.data.datasource.OrdersDataSource;
import com.mesawer.chaty.seqayamvpclean.data.datasource.remote.network.ApiClient;
import com.mesawer.chaty.seqayamvpclean.data.datasource.remote.network.ProductService;
import com.mesawer.chaty.seqayamvpclean.domain.entity.Order;
import com.mesawer.chaty.seqayamvpclean.domain.entity.User;
import com.mesawer.chaty.seqayamvpclean.domain.repository.ErrorCallback;
import com.mesawer.chaty.seqayamvpclean.domain.repository.SuccessCallback;

import java.io.IOException;
import java.util.List;

import retrofit2.Call;
import retrofit2.Callback;
import retrofit2.Response;

import static com.mesawer.chaty.seqayamvpclean.data.datasource.remote.network.Util.apiErrMsg;

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
        try {
            Response<List<Order>> response = ApiClient.getClient().create(ProductService.class)
                    .getOrderHistory(User.getEmail()).execute();
            if (response.isSuccessful()) {
                List<Order> orders = response.body();
                successCallback.onSuccess(orders);
            } else {
                try {
                    errorCallback.onError(apiErrMsg(response.errorBody().string()));
                } catch (IOException e) {
                    e.printStackTrace();
                }
            }
        } catch (IOException e) {
            errorCallback.onError("تأكد من اتصال الانترنت");
        }
    }
}
