package com.ntg.seqaya.seqayamvpclean.domain.usecase.location;

import com.ntg.seqaya.seqayamvpclean.base.UseCase;
import com.ntg.seqaya.seqayamvpclean.domain.entity.Location;
import com.ntg.seqaya.seqayamvpclean.domain.repository.ILocationsRepository;

public class AddLocationUseCase extends UseCase<AddLocationUseCase.RequestValues, AddLocationUseCase.ResponseValue> {
    final ILocationsRepository locationsRepository;

    public AddLocationUseCase(ILocationsRepository locationsRepository) {
        this.locationsRepository = locationsRepository;
    }


    @Override
    protected void executeUseCase(RequestValues requestValues) {
        locationsRepository.addNewLocation(requestValues.location, new ILocationsRepository.AddLocationCallBack() {
            @Override
            public void onSuccess(Location location) {

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