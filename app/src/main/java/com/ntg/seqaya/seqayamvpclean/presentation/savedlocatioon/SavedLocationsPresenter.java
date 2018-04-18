package com.ntg.seqaya.seqayamvpclean.presentation.savedlocatioon;

import com.ntg.seqaya.seqayamvpclean.base.UseCaseHandler;
import com.ntg.seqaya.seqayamvpclean.base.UseCase;
import com.ntg.seqaya.seqayamvpclean.domain.usecase.savedlocation.GetSavedLocations;

public class SavedLocationsPresenter implements SavedLocationsContract.Presenter {

    private UseCaseHandler useCaseHandler;
    private SavedLocationsContract.View savedLocationsView;
    private GetSavedLocations getSavedLocations;

    public SavedLocationsPresenter(UseCaseHandler useCaseHandler,
                                   SavedLocationsContract.View savedLocationsView,
                                   GetSavedLocations getSavedLocations) {
        this.useCaseHandler = useCaseHandler;
        this.savedLocationsView = savedLocationsView;
        this.getSavedLocations = getSavedLocations;
    }

    @Override
    public void getSavedLocations() {
        useCaseHandler.execute(getSavedLocations, null,
                new UseCase.UseCaseCallback<GetSavedLocations.ResponseValues>() {
                    @Override
                    public void onSuccess(GetSavedLocations.ResponseValues response) {
                        if (response.getLocations().isEmpty()) {
                            savedLocationsView.showNoLocations();
                        } else {
                            savedLocationsView.showSavedLocations(response.getLocations());
                        }
                    }

                    @Override
                    public void onError(String errMsg) {
                        savedLocationsView.showErrorMessage(errMsg);
                    }
                });
    }
}
