package com.mesawer.chaty.seqayamvpclean.presentation.orderhistory;

import com.mesawer.chaty.seqayamvpclean.base.UseCaseHandler;
import com.mesawer.chaty.seqayamvpclean.base.UseCase;
import com.mesawer.chaty.seqayamvpclean.domain.usecase.orderhistory.GetOrderHistory;

public class OrderHistoryPresenter implements OrderHistoryContract.Presenter {

    private UseCaseHandler useCaseHandler;
    private OrderHistoryContract.View orderHistoryView;
    private GetOrderHistory getOrderHistory;

    public OrderHistoryPresenter(UseCaseHandler useCaseHandler,
                                 OrderHistoryContract.View orderHistoryView,
                                 GetOrderHistory getOrderHistory) {
        this.useCaseHandler = useCaseHandler;
        this.orderHistoryView = orderHistoryView;
        this.getOrderHistory = getOrderHistory;
    }

    @Override
    public void getOrderHistory() {
        useCaseHandler.execute(getOrderHistory, null,
                new UseCase.UseCaseCallback<GetOrderHistory.ResponseValues>() {
                    @Override
                    public void onSuccess(GetOrderHistory.ResponseValues response) {
                        orderHistoryView.showOrderHistory(response.getOrders());
                    }

                    @Override
                    public void onError(String errMsg) {
                        orderHistoryView.showErrorMessage(errMsg);
                    }
                });
    }
}
