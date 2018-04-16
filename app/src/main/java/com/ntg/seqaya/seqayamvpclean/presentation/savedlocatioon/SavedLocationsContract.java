package com.ntg.seqaya.seqayamvpclean.presentation.savedlocatioon;

import com.ntg.seqaya.seqayamvpclean.base.BasePresenter;
import com.ntg.seqaya.seqayamvpclean.base.BaseView;
import com.ntg.seqaya.seqayamvpclean.domain.entity.Location;

import java.util.List;

public interface SavedLocationsContract {

    interface View extends BaseView<Presenter> {
        void showSavedLocations(List<Location> locations);

        void showNoLocations();
    }

    interface Presenter extends BasePresenter {

        void getSavedLocations();
    }
}
