package com.mesawer.chaty.seqayamvpclean.presentation.cart;

import com.mesawer.chaty.seqayamvpclean.base.BasePresenter;
import com.mesawer.chaty.seqayamvpclean.base.BaseView;

public interface CartContract {

    interface View extends BaseView<Presenter> {
        void navigateToMapActivity();

        void navigateToDeliveryTimeFragment();

        void showNoItems();
    }

    interface Presenter extends BasePresenter {

    }
}
