package com.mesawer.chaty.seqayamvpclean.presentation.savedlocatioon;

import com.mesawer.chaty.seqayamvpclean.domain.usecase.savedlocation.GetSavedLocations;

public class SavedLocationsPresenter implements SavedLocationsContract.Presenter {

    private SavedLocationsContract.View savedLocationsView;
    private GetSavedLocations getSavedLocations;

    public SavedLocationsPresenter(SavedLocationsContract.View savedLocationsView,
                                   GetSavedLocations getSavedLocations) {
        this.savedLocationsView = savedLocationsView;
        this.getSavedLocations = getSavedLocations;
    }

    @Override
    public void getSavedLocations(String userId) {
        getSavedLocations.execute(null,
                response -> {
                    if (response.getLocations().isEmpty()){
                        savedLocationsView.showNoLocations();
                    } else {
                        savedLocationsView.showSavedLocations(response.getLocations());
                    }
                }, savedLocationsView::showErrorMessage);
    }
}
