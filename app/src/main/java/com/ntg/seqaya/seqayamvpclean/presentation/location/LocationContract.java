package com.ntg.seqaya.seqayamvpclean.presentation.location;

import com.ntg.seqaya.seqayamvpclean.base.BasePresenter;
import com.ntg.seqaya.seqayamvpclean.base.BaseView;

public interface LocationContract {

    interface View extends BaseView<Presenter> {
        void addLocationSuccess();
        void addLocationFail();
    }

    interface Presenter extends BasePresenter {

        void addLocation(boolean flag);
    }
}
