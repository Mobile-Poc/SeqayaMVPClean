package com.mesawer.chaty.seqayamvpclean.domain.usecase.orderhistory;

import com.mesawer.chaty.seqayamvpclean.base.UseCase;
import com.mesawer.chaty.seqayamvpclean.domain.entity.Order;
import com.mesawer.chaty.seqayamvpclean.domain.repository.IOrdersRepository;

import java.util.List;

public class GetOrderHistory extends
        UseCase<GetOrderHistory.RequestValues,GetOrderHistory.ResponseValues> {

    private IOrdersRepository ordersRepository;

    public GetOrderHistory(IOrdersRepository ordersRepository) {
        this.ordersRepository = ordersRepository;
    }

    @Override
    protected void executeUseCase(RequestValues requestValues) {
        ordersRepository.getOrderHistory(orders -> {
            ResponseValues responseValues = new ResponseValues(orders);
            getUseCaseCallback().onSuccess(responseValues);
        }, getUseCaseCallback()::onError);
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
