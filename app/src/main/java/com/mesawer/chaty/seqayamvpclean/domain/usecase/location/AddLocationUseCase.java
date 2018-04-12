package com.mesawer.chaty.seqayamvpclean.domain.usecase.location;

import com.mesawer.chaty.seqayamvpclean.base.UseCase;
import com.mesawer.chaty.seqayamvpclean.domain.entity.Location;
import com.mesawer.chaty.seqayamvpclean.domain.repository.ILocationsRepository;

public class AddLocationUseCase extends UseCase<AddLocationUseCase.RequestValues, AddLocationUseCase.ResponseValue> {
    final ILocationsRepository locationsRepository;

    AddLocationUseCase(ILocationsRepository locationsRepository) {
        this.locationsRepository = locationsRepository;
    }


    @Override
    protected void executeUseCase(RequestValues requestValues) {
        locationsRepository.addNewLocation(requestValues.location, new ILocationsRepository.AddLocationCallBack() {
            @Override
            public void onSuccess() {

            }

            @Override
            public void onFail() {

            }
        });

    }

    public static final class RequestValues implements UseCase.RequestValues {
        Location location;

        public RequestValues(Location location) {
            this.location = location;
        }

    }

    public static final class ResponseValue implements UseCase.ResponseValue {
        String result;

        public ResponseValue(String result) {
            this.result = result;
        }

        public String getResponseModel() {
            return result;
        }
    }
}
