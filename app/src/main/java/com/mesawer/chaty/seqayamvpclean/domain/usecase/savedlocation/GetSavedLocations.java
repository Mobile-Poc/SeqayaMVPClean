package com.mesawer.chaty.seqayamvpclean.domain.usecase.savedlocation;

import com.mesawer.chaty.seqayamvpclean.base.UseCase;
import com.mesawer.chaty.seqayamvpclean.domain.IProductsRepository;
import com.mesawer.chaty.seqayamvpclean.domain.entity.Location;

import java.util.List;

public class GetSavedLocations implements
        UseCase<GetSavedLocations.RequestValues, GetSavedLocations.ResponseValues> {

    private IProductsRepository productsRepository;

    public GetSavedLocations(IProductsRepository productsRepository) {
        this.productsRepository = productsRepository;
    }

    @Override
    public void execute(RequestValues requestValue,
                        UseCaseSuccessCallback<ResponseValues> successCallback,
                        UseCaseErrorCallback errorCallback) {
        productsRepository.getSavedLocations(locations -> {
            ResponseValues responseValues = new ResponseValues(locations);
            successCallback.onSuccess(responseValues);
        }, errorCallback::onError);
    }

    public static final class RequestValues implements UseCase.RequestValues {
    }

    public static final class ResponseValues implements UseCase.ResponseValues {
        private List<Location> locations;

        public ResponseValues(List<Location> locations) {
            this.locations = locations;
        }

        public List<Location> getLocations() {
            return locations;
        }
    }
}
