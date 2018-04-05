package com.mesawer.chaty.seqayamvpclean.presentation.savedlocatioon;

import com.mesawer.chaty.seqayamvpclean.base.BasePresenter;
import com.mesawer.chaty.seqayamvpclean.base.BaseView;
import com.mesawer.chaty.seqayamvpclean.domain.entity.Location;

public interface SavedLocationsContract {

    interface View extends BaseView<Presenter> {

    }

    interface Presenter extends BasePresenter {

        void getSavedLocations(String userId);
    }
}
