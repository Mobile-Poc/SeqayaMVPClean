package com.ntg.seqaya.seqayamvpclean.domain.usecase.orderhistory;

import com.ntg.seqaya.seqayamvpclean.base.UseCase;
import com.ntg.seqaya.seqayamvpclean.domain.entity.Order;
import com.ntg.seqaya.seqayamvpclean.domain.repository.IOrdersRepository;

import java.util.List;

public class GetOrderHistory implements UseCase<GetOrderHistory.RequestValues, GetOrderHistory.ResponseValues> {

    private IOrdersRepository ordersRepository;

    public GetOrderHistory(IOrdersRepository ordersRepository) {
        this.ordersRepository = ordersRepository;
    }

    @Override
    public void execute(RequestValues requestValue,
                        UseCaseSuccessCallback<ResponseValues> successCallback,
                        UseCaseErrorCallback errorCallback) {
        ordersRepository.getOrderHistory(orders -> {
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
