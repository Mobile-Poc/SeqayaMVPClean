package com.mesawer.chaty.seqayamvpclean.presentation.ordersummery;

import com.mesawer.chaty.seqayamvpclean.base.BasePresenter;
import com.mesawer.chaty.seqayamvpclean.base.BaseView;

public interface OrderSummeryContract {

    interface View extends BaseView<Presenter> {
        void navigateToMainActivity();
    }

    interface Presenter extends BasePresenter {

    }
}
