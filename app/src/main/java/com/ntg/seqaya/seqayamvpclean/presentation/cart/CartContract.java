package com.ntg.seqaya.seqayamvpclean.presentation.cart;

import com.ntg.seqaya.seqayamvpclean.base.BasePresenter;
import com.ntg.seqaya.seqayamvpclean.base.BaseView;

public interface CartContract {

    interface View extends BaseView<Presenter> {
        void navigateToMapActivity();

        void navigateToDeliveryTimeFragment();

        void showNoItems();
    }

    interface Presenter extends BasePresenter {

    }
}
