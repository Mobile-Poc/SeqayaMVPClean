package com.mesawer.chaty.seqayamvpclean.presentation.location;

import com.mesawer.chaty.seqayamvpclean.base.BasePresenter;
import com.mesawer.chaty.seqayamvpclean.base.BaseView;
import com.mesawer.chaty.seqayamvpclean.domain.entity.Location;

public interface LocationContract {

    interface View extends BaseView<Presenter> {

    }

    interface Presenter extends BasePresenter {

        void addLocation(Location location);
    }
}
