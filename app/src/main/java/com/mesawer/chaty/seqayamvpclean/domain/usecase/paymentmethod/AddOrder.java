package com.mesawer.chaty.seqayamvpclean.domain.usecase.paymentmethod;

import com.mesawer.chaty.seqayamvpclean.base.UseCase;
import com.mesawer.chaty.seqayamvpclean.domain.entity.Order;
import com.mesawer.chaty.seqayamvpclean.domain.repository.ErrorCallback;
import com.mesawer.chaty.seqayamvpclean.domain.repository.IOrdersRepository;
import com.mesawer.chaty.seqayamvpclean.domain.repository.SuccessCallback;

public class AddOrder implements UseCase<AddOrder.RequestValues, AddOrder.ResponseValues> {

    private IOrdersRepository ordersRepository;

    public AddOrder(IOrdersRepository ordersRepository) {
        this.ordersRepository = ordersRepository;
    }

    @Override
    public void execute(RequestValues requestValue,
                        UseCaseSuccessCallback<ResponseValues> successCallback,
                        UseCaseErrorCallback errorCallback) {
        ordersRepository.addNewOrder(requestValue.getOrder(), new SuccessCallback<Order>() {
            @Override
            public void onSuccess(Order result) {
                ResponseValues responseValues = new ResponseValues(result);
                successCallback.onSuccess(responseValues);
            }
        }, new ErrorCallback() {
            @Override
            public void onError(String errMsg) {
                errorCallback.onError(errMsg);
            }
        }/*,
                order -> {
            ResponseValues responseValues = new ResponseValues(order);
            successCallback.onSuccess(responseValues);
                }, errorCallback::onError*/);
    }

    public static final class RequestValues implements UseCase.RequestValues {
        private Order order;

        public RequestValues(Order order) {
            this.order = order;
        }

        public Order getOrder() {
            return order;
        }
    }

    public static final class ResponseValues implements UseCase.ResponseValues {
        private Order order;

        public ResponseValues(Order order) {
            this.order = order;
        }

        public Order getOrder() {
            return order;
        }
    }
}
