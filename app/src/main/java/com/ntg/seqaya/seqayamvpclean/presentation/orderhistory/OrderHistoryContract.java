package com.ntg.seqaya.seqayamvpclean.presentation.orderhistory;

import com.ntg.seqaya.seqayamvpclean.base.BasePresenter;
import com.ntg.seqaya.seqayamvpclean.base.BaseView;
import com.ntg.seqaya.seqayamvpclean.domain.entity.Order;

import java.util.List;

public interface OrderHistoryContract {

    interface View extends BaseView<Presenter> {
        void showOrderHistory(List<Order> orders);
    }

    interface Presenter extends BasePresenter {
        void getOrderHistory();
    }
}
