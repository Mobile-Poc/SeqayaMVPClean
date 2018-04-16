package com.mesawer.chaty.seqayamvpclean.presentation.orderhistory;

import com.mesawer.chaty.seqayamvpclean.base.BasePresenter;
import com.mesawer.chaty.seqayamvpclean.base.BaseView;
import com.mesawer.chaty.seqayamvpclean.domain.entity.Order;

import java.util.List;

public interface OrderHistoryContract {

    interface View extends BaseView<Presenter> {
        void showOrderHistory(List<Order> orders);
    }

    interface Presenter extends BasePresenter {
        void getOrderHistory();
    }
}
