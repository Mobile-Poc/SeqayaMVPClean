package com.mesawer.chaty.seqayamvpclean.domain.usecase.orderhistory;

import com.mesawer.chaty.seqayamvpclean.base.UseCase;
import com.mesawer.chaty.seqayamvpclean.domain.IProductsRepository;
import com.mesawer.chaty.seqayamvpclean.domain.entity.Order;

import java.util.List;

public class GetOrderHistory implements UseCase<GetOrderHistory.RequestValues, GetOrderHistory.ResponseValues> {

    private IProductsRepository productsRepository;

    public GetOrderHistory(IProductsRepository productsRepository) {
        this.productsRepository = productsRepository;
    }

    @Override
    public void execute(RequestValues requestValue,
                        UseCaseSuccessCallback<ResponseValues> successCallback,
                        UseCaseErrorCallback errorCallback) {
        productsRepository.getOrderHistory(orders -> {
                    ResponseValues responseValues = new ResponseValues(orders);
                    successCallback.onSuccess(responseValues);
                }, errorCallback::onError);
    }

    public static final class RequestValues implements UseCase.RequestValues {
    }

    public static final class ResponseValues implements UseCase.ResponseValues {
        private List<Order> orders;

        public ResponseValues(List<Order> orders) {
            this.orders = orders;
        }

        public List<Order> getOrders() {
            return orders;
        }
    }
}
