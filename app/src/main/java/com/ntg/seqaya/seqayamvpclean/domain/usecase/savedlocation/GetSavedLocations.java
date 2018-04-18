package com.ntg.seqaya.seqayamvpclean.domain.usecase.savedlocation;

import com.ntg.seqaya.seqayamvpclean.base.UseCase;
import com.ntg.seqaya.seqayamvpclean.domain.entity.Location;
import com.ntg.seqaya.seqayamvpclean.domain.repository.ILocationsRepository;

import java.util.List;

public class GetSavedLocations implements
        UseCase<GetSavedLocations.RequestValues, GetSavedLocations.ResponseValues> {

    private ILocationsRepository locationsRepository;

    public GetSavedLocations(ILocationsRepository locationsRepository) {
        this.locationsRepository = locationsRepository;
    }

    @Override
    public void execute(RequestValues requestValue,
                        UseCaseSuccessCallback<ResponseValues> successCallback,
                        UseCaseErrorCallback errorCallback) {
        locationsRepository.getSavedLocations(locations -> {
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
