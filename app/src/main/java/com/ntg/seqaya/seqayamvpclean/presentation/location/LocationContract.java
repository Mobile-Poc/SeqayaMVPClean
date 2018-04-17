package com.ntg.seqaya.seqayamvpclean.presentation.location;

import com.ntg.seqaya.seqayamvpclean.base.BasePresenter;
import com.ntg.seqaya.seqayamvpclean.base.BaseView;
import com.ntg.seqaya.seqayamvpclean.domain.entity.Location;

public interface LocationContract {

    interface View extends BaseView<Presenter> {

    }

    interface Presenter extends BasePresenter {

        void addLocation(Location location);
    }
}
