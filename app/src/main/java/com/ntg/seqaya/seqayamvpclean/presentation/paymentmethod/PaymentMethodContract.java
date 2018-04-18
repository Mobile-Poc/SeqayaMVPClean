package com.ntg.seqaya.seqayamvpclean.presentation.paymentmethod;

import com.ntg.seqaya.seqayamvpclean.base.BasePresenter;
import com.ntg.seqaya.seqayamvpclean.base.BaseView;
import com.ntg.seqaya.seqayamvpclean.domain.entity.Order;

public interface PaymentMethodContract {

    interface View extends BaseView<Presenter> {
        void navigateToOrderSummeryFragment(Order order);
    }

    interface Presenter extends BasePresenter {
        void addOrder(Order order);
    }
}
