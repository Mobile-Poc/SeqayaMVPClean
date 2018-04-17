package com.ntg.seqaya.seqayamvpclean.presentation.ordersummery;

import com.ntg.seqaya.seqayamvpclean.base.BasePresenter;
import com.ntg.seqaya.seqayamvpclean.base.BaseView;

public interface OrderSummeryContract {

    interface View extends BaseView<Presenter> {
        void navigateToMainActivity();
    }

    interface Presenter extends BasePresenter {

    }
}
