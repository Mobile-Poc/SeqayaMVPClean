package com.ntg.seqaya.seqayamvpclean.presentation.orderhistory;

import com.ntg.seqaya.seqayamvpclean.domain.usecase.orderhistory.GetOrderHistory;

public class OrderHistoryPresenter implements OrderHistoryContract.Presenter {

    private OrderHistoryContract.View orderHistoryView;
    private GetOrderHistory getOrderHistory;

    public OrderHistoryPresenter(OrderHistoryContract.View orderHistoryView,
                                 GetOrderHistory getOrderHistory) {
        this.orderHistoryView = orderHistoryView;
        this.getOrderHistory = getOrderHistory;
    }

    @Override
    public void getOrderHistory() {
        getOrderHistory.execute(null,
                responseValues -> orderHistoryView.showOrderHistory(responseValues.getOrders()),
                orderHistoryView::showErrorMessage);
    }
}
