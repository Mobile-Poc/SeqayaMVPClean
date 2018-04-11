package com.mesawer.chaty.seqayamvpclean.domain.usecase.location;

import com.mesawer.chaty.seqayamvpclean.base.UseCase;
import com.mesawer.chaty.seqayamvpclean.domain.entity.Location;
import com.mesawer.chaty.seqayamvpclean.domain.repository.ILocationsRepository;

public class AddLocation implements UseCase<AddLocation.RequestValues, AddLocation.ResponseValues> {

    private ILocationsRepository locationsRepository;

    public AddLocation(ILocationsRepository locationsRepository) {
        this.locationsRepository = locationsRepository;
    }

    @Override
    public void execute(RequestValues requestValue,
                        UseCaseSuccessCallback<ResponseValues> successCallback,
                        UseCaseErrorCallback errorCallback) {
        locationsRepository.addNewLocation(requestValue.location,
                result -> successCallback.onSuccess(null),
                errorCallback::onError);
    }

    public static final class RequestValues implements UseCase.RequestValues {
        private Location location;

        public RequestValues(Location location) {
            this.location = location;
        }

        public Location getLocation() {
            return location;
        }
    }

    public static final class ResponseValues implements UseCase.ResponseValues {

    }
}
