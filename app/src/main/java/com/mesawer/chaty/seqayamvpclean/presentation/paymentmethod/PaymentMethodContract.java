package com.mesawer.chaty.seqayamvpclean.presentation.paymentmethod;

import com.mesawer.chaty.seqayamvpclean.base.BasePresenter;
import com.mesawer.chaty.seqayamvpclean.base.BaseView;
import com.mesawer.chaty.seqayamvpclean.domain.entity.Order;

public interface PaymentMethodContract {

    interface View extends BaseView<Presenter> {
        void navigateToOrderSummeryFragment(Order order);
    }

    interface Presenter extends BasePresenter {
        void addOrder(Order order);
    }
}
